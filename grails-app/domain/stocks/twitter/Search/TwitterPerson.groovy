package stocks.twitter.Search

class TwitterPerson {

    static searchable = true

    String rid
    String title
    Long identifier

    static mapping = {
        table 'twit_search_person'
        title type: "text", sqlType: 'clob'
    }

    static constraints = {
    }
}
