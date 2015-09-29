package stocks

import grails.converters.JSON

class GlobalSettingController {

    def list() {}

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]

        def list = GlobalSetting.findAll(parameters)
        value.total = GlobalSetting.count()

        value.data = list.collect {
            [
                    id   : it.id,
                    name : message(code: "globalSetting.${it.name}"),
                    value: it.value
            ]
        }

        render value as JSON
    }

    def save(){

        JSON.parse(params.models as String).each {
            def setting = GlobalSetting.get(it.id as Long)
            setting.value = it.value?.toString()?.trim()
            setting.save(flush: true)
        }

        render params.models
    }
}
