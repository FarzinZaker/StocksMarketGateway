package stocks.filters

import grails.converters.JSON
import grails.gorm.DetachedCriteria
import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.alerting.Rule
import stocks.util.ClassResolver
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

    public ArrayList<String> getGroupFilters(String group) {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("${group}")
        }.findAll {
            (ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase).enabled
        }.collect {
            def service = ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase
            if (service.enabled) {
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

    def getValueModel(String filter, String operator) {
        (ClassResolver.loadServiceByName(filter) as FilterServiceBase).getValueModel(springSecurityService.currentUser as User, operator)
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
        def includeList = [] as Set<Long>
        def noResult = false
        includeFilters.each {
            if (!noResult) {
                def newCollection = (it.service as IncludeFilterService).getIncludeList(it.parameter, it.operator, it.value)
                if (newCollection && newCollection.size()) {
                    if (includeList.size() == 0)
                        includeList.addAll(newCollection)
                    else
                        includeList = includeList.intersect(newCollection)
                } else
                    noResult = true
            }
        }

        if (noResult)
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
        if (includeList && includeList.size())
            criteria.add(Restrictions.in('id', includeList))
        if (excludeList && excludeList.size())
            criteria.add(new Query.Negation().add(Restrictions.in('id', excludeList)))
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
        lowLevelDataService.executeStoredProcedure('symbol_select_screener', [idList: items.join(',')])
    }
}
