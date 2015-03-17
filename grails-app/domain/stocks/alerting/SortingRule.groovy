package stocks.alerting

class SortingRule {

    static auditable = true

    String fieldName
    String sortDirection
    Integer sortOrder
    Query query

    static mapping = {
        table 'alt_sorting_rule'
        sort 'sortOrder'
    }

    static constraints = {
        sortDirection inList: ['asc', 'desc']
    }
}
