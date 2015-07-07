package stocks.tse.data

import grails.plugin.cache.web.filter.PageFragmentCachingFilter
import groovyx.net.http.HTTPBuilder
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.params.HttpClientParams
import org.ccil.cowan.tagsoup.Parser
import org.jsoup.Jsoup
import stocks.RandomUserAgent
import stocks.tse.MarketValue
import stocks.tse.event.MarketValueEvent
import stocks.util.ByteUtils

class MarketValueDataService {
    static transactional = false
    def tseEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]


    void importData() {
//        def html = new XmlSlurper().parse("http://www.tsetmc.com/Loader.aspx?ParTree=15")
        def http = new HTTPBuilder("http://www.tsetmc.com/Loader.aspx?ParTree=15")
        def html = http.get([:])
        def marketValueEvent = new MarketValueEvent()
        marketValueEvent.marketIdentifier = 1
        def rows = html?.'**'?.find { it?.@id == 'GlobalTab1Elm' }?.'**'?.findAll { it?.name() == 'TR' }
        rows?.each { row ->
            if (row.text().toString().startsWith('ارزش بازار'))
                marketValueEvent.value = parseDouble(row.children()[1].text())
            if (row.text().toString().startsWith('تعداد معاملات'))
                marketValueEvent.tradeCount = parseInteger(row.children()[1].text())
            if (row.text().toString().startsWith('ارزش معاملات'))
                marketValueEvent.tradeValue = parseDouble(row.children()[1].text())
            if (row.text().toString().startsWith('حجم معاملات'))
                marketValueEvent.tradeVolume = parseDouble(row.children()[1].text())

        }
        marketValueEvent.date = new Date().clearTime()
        marketValueEvent.data = MarketValue.findByMarketIdentifierAndDate(marketValueEvent.marketIdentifier, marketValueEvent.date)
        tseEventGateway.send(marketValueEvent, this.class.canonicalName)


        marketValueEvent = new MarketValueEvent()
        marketValueEvent.marketIdentifier = 2
        rows = html?.'**'?.find { it?.@id == 'GlobalTab2Elm' }?.'**'?.findAll { it?.name() == 'TR' }
        rows?.each { row ->
            if (row.text().toString().startsWith('ارزش بازار اول و دوم'))
                marketValueEvent.value = parseDouble(row.children()[1].text())
            if (row.text().toString().startsWith('تعداد معاملات'))
                marketValueEvent.tradeCount = parseInteger(row.children()[1].text())
            if (row.text().toString().startsWith('ارزش معاملات'))
                marketValueEvent.tradeValue = parseDouble(row.children()[1].text())
            if (row.text().toString().startsWith('حجم معاملات'))
                marketValueEvent.tradeVolume = parseDouble(row.children()[1].text())

        }
        marketValueEvent.date = new Date().clearTime()
        marketValueEvent.data = MarketValue.findByMarketIdentifierAndDate(marketValueEvent.marketIdentifier, marketValueEvent.date)
        tseEventGateway.send(marketValueEvent, this.class.canonicalName)
    }

    private static Double parseDouble(String text) {
        def parts = text.split(' ')
        def result = parts[0].replace(',', '') as Double
        if (parts.size() > 1)
            switch (parts[1]) {
                case 'B':
                    result *= 1000000000
                    break
                case 'M':
                    result *= 1000000
                    break
                case 'K':
                    result *= 1000
            }
        result
    }

    private static Integer parseInteger(String text) {
        def parts = text.split(' ')
        def result = parts[0].replace(',', '') as Integer
        if (parts.size() > 1)
            switch (parts[1]) {
                case 'B':
                    result *= 1000000000
                    break
                case 'M':
                    result *= 1000000
                    break
                case 'K':
                    result *= 1000
            }
        result
    }
}
