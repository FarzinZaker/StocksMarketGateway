package stocks.filters.symbol.trend

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.indicators.symbol.trend.MACD
import stocks.indicators.symbol.trend.MACDSignal
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol

import java.text.NumberFormat

class MACDFilterService implements IncludeFilterService {

    def lowLevelDataService
    def messageSource
    def indicatorCompareService

    @Override
    Map getEnabled() {
        [
                screener: true,
                backTest: true
        ]
    }

    @Override
    ArrayList<String> getOperators() {

        [
                Operators.UPPER_THAN,
                Operators.LOWER_THAN,
                Operators.CROSSING_TO_UP,
                Operators.CROSSING_TO_DOWN
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        'parameters'
    }

    @Override
    def getFilterParamsModel() {
        [value: [12, 26, 9].join(',')]
    }

    @Override
    String getValueTemplate(String operator) {
        'value'
    }

    @Override
    def getValueModel(User user, String operator, String place) {
        [value: 0]
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        if (value?.contains( 'constant_switch'))
            [NumberFormat.instance.format(value.first() as Double)]
        else
            [messageSource.getMessage('macd.signal', null, Locale.ENGLISH)]
    }

    @Override
    Boolean check(String parameter, String operator, value, Date date, List dailyTrades, List indicators) {
        if (value.last() == 'constant_switch') {
            def targetValue = value.first() as Double
            switch (operator) {
                case Operators.UPPER_THAN:
                    return indicatorCompareService.indicatorUpperThanValue(MACD, parameter, targetValue, date, dailyTrades, indicators)
                case Operators.LOWER_THAN:
                    return indicatorCompareService.indicatorLowerThanValue(MACD, parameter, targetValue, date, dailyTrades, indicators)
                case Operators.CROSSING_TO_UP:
                    return indicatorCompareService.indicatorCrossUpValue(MACD, parameter, targetValue, date, dailyTrades, indicators)
                case Operators.CROSSING_TO_DOWN:
                    return indicatorCompareService.indicatorCrossDownValue(MACD, parameter, targetValue, date, dailyTrades, indicators)
            }
        } else
            switch (operator) {
                case Operators.UPPER_THAN:
                    return indicatorCompareService.indicatorUpperThanIndicator(MACD, parameter, MACDSignal, parameter, date, dailyTrades, indicators)
                case Operators.LOWER_THAN:
                    return indicatorCompareService.indicatorLowerThanIndicator(MACD, parameter, MACDSignal, parameter, date, dailyTrades, indicators)
                case Operators.CROSSING_TO_UP:
                    return indicatorCompareService.indicatorCrossUpIndicator(MACD, parameter, MACDSignal, parameter, date, dailyTrades, indicators)
                case Operators.CROSSING_TO_DOWN:
                    return indicatorCompareService.indicatorCrossDownIndicator(MACD, parameter, MACDSignal, parameter, date, dailyTrades, indicators)
            }

        false
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {
        def idList = []
        if (value.contains( 'constant_switch')) {
            def targetValue = value?.sort()?.first()
            switch (operator) {
                case Operators.UPPER_THAN:
                    idList = lowLevelDataService.executeFunction('IND_UPPER_THAN_VAL_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.LOWER_THAN:
                    idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_VAL_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.CROSSING_TO_UP:
                    idList = lowLevelDataService.executeFunction('IND_CROSS_UP_VAL_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.CROSSING_TO_DOWN:
                    idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_VAL_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double,
                            adjustmentType : adjustmentType
                    ])
                    break
            }
        } else
            switch (operator) {
                case Operators.UPPER_THAN:
                    idList = lowLevelDataService.executeFunction('IND_UPPER_THAN_IND_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.LOWER_THAN:
                    idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_IND_FILTER', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.CROSSING_TO_UP:
                    idList = lowLevelDataService.executeFunction('IND_CROSS_UP_IND_FILTER', [
                            sourceClass    : MACD.class.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter,
                            adjustmentType : adjustmentType
                    ])
                    break
                case Operators.CROSSING_TO_DOWN:
                    idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_IND_FILTER', [
                            sourceClass    : MACD.class.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter,
                            adjustmentType : adjustmentType
                    ])
                    break
            }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
