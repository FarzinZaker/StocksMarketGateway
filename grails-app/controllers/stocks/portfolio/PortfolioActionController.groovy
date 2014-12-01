package stocks.portfolio

import grails.converters.JSON

class PortfolioActionController {
    def portfolioActionUpdate() {
        def id = 0
//        JSON.parse(params.models).each {
//            def item = PortfolioAction.get(it.id)
//            item.actionDate = it.actionDate
//            item.save(flush: true)
//            id = item.id
//        }
        render id
    }

    def portfolioActionDelete() {
        JSON.parse(params.models).each {
            def item = PortfolioAction.get(it.id)
            item.delete(flush: true)
        }
        render 0
    }

    def portfolioActionCreate() {

    }
}
