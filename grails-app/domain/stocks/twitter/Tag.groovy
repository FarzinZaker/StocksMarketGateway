package stocks.twitter

class Tag {

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
