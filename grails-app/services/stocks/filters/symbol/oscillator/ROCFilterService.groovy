package stocks.filters.symbol.oscillator

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.indicators.symbol.oscillator.ROC
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol

import java.text.NumberFormat

class ROCFilterService implements IncludeFilterService {

    def lowLevelDataService
    def indicatorCompareService

    @Override
    Map getEnabled() {
        [
                screener: false,
                backTest: false
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
        [value: 10]
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
        [NumberFormat.instance.format(value.first() as Double)]
    }

    @Override
    Boolean check(String parameter, String operator, value, Date date, List dailyTrades, List indicators) {
        def targetValue = value.first() as Double

        switch (operator) {
            case Operators.UPPER_THAN:
                return indicatorCompareService.indicatorUpperThanValue(ROC, parameter, targetValue, date, dailyTrades, indicators)
            case Operators.LOWER_THAN:
                return indicatorCompareService.indicatorLowerThanValue(ROC, parameter, targetValue, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_UP:
                return indicatorCompareService.indicatorCrossUpValue(ROC, parameter, targetValue, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_DOWN:
                return indicatorCompareService.indicatorCrossDownValue(ROC, parameter, targetValue, date, dailyTrades, indicators)
        }
        false
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {
        def idList = []
        def targetValue = value.first()

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeFunction('IND_UPPER_THAN_VAL_FILTER', [
                        sourceClass    : ROC.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_VAL_FILTER', [
                        sourceClass    : ROC.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeFunction('IND_CROSS_UP_VAL_FILTER', [
                        sourceClass    : ROC.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_VAL_FILTER', [
                        sourceClass    : ROC.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double,
                        adjustmentType : adjustmentType
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
