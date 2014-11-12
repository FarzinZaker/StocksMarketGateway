package stocks.alerting

class ParameterSuggestedValue {

    String title
    Parameter parameter

    static hasMany = [variations: ParameterSuggestedValueVariation]

    static mapping = {
        table 'alerting_parameter_suggested_value'
//        sort('id')
    }

    static constraints = {
    }
}
