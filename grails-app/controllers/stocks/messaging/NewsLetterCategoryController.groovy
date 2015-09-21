package stocks.messaging

import grails.converters.JSON

class NewsLetterCategoryController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def build() {
        def newsLetterCategory
        if (params.id)
            newsLetterCategory = NewsLetterCategory.get(params.id)
        else
            newsLetterCategory = new NewsLetterCategory()
        [newsLetterCategory: newsLetterCategory]
    }

    def list() {
    }

    def save() {
        def newsLetterCategory
        if (params.id) {
            newsLetterCategory = NewsLetterCategory.get(params.id)
        } else
            newsLetterCategory = new NewsLetterCategory()
        newsLetterCategory.name = params.name
        newsLetterCategory.save()
        redirect(action: 'list')
    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "id", order: params["sort[0][dir]"] ?: "desc"]

        def list = NewsLetterCategory.findAllByDeleted(false, parameters)
        value.total = NewsLetterCategory.countByDeleted(false)

        value.data = list.collect {
            [
                    id  : it.id,
                    name: it.name,
            ]
        }

        render value as JSON
    }

    def delete() {
        def item = NewsLetterCategory.get(params.id)
        item.deleted = true
        render(item.save() ? '1' : '0')
    }
}
