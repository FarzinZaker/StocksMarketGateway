package stocks.alerting

class ParameterValue {

    static auditable = true

    String value
    String text
    String type
    QueryInstance queryInstance
    Parameter parameter

    static belongsTo = [QueryInstance]

    static mapping = {
        table 'alt_param_value'
    }


    static constraints = {
        text nullable: true
        type nullable: true
    }
}
