package stocks.commodity

class Provider {

    String name

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'commodity_provider'
    }

    static constraints = {
        name nullable: true
    }
}
