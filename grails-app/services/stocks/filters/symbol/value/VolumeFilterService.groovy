package stocks.filters.symbol.value

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol

import java.text.NumberFormat

class VolumeFilterService implements IncludeFilterService {

    def lowLevelDataService
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
                Operators.GREATER_THAN,
                Operators.LESS_THAN,
                Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN,
                Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        return null
    }

    @Override
    def getFilterParamsModel() {
        return null
    }

    @Override
    String getValueTemplate(String operator) {
        switch (operator) {
            case Operators.GREATER_THAN:
                return 'value'
            case Operators.LESS_THAN:
                return 'value'
            case Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                return 'percent'
            case Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                return 'percent'
            default:
                return 'value'
        }
    }

    @Override
    def getValueModel(User user, String operator, String place) {
        switch (operator) {
            case Operators.GREATER_THAN:
                return [value: 100000]
            case Operators.LESS_THAN:
                return [value: 100000]
            case Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                return [percent: 0.1, days: 30]
            case Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                return [percent: 0.1, days: 30]
            default:
                return [value: 100000]
        }
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        if (operator in [Operators.GREATER_THAN, Operators.LESS_THAN])
            [NumberFormat.instance.format(value.first() as Double)]
        else {
            def valArray = value.find { !(it instanceof String) }
            [NumberFormat.instance.format((valArray.first() as Double) * 100), NumberFormat.instance.format((valArray.last() as Double))]
        }
    }

    @Override
    Boolean check(String parameter, String operator, value, Date date, List dailyTrades, List indicators) {

        switch (operator) {
            case Operators.GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).first()
                return indicatorCompareService.volumeUpperThanValue(parsedValue as Double, date, dailyTrades, indicators)
            case Operators.LESS_THAN:
                def parsedValue = JSON.parse(value?.toString()).first()
                return indicatorCompareService.volumeLowerThanValue(parsedValue as Double, date, dailyTrades, indicators)
            case Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).find { !(it instanceof String) }
                return indicatorCompareService.volumePositiveChangeCompareToAverageGreaterThan(parsedValue.first() as double, parsedValue.last() as Integer, date, dailyTrades, indicators)
            case Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).find { !(it instanceof String) }
                return indicatorCompareService.volumeNegativeChangeCompareToAverageGreaterThan(parsedValue.first() as double, parsedValue.last() as Integer, date, dailyTrades, indicators)
        }
    }

    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {
        def idList = []

        switch (operator) {
            case Operators.GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).first()
                idList = lowLevelDataService.executeFunction('VOL_UPPER_THAN_VAL_FILTER', [
                        value         : parsedValue as double,
                        adjustmentType: adjustmentType
                ])
                break
            case Operators.LESS_THAN:
                def parsedValue = JSON.parse(value?.toString()).first()
                idList = lowLevelDataService.executeFunction('VOL_LOWER_THAN_VAL_FILTER', [
                        value         : parsedValue as double,
                        adjustmentType: adjustmentType
                ])
                break
            case Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).find { !(it instanceof String) }
                idList = lowLevelDataService.executeFunction('VOL_PCA_UPPER_THAN_VAL_FILTER', [
                        percent       : parsedValue.first() as double,
                        days          : parsedValue.last() as Integer,
                        adjustmentType: adjustmentType
                ])
                break
            case Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                def parsedValue = JSON.parse(value?.toString()).find { !(it instanceof String) }
                idList = lowLevelDataService.executeFunction('VOL_NCA_UPPER_THAN_VAL_FILTER', [
                        percent       : parsedValue.first() as double,
                        days          : parsedValue.last() as Integer,
                        adjustmentType: adjustmentType
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
