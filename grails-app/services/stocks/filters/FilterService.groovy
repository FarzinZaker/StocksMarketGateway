package stocks.filters

import grails.converters.JSON
import grails.gorm.DetachedCriteria
import org.codehaus.groovy.grails.web.json.JSONArray
import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.alerting.Rule
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver
import stocks.util.CollectionHelper

import java.beans.Introspector;


class FilterService {

    def grailsApplication
    def springSecurityService
    def lowLevelDataService

    public List<String> getFilterGroupList(String property) {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.filters.${property}.")
        }.collect {
            it.packageName
        }.unique { a, b -> a.equals(b) ? 0 : 1 }
    }

    public ArrayList<String> getGroupFilters(String group, String type) {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("${group}")
        }.findAll {
            (ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase)?.enabled?."${type}"
        }.collect {
            def service = ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase
            if (service?.enabled?."${type}") {
                def filterParts = it.fullName.split('\\.')
                def filterName = Introspector.decapitalize(filterParts.last().replace('FilterService', ''))
                def path = filterParts[1..filterParts.size() - 2].join('/')
                def templateName = service.getFilterParamsTemplate()
                [
                        name             : it.fullName,
                        hasParameter     : templateName != null,
                        parameterTemplate: '/' + [path, filterName, templateName].join('/'),
                        parameterModel   : service.getFilterParamsModel()
                ]
            } else {
                null
            }
        }.findAll { it }
    }

    String getValueTemplate(String filter, String operator) {
        def filterParts = filter.split('\\.')
        def filterName = Introspector.decapitalize(filterParts.last().replace('FilterService', ''))
        def path = filterParts[1..filterParts.size() - 2].join('/')
        '/' + [path, filterName, (ClassResolver.loadServiceByName(filter) as FilterServiceBase).getValueTemplate(operator)].join('/')
    }

    def getValueModel(String filter, String operator, String place) {
        (ClassResolver.loadServiceByName(filter) as FilterServiceBase).getValueModel(springSecurityService.currentUser as User, operator, place)
    }

    String[] getQueryText(String filter, String operator, value) {
        (ClassResolver.loadServiceByName(filter) as FilterServiceBase).formatQueryValue(value, operator)
    }

    def applyFilters(Class targetClass, List<Rule> rules) {
        def filters = rules.collect { rule ->
            [
                    service  : ClassResolver.loadServiceByName(rule.field),
                    parameter: rule.inputType,
                    operator : rule.operator,
                    value    : JSON.parse(rule.value)
            ]
        }

        def includeFilters = filters.findAll { it.service instanceof IncludeFilterService }
        def includeList = SymbolDailyTrade.createCriteria().list{
            gte('date', new Date().clearTime())
            projections {
                symbol {
                    property('id')
                }
            }
        }
        def noResult = false
        includeList = noResult ? [] : CollectionHelper.getConjunction(
//                includeList +
                includeFilters.collect{
                    (it.service as IncludeFilterService).getIncludeList(it.parameter, it.operator, it.value)
                } as ArrayList<ArrayList>
        )

        if (noResult || !includeList?.size())
            return []

        def excludeFilters = filters.findAll { it.service instanceof ExcludeFilterService }
        def excludeList = [] as Set<Long>
        excludeFilters.each {
            excludeList.addAll((it.service as ExcludeFilterService).getExcludeList(it.parameter, it.operator, it.value) ?: [])
        }

        def queryFilters = filters.findAll { it.service instanceof QueryFilterService }
        def queryList = queryFilters.collect {
            (it.service as QueryFilterService).getCriteria(it.parameter, it.operator, it.value)
        } ?: []

        def criteria = new Query.Conjunction()
        if (includeList && includeList.size()) {
            if(includeList.size() > 1000)
                includeList = includeList.toList()[0..999]
            criteria.add(Restrictions.in('id', includeList))
        }
        if (excludeList && excludeList.size()) {
            if (excludeList.size() > 1000)
                excludeList = excludeList.toList()[0..999]
            criteria.add(new Query.Negation().add(Restrictions.in('id', excludeList)))
        }
        queryList.each {
            criteria.add(it)
        }
        def dc = new DetachedCriteria(targetClass)
        dc.add(criteria)
        def items = dc.list([max: 500], {
            projections {
                property('id')
            }
        })

        def indicatorColumns = []
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                indicatorColumns << "${indicatorName.replace('.', '_')}_${rule.inputType}"

            def value =  JSON.parse(rule.value)?.first()
            if(value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    indicatorColumns << "${indicatorName.replace('.', '_')}_${value?.last()}"
            }
        }
        def result = lowLevelDataService.executeFunction('SYM_SEL_SCREENER', [idList: items.join(','), cols: indicatorColumns.collect{"'" + it.replace('stocks_indicators_symbol_', '') + "'"}.join(',')])
        for(def i = 0; i < result.size(); i++) {
            def row = result[i] as LinkedHashMap
            def keys = row.keySet().toList()
            for (def j = 0; j < keys.size(); j++) {
                def currentKey = keys[j] as String
                if (currentKey.contains(','))
                    row."${currentKey.replace(',', '_')}" = row."${currentKey}"
            }
        }
        result
    }
}
