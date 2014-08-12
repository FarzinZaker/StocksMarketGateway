package stocks.alerting

import org.apache.jasper.tagplugins.jstl.core.Param
import stocks.User

class Query {

    static searchable = true

    String title
    String description
    String domainClazz
    Rule rule
    User owner
    String smsTemplate
    ScheduleTemplate scheduleTemplate

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alerting_query'
        description type:'text'
        smsTemplate type:'text'
        sort('title')
    }

    static constraints = {
        rule nullable: true
    }

    static transients = ['parameterListString']

    String getParameterListString(){
        Parameter.findAllByQuery(this).collect {it.name}.join(',')
    }
}
