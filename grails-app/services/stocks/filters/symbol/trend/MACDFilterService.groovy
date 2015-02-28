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

import java.text.NumberFormat

class MACDFilterService implements IncludeFilterService {

    def lowLevelDataService
    def messageSource
    SessionLocaleResolver localeResolver

    @Override
    Boolean getEnabled() {
        true
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
    def getValueModel(User user, String operator) {
        [value: 0]
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        if (value.last() == 'constant_switch')
            [NumberFormat.instance.format(value.first() as Double)]
        else
            [messageSource.getMessage('macd.signal', null, localeResolver.defaultLocale)]
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value) {
        def idList = []
        if (value.last() == 'constant_switch') {
            def targetValue = value.first()
            switch (operator) {
                case Operators.UPPER_THAN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_value_filter', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double
                    ])
                    break
                case Operators.LOWER_THAN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_lower_than_value_filter', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double
                    ])
                    break
                case Operators.CROSSING_TO_UP:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_value_filter', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double
                    ])
                    break
                case Operators.CROSSING_TO_DOWN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_cross_down_value_filter', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetValue    : targetValue as Double
                    ])
                    break
            }
        } else
            switch (operator) {
                case Operators.UPPER_THAN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                            sourceClass    : MACD.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter
                    ])
                    break
                case Operators.LOWER_THAN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                            sourceClass    : MACDSignal.class.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACD.class.canonicalName,
                            targetParameter: parameter
                    ])
                    break
                case Operators.CROSSING_TO_UP:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_indicator_filter', [
                            sourceClass    : MACD.class.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACDSignal.class.canonicalName,
                            targetParameter: parameter
                    ])
                    break
                case Operators.CROSSING_TO_DOWN:
                    idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_indicator_filter', [
                            sourceClass    : MACDSignal.class.canonicalName,
                            sourceParameter: parameter,
                            targetClass    : MACD.class.canonicalName,
                            targetParameter: parameter
                    ])
                    break
            }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
