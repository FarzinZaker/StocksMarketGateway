package stocks.twitter.Search

class TwitterGroup {

    static searchable = true

    String rid
    String title
    String description
    String imageId

    static mapping = {
        table 'twit_search_group'
        title type: "text", sqlType: 'clob'
        description type: "text", sqlType: 'clob'
    }

    static constraints = {
        imageId nullable: true
    }
}
