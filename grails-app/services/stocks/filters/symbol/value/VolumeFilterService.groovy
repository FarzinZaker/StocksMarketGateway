package stocks.filters.symbol.value

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators

import java.text.NumberFormat

class VolumeFilterService implements IncludeFilterService {

    def lowLevelDataService

    @Override
    Boolean getEnabled() {
        true
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
    def getValueModel(User user, String operator) {
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
        else
            [NumberFormat.instance.format((value.first().first() as Double) * 100), NumberFormat.instance.format((value.first().last() as Double))]
    }

    List<Long> getIncludeList(String parameter, String operator, Object value) {
        def idList = []
        def parsedValue = JSON.parse(value?.toString()).first()

        switch (operator) {
            case Operators.GREATER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('volume_greater_than', [
                        value: parsedValue as double
                ])
                break
            case Operators.LESS_THAN:
                idList = lowLevelDataService.executeStoredProcedure('volume_less_than', [
                        value: parsedValue as double
                ])
                break
            case Operators.INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('volume_positive_change_compare_to_average_greater_than', [
                        percent: parsedValue.first() as double,
                        days   : parsedValue.last() as Integer
                ])
                break
            case Operators.DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('volume_negative_change_compare_to_average_greater_than', [
                        percent: parsedValue.first() as double,
                        days   : parsedValue.last() as Integer
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
