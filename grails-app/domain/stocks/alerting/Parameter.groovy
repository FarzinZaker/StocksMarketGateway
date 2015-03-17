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
        table 'alt_param'
        sort('name')
    }


    static constraints = {
        defaultValue nullable: true
        multiSelect nullable: true
    }
}
