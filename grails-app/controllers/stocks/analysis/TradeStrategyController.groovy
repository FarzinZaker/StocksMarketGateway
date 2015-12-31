package stocks.analysis

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.util.ClassResolver
import stocks.alerting.Rule

class TradeStrategyController {

    def filterService
    def springSecurityService

    def index() {
        redirect(action: 'list')
    }

    def build() {
        def tradeStrategy = TradeStrategy.get(params.id as Long) ?: new TradeStrategy()
        [tradeStrategy: tradeStrategy]
    }

    def ruleBuilder() {

        def property = 'symbol'
        def filterMap = [:]
        filterService.getFilterGroupList(property).each { group ->
            filterMap.put(group, filterService.getGroupFilters(group, 'backTest'))
        }
        def tradeStrategy = TradeStrategy.get(params.id as Long) ?: new TradeStrategy()
        def rules = []
        def baseRule = params.type == 'buy' ? tradeStrategy.buyRule : tradeStrategy.sellRule
        if (baseRule)
            rules = Rule.findAllByParent(baseRule).collect { rule ->
                def value = JSON.parse(rule.value)
                [
                        filter   : rule.field,
                        operator : rule.operator,
                        value    : value,
                        parameter: rule.inputType,
                        text     : (ClassResolver.loadServiceByName(rule.field) as FilterServiceBase)?.formatQueryValue(value, rule.operator)
                ]
            }
        [filterMap: filterMap.findAll { it.value.size() }, tradeStrategy: tradeStrategy, rules: rules]
    }

    def operators() {
        def filter = ClassResolver.loadServiceByName(params.filter?.toString()) as FilterServiceBase
        render template: 'operators', model: [filter: params.filter, operators: filter.operators]
    }

    def values() {
        render template: filterService.getValueTemplate(params.filter?.toString(), params.operator?.toString()), model: filterService.getValueModel(params.filter?.toString(), params.operator?.toString(), 'backTest')
    }

    def queryItem() {
        def value = params.findAll { it.key.toString().contains('value') }.collect {
            it.value == 'on' ? it.key.toString().replace('value_', '') : it.value
        }?.sort { it[0] }
        render template: 'queryItem', model: [text: filterService.getQueryText(params.filter?.toString(), params.operator, value), filter: params.filter, parameter: params.parameter, operator: params.operator, value: value as JSON]
    }

    def save() {
        def strategy
        def buyRule
        def sellRule
        if (params.id) {
            strategy = TradeStrategy.get(params.id as Long)
            if (strategy.buyRule) {
                buyRule = Rule.get(strategy.buyRuleId)
                Rule.findAllByParent(buyRule).each { it.delete() }
            }
            if (strategy.sellRule) {
                sellRule = Rule.get(strategy.buyRuleId)
                Rule.findAllByParent(sellRule).each { it.delete() }
            }
        } else {
            strategy = new TradeStrategy()
            strategy.owner = springSecurityService.currentUser as User
        }
        strategy.name = params.name
        if (!params.submitAndContinue) {
            strategy.buyRule = parseRules(params.buyRule as String)
            strategy.sellRule = parseRules(params.sellRule as String)
            strategy.lossLimit = params.lossLimit as Float
            strategy.profitLimit = params.profitLimit as Float
            strategy.timeLimit = params.timeLimit as Integer
        } else {
            strategy.buyRule = new Rule(aggregationType: 'AND').save(flush: true)
            strategy.sellRule = new Rule(aggregationType: 'AND').save(flush: true)
        }
        if(params.id) {
            flash.errors = validateStrategy(strategy)
            strategy.isValid = flash.errors.size() == 0
        }
        strategy.save(flush: true)

        if (flash.errors?.size() > 0)
            redirect(action: 'build', id: strategy.id)
        else if (params.submitAndExit)
            redirect(action: 'list')
        else
            redirect(action: 'build', id: strategy.id)
    }

    private List<String> validateStrategy(TradeStrategy strategy) {
        def errors = []
        if (Rule.countByParent(strategy.buyRule) == 0)
            errors << message(code: 'strategy.build.errors.noBuyRule')

        if (Rule.countByParent(strategy.sellRule) == 0
                && strategy.lossLimit == 0
                && strategy.profitLimit == 0
                && strategy.timeLimit == 0)
            errors << message(code: 'strategy.build.errors.noSellRule')

        errors
    }

    Rule parseRules(String ruleStr) {
        def mainRule = new Rule(aggregationType: 'AND').save(flush: true)
        (JSON.parse(ruleStr) as JSONArray).toList().each { JSONArray ruleInfo ->
            def items = ruleInfo.toList()
            def rule = new Rule()
            rule.field = items.find { it.name == 'filter' }.value
            rule.operator = items.find { it.name == 'operator' }.value
            rule.value = items.find { it.name == 'value' }.value
            rule.type = 'service'
            rule.inputType = items.find { it.name == 'parameter' }?.value
            rule.parent = mainRule
            rule.save(flush: true)
        }
        mainRule
    }


    def list() {
        if (!springSecurityService.loggedIn) {
            redirect(controller: 'help', action: 'tradeStrategy')
        }
    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]
        def owner = springSecurityService.currentUser as User

        def list
        if (params.search && params.search != '') {
            def searchResult = TradeStrategy.search(params.search?.toString()).results.collect { it.id }
            list = searchResult?.size() > 0 ? TradeStrategy.findAllByIdInListAndOwnerAndDeletedAndIsValid(searchResult, owner, false, true, parameters) : []
            value.total = searchResult?.size() > 0 ? TradeStrategy.countByIdInListAndOwnerAndDeletedAndIsValid(searchResult, owner, false, true) : 0
        } else {
            list = TradeStrategy.findAllByOwnerAndDeletedAndIsValid(owner, false, true, parameters)
            value.total = TradeStrategy.countByOwnerAndDeletedAndIsValid(owner, false, true)
        }

        value.data = list.collect {
            [
                    id         : it.id,
                    title      : it.name,
                    lastUpdated: format.jalaliDate(date: it.lastUpdated)
            ]
        }

        render value as JSON
    }

    def delete() {

        def tradeStrategy = TradeStrategy.get(params.id as Long)
        tradeStrategy.deleted = true
        render(tradeStrategy.save() ? '1' : '0')
    }
}
