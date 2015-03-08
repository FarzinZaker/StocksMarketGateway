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
        render template: filterService.getValueTemplate(params.filter?.toString(), params.operator?.toString()), model: filterService.getValueModel(params.filter?.toString(), params.operator?.toString())
    }

    def queryItem() {
        def value = params.findAll { it.key.toString().contains('value') }.collect {
            it.value == 'on' ? it.key.toString().replace('value_', '') : it.value
        }
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
        if(!params.submitAndContinue) {
            strategy.buyRule = parseRules(params.buyRule as String)
            strategy.sellRule = parseRules(params.sellRule as String)
            strategy.lossLimit = params.lossLimit as Integer
            strategy.profitLimit = params.profitLimit as Integer
            strategy.timeLimit = params.timeLimit as Integer
        }
        else {
            strategy.buyRule = new Rule(aggregationType: 'AND').save(flush: true)
            strategy.sellRule = new Rule(aggregationType: 'AND').save(flush: true)
        }
        strategy.save(flush: true)

        if (params.submitAndExit)
            redirect(action: 'list')
        else
            redirect(action: 'build', id: strategy.id)
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

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]
        def owner = springSecurityService.currentUser as User

        def list
        if (params.search && params.search != '') {
            def searchResult = TradeStrategy.search(params.search?.toString()).results.collect { it.id }
            list = searchResult?.size() > 0 ? TradeStrategy.findAllByIdInListAndOwnerAndDeleted(searchResult, owner, false, parameters) : []
            value.total = searchResult?.size() > 0 ? TradeStrategy.countByIdInListAndOwnerAndDeleted(searchResult, owner, false) : 0
        } else {
            list = TradeStrategy.findAllByOwnerAndDeleted(owner, false, parameters)
            value.total = TradeStrategy.countByOwnerAndDeleted(owner, false)
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
