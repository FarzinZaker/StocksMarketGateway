package stocks.alerting

class ParameterSuggestedValueVariation {

    String title
    ParameterSuggestedValue suggestedValue

    static belongsTo = ParameterSuggestedValue

    static mapping = {
        table 'alt_param_sgt_val_var'
        sort('value')
    }

    static constraints = {
    }
}
