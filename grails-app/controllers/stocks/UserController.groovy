package stocks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.apache.lucene.search.BooleanQuery
import stocks.accounting.Transaction
import stocks.messaging.Unsubscribe
import stocks.messaging.Invitation

class UserController {

    def grailsApplication
    def mailService
    def springSecurityService
    def personGraphService
    def materialGraphService
    def groupGraphService
    def userService
    def smsService

    def registrationDisabled() {

    }

    def registerInvited() {

        if (grailsApplication.config.registrationDisabled)
            redirect(controller: 'user', action: 'registrationDisabled')

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def invitation = Invitation.findByIdentifier(params.invitation as String)
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

        if (grailsApplication.config.registrationDisabled)
            redirect(controller: 'user', action: 'registrationDisabled')

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def code = new String(params.code.toString().decodeBase64()).split('_')[2]
        if (code.toString() == params.id.toString()) {
            [user: User.get(params.id as Long)]

        } else
            render "code error"
    }

    def saveInitialRegistration() {

        if (grailsApplication.config.registrationDisabled) {
            render message(code: 'registration.disabled.message')
            return
        }

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
//            mailService.sendMail {
//                to user.email
//                subject message(code: 'emailTemplates.email_verification.subject')
//                html(view: "/messageTemplates/email_template",
//                        model: [message: g.render(template: '/messageTemplates/mail/email_verification', model: [user: user]).toString(),
//                                source : 'registration',
//                                email  : user?.email])
//            }
//            }
//            render '1'
            render "1|${createLink(absolute: true, controller: 'user', action: 'completeRegistration', params: [id: user.id, code: "${new Date().timeString}_activate_${user.id}".encodeAsBase64()])}"
        } else
            render '-1'
    }

    def saveRegistration() {

        if (grailsApplication.config.registrationDisabled)
            redirect(controller: 'user', action: 'registrationDisabled')

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
        user.generateNickname()

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

                Invitation.findAllByReceiverAddress(user.email).each {
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

        if (grailsApplication.config.registrationDisabled)
            redirect(controller: 'user', action: 'registrationDisabled')

    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def reindex() {
        render User.reindexAll()
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def list() {

    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "username", order: params["sort[0][dir]"] ?: "asc"]

        def list
        if (params.search && params.search != '') {
            def searchResult = User.search(params.search?.toString(), max: 1000000).results.collect { it.id }
            list = searchResult?.size() > 0 ? User.findAllByIdInList(searchResult, parameters) : []
            value.total = searchResult?.size() > 0 ? User.countByIdInList(searchResult) : 0
        } else {
            list = User.findAllByBrokerIsNull(parameters)
            value.total = User.countByBrokerIsNull()
        }

        value.data = list.collect {
            [
                    id          : it.id,
                    firstName   : it.firstName,
                    lastName    : it.lastName,
                    nickname    : it.nickname,
                    username    : it.username?.replace('@', ' @ '),
                    sex         : it.sex,
                    mobile      : it.mobile,
                    nationalCode: it.nationalCode,
                    city        : it.city?.name,
                    enabled     : it.enabled,
                    broker      : it.broker?.name ?: '',
                    roles       : it.authorities.collect { message(code: "userInfo.roles.${it.authority}") }.join(',')
            ]
        }

        render value as JSON
    }

    @Secured([RoleHelper.ROLE_ADMIN])
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

    @Secured([RoleHelper.ROLE_ADMIN])
    def save() {

        def user
        if (params.id) {
            user = User.get(params.id)
//            def oldPassword = user.password
//            user.properties = params
//            if (oldPassword != user.password)
//                user.password = springSecurityService.encodePassword(user.password)
        } else {

            if (User.findByEmail(params.email)) {
                flash.validationError = message(code: 'user.save.error.repetitiveEmail')
                redirect(action: 'build')
                return
            }

            user = new User()
        }

        user.nickname = params.nickname
        user.firstName = params.firstName
        user.lastName = params.lastName
        user.sex = params.sex
        user.mobile = params.mobile
        user.nationalCode = params.nationalCode
        user.email = params.email

        if (!params.id || (params.password && params.password?.trim() != ''))
            user.password = params.password

        if (params.maxDept)
            user.maxDept = params.maxDept as Integer

        user.enabled = params.enabled ? true : false
        user.accountExpired = params.accountExpired ? true : false
        user.accountLocked = params.accountLocked ? true : false
        user.passwordExpired = params.passwordExpired ? true : false

        user.city = City.get(params.cityId)
        user.username = user.email

        user.generateNickname()

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

        if (grailsApplication.config.registrationDisabled)
            redirect(controller: 'user', action: 'registrationDisabled')

        if (springSecurityService.loggedIn)
            redirect(uri: '/')

        def code = new String(params.code.toString().decodeBase64()).split('_')[2]
        if (code.toString() == params.id.toString()) {
            def user = User.get(params.id)
            user.enabled = true
            user.generateNickname()
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

    def forgetPassword() {

    }

    def sendPasswordResetLink() {
        def username = params.username
        def user = User.findByEmailOrMobileOrUsername(username, username, username)
        if (!user) {
            flash.message = message(code: 'user.resetPassword.error.noSuchUser')
            return redirect(action: 'forgetPassword')
        }

        def password = (Math.random() * 900000 + 100000) as int
        user.password = password
        user.save()

        if (user.mobile) {
            smsService.sendCustomMessage(user.mobile, message(code: 'resetPassword.sms', args: [password.toString()])?.toString())
        }

        if (user.email) {
            mailService.sendMail {
                to user.email
                subject message(code: 'emailTemplates.resetPassword.subject')
                html(view: "/messageTemplates/email_template",
                        model: [message: g.render(template: '/messageTemplates/mail/reset_password', model: [user: user, newPassword: password]).toString(),
                                source : 'resetPassword',
                                email  : user?.email])
            }
        }

        flash.info = message(code: 'password.reset.succeed')
        redirect(controller: 'login', action: 'auth')
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
        def id = params.id as Long
        if (!id)
            id = springSecurityService.currentUser?.id as Long
        def user = User.get(id)
        def vertex = personGraphService.ensureAndUnwrapPerson(user)
        [
                user      : user,
                vertex    : vertex,
                groupList : groupGraphService.listForAuthor(user),
                authorInfo: personGraphService.authorInfo(vertex.idNumber),
                ownPage   : user?.id == springSecurityService.currentUser?.id
        ]
    }

    def wallJson() {
        def list = materialGraphService.listByAuthor(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true])
        } as JSON)
    }

    @Secured([RoleHelper.ROLE_USER, RoleHelper.ROLE_BROKER_USER])
    def home() {
        def user = springSecurityService.currentUser as User
        def vertex = personGraphService.ensureAndUnwrapPerson(user)
        [
                user      : user,
                vertex    : vertex,
                groupList : groupGraphService.listForHome(user),
                followList: materialGraphService.getFollowList(user?.id, 0, 20),
                ownPage   : true
        ]
    }

    def mostCards() {
        render(template: '/user/home/mostCards', model: [id: params.id])
    }

    def homeOldJson() {
        def user = springSecurityService.currentUser as User
        def list = materialGraphService.listOldForHome(user?.id, new Date(params.minDate as Long), params.limit as Integer)
        render(
                [
                        data   : list.collect {
                            g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true])
                        },
                        minDate: list.collect { it.publishDate.time }.min(),
                        maxDate: list.collect { it.publishDate.time }.max()
                ] as JSON)
    }

    def homeNewJson() {
        def user = springSecurityService.currentUser as User
        def list = materialGraphService.listNewForHome(user?.id, new Date(params.maxDate as Long), params.limit as Integer)
        render(
                [
                        data   : list.collect {
                            g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true])
                        },
                        minDate: list.collect { it.publishDate.time }.min(),
                        maxDate: list.collect { it.publishDate.time }.max()
                ] as JSON)
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

    @Secured([RoleHelper.ROLE_ADMIN])
    def importUsers() {
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def importResult() {
        def fileId = session["userImport"]
        def path = "${grailsApplication.config.user.files.temp}/${fileId}"
        def result = userService.importUser(path)
        [result: result]
    }

    @Secured([RoleHelper.ROLE_ADMIN])
    def uploadFile() {

        def fileId = UUID.randomUUID()?.toString()
        def fileName = "${fileId}-${params.file.fileItem.fileName}"
        def path = "${grailsApplication.config.user.files.temp}/${fileName}"
        def file = new java.io.File(path)

        def directory = file.parentFile
        if (!directory.exists())
            directory.mkdirs()

        if (file.exists())
            file.delete()

        params.file.transferTo(file)

        session[params.id] = fileName

        render 1
    }


    def topMaterials() {
        def daysCount = params.period as Integer
        def id = params.id as String
        render([
                topScored    : personGraphService.topScoredMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                mostVisited  : personGraphService.mostVisitedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                topRated     : personGraphService.topRatedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                mostCommented: personGraphService.mostCommentedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join('')
        ] as JSON)
    }

}
