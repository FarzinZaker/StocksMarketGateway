package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User
import stocks.twitter.Article

//import stocks.twitter.Rate

class RateController {

    def springSecurityService
    def rateGraphService

    @Secured([RoleHelper.ROLE_USER])
    def save() {

        rateGraphService.saveRate(springSecurityService.currentUser as User, params.id as String, params.rating as Integer)
        render template: 'result', model: [rateValue: params.rating as Integer]
    }
}
