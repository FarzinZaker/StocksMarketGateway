package stocks.twitter.Search

class TwitterProperty {

    static searchable = true

    String rid
    String title
    String type
    Long identifier

    static mapping = {
        table 'twit_search_property'
        title type: "text", sqlType: 'clob'
    }

    static constraints = {
        identifier nullable: true
    }
}
