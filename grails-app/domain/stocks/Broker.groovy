package stocks

class Broker {

    String name
    String englishName
    Image logo

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        logo nullable: true
    }
}
