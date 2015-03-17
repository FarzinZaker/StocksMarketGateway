package stocks.twitter

import stocks.User

class Rate {

    static auditable = true

    Integer value
    Date dateCreated
    Document document
    User owner

    static belongsTo = [Document]


    static mapping = {
        table 'twit_rate'
    }

    static constraints = {
    }

    @Override
    String toString(){
        value
    }
}
