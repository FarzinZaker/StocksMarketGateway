package stocks.alerting

import stocks.User

class QueryInstance {

    static auditable = true

    static searchable = true

    Query query
    Schedule schedule
    User owner
    Boolean enabled = true
    Date lastExecutionTime
    Date nextExecutionTime

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [parameterValues: ParameterValue]

    static mapping = {
        table 'alerting_query_instance'
    }

    static constraints = {
        nextExecutionTime nullable: true
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

    String getSmsHeaderTemplate() {
        query?.smsHeaderTemplate ?: ''
    }

    String getSmsTemplate() {
        query?.smsTemplate ?: ''
    }

    String getSmsFooterTemplate() {
        query?.smsFooterTemplate ?: ''
    }

    String getParameterListString() {
        query?.parameterListString ?: ''
    }
}
