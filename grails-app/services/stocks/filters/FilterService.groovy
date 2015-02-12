package stocks.filters

import grails.converters.JSON
import grails.gorm.DetachedCriteria
import grails.plugins.springsecurity.SpringSecurityService
import org.grails.datastore.mapping.query.Query
import stocks.User
import stocks.alerting.Rule
import stocks.tse.Symbol
import stocks.util.ClassResolver
import java.beans.Introspector;


class FilterService {

    def grailsApplication
    def springSecurityService

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
        }.collect {
            def service = ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase
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
        }
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

    String getQueryText(String filter, value) {
        (ClassResolver.loadServiceByName(filter) as FilterServiceBase).formatQueryValue(value)
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

        def dbFilters = filters.findAll { it.service instanceof QueryFilterServiceBase }
        def criteriaList = dbFilters.collect {
            (it.service as QueryFilterServiceBase).getCriteria(it.parameter, it.operator, it.value)
        }
        def criteria = new Query.Conjunction()
        criteriaList.each {
            criteria.add(it)
        }
        def dc = new DetachedCriteria(targetClass)
        dc.add(criteria)
        def items = dc.list(max: 500)

        def technicalFilters = filters.findAll { it.service instanceof TechnicalFilterServiceBase }
        technicalFilters.each {
            items = (it.service as TechnicalFilterServiceBase<Symbol>).apply(it.parameter, it.operator, it.value, items)
        }

        items
    }
}
