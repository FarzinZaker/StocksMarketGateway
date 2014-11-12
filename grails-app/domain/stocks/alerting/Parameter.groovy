package stocks.alerting

class Parameter {

    static auditable = true

    String name
    String type
    String defaultValue
    Boolean multiSelect
    Query query

    static hasMany = [suggestedValues: ParameterSuggestedValue]

    static mapping = {
        table 'alerting_parameter'
        sort('name')
    }


    static constraints = {
        defaultValue nullable: true
        multiSelect nullable: true
    }
}
