package stocks.alerting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.gorm.DetachedCriteria
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.grails.datastore.mapping.query.Restrictions
import org.grails.datastore.mapping.query.Query.Criterion
import org.grails.datastore.mapping.query.Query.Disjunction
import org.grails.datastore.mapping.query.Query.Conjunction
import org.grails.datastore.mapping.query.Query.Junction
import org.grails.datastore.mapping.query.Query.Negation
import stocks.FarsiNormalizationFilter

class QueryService {
    static transactional = false
    def grailsApplication
    def smsService
    def scheduleService

    def applyEventBasedQueries(data) {

        QueryInstance.createCriteria().list {
            eq('enabled', true)
            or {
                eq('deleted', false)
                isNull('deleted')
            }
            schedule {
                eq('type', 'eventBased')
            }
            query {
                eq('domainClazz', data.class.name)
            }
        }.each { queryInstance ->
            if (check(queryInstance, data.id)) {
                smsService.sendEventBasedMessage(queryInstance, data)
            }
        }
    }

    def applyScheduledQuery(QueryInstance queryInstance) {
        def list = list(queryInstance)
        if (list?.size() > 0)
            smsService.sendScheduledMessage(queryInstance, list)
        queryInstance.lastExecutionTime = new Date()
        queryInstance.save()
        scheduleService.calculateQueryInstanceNextExecutionTime(queryInstance)
    }

    def list(QueryInstance queryInstance) {
        def query = Query.get(queryInstance.queryId)
        if (query.maxRecordsCount > 0)
            getDetachedCriteria(queryInstance).list([max: query.maxRecordsCount], getAdditionalCriteria(queryInstance))
        else
            getDetachedCriteria(queryInstance).list(getAdditionalCriteria(queryInstance))
    }

    def count(QueryInstance queryInstance) {
        getDetachedCriteria(queryInstance).count()
    }

    def get(QueryInstance queryInstance) {
        getDetachedCriteria(queryInstance).get(getAdditionalCriteria(queryInstance))
    }

    def check(QueryInstance queryInstance, Long id) {
        def criteria = getDetachedCriteria(queryInstance, id)
        QueryInstance.withTransaction {
            criteria.count()
        }
    }

    Closure getAdditionalCriteria(QueryInstance queryInstance) {
        return {
            SortingRule.findAllByQuery(queryInstance.query).each { sortingRule ->
                order(sortingRule.fieldName, sortingRule.sortDirection)
            }
        }
    }

    DetachedCriteria getDetachedCriteria(QueryInstance queryInstance) {
        DefaultGrailsDomainClass domainClass = grailsApplication.getDomainClass(queryInstance.query.domainClazz) as DefaultGrailsDomainClass
        def parameters = getParameters(queryInstance)
        def criteria = createCriteria(queryInstance.query.rule, domainClass, parameters)
        def dc = new DetachedCriteria(domainClass.clazz)
        dc.add(criteria)
        dc
    }

    DetachedCriteria getDetachedCriteria(QueryInstance queryInstance, Long itemId) {
        DefaultGrailsDomainClass domainClass = grailsApplication.getDomainClass(queryInstance.query.domainClazz) as DefaultGrailsDomainClass
        def parameters = getParameters(queryInstance)
        def criteria = new Conjunction()
        criteria.add(Restrictions.eq('id', itemId))
        def query = Query.get(queryInstance.queryId)
        def rule = Rule.get(query.ruleId)
        criteria.add(createCriteria(rule, domainClass, parameters))
        def dc = new DetachedCriteria(domainClass.clazz)
        dc.add(criteria)
        dc
    }

    private static Criterion createCriteria(Rule rule, DefaultGrailsDomainClass domainClass, Map parameters) {

        if (!rule) {
            def someProperty = domainClass.persistantProperties.find();
            def criterion = new Disjunction()
            criterion.add(Restrictions.eqProperty(someProperty.name, someProperty.name))
            criterion.add(Restrictions.isNull(someProperty.name))
            return criterion
        }

        Criterion criterion
        if (rule.aggregationType) {
            if (rule.aggregationType == 'OR')
                criterion = new Disjunction()
            else
                criterion = new Conjunction()

            Rule.findAllByParent(rule).each { Rule item ->
                ((Junction) criterion).add(createCriteria(item, domainClass, parameters))
            }
        } else {
            if (getOperandsCount(rule.operator) == 2) {
                criterion = Restrictions."${getOperator(rule, domainClass)}"(rule.field, getValue(rule, domainClass, parameters))
            } else {
                criterion = Restrictions."${getOperator(rule, domainClass)}"(rule.field)
            }
            if (negationRequired(rule.operator))
                criterion = new Negation().add(criterion)
        }
        criterion
    }

    private static def getValue(Rule rule, DefaultGrailsDomainClass domainClass, Map parameters) {
        if (domainClass.persistantProperties.any { it.name == rule.value })
            return rule.value.toString()

        def value = rule.value
        if (parameters.containsKey(rule.value))
            value = parameters."${value}"

        def property = domainClass.persistantProperties.find { it.name == rule.field }
        switch (property.type) {
            case Integer:
                value = value.toInteger()
                break;
            case Date:
                value = parseDate(value)
                break;
            default:
                value = FarsiNormalizationFilter.apply(value.toString())
        }

        switch (rule.operator) {
            case 'contains':
                return "%${value}%"
            case 'not_contains':
                return "%${value}%"
            case 'begins_with':
                return "${value}%"
            case 'not_begins_with':
                return "${value}%"
            case 'ends_with':
                return "%${value}"
            case 'not_ends_with':
                return "%${value}"
            default:
                return value
        }
    }

    private static Integer getOperandsCount(String operator) {
        operator in [
                'is_empty',
                'is_not_empty',
                'is_null',
                'is_not_null'
        ] ? 1 : 2
    }

    private static String getOperator(Rule rule, DefaultGrailsDomainClass domainClass) {
        def simpleComparison = !domainClass.persistantProperties.any { it.name == rule.value }
        switch (rule.operator) {
            case 'equal':
                return simpleComparison ? 'eq' : 'eqProperty';
            case 'not_equal':
                return simpleComparison ? 'ne' : 'neProperty';
            case 'less':
                return simpleComparison ? 'lt' : 'ltProperty';
            case 'less_or_equal':
                return simpleComparison ? 'lte' : 'leProperty';
            case 'greater':
                return simpleComparison ? 'gt' : 'gtProperty';
            case 'greater_or_equal':
                return simpleComparison ? 'gte' : 'geProperty';
            case 'contains':
                return 'like';
            case 'not_contains':
                return 'like';
            case 'begins_with':
                return 'like';
            case 'not_begins_with':
                return 'like';
            case 'ends_with':
                return 'like';
            case 'not_ends_with':
                return 'like';
            case 'is_empty':
                return 'isEmpty';
            case 'is_not_empty':
                return 'isNotEmpty';
            case 'is_null':
                return 'isNull';
            case 'is_not_null':
                return 'isNotNull';
            default:
                return simpleComparison ? 'eq' : 'eqProperty';
        }
    }

    private static Boolean negationRequired(String operator) {
        operator in ['not_contains', 'not_begins_with', 'not_ends_with']
    }

    private static Map getParameters(QueryInstance queryInstance) {
        def parameters = [:]
        ParameterValue.findAllByQueryInstance(queryInstance).each { parameterValue ->
            def parameter = Parameter.get(parameterValue.parameterId)
            parameters.put(parameter.name, parameterValue.value.toString())
        }
        parameters
    }

    private static Date parseDate(String date) {
        def parts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(parts[0], parts[1], parts[2])
        jc.toJavaUtilGregorianCalendar().time
    }

}
