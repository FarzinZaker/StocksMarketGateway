package stocks

import grails.plugins.springsecurity.Secured

class ProfileController {

    def springSecurityService
    def accountingService

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def index() {
        def user = springSecurityService.currentUser as User
        [user: user, balance: accountingService.userBalance(user?.id)]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def edit() {
        [user: springSecurityService.currentUser as User]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def saveProfile() {
        def user = springSecurityService.currentUser as User
        user.firstName = params.firstName
        user.lastName = params.lastName
        user.sex = params.sex
        user.mobile = params.mobile
        user.nationalCode = params.nationalCode
        user.city = City.get(params.cityId as Long)
        user.image = Image.get(params.image?.id as Long)
        if (user.save(flush: true)) {
            flash.message = message(code: 'user.profile.savedSuccessfully')
        }
        redirect(action: 'edit')
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def payment() {

    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def transactions() {
        [user: springSecurityService.currentUser as User]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def newsLetterSubscription() {
        [user: springSecurityService.currentUser as User]
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def invite() {
        [user: springSecurityService.currentUser as User]
    }
}
