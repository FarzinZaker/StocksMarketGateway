package stocks.twitter

import stocks.Image
import stocks.User

class Article {

    static auditable = true

    static searchable = true

    User author
    String title
    String summary
    String body
    Image image
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'twit_article'
        summary type: "text"
        body type: "text"
    }

    static constraints = {
    }

    @Override
    String toString(){
        title
    }
}
