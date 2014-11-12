package stocks.alerting

class ParameterSuggestedValueVariation {

    String title
    ParameterSuggestedValue suggestedValue

    static belongsTo = ParameterSuggestedValue

    static mapping = {
        table 'alerting_parameter_suggested_value_variation'
        sort('value')
    }

    static constraints = {
    }
}
