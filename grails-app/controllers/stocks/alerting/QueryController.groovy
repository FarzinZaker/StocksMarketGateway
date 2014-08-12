package stocks.alerting

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsClass
import stocks.User

class QueryController {

    def springSecurityService
    def grailsApplication

    def selectData() {
    }

    def build() {
        if (params.id) {
            def queryInstance = Query.get(params.id)
            def domainClass = grailsApplication.getDomainClass(queryInstance.domainClazz)
            domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
            }.each {
                queryInstance.smsTemplate = queryInstance.smsTemplate.replace("[${it.name}]", "[${message(code: "${queryInstance.domainClazz}.${it.name}.label")}]")
            }
            def fields = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.collect { it.name }
            def tokens = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
            }.collect { it.name }
            [
                    queryInstance    : queryInstance,
                    domainClass      : queryInstance.domainClazz,
                    parameters       : Parameter.findAllByQuery(queryInstance),
                    fields           : fields,
                    rules            : serializeRule(domainClass, queryInstance.rule),
                    scheduleTemplates: ScheduleTemplate.findAllByDeleted(false),
                    tokens           : tokens
            ]
        } else {
            def domainClass = grailsApplication.getDomainClass(params.domainClass)
            def fields = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.collect { it.name }
            [
                    domainClass      : params.domainClass,
                    parameters       : [],
                    fields           : fields,
                    rules            : [:],
                    scheduleTemplates: ScheduleTemplate.findAllByDeleted(false),
                    tokens           : tokens
            ]
        }
    }

    def save() {
        def query
        if (params.id) {
            query = Query.get(params.id)
            query.properties = params

            Parameter.findAllByQuery(query).each {
                it.delete()
            }

            def currentRule = query.rule
            query.rule = null
            query.scheduleTemplate = ScheduleTemplate.get(params.scheduleTemplate)
            query.save()
            deleteRuleTree(currentRule)
            query = Query.get(query.id)

        } else {
            query = new Query(params)
            query.owner = springSecurityService.currentUser as User ?: User.findByUsername('admin')
            query.scheduleTemplate = ScheduleTemplate.get(params.scheduleTemplate)
        }

        def domainClass = grailsApplication.getDomainClass(params.domainClazz)
        domainClass.persistentProperties.findAll {
            it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
        }.each {
            query.smsTemplate = query.smsTemplate.replace("[${message(code: "${query.domainClazz}.${it.name}.label")}]", "[${it.name}]")
        }

        query.save()
        query.rule = parseRule(domainClass, JSON.parse(params.query), null)

        params.parameterNames.eachWithIndex { parameterName, index ->
            def parameter = new Parameter(query: query)
            parameter.name = parameterName
            parameter.type = params.parameterTypes[index]
            if (params.parameterValues[index] && params.parameterValues[index] != '')
                parameter.defaultValue = params.parameterValues[index]
            parameter.save()
        }

        redirect(action: 'list')
    }

    private void deleteRuleTree(Rule rule) {
        Rule.findAllByParent(rule).each {
            deleteRuleTree(it)
        }
        rule?.delete()
    }

    private Rule parseRule(GrailsClass domainClass, json, Rule parent) {
        def rule = new Rule()
        if (parent)
            rule.parent = parent
        if (json.condition) {
            rule.aggregationType = json.condition
            rule.save()
            json.rules.each { item ->
                parseRule(domainClass, item, rule)
            }
        } else {
            rule.field = json.field
            rule.inputType = json.input
            rule.operator = json.operator
            rule.type = json.type
            rule.value = json.value

            def fieldsMap = [:]
            domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.each { fieldsMap.put(message(code: "${domainClass.fullName}.${it.name}.label"), it.name) }

            rule.value = fieldsMap[rule.value] ?: rule.value

            rule.save()
        }
        if (rule.aggregationType || rule.field)
            rule
        else
            null
    }

    private Map serializeRule(GrailsClass domainClass, Rule rule) {
        def object = [:]
        if (!rule)
            return object

        if (rule.aggregationType) {
            object.rules = []
            Rule.findAllByParent(rule).each { childRule ->
                object.rules << serializeRule(domainClass, childRule)
            }
        } else if (rule.field) {
            object.id = rule.field
            object.operator = rule.operator
            object.value = rule.value

            def fieldsMap = [:]
            domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.each { fieldsMap.put(it.name, message(code: "${domainClass.fullName}.${it.name}.label")) }

            object.value = fieldsMap[object.value] ?: object.value
        }
        object
    }

    def list() {

    }

    def jsonList() {
        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        value.data = (params.search ?
                Query.findAllByIdInListAndDeleted(Query.search(params.search?.toString()).results.collect {
                    it.id
                }, false, parameters) :
                Query.findAllByDeleted(false, parameters)).collect {
            def parameterList = Parameter.findAllByQuery(it)
            [
                    id              : it.id,
                    title           : it.title,
                    description     : it.description?.replaceAll("<(.|\n)*?>", ''),
                    domainClazz     : message(code: "${it.domainClazz}.label"),
                    scheduleTemplate: it.scheduleTemplate?.title,
                    owner           : it.owner.toString(),
                    dateCreated     : format.jalaliDate(date: it.dateCreated, hm: 'true'),
                    lastUpdated     : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                    parameterTags   : parameterList?.size() > 0 ? parameterList.sort { it.name }.collect {
                        "${it.name}"
                    }.join(' , ') : '-'
            ]
        }
        value.total = Query.countByDeleted(false).toString()
        render value as JSON
    }

    def delete() {

        def query = Query.get(params.id)
        query.deleted = true
        render(query.save() ? '1' : '0')
    }

    def select() {

    }

    def register() {
        def queryInstance = params.id ? QueryInstance.get(params.id) : new QueryInstance(query: Query.get(params.query))
        def scheduleTypes = []
        if (queryInstance.query.scheduleTemplate.eventBasedNotificationEnabled)
            scheduleTypes << 'eventBased'
        if (queryInstance.query.scheduleTemplate.periodicNotificationEnabled)
            scheduleTypes << 'periodic'
        [queryInstance: queryInstance, scheduleTypes: scheduleTypes]
    }

    def saveRegistration() {

        def queryInstance
        if (params.id) {
            queryInstance = QueryInstance.get(params.id)
        } else {
            queryInstance = new QueryInstance()
            queryInstance.owner = springSecurityService.currentUser as User ?: User.findByUsername('admin')
        }
        queryInstance.query = Query.get(params.queryId)
        if (!queryInstance.schedule) {
            queryInstance.schedule = new Schedule()
        }
        queryInstance.schedule?.type = params.scheduleType
        queryInstance.schedule?.intervalStep = params.intervalStep as Integer
        queryInstance.schedule?.save()

        queryInstance.lastExecutionDate = new Date()
        queryInstance.save()

        ScheduleDay.findAllBySchedule(queryInstance.schedule).each { it.delete() }
        if (queryInstance.schedule?.type == 'periodic') {
            queryInstance.query.scheduleTemplate.dayTemplates.collect { it.day }.each { day ->
                def scheduleDay = new ScheduleDay(day: day)
                scheduleDay.startTimeInMinute = params."${day}_allowedTimeRangeStart".toInteger()
                scheduleDay.endTimeInMinute = params."${day}_allowedTimeRangeEnd".toInteger()
                scheduleDay.schedule = queryInstance.schedule
                scheduleDay.save()
            }
        }


        ParameterValue.findAllByQueryInstance(queryInstance).each { it.delete() }
        Parameter.findAllByQuery(queryInstance.query).each { parameter ->
            def parameterValue = new ParameterValue(queryInstance: queryInstance)
            parameterValue.parameter = parameter
            parameterValue.value = params."parameter_${parameter.id}"
            parameterValue.save()
        }

        redirect(action: 'instanceList')
    }

    def instanceList() {
    }

    def instanceJsonList() {

        def user = springSecurityService.currentUser as User ?: User.findByUsername('admin')

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list
        if (params.search) {
            def searchResult = QueryInstance.search(params.search?.toString()).results.collect { it.id }
            list = QueryInstance.findAllByOwnerAndIdInListAndDeleted(user, searchResult, false, parameters);
            value.total = QueryInstance.findAllByOwnerAndIdInListAndDeleted(user, searchResult, false).size().toString()
        } else {
            list = QueryInstance.findAllByOwnerAndDeleted(user, false, parameters)
            value.total = QueryInstance.countByOwnerAndDeleted(user, false).toString()
        }
        value.data = list.collect {
            def parameterList = ParameterValue.findAllByQueryInstance(it)
            [
                    id               : it.id,
                    title            : it.title,
                    description      : it.description?.replaceAll("<(.|\n)*?>", ''),
                    domainClazz      : message(code: "${it.domainClazz}.label"),
                    scheduleTemplate : it.query?.scheduleTemplate?.title,
                    owner            : it.owner.toString(),
                    dateCreated      : format.jalaliDate(date: it.dateCreated, hm: 'true'),
                    lastUpdated      : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                    parameterTags    : parameterList?.size() > 0 ? parameterList.sort { it.parameter.name }.collect {
                        "${it.parameter.name}: ${it.value}"
                    }.join(' , ') : '-',
                    toggleAction     : it.enabled ? 'disable' : 'enable',
                    toggleActionTitle: it.enabled ? message(code: 'queryInstance.disable.button') : message(code: 'queryInstance.enable.button')
            ]
        }

        render value as JSON
    }

    def instanceDelete() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.deleted = true
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }

    def instanceEnable() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.enabled = true
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }

    def instanceDisable() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.enabled = false
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }
}
