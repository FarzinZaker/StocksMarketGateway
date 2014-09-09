package stocks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured([RoleHelper.ROLE_ADMIN])
class CityController {
    def springSecurityService

    def build() {
        [city: params.id ? City.get(params.id) : null, provinceList: Province.findAllByDeleted(false)]
    }

    def save() {
        def city
        if (params.id) {
            city = City.get(params.id)
            city.properties = params
        } else {
            city = new City(params)
        }
        if (params.provinceId)
            city.province = Province.get(params.provinceId)
        else
            city.province = null
        if (city.save())
            redirect(action: 'list')
        else if (city.save())  //retry
            redirect(action: 'list')
    }

    def list() {
        [provinceList: Province.findAllByDeleted(false)]
    }

    def jsonList() {

        def province = Province.get(params.provinceId)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list = City.findAllByProvinceAndDeleted(province, false, parameters)
        value.total = City.countByProvinceAndDeleted(province, false)

        value.data = list.collect {
            [
                    id      : it.id,
                    name    : it.name,
                    province: it.province?.name
            ]
        }

        render value as JSON
    }

    def delete() {

        def city = City.get(params.id)
        city.deleted = true
        render(city.save() ? '1' : '0')
    }
}
