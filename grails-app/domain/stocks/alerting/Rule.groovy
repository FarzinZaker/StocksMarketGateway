package stocks.alerting

class Rule {

    static auditable = true

    String aggregationType
    String field
    String type
    String inputType
    String operator
    String value
    Rule parent

    static mapping = {
        table 'alt_rule'
    }

    static constraints = {
        aggregationType nullable: true, inList: ['AND', 'OR']
        field nullable: true
        type nullable: true
        inputType nullable: true
        operator nullable: true
        value nullable: true
        parent nullable: true
    }
}
