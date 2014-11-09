package stocks.twitter

class Tag {

    static auditable = true

    String name
    Date dateCreated

    static hasMany = [documents: Document]

    static constraints = {
    }

    @Override
    String toString(){
        name
    }
}
