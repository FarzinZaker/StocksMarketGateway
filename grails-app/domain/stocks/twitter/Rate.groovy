package stocks.twitter

import stocks.User

class Rate {

    Integer value
    Date dateCreated
    Document document
    User owner

    static belongsTo = [Document]

    static constraints = {
    }

    @Override
    String toString(){
        value
    }
}
