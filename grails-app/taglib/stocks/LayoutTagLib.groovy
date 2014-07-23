package stocks

class LayoutTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def springSecurityService

    static namespace = "layout"

    def panel = { attrs, body ->
        out << """
            <div class="panel ${attrs.class ?: ''}">
"""
        if (attrs.showHeader != 'no')
            out << """
                <${attrs.header ?: 'h2'}>${attrs.title ?: ''}</${attrs.header ?: 'h2'}>
"""
        out << """
                <div class="content ${attrs.contentClass ?: ''}">
                ${
            !attrs.loginRequiredAction || springSecurityService.loggedIn ? body() : loginMessage(attrs.loginRequiredAction)
        }
                </div>
            </div>
"""
    }

    def loginMessage(action) {
        """
            <div class="info">
                ${message(code: 'loginRequired.message', args: [action])}
                <div>
                    <a href="${createLink(controller: 'login', action: 'auth')}" class='k-button'>${
            message(code: 'login')
        }</a>
                    <a href="${createLink(controller: 'user', action: 'register')}" class='k-button'>${
            message(code: 'register')
        }</a>
                </div>
            </div>
"""
    }
}
