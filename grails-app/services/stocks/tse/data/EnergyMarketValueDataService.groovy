package stocks.tse.data

import groovyx.net.http.HTTPBuilder
import stocks.tse.EnergyMarketValue
import stocks.tse.event.EnergyMarketValueEvent

class EnergyMarketValueDataService {
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
        def http = new HTTPBuilder("http://www.tsetmc.com/Loader.aspx?ParTree=15131R")
        def html = http.get([:])
        def energyMarketValueEvent = new EnergyMarketValueEvent()
        energyMarketValueEvent.marketIdentifier = 1
        def rows = html?.'**'?.find { it?.@id == 'PureData1' }?.'**'?.findAll { it?.name() == 'TR' }
        rows?.each { row ->
            if (energyMarketValueEvent.tradeCount == null && row.text().toString().startsWith('تعداد معاملات'))
                energyMarketValueEvent.tradeCount = parseInteger(row.children()[1].text())
            if (energyMarketValueEvent.tradeValue == null && row.text().toString().startsWith('ارزش معاملات'))
                energyMarketValueEvent.tradeValue = parseDouble(row.children()[1].text())
            if (energyMarketValueEvent.tradeVolume == null && row.text().toString().startsWith('حجم معاملات'))
                energyMarketValueEvent.tradeVolume = parseDouble(row.children()[1].text())

        }
        energyMarketValueEvent.date = new Date().clearTime()
        energyMarketValueEvent.data = EnergyMarketValue.findByMarketIdentifierAndDate(energyMarketValueEvent.marketIdentifier, energyMarketValueEvent.date)
        tseEventGateway.send(energyMarketValueEvent, this.class.canonicalName)


        energyMarketValueEvent = new EnergyMarketValueEvent()
        energyMarketValueEvent.marketIdentifier = 2
        rows = html?.'**'?.find { it?.@id == 'PureData2' }?.'**'?.findAll { it?.name() == 'TR' }
        rows?.each { row ->
            if (energyMarketValueEvent.tradeCount == null && row.text().toString().startsWith('تعداد معاملات'))
                energyMarketValueEvent.tradeCount = parseInteger(row.children()[1].text())
            if (energyMarketValueEvent.tradeValue == null && row.text().toString().startsWith('ارزش معاملات'))
                energyMarketValueEvent.tradeValue = parseDouble(row.children()[1].text())
            if (energyMarketValueEvent.extraTradeValue == null && row.text().toString().startsWith('ارزش معاملات مازاد'))
                energyMarketValueEvent.extraTradeValue = parseDouble(row.children()[1].text())
            if (energyMarketValueEvent.extraTradeCount == null && row.text().toString().startsWith('تعداد معاملات مازاد'))
                energyMarketValueEvent.extraTradeCount = parseDouble(row.children()[1].text())

        }
        energyMarketValueEvent.date = new Date().clearTime()
        energyMarketValueEvent.data = EnergyMarketValue.findByMarketIdentifierAndDate(energyMarketValueEvent.marketIdentifier, energyMarketValueEvent.date)
        tseEventGateway.send(energyMarketValueEvent, this.class.canonicalName)
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
