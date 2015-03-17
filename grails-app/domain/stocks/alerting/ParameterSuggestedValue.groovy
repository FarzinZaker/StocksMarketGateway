package stocks.alerting

class ParameterSuggestedValue {

    String title
    Parameter parameter

    static hasMany = [variations: ParameterSuggestedValueVariation]

    static mapping = {
        table 'alt_param_suggested_value'
//        sort('id')
    }

    static constraints = {
    }
}
