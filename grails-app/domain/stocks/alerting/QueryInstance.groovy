package stocks.alerting

import stocks.User

class QueryInstance {

    static auditable = true

    static searchable = true

    Query query
    Schedule schedule
    Boolean smsEnabled
    Boolean telegramEnabled
    Boolean appEnabled

    User owner
    Boolean enabled = true
    Date lastExecutionTime
    Date nextExecutionTime

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [parameterValues: ParameterValue]

    static mapping = {
        table 'alt_query_instance'
    }

    static constraints = {
        nextExecutionTime nullable: true
        smsEnabled nullable: true
        telegramEnabled nullable: true
        appEnabled nullable: true
    }

    static transients = ['title', 'description', 'domainClazz', 'smsTemplate', 'parameterListString']

    String getTitle() {
        query?.title ?: ''
    }

    String getDescription() {
        query?.description ?: ''
    }

    String getDomainClazz() {
        query?.domainClazz ?: ''
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
