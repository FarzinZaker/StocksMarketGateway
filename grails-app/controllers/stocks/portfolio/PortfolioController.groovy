package stocks.portfolio

import grails.converters.JSON

class PortfolioController {
    def build() {
        [portfolio: params.id ? Portfolio.get(params.id) : null]
    }

    def save() {
        def portfolio
        if (params.id) {
            portfolio = Portfolio.get(params.id)
            portfolio.properties = params
        } else {
            portfolio = new Portfolio(params)
        }
        if (portfolio.save())
            redirect(action: 'list')
        else if (portfolio.save())  //retry
            redirect(action: 'list')
    }

    def list() {

    }

    def view() {

    }

    def jsonPortfolioView() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]

        def list = Portfolio.findAllByDeleted(false, parameters)
        value.total = Portfolio.countByDeleted(false)

        value.data = list.collect {
            [
                    id         : it.id,
                    name       : it.name
            ]
        }

        render value as JSON
    }

    def delete() {

        def portfolio = Portfolio.get(params.id)
        portfolio.deleted = true
        render(portfolio.save() ? '1' : '0')
    }
}
