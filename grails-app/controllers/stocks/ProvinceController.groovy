package stocks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured([RoleHelper.ROLE_ADMIN])
class ProvinceController {

    def build() {
        [province: params.id ? Province.get(params.id) : null]
    }

    def save() {
        def province
        if (params.id) {
            province = Province.get(params.id)
            province.properties = params
        } else {
            province = new Province(params)
        }
        if (province.save())
            redirect(action: 'list')
        else if (province.save())  //retry
            redirect(action: 'list')
    }

    def list() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list = Province.findAllByDeleted(false, parameters)
            value.total = Province.countByDeleted(false)

        value.data = list.collect {
            [
                    id         : it.id,
                    name       : it.name
            ]
        }

        render value as JSON
    }

    def delete() {

        def province = Province.get(params.id)
        province.deleted = true
        render(province.save() ? '1' : '0')
    }
}
