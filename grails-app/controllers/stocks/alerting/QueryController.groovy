package stocks.alerting

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import org.codehaus.groovy.grails.commons.GrailsClass
import stocks.FarsiNormalizationFilter
import stocks.RoleHelper
import stocks.User
import grails.plugins.springsecurity.Secured

class QueryController {

    def springSecurityService
    def grailsApplication
    def scheduleService


    @Secured([RoleHelper.ROLE_ADMIN])
    def selectData() {
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def build() {
        if (params.id) {
            def queryInstance = Query.get(params.id)
            def domainClass = grailsApplication.getDomainClass(queryInstance.domainClazz)
            domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
            }.each {
                queryInstance.smsTemplate = queryInstance.smsTemplate.replace("[${it.name}]", "[${FarsiNormalizationFilter.apply(message(code: "${queryInstance.domainClazz}.${it.name}.label"))}]")
            }
            stocks.TemplateHelper.SYSTEM_TOKENS.each {
                queryInstance.smsHeaderTemplate = queryInstance.smsHeaderTemplate?.replace("[${it}]", "[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]")
                queryInstance.smsTemplate = queryInstance.smsTemplate?.replace("[${it}]", "[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]")
                queryInstance.smsFooterTemplate = queryInstance.smsFooterTemplate?.replace("[${it}]", "[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]")
            }
            def fields = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.collect { it.name }
            def tokens = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
            }.collect { it.name }
            def parameterTypes = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query &&
                        !(it.domainClass.constrainedProperties."${it.name}".metaConstraints.query instanceof Boolean) &&
                        it.domainClass.constrainedProperties."${it.name}".metaConstraints.query?.domain &&
                        it.domainClass.constrainedProperties."${it.name}".metaConstraints.query?.value
            }.collect {
                [text  : message(code: "${domainClass.fullName}.${it.name}.label"), value: it.name,
                 domain: it.domainClass.fullName,
                 field : "${it.name}"]
            } + [
                    [text: message(code: 'query.build.parameters.type.string'), value: 'string'],
                    [text: message(code: 'query.build.parameters.type.integer'), value: 'integer']
            ]

            queryInstance.discard()
            [
                    queryInstance    : queryInstance,
                    domainClass      : queryInstance.domainClazz,
                    parameters       : Parameter.findAllByQuery(queryInstance),
                    sortingRules     : SortingRule.findAllByQuery(queryInstance),
                    fields           : fields,
                    rules            : serializeRule(domainClass, queryInstance.rule),
                    scheduleTemplates: ScheduleTemplate.findAllByDeleted(false),
                    tokens           : tokens,
                    parameterTypeList: parameterTypes
            ]
        } else {
            def domainClass = grailsApplication.getDomainClass(params.domainClass)
            def fields = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
            }.collect { it.name }
            def tokens = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
            }.collect { it.name }
            def parameterTypes = domainClass.persistentProperties.findAll {
                it.domainClass.constrainedProperties."${it.name}".metaConstraints.query &&
                        !(it.domainClass.constrainedProperties."${it.name}".metaConstraints.query instanceof Boolean) &&
                        it.domainClass.constrainedProperties."${it.name}".metaConstraints.query?.domain &&
                        it.domainClass.constrainedProperties."${it.name}".metaConstraints.query?.value
            }.collect {
                [text  : message(code: "${domainClass.fullName}.${it.name}.label"), value: it.name,
                 domain: it.domainClass.fullName,
                 field : "${it.name}"]
            } + [
                    [text: message(code: 'query.build.parameters.type.string'), value: 'string'],
                    [text: message(code: 'query.build.parameters.type.integer'), value: 'integer']
            ]
            [
                    domainClass      : params.domainClass,
                    parameters       : [],
                    sortingRules     : [],
                    fields           : fields,
                    rules            : [:],
                    scheduleTemplates: ScheduleTemplate.findAllByDeleted(false),
                    tokens           : tokens,
                    parameterTypeList: parameterTypes
            ]
        }
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def save() {
        def query

        //prepare parameters
        if (params.parameterNames instanceof String) {
            params.parameterNames = [params.parameterNames]
            params.parameterTypes = [params.parameterTypes]
            params.parameterValues = [params.parameterValues]
        }

        def parameters = []
        params.parameterNames.eachWithIndex { parameterName, index ->
            parameters << [name: parameterName, type: params.parameterTypes[index], defaultValue: params.parameterValues[index], multiSelect: true]//params."parameterMultiSelects_${index + 1}" == 'on']
        }

        //prepare sortingRules
        if (params.sortingRuleFieldNames instanceof String) {
            params.sortingRuleFieldNames = [params.sortingRuleFieldNames]
            params.sortingRuleSortDirections = [params.sortingRuleSortDirections]
            params.sortingRuleSortOrders = [params.sortingRuleSortOrders]
        }

        def sortingRules = []
        params.sortingRuleFieldNames.eachWithIndex { fieldName, index ->
            sortingRules << [fieldName: fieldName, sortDirection: params.sortingRuleSortDirections[index], sortOrder: params.sortingRuleSortOrders[index]]
        }

        if (params.id) {
            query = Query.get(params.id)
            query.properties = params

            (parameters && parameters.size() ? Parameter.findAllByQueryAndNameNotInList(query, parameters?.collect {
                it?.name
            }) : Parameter.findAllByQuery(query)).each { parameter ->
                ParameterValue.findAllByParameter(parameter).each { it.delete() }
                parameter.delete()
            }

            if (parameters && parameters.size())
                Parameter.findAllByQueryAndNameInList(query, parameters.collect { it.name })?.each { parameter ->
                    def newParameter = parameters.find { it.name == parameter.name }
                    parameter.name = newParameter.name
                    parameter.type = newParameter.type
                    parameter.defaultValue = newParameter.defaultValue
                    parameter.multiSelect = newParameter.multiSelect
                    parameter.save()
                }
            if (sortingRules) {
                SortingRule.findAllByQueryAndFieldNameNotInList(query, sortingRules?.collect {
                    it?.fieldName
                } ?: []).each { sortingRule ->
                    sortingRule.delete()
                }

                SortingRule.findAllByQueryAndFieldNameInList(query, sortingRules?.collect {
                    it?.fieldName
                } ?: []).each { sortingRule ->
                    def newSortingRule = sortingRules.find { it.fieldName == sortingRule.fieldName }
                    sortingRule.fieldName = newSortingRule.fieldName
                    sortingRule.sortDirection = newSortingRule.sortDirection
                    sortingRule.sortOrder = newSortingRule.sortOrder
                    sortingRule.save()
                }
            } else {
                SortingRule.findAllByQuery(query).each { sortingRule ->
                    sortingRule.delete()
                }
            }

            def currentRule = query.rule
            query.rule = null
            query.save(flush: true)
            deleteRuleTree(currentRule)
            query = Query.get(query.id)

        } else {
            query = new Query(params)
            query.owner = springSecurityService.currentUser as User ?: User.findByUsername('admin')
        }

        def domainClass = grailsApplication.getDomainClass(params.domainClazz)
        query.smsTemplate = FarsiNormalizationFilter.apply(query.smsTemplate)
        domainClass.persistentProperties.findAll {
            it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
        }.each {
            query.smsTemplate = query.smsTemplate.replace("[${FarsiNormalizationFilter.apply(message(code: "${query.domainClazz}.${it.name}.label"))}]", "[${it.name}]")
        }
        stocks.TemplateHelper.SYSTEM_TOKENS.each {
            query.smsHeaderTemplate = query.smsHeaderTemplate?.replace("[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]", "[${it}]")
            query.smsTemplate = query.smsTemplate?.replace("[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]", "[${it}]")
            query.smsFooterTemplate = query.smsFooterTemplate?.replace("[${FarsiNormalizationFilter.apply(message(code: "systemTokens.${it}.label"))}]", "[${it}]")
        }


        query.rule = parseRule(domainClass, JSON.parse(params.query), null)

        query.scheduleTemplate = ScheduleTemplate.get(params.scheduleTemplate) as ScheduleTemplate
        query.category = QueryCategory.get(params.category)
        if(!query.description || query.description == '')
            query.description = ' '
        if (!query.save()) {
            query.scheduleTemplate = ScheduleTemplate.get(params.scheduleTemplate) as ScheduleTemplate
            query.category = QueryCategory.get(params.category)
            query.save()

        }

        parameters.findAll { !Parameter.findByQueryAndName(query, it.name) }.each { param ->
            def parameter = new Parameter(query: query)
            parameter.name = param.name
            parameter.type = param.type
            parameter.multiSelect = param.multiSelect
            if (param.defaultValue && param.defaultValue != '')
                parameter.defaultValue = param.defaultValue
            parameter.save()
        }

        sortingRules.findAll { !SortingRule.findByQueryAndFieldName(query, it.fieldName) }.each { rule ->
            def sortingRule = new SortingRule(query: query)
            sortingRule.fieldName = rule.fieldName
            sortingRule.sortDirection = rule.sortDirection
            sortingRule.sortOrder = rule.sortOrder
            sortingRule.save()
        }

        query.save(flush:true)
        redirect(action: 'list')
    }

    private void deleteRuleTree(Rule rule) {
        if (!rule || Query.findByRule(rule))
            return
        Rule.findAllByParent(rule).each {
            deleteRuleTree(it)
        }
        rule?.delete()
    }

    private Rule parseRule(GrailsClass domainClass, json, Rule parent) {
        if (!json.field && !json.condition)
            return null
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
            }.each {
                fieldsMap.put(FarsiNormalizationFilter.apply(message(code: "${domainClass.fullName}.${it.name}.label")), it.name)
            }

            rule.value = fieldsMap[rule.value] ?: rule.value

            rule.save(flush: true)
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
            object.condition = rule.aggregationType
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
            }.each {
                fieldsMap.put(it.name, FarsiNormalizationFilter.apply(message(code: "${domainClass.fullName}.${it.name}.label")))
            }

            object.value = fieldsMap[object.value] ?: object.value
        }
        object
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def list() {
        def root = [:]
        root.id = 0
        root.text = message(code: 'root')
        root.items = getCategoryTree(null)
        root.expanded = true
        [categoryTree: root]
    }

    private def findChildCategories(QueryCategory parent) {
        def list = []
        if (parent != null) {
            list << parent

            QueryCategory.findAllByParentAndDeleted(parent, false).each {
                list = list + findChildCategories(it)
            }
        } else {
            QueryCategory.findAllByParentIsNullAndDeleted(false).each {
                list = list + findChildCategories(it)
            }
        }
        list
    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def jsonList() {
        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list = []
        def idList = findChildCategories(QueryCategory.get(params.category as Long)).collect { it.id }
        if (params.search && params.search != '') {
            def searchResult = Query.search(params.search?.toString()).results.collect { it.id }

            def criteria = {
                eq('deleted', false)
                'in'('id', searchResult)
                category {
                    'in'('id', idList)
                }
            }
            list = Query.createCriteria().list(parameters, criteria)
            value.total = Query.createCriteria().count(criteria)
        } else {
            def criteria = {
                eq('deleted', false)
                if (params.category != '0') {
                    category {
                        'in'('id', idList)
                    }
                }
            }
            list = Query.createCriteria().list(parameters, criteria)

            value.total = Query.createCriteria().count(criteria)
        }
        value.data = list.collect {
            def parameterList = Parameter.findAllByQuery(it)
            [
                    id              : it.id,
                    title           : it.title,
                    description     : it.description?.replaceAll("<(.|\n)*?>", ''),
                    domainClazz     : message(code: "${it.domainClazz}.label"),
                    scheduleTemplate: it.scheduleTemplate?.title,
                    owner           : it.owner.toString(),
                    imageUrl        : createLink(controller: 'image', id: it.image?.id, params: [size: 120]),
                    dateCreated     : format.jalaliDate(date: it.dateCreated, hm: 'true'),
                    lastUpdated     : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                    parameterTags   : parameterList?.size() > 0 ? parameterList.sort { it.name }.collect {
                        "${it.name}"
                    }.join(' , ') : '-'
            ]
        }
        render value as JSON
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def delete() {

        def query = Query.get(params.id)

        QueryInstance.findAllByQuery(query).each {
            it.deleted = true
            it.save()
        }

        query.deleted = true
        render(query.save() ? '1' : '0')
    }

    private def getCategoryTree(root) {
        def recordList
        if (root)
            recordList = QueryCategory.createCriteria().list {
                parent {
                    eq("id", root.id)
                }
                eq('deleted', false)
            }
        else
            recordList = QueryCategory.findAllByParentIsNullAndDeleted(false)

        recordList.collect {
            [
                    id      : it.id,
                    text    : it.name,
                    expanded: true,
                    imageUrl: createLink(controller: 'image', id: it.image?.id, params: [size: '80']),
                    items   : getCategoryTree(it)
            ]
        }
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def select() {
        def root = [:]
        root.id = 0
        root.text = message(code: 'root')
        root.items = getCategoryTree(null)
        root.expanded = true
        [categoryTree: root]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def register() {
        def queryInstance = params.id ? QueryInstance.get(params.id) : new QueryInstance(query: Query.get(params.query))
        def scheduleTypes = []
        if (queryInstance.query.scheduleTemplate.eventBasedNotificationEnabled)
            scheduleTypes << 'eventBased'
        if (queryInstance.query.scheduleTemplate.periodicNotificationEnabled)
            scheduleTypes << 'periodic'
        if (queryInstance.query.scheduleTemplate.specificTimeNotificationEnabled)
            scheduleTypes << 'specificTime'
        [queryInstance: queryInstance, scheduleTypes: scheduleTypes]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def saveRegistration() {

        def queryInstance
        if (params.id) {
            queryInstance = QueryInstance.get(params.id)
            ParameterValue.findAllByQueryInstance(queryInstance).each { it.delete() }
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

        queryInstance.lastExecutionTime = new Date()
        queryInstance.save()

        ScheduleDay.findAllBySchedule(queryInstance.schedule).each { it.delete() }
        ScheduleTime.findAllBySchedule(queryInstance.schedule).each { it.delete() }

        if (queryInstance.schedule?.type == 'periodic') {
            queryInstance.query.scheduleTemplate.dayTemplates.collect { it.day }.each { day ->
                def scheduleDay = new ScheduleDay(day: day)
                scheduleDay.startTimeInMinute = params."${day}_allowedTimeRangeStart".toInteger()
                scheduleDay.endTimeInMinute = params."${day}_allowedTimeRangeEnd".toInteger()
                scheduleDay.schedule = queryInstance.schedule
                scheduleDay.save()
            }
        }

        if (queryInstance.schedule?.type == 'specificTime') {
            ScheduleDay.constraints.day.inList.findAll { params."scheduleTime_${it}" }.each { day ->
                def scheduleDay = new ScheduleDay(day: day)
                scheduleDay.schedule = queryInstance.schedule
                scheduleDay.save()
            }

            def scheduleTimes = params.scheduleTimes
            if (scheduleTimes instanceof String)
                scheduleTimes = [scheduleTimes]
            scheduleTimes.each { time ->
                def scheduleTime = new ScheduleTime()
                def timeParts = time.split(':')
                scheduleTime.timeInMinute = timeParts[0].toInteger() * 60 + timeParts[1].toInteger()
                scheduleTime.schedule = queryInstance.schedule
                scheduleTime.save()
            }
        }

        //save parameters
        Parameter.findAllByQuery(queryInstance.query).each { parameter ->
            if (params."parameterValueValue_${parameter.id}" instanceof String) {
                params."parameterValueValue_${parameter.id}" = [params."parameterValueValue_${parameter.id}"]
                params."parameterValueText_${parameter.id}" = [params."parameterValueText_${parameter.id}"]
                params."parameterValueType_${parameter.id}" = [params."parameterValueType_${parameter.id}"]
            }

            for (def i = 0; i < params."parameterValueValue_${parameter.id}".size(); i++) {
                def parameterValue = new ParameterValue(queryInstance: queryInstance)
                parameterValue.parameter = parameter
                parameterValue.value = params."parameterValueValue_${parameter.id}"[i]
                parameterValue.text = params."parameterValueText_${parameter.id}"[i]
                parameterValue.type = params."parameterValueType_${parameter.id}"[i]
                parameterValue.save()
            }
        }

        //schedule execution
        scheduleService.calculateQueryInstanceNextExecutionTime(queryInstance)

        if (params.saveAndNew)
            redirect(action: 'register', params: [query: queryInstance?.queryId])
        else
            redirect(action: 'instanceList')
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def instanceList() {
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
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
                    imageUrl         : createLink(controller: 'image', id: it.query?.image?.id, params: [size: 120]),
                    dateCreated      : format.jalaliDate(date: it.dateCreated, hm: 'true'),
                    lastUpdated      : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                    parameterTags    : parameterList?.size() > 0 ? parameterList.sort { it.parameter.name }.collect {
                        "${it.parameter.name}: ${it.text ?: it.value}"
                    }.join(' , ') : '-',
                    toggleAction     : it.enabled ? 'disable' : 'enable',
                    toggleActionTitle: it.enabled ? message(code: 'queryInstance.disable.button') : message(code: 'queryInstance.enable.button')
            ]
        }

        render value as JSON
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def instanceDelete() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.deleted = true
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def instanceEnable() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.enabled = true
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def instanceDisable() {
        def queryInstance = QueryInstance.get(params.id)
        queryInstance.enabled = false
        if (queryInstance.save())
            render '1'
        else
            render '0'
    }

    def autoComplete() {
        def term = params."filter[filters][0][value]"?.toString() ?: ''
        def domainClass = grailsApplication.getDomainClass("${params.domain}")
        def property = domainClass.persistentProperties.find { it.name == params.field }
        def sourceDomainClass = domainClass.constrainedProperties."${property.name}".metaConstraints.query?.domain
        BooleanQuery.setMaxClauseCount(1000000)
        def result = sourceDomainClass.search("*${term}*", max: 1000000)
        result = result.results.findAll {
            domainClass.constrainedProperties."${property.name}".metaConstraints.query?.filter(it)
        }
        result = result.collect {
            [
                    name      : domainClass.constrainedProperties."${property.name}".metaConstraints.query?.display(it),
                    value     : it."${domainClass.constrainedProperties."${property.name}".metaConstraints.query?.value}",
                    typeString: message(code: 'autocomplete.itemType.domain')]
        }.sort { it.name }
        def parameters = []
        def indexer = 0
        while (params."currentData[${indexer}][name]") {
            def name = params."currentData[${indexer}][name]"?.toString()
            if (!term?.split(' ')?.any { !name.contains(it) })
                parameters << [name: name, value: name, typeString: params."currentData[${indexer}][typeString]"]
            indexer++
        }
        render([data: parameters + result] as JSON)
    }

    def parameterValues() {
        def query = Query.get(params.id)
        [
                query     : query,
                parameters: Parameter.findAllByQueryAndTypeInList(query, ['integer', 'string'])
        ]
    }

    def cascadingData() {
        def domainClass = grailsApplication.getDomainClass("${params.domain}")
        if (!params.parentDomain) {
            def data = domainClass.clazz.findAll()
            if (params.filter) {
                def filter = JSON.parse(params.filter)
                filter.keySet().each { key ->
                    switch (filter[key]) {
                        case 'currentUser':
                            data = data.findAll { it."${key}" == springSecurityService.currentUser }
                            break;
                        default:
                            data = data.findAll { it."${key}" == it."${filter[key]}" }

                    }
                }
            }
            render([data: data.collect {
                [name: it."${params.display}", value: it."${params.primaryKey}"]
            }] as JSON)
        } else {
            def foreignKey = params.foreignKey.toCharArray()
            foreignKey[0] = foreignKey.toString().toUpperCase()[0]
            def parentDomainClass = grailsApplication.getDomainClass("${params.parentDomain}")
            def parent = parentDomainClass.clazz.get(params."filter[filters][0][value]")
            def result = domainClass.clazz."findAllBy${foreignKey.toString()}"(parent).collect {
                [name: it."${params.display}", value: it."${params.primaryKey}"]
            }
            result = [[name: message(code: "${params.domain}.all"), value: 0]] + result
            render([data: result] as JSON)
        }

    }
}
