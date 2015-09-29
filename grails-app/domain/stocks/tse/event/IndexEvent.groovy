package stocks.tse.event

import stocks.tse.Symbol
import stocks.tse.Index

class IndexEvent {

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
    Index data

    static mapping = {
        table 'tse_index_ev'
        date column: 'dat'
        todayIndexChangePercentTowardYesterday column: 'todayIndexChangePercToYest'
        averageDecreasedSymbolsChangePercent column: 'averageDecSymbolsChangePerc'
        averageIncreasedSymbolsChangePercent column: 'averageIncSymbolsChangePerc'
    }

    static constraints = {
        marketIdentifier nullable: true, parameterIndex: 0
        internalCode nullable: true, xmlNodeName: 'InsCode'
        persianName nullable: true, xmlNodeName: 'LVal30'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEven'
        tradedSymbolCount nullable: true, xmlNodeName: 'ZValIdxCot'
        decreasedSymbolCount nullable: true, xmlNodeName: 'ZValBaiIbs'
        increasedSymbolCount nullable: true, xmlNodeName: 'ZValHauIbs'
        unchangedSymbolCount nullable: true, xmlNodeName: 'ZValIchgIbs'
        notTradedSymbolCount nullable: true, xmlNodeName: 'ZValNonCotIbs'
        reservedSymbolCount nullable: true, xmlNodeName: 'ZValResIbs'
        suspendedSymbolCount nullable: true, xmlNodeName: 'ZValSuIbs'
        totalSymbolCount nullable: true, xmlNodeName: 'ZTotValIbs'
        highestValueTime nullable: true, xmlNodeName: 'HPhJIdx'
        highestValue nullable: true, xmlNodeName: 'HPbJIdx'
        finalIndexValue nullable: true, xmlNodeName: 'XDrNivJIdx004'
        highestIndexValue nullable: true, xmlNodeName: 'XPhNivJIdx004'
        lowestIndexValue nullable: true, xmlNodeName: 'XPbNivJIdx004'
        cashEfficiencyIndexValue nullable: true, xmlNodeName: 'XNivIrteNetIbs'
        todayIndexChangePercent nullable: true, xmlNodeName: 'XVarIdxJ'
        todayIndexChangePercentTowardYesterday nullable: true, xmlNodeName: 'XVarIdxJRfV'
        averageSymbolChangePercent nullable: true, xmlNodeName: 'XMoyVarValIbs'
        averageDecreasedSymbolsChangePercent nullable: true, xmlNodeName: 'XMoyVarValBaiIbs'
        averageIncreasedSymbolsChangePercent nullable: true, xmlNodeName: 'XmoyVarValHauIbs'

        data nullable: true
    }
}
