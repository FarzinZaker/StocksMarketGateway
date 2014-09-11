package stocks.commodity

class Producer {

    String name
    Integer code

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'commodity_producer'
    }

    static constraints = {
    }
}
