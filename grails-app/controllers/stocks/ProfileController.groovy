package stocks

import grails.plugins.springsecurity.Secured
import stocks.util.EncodingHelper

class ProfileController {

    def springSecurityService
    def accountingService
    def personGraphService

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
        def nickNameExists = User.findByNicknameAndIdNotEqual(params.nickname, user?.id)
        if (nickNameExists) {
            flash.validationError = message(code: 'user.profile.nicknameIsRepetitive')
            redirect(action: 'edit')
            return
        }
        user.firstName = params.firstName
        user.lastName = params.lastName
        user.nickname = params.nickname
        user.sex = params.sex
        user.mobile = params.mobile
        user.nationalCode = params.nationalCode
        user.city = City.get(params.cityId as Long)
        user.image = params?.image?.id && params?.image?.id != '' ? Image.get(params.image?.id as Long) : null
        if (user.save(flush: true)) {
            personGraphService.ensurePerson(user)
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

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def social() {
        def user = springSecurityService.currentUser as User
        def code = user?.id?.toString()?.bytes?.encodeHex()?.toString()?.toLong()
        while(code > 100)
            code = Math.round(code / 10) + code % 10
        [
                user        : user,
                telegramUser: user.telegramUser,
                key         : "${user?.id}${code?.toString()?.padLeft(2, '0')}"
        ]
    }
}
