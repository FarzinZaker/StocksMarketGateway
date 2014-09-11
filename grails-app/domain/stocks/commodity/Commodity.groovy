package stocks.commodity

class Commodity {

    String name

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'commodity_commodity'
    }

    static constraints = {
        name nullable: true
    }
}
