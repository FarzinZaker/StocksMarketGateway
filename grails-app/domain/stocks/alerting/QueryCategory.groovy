package stocks.alerting

import stocks.Image
import stocks.User

class QueryCategory {

    static auditable = true

    String name
    String description
    Image image
    User owner
    QueryCategory parent
    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table "alt_query_category"
    }

    static constraints = {
        description nullable: true, sqlType: "clob"
        image nullable:true, maxSize: 2000000000
        parent nullable: true
    }
}
