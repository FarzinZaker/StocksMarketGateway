package stocks.tse

class Index {

    static searchable = [only:['persianName']]

    static notify = true

    Integer marketIdentifier
    Long internalCode
    String persianName
    Date date
    Integer tradedSymbolCount
    Integer decreasedSymbolCount
    Integer increasedSymbolCount
    Integer unchangedSymbolCount
    Integer notTradedSymbolCount
    Integer reservedSymbolCount
    Integer suspendedSymbolCount
    Integer totalSymbolCount
    Integer highestValueTime
    Integer highestValue
    Double finalIndexValue
    Double highestIndexValue
    Double lowestIndexValue
    Double cashEfficiencyIndexValue
    Double todayIndexChangePercent
    Double todayIndexChangePercentTowardYesterday
    Double averageSymbolChangePercent
    Double averageDecreasedSymbolsChangePercent
    Double averageIncreasedSymbolsChangePercent

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_index'
    }

    static constraints = {
        marketIdentifier nullable: true
        internalCode nullable: true
        persianName nullable: true, query: true, token: true
        date nullable: true, token: true
        tradedSymbolCount nullable: true, query: true, token: true
        decreasedSymbolCount nullable: true, query: true, token: true
        increasedSymbolCount nullable: true, query: true, token: true
        unchangedSymbolCount nullable: true, query: true, token: true
        notTradedSymbolCount nullable: true, query: true, token: true
        reservedSymbolCount nullable: true, query: true, token: true
        suspendedSymbolCount nullable: true, query: true, token: true
        totalSymbolCount nullable: true, query: true, token: true
        highestValueTime nullable: true, query: true, token: true
        highestValue nullable: true, query: true, token: true
        finalIndexValue nullable: true, query: true, token: true
        highestIndexValue nullable: true, query: true, token: true
        lowestIndexValue nullable: true, query: true, token: true
        cashEfficiencyIndexValue nullable: true, query: true, token: true
        todayIndexChangePercent nullable: true, query: true, token: true
        todayIndexChangePercentTowardYesterday nullable: true, query: true, token: true
        averageSymbolChangePercent nullable: true, query: true, token: true
        averageDecreasedSymbolsChangePercent nullable: true, query: true, token: true
        averageIncreasedSymbolsChangePercent nullable: true, query: true, token: true
    }
}
