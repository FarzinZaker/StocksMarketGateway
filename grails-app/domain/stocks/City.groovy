package stocks

class City {

    static auditable = true

    String name
    Province province

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Province]

    static constraints = {
    }
}
