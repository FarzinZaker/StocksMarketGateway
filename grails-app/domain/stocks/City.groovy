package stocks

class City {

    String name
    Province province

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Province]

    static constraints = {
    }
}
