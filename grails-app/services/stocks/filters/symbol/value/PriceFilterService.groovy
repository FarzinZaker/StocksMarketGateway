package stocks.filters.symbol.value

import grails.converters.JSON
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol

import java.text.NumberFormat

class PriceFilterService implements IncludeFilterService {

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
                Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN,
                Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN,
                Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN,
                Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN
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
            case Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return 'percent'
            case Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return 'percent'
            case Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return 'percent'
            case Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return 'percent'
            default:
                return 'value'
        }
    }

    @Override
    def getValueModel(User user, String operator, String place) {
        switch (operator) {
            case Operators.GREATER_THAN:
                return [value: 1000]
            case Operators.LESS_THAN:
                return [value: 1000]
            case Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return [value: 0.05]
            case Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return [value: 0.05]
            case Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return [value: 0.05]
            case Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return [value: 0.05]
            default:
                return [value: 1000]
        }
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        if (operator in [Operators.GREATER_THAN, Operators.LESS_THAN])
            [NumberFormat.instance.format(value.first() as Double)]
        else
            [NumberFormat.instance.format((value.first() as Double) * 100)]
    }

    @Override
    Boolean check(String parameter, String operator, value, Date date, List dailyTrades, List indicators) {
        def parsedValue = JSON.parse(value?.toString()).find() as Double

        switch (operator) {
            case Operators.GREATER_THAN:
                return indicatorCompareService.priceUpperThanValue(parsedValue, date, dailyTrades, indicators)
            case Operators.LESS_THAN:
                return indicatorCompareService.priceLowerThanValue(parsedValue, date, dailyTrades, indicators)
            case Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return indicatorCompareService.pricePositiveChangeCompareToPreviousDayGreaterThan(parsedValue, date, dailyTrades, indicators)
            case Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                return indicatorCompareService.priceNegativeChangeCompareToPreviousDayGreaterThan(parsedValue, date, dailyTrades, indicators)
            case Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return indicatorCompareService.pricePositiveChangeCompareToFirstPriceGreaterThan(parsedValue, date, dailyTrades, indicators)
            case Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                return indicatorCompareService.priceNegativeChangeCompareToFirstPriceGreaterThan(parsedValue, date, dailyTrades, indicators)
        }
        false
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {
        def idList = []
        def parsedValue = JSON.parse(value?.toString()).find()

        switch (operator) {
            case Operators.GREATER_THAN:
                idList = lowLevelDataService.executeFunction('PRC_UPPER_THAN_VAL_FILTER', [
                        value: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.LESS_THAN:
                idList = lowLevelDataService.executeFunction('PRC_LOWER_THAN_VAL_FILTER', [
                        value: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                idList = lowLevelDataService.executeFunction('PRC_PCPD_UPPER_THAN_VAL_FILTER', [
                        percent: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN:
                idList = lowLevelDataService.executeFunction('PRC_NCPD_UPPER_THAN_VAL_FILTER', [
                        percent: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                idList = lowLevelDataService.executeFunction('PRC_PCFP_UPPER_THAN_VAL_FILTER', [
                        percent: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN:
                idList = lowLevelDataService.executeFunction('PRC_NCFP_UPPER_THAN_VAL_FILTER', [
                        percent: parsedValue as double,
                        adjustmentType : adjustmentType
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
