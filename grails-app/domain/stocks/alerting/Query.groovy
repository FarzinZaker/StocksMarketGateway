package stocks.alerting

import org.apache.jasper.tagplugins.jstl.core.Param
import stocks.Image
import stocks.User

class Query {

    static auditable = true

    static searchable = true

    String title
    String description
    String domainClazz
    Rule rule
    User owner
    String smsTemplate
    String smsHeaderTemplate
    String smsFooterTemplate
    ScheduleTemplate scheduleTemplate
    QueryCategory category
    Image image
    Integer maxRecordsCount = 0

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alt_query'
        description type:'text'
        smsTemplate type:'text'
        sort('title')
    }

    static constraints = {
        rule nullable: true
        image nullable: true
        smsHeaderTemplate nullable: true
        smsFooterTemplate nullable: true
        category nullable: true
    }

    static transients = ['parameterListString']

    String getParameterListString(){
        Parameter.findAllByQuery(this).collect {it.name}.join(',')
    }

    def beforeInsert(){
        checkToken()
    }

    def afterInsert(){
        checkToken()
    }

    def beforeUpdate(){
        checkToken()
    }

    def afterUpdate(){
        checkToken()
    }

    def checkToken(){

        try{
            if(smsHeaderTemplate.contains('[تاريخ]'))
                throw new Exception('Date problem again!!!!')
        }
        catch(ex){

            println(ex.message)
            ex.printStackTrace()
            throw ex
        }
    }
}
