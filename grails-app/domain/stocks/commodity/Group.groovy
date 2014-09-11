package stocks.commodity

class Group {

    String name
    Integer code
    MainGroup mainGroup

    Date dateCreated
    Date lastUpdated

    static belongsTo = [MainGroup]

    static mapping = {
        table 'commodity_group'
    }

    static constraints = {
    }
}
