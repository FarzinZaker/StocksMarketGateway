package stocks.twitter

import grails.converters.JSON
import stocks.User

class GroupController {

    def groupGraphService
    def springSecurityService

    def index() {}

    def build() {
        def group = params.id ? [:] : null
        [
                group: group
        ]
    }

    def save() {

        if (!springSecurityService.loggedIn)
            render 'login plz'

        groupGraphService.create(
                params.title as String,
                params.membershipType as String,
                params.membershipPeriod as String,
                params.membershipPrice as Integer,
                params?.allowExceptionalUsers == 'on',
                springSecurityService.currentUser as User)
    }

    def list(){
    }

    def adminJsonList(){
        render (groupGraphService.list(springSecurityService.currentUser as User) as JSON)
    }

}
