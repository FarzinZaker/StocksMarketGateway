package stocks

import grails.plugins.springsecurity.Secured

class UserController {

    def simpleCaptchaService
    def mailService
    def springSecurityService

    def register() {
        if(springSecurityService.loggedIn)
            redirect(uri: '/')
    }

    def save() {

        if(springSecurityService.loggedIn)
            redirect(uri: '/')

        def user = new User(params)
        if (!simpleCaptchaService.validateCaptcha(params.captcha)) {

            flash.validationError = message(code: 'captcha.invalid')
            render view: 'register', model: [user: user]
            return
        }

        user.username = user.email
        user.enabled = false

        if (user.validate() && user.save()) {

            UserRole.create user, Role.findByAuthority(RoleHelper.ROLE_USER)

            mailService.sendMail {
                to user.email
                subject message(code: 'emailTemplates.email_verification.subject')
                html(view: "/messageTemplates/email_template",
                        model: [message: g.render(template: '/messageTemplates/mail/email_verification', model: [user: user]).toString()])
            }

            render(view: 'checkForActivationMail')
        } else {
            render(view: 'register', model: [user: user])
        }
    }

    def activate() {

        if(springSecurityService.loggedIn)
            redirect(uri: '/')

        def code = new String(params.code.toString().decodeBase64()).split('_')[2]
        if (code.toString() == params.id.toString()) {
            def user = User.get(params.id)
            user.enabled = true
            user.save()


            mailService.sendMail {
                to user.email
                subject message(code: 'emailTemplates.activation_result.subject')
                html(view: "/messageTemplates/email_template",
                        model: [message: g.render(template: '/messageTemplates/mail/activation_result', model: [user: user]).toString()])
            }

            flash.info = message(code: 'accountEnabled')
            redirect(controller: 'login', action: 'auth')
            return
        }

        render "code error"
    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER])
    def changePassword(){

    }

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER])
    def saveNewPassword() {
        def user = (User) springSecurityService.currentUser
        if (user.password == springSecurityService.encodePassword(params.oldPassword)) {
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

    @Secured([RoleHelper.ROLE_ADMIN, RoleHelper.ROLE_USER])
    def passwordChanged(){

    }

    @Secured([RoleHelper.ROLE_USER])
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

    @Secured([RoleHelper.ROLE_USER])
    def wall() {
        [user: User.get(params.id)]
    }

    @Secured([RoleHelper.ROLE_USER])
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

    @Secured([RoleHelper.ROLE_USER])
    def home(){
        [user: springSecurityService.currentUser as User]
    }


}
