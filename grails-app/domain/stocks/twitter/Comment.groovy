package stocks.twitter

import stocks.User

class Comment {

    User author
    String body
    Date dateCreated
    Date lastUpdated
    Document document
    Comment relatedComment

    static hasMany = [likes: Like, comments: Comment]

    static mapping = {
        body sqlType: "text"
    }

    static constraints = {
    }

    @Override
    String toString(){
        body
    }
}
