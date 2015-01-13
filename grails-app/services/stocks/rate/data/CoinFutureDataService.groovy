package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.FarsiNormalizationFilter
import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent
import stocks.rate.ws.Fut_Live_Loc_ServiceLocator
import stocks.rate.ws.Fut_Live_Loc_ServiceSoap_BindingStub
import stocks.rate.ws.Fut_Live_Loc_ServiceSoap_PortType
import stocks.rate.ws.Future_Market_Data

class CoinFutureDataService {
    static transactional = false
    def rateEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]

    private Fut_Live_Loc_ServiceSoap_PortType service

    private Fut_Live_Loc_ServiceSoap_PortType getService() {
        if (!service) {
            Fut_Live_Loc_ServiceLocator fooLocator = new Fut_Live_Loc_ServiceLocator();
            fooLocator.getEngine().setOption("sendMultiRefs", false);
            service = fooLocator.getFut_Live_Loc_ServiceSoap();

            def binding = (Fut_Live_Loc_ServiceSoap_BindingStub) service;
            binding.setTimeout(120000);
        }
        service
    }

    void importData() {
        parseData()
    }

    private static CoinFuture find(CoinFutureEvent object) {
        CoinFuture.withTransaction {
            CoinFuture.findByContractCode(object.contractCode)
        }
    }

    private void parseData() {

        try {
            getList().each { Future_Market_Data contract ->

                def coinFutureEvent = new CoinFutureEvent()
                coinFutureEvent.contractCode = contract.contractCode
                coinFutureEvent.lastTradingDate = getFullDateTime(contract.persianLastTradingDate)
                coinFutureEvent.contractDescription = contract.contractDescription
                coinFutureEvent.contractSize = contract.contractSize
                coinFutureEvent.contractSizeUnitPersianDescription = contract.contractSizeUnitFaDesc
                coinFutureEvent.contractSizeUnitDescription = contract.contractSizeUnitEnDesc
                coinFutureEvent.contractCurrencyPersianDescription = contract.contractCurrencyFaDesc
                coinFutureEvent.contractCurrencyDescription = contract.contractCurrencyEnDesc
                coinFutureEvent.contractCurrencyDecimalPlaces = contract.contractCurrencyDecimalPlaces
                coinFutureEvent.lastSettlementPrice = contract.lastSettlementPrice
                coinFutureEvent.lastSettlementPriceDate = getFullDateTime(contract.persianLastSettlementPriceDate)
                coinFutureEvent.yesterdayOpenInterests = contract.yesterdayOpenInterests
                coinFutureEvent.bidTotalVolume = contract.bidTotalVolume
                coinFutureEvent.bidVolume1 = contract.bidVolume1
                coinFutureEvent.bidPrice1 = contract.bidPrice1
                coinFutureEvent.bidVolume2 = contract.bidVolume2
                coinFutureEvent.bidPrice2 = contract.bidPrice2
                coinFutureEvent.bidVolume3 = contract.bidVolume3
                coinFutureEvent.bidPrice3 = contract.bidPrice3
                coinFutureEvent.bidVolume4 = contract.bidVolume4
                coinFutureEvent.bidPrice4 = contract.bidPrice4
                coinFutureEvent.bidVolume5 = contract.bidVolume5
                coinFutureEvent.bidPrice5 = contract.bidPrice5
                coinFutureEvent.askTotalVolume = contract.askTotalVolume
                coinFutureEvent.askVolume1 = contract.askVolume1
                coinFutureEvent.askPrice1 = contract.askPrice1
                coinFutureEvent.askVolume2 = contract.askVolume2
                coinFutureEvent.askPrice2 = contract.askPrice2
                coinFutureEvent.askVolume3 = contract.askVolume3
                coinFutureEvent.askPrice3 = contract.askPrice3
                coinFutureEvent.askVolume4 = contract.askVolume4
                coinFutureEvent.askPrice4 = contract.askPrice4
                coinFutureEvent.askVolume5 = contract.askVolume5
                coinFutureEvent.askPrice5 = contract.askPrice5
                coinFutureEvent.ordersDateTime = getFullDateTime(contract.persianOrdersDateTime)
                coinFutureEvent.firstTradedPrice = contract.firstTradedPrice
                coinFutureEvent.firstTradedPriceTime = getFullDateTime(contract.persianFirstTradedPriceTime)
                coinFutureEvent.firstTradedPriceChanges = contract.c_FirstTradedPriceChanges
                coinFutureEvent.firstTradedPriceChangesPercent = contract.c_FirstTradedPriceChangesPercent
                coinFutureEvent.lastTradedPrice = contract.lastTradedPrice
                coinFutureEvent.lastTradedPriceTime = getFullDateTime(contract.persianLastTradedPriceTime)
                coinFutureEvent.lastTradedPriceChanges = contract.c_LastTradedPriceChanges
                coinFutureEvent.lastTradedPriceChangesPercent = contract.c_LastTradedPriceChangesPercent
                coinFutureEvent.highTradedPrice = contract.highTradedPrice
                coinFutureEvent.highTradedPriceChanges = contract.c_HighTradedPriceChanges
                coinFutureEvent.highTradedPriceChangesPercent = contract.c_HighTradedPriceChanges
                coinFutureEvent.lowTradedPrice = contract.lowTradedPrice
                coinFutureEvent.lowTradedPriceChanges = contract.c_LowTradedPriceChanges
                coinFutureEvent.lowTradedPriceChangesPercent = contract.c_LowTradedPriceChangesPercent
                coinFutureEvent.averageTradedPrice = contract.averageTradedPrice
                coinFutureEvent.openingPrice = contract.openingPrice
                coinFutureEvent.closingPrice = contract.closingPrice
                coinFutureEvent.tradesCount = contract.tradesCount
                coinFutureEvent.tradesVolume = contract.tradesVolume
                coinFutureEvent.tradesValue = contract.tradesValue
                coinFutureEvent.tradesValueCurrencyPersianDescription = contract.tradesValueCurrencyFaDesc
                coinFutureEvent.tradesValueCurrencyDescription = contract.tradesValueCurrencyEnDesc
                coinFutureEvent.openInterests = contract.openInterests
                coinFutureEvent.openInterestsChanges = contract.c_OpenInterestsChanges
                coinFutureEvent.openInterestsChangesPercent = contract.openInterestsChangesPercent
                coinFutureEvent.lastUpdate = getFullDateTime(contract.persianLastUpdate)
                coinFutureEvent.expired = contract.expired

                coinFutureEvent.data = find(coinFutureEvent)
                rateEventGateway.send(coinFutureEvent)
            }
            logState([status: 'successful'])
        }
        catch (ex) {
            ex.printStackTrace()
            logState([status: 'failed', message: ex.message, stackTrace: ex.stackTrace])
        }
    }

    private def getList() {
        def contractsList = getService().getContractsList().split(',')
        contractsList.collect { contractCode ->
            def contract = null
            try {
                getService().getContractInfo(contractCode)
            } catch (ignored) {
            }
            contract
        }.findAll { it }
    }

    private static Date getFullDateTime(String dateStr) {

        if (!dateStr || dateStr == '')
            return null

        def months = [
                FarsiNormalizationFilter.apply('فروردین'),
                FarsiNormalizationFilter.apply('اردیبهشت'),
                FarsiNormalizationFilter.apply('خرداد'),
                FarsiNormalizationFilter.apply('تیر'),
                FarsiNormalizationFilter.apply('مرداد'),
                FarsiNormalizationFilter.apply('شهریور'),
                FarsiNormalizationFilter.apply('مهر'),
                FarsiNormalizationFilter.apply('آبان'),
                FarsiNormalizationFilter.apply('آذر'),
                FarsiNormalizationFilter.apply('دی'),
                FarsiNormalizationFilter.apply('بهمن'),
                FarsiNormalizationFilter.apply('اسفند')
        ]
        def dateParts = dateStr.split(' ')
        def startIndex = 0
        while (dateParts[++startIndex].contains('شنبه'));
        ArrayList<Integer> dateArray = []
        dateArray[0] = dateParts[startIndex + 2] as Integer
        def normalizedMonth = FarsiNormalizationFilter.apply(dateParts[startIndex + 1])
        for (def i = 0; i < months.size(); i++)
            if (normalizedMonth == months[i])
                dateArray[1] = i + 1
        dateArray[2] = dateParts[startIndex] as Integer
        dateArray[3] = dateArray[4] = dateArray[5] = 0
        if (dateParts.size() > startIndex + 3) {
            def timeParts = dateParts[startIndex + 3].split(':')
            dateArray[3] = timeParts[0] as Integer
            dateArray[4] = timeParts[1] as Integer
            dateArray[5] = timeParts[2] as Integer
        }
        JalaliCalendar jc = new JalaliCalendar(dateArray[0], dateArray[1], dateArray[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        cal.set(Calendar.HOUR_OF_DAY, dateArray[3])
        cal.set(Calendar.MINUTE, dateArray[4])
        cal.set(Calendar.SECOND, dateArray[5])
        cal.time
    }
}
