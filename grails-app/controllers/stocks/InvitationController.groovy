package stocks

import org.scribe.model.Token

class InvitationController {

    def grailsApplication

    def springSecurityService

    def mailService

    def index = {}

    def invite() {
        validateRequest(params.provider)
        redirect(url: getAuthUrl(params.provider, 'contacts'))
    }

    def pick() {
        validateRequest(params.provider)
        redirect(url: getAuthUrl(params.provider, 'pickContacts'))
    }

    def validateRequest(provider) {
        if (!provider ||
                !grailsApplication.config.grails.plugin.invitation[params.provider] ||
                !grailsApplication.config.grails.plugin.invitation[params.provider].enabled) {
            throw new RuntimeException("Provider '${provider}' not enabled")
        }
    }

    private getAuthUrl(provider, action) {
        def service = resolveService(provider)
        def authInfo = service.getAuthDetails(createLink(action: action, controller: 'invitation', absolute: 'true', params: [provider: provider]))
        if (authInfo.requestToken) {
            session["${provider}_requestToken"] = authInfo.requestToken
        }
        return authInfo.authUrl
    }

    def sendInvites = {
//        def service
//        if (params.provider.toString() != 'no-social')
//            service = resolveService(params.provider)
        def accessToken = session["${params.provider}_accessToken"]
        def message = params.message + ' ' + (grailsApplication.config.grails.plugin.invitation.defaultMessage ?: '') as String

        if (grailsApplication.config.grails.plugin.invitation.debug) {
            render """

				<html>
				<body>
				This is a debug screen.<br/>
				In a real life situation, I would have sent ${message} to ${params.receivers} on ${params.provider}
				</body>
				</html>

			"""
            return
        } else {
            params.addresses.split(',').each { address ->

                def invitation = new Invitation()
                invitation.subject = params.subject
                invitation.body = params.message
                invitation.sendDate = new Date()
                invitation.provider = params.provider
                invitation.sender = springSecurityService.currentUser as User
                invitation.identifier = UUID.randomUUID().toString()
                invitation.save()

                mailService.sendMail {
                    to address
                    subject params.subject
                    html(view: "/messageTemplates/email_template",
                            model: [message: g.render(template: '/invitation/templates/email', model: [
                                    inviter   : invitation.sender,
                                    message   : params.message.toString().replace('\n', '<br/>'),
                                    identifier: invitation.identifier]).toString()])
                }
            }

            flash.message = g.message(code: 'grails.plugin.invitation.result.title')
            redirect(controller: 'profile', action: 'invite')
        }
    }

    /*
     * the action intended to show the 'send invites' form.
     */

    def contacts() {
//        validateRequest(params.provider)
//        def service = resolveService(params.provider)
//        def accessToken = service.getAccessToken(params, session["${params.provider}_requestToken"])
//        session["${params.provider}_accessToken"] = accessToken
        def contacts = session["invite_${params.provider}_contacts"] //service.getContacts(accessToken)
        render(view: '/invitation/contacts', model: [contacts: contacts, provider: params.provider])
    }

    def emailList() {
        def contacts = params.mailList.split('\n').collect { [address: it?.toString()?.trim()] }.findAll { it.address }
        render(view: '/invitation/contacts', model: [contacts: contacts, provider: 'no-social'])
    }

    /*
     * the action intended to show the 'pick contacts' form.
     */

    def pickContacts() {
        validateRequest(params.provider)
        def service = resolveService(params.provider)
        def accessToken = service.getAccessToken(params, session["${params.provider}_requestToken"])
        session["${params.provider}_accessToken"] = accessToken
        def contacts = service.getContacts(accessToken)
        render(view: '/invitation/pick', model: [contacts: contacts, provider: params.provider], plugin: 'invitation')
    }

    def photo = {
        def service = resolveService(params.provider)
        def photo = service.getPhoto(session["${params.provider}_accessToken"] as Token, params.photo)

        response.contentType = photo.getHeader('Content-Type')

        BufferedReader reader = new BufferedReader(new InputStreamReader(photo.stream));

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = reader.read(buffer)) != -1) {
            response.outputStream.write(buffer, 0, bytesRead);
        }
        reader.close();
        response.flushBuffer()
    }

    private def resolveService(provider) {
        def serviceName = "${provider as String}InvitationService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() - 1]
        grailsApplication.mainContext.getBean(serviceName)
    }
}
