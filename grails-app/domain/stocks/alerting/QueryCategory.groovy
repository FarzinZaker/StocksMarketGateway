package stocks.alerting

import stocks.Image
import stocks.User

class QueryCategory {

    String name
    String description
    Image image
    User owner
    QueryCategory parent
    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table "alerting_query_category"
    }

    static constraints = {
        description nullable: true, sqlType: "text"
        image nullable:true, maxSize: 2000000000
        parent nullable: true
    }
}
