package stocks.excel

import fi.joensuu.joyds1.calendar.JalaliCalendar
import jxl.Sheet
import jxl.Workbook
import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent

class FutureContractExcelService {

    def bulkDataGateway

    def grailsApplication

    def importList(InputStream inputFileStream) {

        //read workbook
        Workbook book = Workbook.getWorkbook(inputFileStream)
        Sheet sheet = book.getSheet(0)
        for (def i = 1; i < sheet.rows; i++) {
            try {
                def coinFutureEvent = new CoinFutureEvent()
                coinFutureEvent.contractCode = sheet.getCell(1, i).contents?.trim()?.toUpperCase()
                coinFutureEvent.lastTradingDate = parseDate(sheet.getCell(18, i).contents)
                coinFutureEvent.contractDescription = sheet.getCell(2, i).contents
                coinFutureEvent.contractCurrencyPersianDescription = 'ریال'
                coinFutureEvent.contractCurrencyDescription = 'Rials'
                coinFutureEvent.contractCurrencyDecimalPlaces = 0
                coinFutureEvent.lastSettlementPrice = sheet.getCell(15, i).contents.replace(',', '').toDouble()
                coinFutureEvent.lastSettlementPriceDate = parseDate(sheet.getCell(17, i).contents)
                coinFutureEvent.yesterdayOpenInterests = sheet.getCell(9, i).contents.replace(',', '').toInteger() - sheet.getCell(10, i).contents.replace(',', '').toInteger()
                coinFutureEvent.ordersDateTime = parseDate(sheet.getCell(17, i).contents)
                coinFutureEvent.lastTradedPriceTime = parseDate(sheet.getCell(17, i).contents)
                coinFutureEvent.firstTradedPrice = sheet.getCell(8, i).contents.replace(',', '').toDouble()
                coinFutureEvent.lastTradedPrice = sheet.getCell(7, i).contents.replace(',', '').toDouble()
                coinFutureEvent.highTradedPrice = sheet.getCell(5, i).contents.replace(',', '').toDouble()
                coinFutureEvent.lowTradedPrice = sheet.getCell(6, i).contents.replace(',', '').toDouble()
                coinFutureEvent.openingPrice = coinFutureEvent.firstTradedPrice
                coinFutureEvent.closingPrice = coinFutureEvent.lastTradedPrice
                coinFutureEvent.tradesCount = sheet.getCell(23, i).contents.replace(',', '').toInteger() + sheet.getCell(24, i).contents.replace(',', '').toInteger() + sheet.getCell(25, i).contents.replace(',', '').toInteger() + sheet.getCell(26, i).contents.replace(',', '').toInteger()
                coinFutureEvent.tradesVolume = sheet.getCell(3, i).contents.replace(',', '').toInteger()
                coinFutureEvent.tradesValue = sheet.getCell(4, i).contents.replace(',', '').toDouble()
                coinFutureEvent.tradesValueCurrencyPersianDescription = 'هزار ریال'
                coinFutureEvent.tradesValueCurrencyDescription = 'Thousand Rials'
                coinFutureEvent.openInterests = sheet.getCell(9, i).contents.replace(',', '').toInteger()
                coinFutureEvent.lastUpdate = parseDate(sheet.getCell(17, i).contents)
                coinFutureEvent.expired = parseDate(sheet.getCell(18, i).contents) < new Date()
                coinFutureEvent.creationDate = parseDate(sheet.getCell(17, i).contents)
                coinFutureEvent.data = find(coinFutureEvent)

                def date = coinFutureEvent.lastTradedPriceTime
                date = date.clearTime()
                coinFutureEvent.dailySnapshot = date
                def calendar = Calendar.getInstance() as GregorianCalendar
                calendar.setTime(date)
                def jc = new JalaliCalendar(calendar)
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
                    coinFutureEvent.weeklySnapshot = date
                if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                    coinFutureEvent.monthlySnapshot = date

                if (coinFutureEvent.data) {
                    if (update(coinFutureEvent))
                        println coinFutureEvent.save(flush: true)
                } else {
                    coinFutureEvent.data = create(coinFutureEvent)
                    println coinFutureEvent.save(flush: true)
                }

                println "${i}/${sheet.rows}"
            }
            catch (ignored) {
                println ignored.message
                println ignored.stackTrace
            }
        }
    }

    private static CoinFuture find(CoinFutureEvent object) {
        CoinFuture.withTransaction {
            CoinFuture.findByContractCode(object.contractCode)
        }
    }

    Boolean update(CoinFutureEvent event) {
        def future = CoinFuture.get(event.data.id)
        def result = future.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        future.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        future.save(flush: true)
        result
    }

    CoinFuture create(CoinFutureEvent event) {
        def data = new CoinFuture(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        data.save(flush: true)
        data
    }

    //utils
    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
