package stocks.twitter

import stocks.User

class Like {

    static auditable = true

    Date dateCreated
    Comment comment
    String type
    User owner

    static belongsTo = [Comment]

    static constraints = {
        type inList: ['LIKE', 'DISLIKE']
    }

    static mapping = {
        table 'twit_like'
    }

    @Override
    String toString(){
        type
    }
}
