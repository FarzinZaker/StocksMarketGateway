package stocks.messaging

import org.scribe.model.Token
import stocks.User

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
        def result = []
        params.addresses.split(',').each { address ->

            if (!stocks.User.findByEmail(address?.toString()?.trim()?.toLowerCase())) {
                def invitation = new Invitation()
                invitation.subject = params.subject
                invitation.body = params.message
                invitation.sendDate = new Date()
                invitation.provider = params.provider
                invitation.sender = springSecurityService.currentUser as User
                invitation.receiverAddress = address
                invitation.identifier = UUID.randomUUID().toString()
                invitation.save()

                def unsubscribe = Unsubscribe.findByEmailAndRemoved(address?.toString()?.toLowerCase(), false)
                if (!unsubscribe) {
                    mailService.sendMail {
                        from '4tablo Invitation <invitation@4tablo.ir>'
                        to address
                        subject params.subject
                        html(view: "/messageTemplates/email_template",
                                model: [message: g.render(template: '/invitation/templates/email', model: [
                                        inviter   : invitation.sender,
                                        message   : params.message.toString().replace('\n', '<br/>'),
                                        identifier: invitation.identifier]).toString(),
                                        source : 'invitation',
                                        email  : address])
                    }
                    result << [address: address, state: 'yes', description: message(code: 'invitation.status.succeed')]
                } else {
                    result << [address: address, state: 'no', description: message(code: 'invitation.status.unsubscribe')]
                }
            } else
                result << [address: address, state: 'no', description: message(code: 'invitation.status.repetitive')]
        }

        flash.message = g.message(code: 'grails.plugin.invitation.result.title')
        [list: result]
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
