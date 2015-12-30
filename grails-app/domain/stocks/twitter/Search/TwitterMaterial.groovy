package stocks.twitter.Search

class TwitterMaterial {

    static searchable = true

    String rid
    String title
    String summary
    String type
    Long identifier
    String imageId

    String authorRid
    String groupRidsString
    String propertyTitlesString

    transient List<String> getGroupRidList() {
        groupRidsString?.split(',')?.collect { it.replace('[', '').replace(']', '') } ?: []
    }

    transient void setGroupRidList(List<String> list) {
        groupRidsString = list.collect { "[${it}]" }.join(',')
    }

    transient List<String> getPropertyTitleList() {
        propertyTitlesString?.split('||')?.collect { it.replace('[', '').replace(']', '') } ?: []
    }

    transient void setPropertyTitleList(List<String> list) {
        propertyTitlesString = list.join('||')
    }

    static mapping = {
        table 'twit_search_material'
        title type: "text", sqlType: 'clob'
        summary type: "text", sqlType: 'clob'
        groupRidsString column: 'group_rids'
        propertyTitlesString column: 'property_titles'
    }

    static constraints = {
        title nullable: true
        identifier nullable: true
        imageId nullable: true
        authorRid nullable: true
        groupRidsString nullable: true
        propertyTitlesString nullable: true
    }
}
