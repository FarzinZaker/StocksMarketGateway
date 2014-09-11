package stocks.commodity

class MainGroup {

    String name
    Integer code

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'commodity_main_group'
    }

    static constraints = {
    }
}
