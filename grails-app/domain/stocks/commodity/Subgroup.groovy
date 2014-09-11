package stocks.commodity

class Subgroup {

    String name
    Integer code
    Group group

    Date dateCreated
    Date lastUpdated

    static belongsTo = [Group]

    static mapping = {
        table 'commodity_subgroup'
    }

    static constraints = {
    }
}
