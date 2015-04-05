package stocks.analysis

//import com.sun.servicetag.UnauthorizedAccessException
import grails.converters.JSON
import grails.util.Environment
import stocks.User
import stocks.alerting.Rule
import stocks.filters.FilterServiceBase
import stocks.tse.Symbol
import stocks.util.ClassResolver
import org.codehaus.groovy.grails.web.json.JSONArray

class ScreenerController {

    def filterService
    def springSecurityService

    def index() {
        redirect(action: 'list')
    }

    def build() {
        def property = 'symbol'
        def filterMap = [:]
        filterService.getFilterGroupList(property).each { group ->
            filterMap.put(group, filterService.getGroupFilters(group, 'screener'))
        }
        def screener = Screener.get(params.id as Long) ?: new Screener()
        def rules = []
        if (screener.rule)
            rules = Rule.findAllByParent(screener.rule).collect { rule ->
                def value = JSON.parse(rule.value)
                [
                        filter   : rule.field,
                        operator : rule.operator,
                        value    : value,
                        parameter: rule.inputType,
                        text     : (ClassResolver.loadServiceByName(rule.field) as FilterServiceBase)?.formatQueryValue(value, rule.operator)
                ]
            }
        [filterMap: filterMap.findAll { it.value.size() }, screener: screener, rules: rules]
    }

    def operators() {
        def filter = ClassResolver.loadServiceByName(params.filter?.toString()) as FilterServiceBase
        render template: 'operators', model: [filter: params.filter, operators: filter.operators]
    }

    def values() {
        render template: filterService.getValueTemplate(params.filter?.toString(), params.operator?.toString()), model: filterService.getValueModel(params.filter?.toString(), params.operator?.toString(), 'screener')
    }

    def queryItem() {
        def value = params.findAll { it.key.toString().contains('value') }.collect {
            it.value == 'on' ? it.key.toString().replace('value_', '') : it.value
        }
        render template: 'queryItem', model: [text: filterService.getQueryText(params.filter?.toString(), params.operator, value), filter: params.filter, parameter: params.parameter, operator: params.operator, value: value as JSON]
    }

    def save() {
        def screener
        def rule = null
        if (params.id) {
            screener = Screener.get(params.id as Long)
            if (screener.rule) {
                rule = Rule.get(screener.ruleId)
                Rule.findAllByParent(rule).each { it.delete() }
            }
        } else {
            screener = new Screener()
            screener.owner = springSecurityService.currentUser as User
        }
        screener.title = params.name
        screener.rule = parseRules(params.rules as String)
        screener.save(flush: true)
        rule?.delete()

        if (params.submitAndExit)
            redirect(action: 'list')
        else
            redirect(action: 'build', id: screener.id)
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
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "title", order: params["sort[0][dir]"] ?: "asc"]
        def owner = springSecurityService.currentUser as User

        def list
        if (params.search && params.search != '') {
            def searchResult = Screener.search(params.search?.toString()).results.collect { it.id }
            list = searchResult?.size() > 0 ? Screener.findAllByIdInListAndOwnerAndDeleted(searchResult, owner, false, parameters) : []
            value.total = searchResult?.size() > 0 ? Screener.countByIdInListAndOwnerAndDeleted(searchResult, owner, false) : 0
        } else {
            list = Screener.findAllByOwnerAndDeleted(owner, false, parameters)
            value.total = Screener.countByOwnerAndDeleted(owner, false)
        }

        value.data = list.collect {
            [
                    id         : it.id,
                    title      : it.title,
                    lastUpdated: format.jalaliDate(date: it.lastUpdated)
            ]
        }

        render value as JSON
    }

    def delete() {

        def screener = Screener.get(params.id as Long)
        screener.deleted = true
        render(screener.save() ? '1' : '0')
    }

    def view() {
        def screener = Screener.get(params.id as Long)
        def rules = Rule.findAllByParent(screener?.rule)
        def indicatorColumns = [:]
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                indicatorColumns.put("${indicatorName.replace('.', '_')}_${rule.inputType}".replace('stocks_indicators_symbol_', ''), "(${message(code: rule.field)} (${rule.inputType}")

            def value =  JSON.parse(rule.value)?.first()
            if(value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    indicatorColumns.put("${indicatorName.replace('.', '_')}_${value?.last()}".replace('stocks_indicators_symbol_', ''), "(${message(code: value?.first())} (${value?.last()}")
            }
        }
        [
                screener        : screener,
                indicatorColumns: indicatorColumns,
                rules           : rules.collect { rule ->
                    def value = JSON.parse(rule.value)
                    [
                            filter   : rule.field,
                            parameter: rule.inputType,
                            operator : rule.operator,
                            value    : value,
                            text     : (ClassResolver.loadServiceByName(rule.field) as FilterServiceBase)?.formatQueryValue(value, rule.operator)
                    ]

                }
        ]
    }

    def jsonView() {

        def value = [:]
        def owner = springSecurityService.currentUser as User

        def screener = Screener.get(params.id as Long)
        if (screener.ownerId != owner?.id && Environment.current != Environment.DEVELOPMENT)
            render[] as JSON

        def list = filterService.applyFilters(Symbol, Rule.findAllByParent(screener?.rule))
        value.total = list.size()

        value.data = list
        render(value as JSON)
    }

}
