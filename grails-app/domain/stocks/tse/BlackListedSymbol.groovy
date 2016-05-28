package stocks.tse

class BlackListedSymbol {

    Symbol symbol

    static constraints = {
    }

    static mapping = {
        table 'tse_blacklist_symbol'
    }

    public static String compassQuery() {
        def items = persianCodeList()
        if (!items.size())
            return ''

        items.collect { it.split(' ').collect { " AND -persianCode:${it}" }.join('') }.join('');
    }


    public static List<String> persianCodeList() {
        list().collect { it.symbol?.persianCode }
    }
}
