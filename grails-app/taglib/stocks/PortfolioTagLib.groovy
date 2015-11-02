package stocks

import stocks.portfolio.Portfolio

class PortfolioTagLib {

    static namespace = "portfolio"

    def springSecurityService


    def menu = { attrs, body ->
        def list = Portfolio.createCriteria().list {
            eq('owner', springSecurityService.currentUser as User)
            eq('deleted', false)
            order('name', ORDER_ASCENDING)
            projections {
                property('name')
                property('id')
            }
        }
        if (list?.size())
            out << """
                <li class='k-separator'></li>
"""
        list?.each {
            out << """
                <li>
                    <a href="${createLink(controller: 'portfolio', action: 'portfolioManage', id: it[1])}">${it[0]}</a>
                </li>
"""
        }
    }
}
