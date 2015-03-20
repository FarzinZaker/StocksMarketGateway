package stocks.twitter

import stocks.User

class Comment {

    static auditable = true

    User author
    String body
    Date dateCreated
    Date lastUpdated
    Document document
    Comment relatedComment

    static hasMany = [likes: Like, comments: Comment]

    static mapping = {
        table 'twit_comment'
        body type: "text"
    }

    static constraints = {
    }

    @Override
    String toString(){
        body
    }
}
