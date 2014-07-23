package stocks.twitter

import stocks.Image
import stocks.User

class Document {

    User author
    String title
    String summary
    String body
    Image image
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Tag]

    static hasMany = [rates: Rate, comments: Comment, relatedDocuments: Document, tags: Tag]

    static mapping = {
        summary sqlType: "text"
        body sqlType: "text"
    }

    static constraints = {
    }

    @Override
    String toString(){
        title
    }
}
