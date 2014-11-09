package stocks

class Province {

    static auditable = true

    String name

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
}
