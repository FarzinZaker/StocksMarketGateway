package stocks.alerting

class ParameterValue {

    static auditable = true

    String value
    QueryInstance queryInstance
    Parameter parameter

    static belongsTo = [QueryInstance]

    static mapping = {
        table 'alerting_parameter_value'
    }


    static constraints = {
    }
}
