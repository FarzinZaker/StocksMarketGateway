package stocks.alerting

class SortingRule {

    String fieldName
    String sortDirection
    Integer sortOrder
    Query query

    static mapping = {
        table 'alerting_sorting_rule'
        sort 'sortOrder'
    }

    static constraints = {
        sortDirection inList: ['asc', 'desc']
    }
}
