package stocks.alerting

import grails.converters.JSON

class ParameterController {

    def parameterSuggestedValueList() {

        def parameter = Parameter.get(params.id)
        def value = [:]
        value.data = ParameterSuggestedValue.findAllByParameter(parameter, [max: 1000, offset: 0]).collect {
            [
                    id   : it.id,
                    title: it.title
            ]
        }
        value.total = ParameterSuggestedValue.countByParameter(parameter)
        render value as JSON
    }

    def parameterSuggestedValueCreate() {
        def parameter = Parameter.get(params.id)
        ParameterSuggestedValue item
        JSON.parse(params.models).each {
            item = new ParameterSuggestedValue(it + [parameter: parameter]).save(flush: true)
            new ParameterSuggestedValueVariation(it + [suggestedValue: item]).save(flush: true)
        }
        def value = [:]
        value.data = [item].collect {
            [
                    id   : it.id,
                    title: it.title
            ]
        }
        render value as JSON
    }

    def parameterSuggestedValueUpdate() {
        def id = 0
        JSON.parse(params.models).each {
            def item = ParameterSuggestedValue.get(it.id)
            item.title = it.title
            item.save(flush: true)
            id = item.id
        }
        render id
    }

    def parameterSuggestedValueDelete() {
        JSON.parse(params.models).each {
            def item = ParameterSuggestedValue.get(it.id)
            item.delete(flush: true)
        }
        render 0
    }

    def parameterValueVariations() {
        def suggestedValue = ParameterSuggestedValue.get(params.id)
        render template: '/query/parameterValueVariations', model: [
                suggestedValue: suggestedValue,
                variations    : ParameterSuggestedValueVariation.createCriteria().list {
                    eq('suggestedValue', suggestedValue)
                }
        ]
    }

    def parameterSuggestedValueVariationList() {

        def parameterSuggestedValue = ParameterSuggestedValue.get(params.id)
        def value = [:]
        value.data = ParameterSuggestedValueVariation.createCriteria().list({
            eq('suggestedValue', parameterSuggestedValue)
//            maxResults(10)
        }).collect {
            [
                    id   : it.id,
                    title: it.title
            ]
        }
        value.total = ParameterSuggestedValueVariation.createCriteria().count {
            eq('suggestedValue', parameterSuggestedValue)
        }
        render value as JSON
    }

    def parameterSuggestedValueVariationCreate() {
        ParameterSuggestedValueVariation item
        def parameterSuggestedValue = ParameterSuggestedValue.get(params.id)
        JSON.parse(params.models).each {
            item = new ParameterSuggestedValueVariation(it + [suggestedValue: parameterSuggestedValue]).save(flush: true)
        }
        def value = [:]
        value.data = [item].collect {
            [
                    id   : it.id,
                    title: it.title
            ]
        }
        render value as JSON
    }

    def parameterSuggestedValueVariationUpdate() {
        def id = 0
        JSON.parse(params.models).each {
            def item = ParameterSuggestedValueVariation.get(it.id)
            item.title = it.title
            item.save(flush: true)
            id = item.id
        }
        render id
    }

    def parameterSuggestedValueVariationDelete() {
        JSON.parse(params.models).each {
            def item = ParameterSuggestedValueVariation.get(it.id)
            item.delete(flush: true)
        }
        render 0
    }
}
