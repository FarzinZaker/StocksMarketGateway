package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.tse.IndexHistory

class IndexSeries9Service {

    def timeSeriesDB9Service

    def write(List<IndexHistory> indexHistories) {

        def serie = new Serie()
        indexHistories.each { indexHistory ->
            if (indexHistory.indexId) {
                [
                        "finalIndexValue",
                        "firstIndexValue",
                        "highestIndexValue",
                        "lowestIndexValue",
                        "changePercentOfHighestValueTowardYesterday",
                        "changePercentOfLowestValueTowardYesterday",
                        "decreasedSymbolsChangePercent",
                        "netPaidBenefit",
                        "yesterdayInvestment",
                        "baseInvestmentAdjustmentFactor",
                        "netCashReturnIndex"
                ].each { property ->
                    serie.addPoint(new Point("index_${property}")
                            .tags([indexId: indexHistory.indexId])
                            .time(indexHistory.date)
                            .value(indexHistory."${property}"))
                }
            }

        }

        timeSeriesDB9Service.write(serie)
    }

    def finalIndexValueList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'finalIndexValue', startDate, endDate, groupingMode)
    }

    def lastFinalIndexValue(Long indexId, Date endDate = null) {
        lastValue(indexId, 'finalIndexValue', endDate)
    }

    def firstIndexValueList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'firstIndexValue', startDate, endDate, groupingMode)
    }

    def lastFirstIndexValue(Long indexId, Date endDate = null) {
        lastValue(indexId, 'firstIndexValue', endDate)
    }

    def highestIndexValueList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'highestIndexValue', startDate, endDate, groupingMode)
    }

    def lastHighestIndexValue(Long indexId, Date endDate = null) {
        lastValue(indexId, 'highestIndexValue', endDate)
    }

    def lowestIndexValueList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'lowestIndexValue', startDate, endDate, groupingMode)
    }

    def lastLowestIndexValue(Long indexId, Date endDate = null) {
        lastValue(indexId, 'lowestIndexValue', endDate)
    }

    def changePercentOfHighestValueTowardYesterdayList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'changePercentOfHighestValueTowardYesterday', startDate, endDate, groupingMode)
    }

    def lastChangePercentOfHighestValueTowardYesterday(Long indexId, Date endDate = null) {
        lastValue(indexId, 'changePercentOfHighestValueTowardYesterday', endDate)
    }

    def changePercentOfLowestValueTowardYesterdayList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'changePercentOfLowestValueTowardYesterday', startDate, endDate, groupingMode)
    }

    def lastChangePercentOfLowestValueTowardYesterday(Long indexId, Date endDate = null) {
        lastValue(indexId, 'changePercentOfLowestValueTowardYesterday', endDate)
    }

    def decreasedSymbolsChangePercentList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'decreasedSymbolsChangePercent', startDate, endDate, groupingMode)
    }

    def lastDecreasedSymbolsChangePercent(Long indexId, Date endDate = null) {
        lastValue(indexId, 'decreasedSymbolsChangePercent', endDate)
    }

    def netPaidBenefitList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'netPaidBenefit', startDate, endDate, groupingMode)
    }

    def lastNetPaidBenefit(Long indexId, Date endDate = null) {
        lastValue(indexId, 'netPaidBenefit', endDate)
    }

    def yesterdayInvestmentList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'yesterdayInvestment', startDate, endDate, groupingMode)
    }

    def lastYesterdayInvestment(Long indexId, Date endDate = null) {
        lastValue(indexId, 'yesterdayInvestment', endDate)
    }

    def baseInvestmentAdjustmentFactorList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'baseInvestmentAdjustmentFactor', startDate, endDate, groupingMode)
    }

    def lastBaseInvestmentAdjustmentFactor(Long indexId, Date endDate = null) {
        lastValue(indexId, 'baseInvestmentAdjustmentFactor', endDate)
    }

    def netCashReturnIndexList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(indexId, 'netCashReturnIndex', startDate, endDate, groupingMode)
    }

    def lastNetCashReturnIndex(Long indexId, Date endDate = null) {
        lastValue(indexId, 'netCashReturnIndex', endDate)
    }

    def indexHistoryList(Long indexId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstIndexDate(indexId)
            }
        if (!endDate)
            endDate = new Date()
        def propertyList = [
                "finalIndexValue",
                "firstIndexValue",
                "highestIndexValue",
                "lowestIndexValue",
                "changePercentOfHighestValueTowardYesterday",
                "changePercentOfLowestValueTowardYesterday",
                "decreasedSymbolsChangePercent",
                "netPaidBenefit",
                "yesterdayInvestment",
                "baseInvestmentAdjustmentFactor",
                "netCashReturnIndex"
        ]
        def series = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "index_${pr}" }.join(', ')} WHERE indexId = '${indexId}' time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.indexId = indexId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            if (item.finalIndexValue)
                list << item
        }
        list.sort { it.date }

    }

    def valueList(Long indexId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM index_${property} WHERE indexId = '${indexId}' time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }

    Double lastValue(Long indexId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM index_${property} WHERE indexId = '${indexId}' time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }

    Date firstIndexDate(Long indexId) {
        IndexHistory.createCriteria().list {
            index {
                eq('id', indexId)
            }
            projections {
                property('date')
            }
            order('date', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
