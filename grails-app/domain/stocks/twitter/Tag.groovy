package stocks.twitter

class Tag {

    static auditable = true

    String name
    Date dateCreated

    static hasMany = [documents: Document]


    static mapping = {
        table 'twit_tag'
    }

    static constraints = {
    }

    @Override
    String toString(){
        name
    }
}
