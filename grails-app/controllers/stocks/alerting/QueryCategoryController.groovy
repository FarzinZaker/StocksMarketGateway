package stocks.alerting

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User

@Secured([RoleHelper.ROLE_ADMIN])
class QueryCategoryController {

    def springSecurityService

    def build() {
        [category: params.id ? QueryCategory.get(params.id) : null]
    }

    def save() {
        def category
        if (params.id) {
            category = QueryCategory.get(params.id)
        } else {
            category = new QueryCategory()
            category.owner = springSecurityService.currentUser as User ?: User.findByUsername('admin')
        }
        category.name = params.name
        category.description = params.description
        category.image = stocks.Image.get(params.image?.id)
        if (params.parent != '0')
            category.parent = QueryCategory.get(params.parent)
        else
            category.parent = null
        if (category.save())
            redirect(action: 'list')
        else if (category.save())  //retry
            redirect(action: 'list')
    }

    def list() {

    }

    def jsonList() {

        def parent = QueryCategory.get(params.parent)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list
        if (parent) {
            list = QueryCategory.findAllByParentAndDeleted(parent, false, parameters)
            value.total = QueryCategory.countByParentAndDeleted(parent, false)
        } else {
            list = QueryCategory.findAllByParentIsNullAndDeleted(false, parameters)
            value.total = QueryCategory.countByParentIsNullAndDeleted(false)
        }

        value.data = list.collect {
            [
                    id         : it.id,
                    name       : it.name,
                    description: it.description?.replaceAll("<(.|\n)*?>", ''),
                    image      : it.image?.id,
            ]
        }

        render value as JSON
    }

    def delete() {

        def queryCategory = QueryCategory.get(params.id)
        queryCategory.deleted = true
        render(queryCategory.save() ? '1' : '0')
    }
}
