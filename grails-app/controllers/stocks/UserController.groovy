package stocks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.apache.lucene.search.BooleanQuery
import stocks.accounting.Transaction
import stocks.messaging.Unsubscribe

class UserController {

    def simpleCaptchaService
    def mailService
    def springSecurityService

    def registerInvited() {

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def invitation = stocks.messaging.Invitation.findByIdentifier(params.invitation as String)
        if (!invitation) {
            render "code error"
            return
        }
        if (invitation.registrationRecorded) {
            render "already registered"
            return
        }

        [user: new User(email: invitation.receiverAddress, username: invitation.receiverAddress, referer: invitation.sender)]
    }

    def completeRegistration() {

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def code = new String(params.code.toString().decodeBase64()).split('_')[2]
        if (code.toString() == params.id.toString()) {
            [user: User.get(params.id as Long)]

        } else
            render "code error"
    }

    def saveInitialRegistration() {

        if (springSecurityService.loggedIn) {
            render '0'
            return
        }

        if (!params.email) {
            render message(code: 'register.error.emailRequired')
            return
        }

        if (!params.password) {
            render message(code: 'register.error.passwordRequired')
            return
        }

        if (params.password != params.confirmPassword) {
            render message(code: 'register.error.passwordsDoesNotMatch')
            return
        }

        if (User.findByEmail(params.email?.toString()?.toLowerCase())) {
            render message(code: 'register.error.repetitiveEmail')
            return
        }

        def user = new User(params)
        user.username = user.email?.toLowerCase()
        user.enabled = false

        if (user.validate() && user.save()) {

//            def unsubscribe = Unsubscribe.findByEmailAndRemoved(user.email.toLowerCase(), false)
//            if (!unsubscribe) {
            mailService.sendMail {
                to user.email
                subject message(code: 'emailTemplates.email_verification.subject')
                html(view: "/messageTemplates/email_template",
                        model: [message: g.render(template: '/messageTemplates/mail/email_verification', model: [user: user]).toString(),
                                source : 'registration',
                                email  : user?.email])
            }
//            }
            render '1'
        } else
            render '-1'
    }

    def saveRegistration() {

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def user
        if (params.id)
            user = User.get(params.id)
        else {
            user = new User()
            user.username = params.email
        }

        user.properties = params
        user.city = City.get(params.cityId as Long)
        if (params.refererId)
            user.referer = User.get(params.refererId as Long)
        user.enabled = true

        if (user.validate() && user.save()) {
            if (user.referer) {
                def transaction = new Transaction()
                transaction.date = new Date()
                transaction.accountId = grailsApplication.config.accounts.find { it.giftAccount }.id
                transaction.creator = user
                transaction.customer = user.referer
                transaction.type = AccountingHelper.TRANSACTION_TYPE_DEPOSIT
                transaction.value = grailsApplication.config.gift.invite.registration
                transaction.description = message(code: 'transaction.description.gift.register', args: [user.toString()])
                transaction.save()

                stocks.messaging.Invitation.findAllByReceiverAddress(user.email).each {
                    it.registrationRecorded = true
                    it.save(flush: true)
                }
            }

            UserRole.create user, Role.findByAuthority(RoleHelper.ROLE_USER)

            redirect(controller: 'login', action: 'auth')
        } else {
            if (User.findByUsername(user.username))
                flash.validationError = message(code: 'register.error.repetitiveUser')
            render(view: params.view, model: [user: user])
        }
    }

    def checkForActivationMail() {

    }

    def list() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "username", order: params["sort[0][dir]"] ?: "asc"]

        def list
        if (params.search && params.search != '') {
            def searchResult = User.search(params.search?.toString()).results.collect { it.id }
            list = searchResult?.size() > 0 ? User.findAllByBrokerIsNullAndIdInList(searchResult, parameters) : []
            value.total = searchResult?.size() > 0 ? User.countByBrokerIsNullAndIdInList(searchResult) : 0
        } else {
            list = User.findAllByBrokerIsNull(parameters)
            value.total = User.countByBrokerIsNull()
        }

        value.data = list.collect {
            [
                    id          : it.id,
                    firstName   : it.firstName,
                    lastName    : it.lastName,
                    username    : it.username,
                    sex         : it.sex,
                    mobile      : it.mobile,
                    nationalCode: it.nationalCode,
                    city        : it.city?.name,
                    enabled     : it.enabled,
                    roles       : it.authorities.collect { message(code: "userInfo.roles.${it.authority}") }.join(',')
            ]
        }

        render value as JSON
    }

    def build() {
        def user = User.get(params.id)

        [
                user          : user ?: new User(),
                roles         : user ? UserRole.findAllByUser(user).collect {
                    it.role.authority
                } : [RoleHelper.ROLE_USER],
                availableRoles: RoleHelper.SYSTEM_ROLES
        ]
    }

    def save() {

        def user
        if (params.id) {
            user = User.get(params.id)
            def oldPassword = user.password
            user.properties = params
//            if (oldPassword != user.password)
//                user.password = springSecurityService.encodePassword(user.password)
        } else {

            if (User.findByEmail(params.email)) {
                flash.validationError = message(code: 'user.save.error.repetitiveEmail')
                redirect(action: 'build')
                return
            }

            user = new User(params)
        }

        user.enabled = params.enabled ? true : false
        user.accountExpired = params.accountExpired ? true : false
        user.accountLocked = params.accountLocked ? true : false
        user.passwordExpired = params.passwordExpired ? true : false

        user.city = City.get(params.cityId)
        user.username = user.email

        if (user.validate() && user.save()) {

            UserRole.findAllByUser(user).each { it.delete() }
            RoleHelper.ROLES.each { role ->
                if (params."roles_${role}")
                    UserRole.create(user, Role.findByAuthority(role))
            }

            redirect(action: 'list')
        } else {
            flash.validationError = user.errors.toString()
            redirect(action: 'build', params: params)
        }
    }

    def activate() {

        return

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def code = new String(params.code.toString().decodeBase64()).split('_')[2]
        if (code.toString() == params.id.toString()) {
            def user = User.get(params.id)
            user.enabled = true
            user.save()

//            def unsubscribe = Unsubscribe.findByEmailAndRemoved(user.email.toLowerCase(), false)
//            if (!unsubscribe) {
            mailService.sendMail {
                to user.email
                subject message(code: 'emailTemplates.activation_result.subject')
                html(view: "/messageTemplates/email_template",
                        model: [message: g.render(template: '/messageTemplates/mail/activation_result', model: [user: user]).toString(),
                                source : 'activation',
                                email  : user?.email])
            }
//            }

            flash.info = message(code: 'accountEnabled')
            redirect(controller: 'login', action: 'auth')
            return
        }

        render "code error"
    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_ADMIN, RoleHelper.ROLE_BROKER_USER])
    def changePassword() {

        def user = User.findByUsername((springSecurityService.currentUser as User).username)
        [askForOldPassword: user.password != springSecurityService.encodePassword(' ')]
    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_ADMIN, RoleHelper.ROLE_BROKER_USER])
    def saveNewPassword() {
        def user = User.findByUsername((springSecurityService.currentUser as User).username)
        if (user.password == springSecurityService.encodePassword(params.oldPassword) || user.password == springSecurityService.encodePassword(' ')) {
            if (params.newPassword.trim() != '') {
                if (params.newPassword == params.newPassword_confirmation) {
                    user.password = params.newPassword
                    if (user.validate() && user.save()) {
                        flash.message = message(code: "password.change.success")
                        redirect(action: 'passwordChanged')
                    } else {
                        flash.validationError = message(code: "password.change.fail")
                        redirect(action: 'changePassword')
                    }
                } else {
                    flash.validationError = message(code: "password.change.notMatch")
                    redirect(action: 'changePassword')
                }
            } else {
                flash.validationError = message(code: "password.change.emptyPassword")
                redirect(action: 'changePassword')
            }
        } else {
            flash.validationError = message(code: "password.change.invalidPassword")
            redirect(action: 'changePassword')
        }
    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_ADMIN, RoleHelper.ROLE_BROKER_USER])
    def passwordChanged() {

    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def follow() {
        def currentUser = springSecurityService.currentUser as User
        currentUser.get(currentUser.id)
        def user = User.get(params.id)
        if (params.type == '1')
            currentUser.addToFollowList(user)
        else
            currentUser.removeFromFollowList(user)
        if (user.save(flush: true))
            render params.type
    }

    def wall() {
        [user: User.get(params.id)]
    }

    def synchronized wallJson() {
        def parameters = [offset: params.skip, max: 10, sort: "lastUpdated", order: "desc"]
        def documents = stocks.twitter.Document.createCriteria().list(parameters, {
            author {
                eq('id', params.id?.toLong())
            }
        })
        documents.each {
            render template: '/document/card', model: [document: it]
        }
        render ''
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def home() {
        [user: springSecurityService.currentUser as User]
    }


    def autoComplete() {
        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def result = User.search("*${queryStr}*").results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    name : it.toString(),
                    value: it.id
            ]
        }
        render([data: result] as JSON)
    }

}
