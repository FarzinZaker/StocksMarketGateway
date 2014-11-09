package stocks.alerting

class Parameter {

    static auditable = true

    String name
    String type
    String defaultValue
    Query query

    static mapping = {
        table 'alerting_parameter'
        sort('name')
    }


    static constraints = {
        defaultValue nullable: true
    }
}
