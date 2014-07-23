package stocks

class UserController {

    def simpleCaptchaService
    def mailService
    def springSecurityService

    def register() {
    }

    def save() {

        def user = new User(params)
        if (!simpleCaptchaService.validateCaptcha(params.captcha)) {

            flash.validationError = message(code: 'captcha.invalid')
            render view: 'register', model: [user: user]
            return
        }

        user.username = user.email
        user.enabled = false

        if (user.validate() && user.save()) {

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

    def home(){
        [user: springSecurityService.currentUser as User]
    }
}
