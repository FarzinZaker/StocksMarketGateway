package stocks.alerting

import stocks.User

class QueryInstance {

    static searchable = true

    Query query
    Schedule schedule
    User owner
    Boolean enabled = true
    Date lastExecutionDate

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [parameterValues: ParameterValue]

    static mapping = {
        table 'alerting_query_instance'
    }

    static constraints = {
    }

    static transients = ['title', 'description', 'domainClazz', 'smsTemplate', 'parameterListString']

    String getTitle() {
        query?.title ?: ''
    }

    String getDescription() {
        query?.description ?: ''
    }

    String getDomainClazz() {
        query?.description ?: ''
    }

    String getSmsTemplate() {
        query?.smsTemplate ?: ''
    }

    String getParameterListString() {
        query?.parameterListString ?: ''
    }
}
