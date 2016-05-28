package stocks.tse

class BlackListedIndex {

    Index index

    static constraints = {
    }

    static mapping = {
        table 'tse_blacklist_index'
    }

    public static String compassQuery() {
        def items = persianNameList()
        if (!items.size())
            return ''

        items.collect { it.split(' ').collect { " AND -persianName:${it}" }.join('') }.join('');
    }


    public static List<String> persianNameList() {
        list().collect { it.index?.persianName }
    }
}
