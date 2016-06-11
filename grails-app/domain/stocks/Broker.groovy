package stocks

class Broker {

    static auditable = true

    String name
    String englishName
    Image logo

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        logo nullable: true
    }

    @Override
    public String toString(){
        name
    }
}
