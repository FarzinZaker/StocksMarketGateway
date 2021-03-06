package stocks

import grails.converters.JSON
import grails.util.Environment
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

import java.nio.charset.StandardCharsets

class OAuth2Controller {

    AuthenticationManager authenticationManager

    def static consumers = [
            google: [
                    client_id    : '541476002495-ee18p159paa7kmtvfj223a64nj2e32kb.apps.googleusercontent.com',
                    client_secret: 'JKcBhsO4r6Ctpwbdp-twyrNL'
            ],
            yahoo : [
                    client_id    : 'dj0yJmk9cnhnQmFZclFtYjlwJmQ9WVdrOVdqTXhjWGxvTjJjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05Yw--',
                    client_secret: '7dfc0469b2cd07fb78e42935c52025cc693015f6'
            ]
    ]

    def google() {
        redirect(url: String.format('https://accounts.google.com/o/oauth2/auth?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s&access_type=%s',
                URLEncoder.encode(consumers.google.client_id),
                URLEncoder.encode("${createLink(url: "http://${Environment.isDevelopmentMode() ? 'localhost' : 'www.4tablo.ir'}/OAuth2/googleCallback")}"),
                URLEncoder.encode('https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/contacts.readonly'),
                URLEncoder.encode('code'),
                URLEncoder.encode('offline')
        ))
    }

    def googleCallback() {
        if (params.error == 'access_denied') {
            redirect(uri: '/')
            return
        }
        String urlParameters = [
                code         : params.code,
                client_id    : consumers.google.client_id,
                client_secret: consumers.google.client_secret,
                redirect_uri : "${createLink(url: "http://${Environment.isDevelopmentMode() ? 'localhost' : 'www.4tablo.ir'}/OAuth2/googleCallback")}",
                grant_type   : 'authorization_code'
        ].collect { "${it.key}=${it.value}" }.join('&');
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "https://accounts.google.com/o/oauth2/token";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream())
        wr.write(postData);
        def data = JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map

        def profile = JSON.parse("https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=${data.access_token}".toURL().text) as Map
        def user = User.findByEmail(profile.email as String)
        def newUser = false
        if (!user) {

            if(grailsApplication.config.registrationDisabled)
                redirect(controller: 'user', action: 'registrationDisabled')

            newUser = true
            user = new User()
            user.email = profile.email
            user.username = profile.email
            user.enabled = true
            user.accountExpired = false
            user.accountLocked = false
            user.passwordExpired = false
            user.password = ''
        }
        if (!user.firstName)
            user.firstName = profile.given_name
        if (!user.lastName)
            user.lastName = profile.family_name
        if (!user.sex)
            user.sex = profile.gender
        if (!user.externalImageUrl)
            user.externalImageUrl = profile.picture
        user.generateNickname()
        user.save(flush: true)

        def oAuthKey = OAuthKey.findByProviderAndIdentifier('google', profile.email as String)
        if (!oAuthKey) {
            oAuthKey = new OAuthKey()
            oAuthKey.provider = 'google'
            oAuthKey.identifier = profile.email
        }
        oAuthKey.accessToken = data.access_token
        if (data.refresh_token)
            oAuthKey.refreshToken = data.refresh_token
        oAuthKey.save(flush: true)

        loginUser(user)

        if (newUser)
            redirect(controller: 'profile', action: 'edit')
        else
            redirect(uri: '/')
    }

    def yahoo() {
        redirect(url: String.format('https://open.login.yahooapis.com/openid/op/auth?' +
                'openid.claimed_id=%s' +
                '&openid.identity=%s' +
                '&openid.mode=%s' +
                '&openid.ns=%s' +
                '&openid.realm=%s' +
                '&openid.return_to=%s' +
                '&openid.ns.oauth=%s' +
                '&openid.oauth.consumer=%s' +
                '&openid.ns.ax=%s' +
                '&openid.ax.mode=%s' +
                '&openid.ax.required=%s' +
                '&openid.ax.type.email=%s' +
                '&openid.ax.type.fullname=%s' +
                '&openid.ax.type.nickname=%s' +
                '&openid.ax.type.gender=%s' +
                '&openid.ax.type.image=%s',
                URLEncoder.encode('http://specs.openid.net/auth/2.0/identifier_select'),
                URLEncoder.encode('http://specs.openid.net/auth/2.0/identifier_select'),
                URLEncoder.encode('checkid_setup'),
                URLEncoder.encode('http://specs.openid.net/auth/2.0'),
                URLEncoder.encode("http://${Environment.isDevelopmentMode() ? 'localhost' : 'www.4tablo.ir'}/"),
                URLEncoder.encode("http://${Environment.isDevelopmentMode() ? 'localhost' : 'www.4tablo.ir'}/OAuth2/yahooCallback"),
                URLEncoder.encode('http://specs.openid.net/extensions/oauth/1.0'),
                URLEncoder.encode(consumers.yahoo.client_id),
                URLEncoder.encode('http://openid.net/srv/ax/1.0'),
                URLEncoder.encode('fetch_request'),
                URLEncoder.encode('email,fullname,nickname,gender,image'),
                URLEncoder.encode('http://axschema.org/contact/email'),
                URLEncoder.encode('http://axschema.org/namePerson'),
                URLEncoder.encode('http://axschema.org/namePerson/friendly'),
                URLEncoder.encode('http://axschema.org/person/gender'),
                URLEncoder.encode('http://axschema.org/media/image/default')

        ))
    }

    def yahooCallback() {

        def email = params.openid.ax.value.email
        def fullName = params.openid.ax.value.fullname as String
        def nameParts = fullName.split(' ')
        def firstName
        def lastName
        if (nameParts.size() > 0)
            firstName = nameParts[0]
        if (nameParts.size() > 1)
            lastName = nameParts[1]
        def gender = params.openid.ax.value.gender == 'M' ? 'male' : 'female'
        def image = params.openid.ax.value.image
        def nickName = params.openid.ax.value.nickname

        def user = User.findByEmail(email as String)
        def newUser = false
        if (!user) {

            if(grailsApplication.config.registrationDisabled)
                redirect(controller: 'user', action: 'registrationDisabled')

            newUser = true
            user = new User()
            user.email = email
            user.username = email
            user.enabled = true
            user.accountExpired = false
            user.accountLocked = false
            user.passwordExpired = false
            user.password = ''
        }
        if (!user.firstName)
            user.firstName = firstName
        if (!user.lastName)
            user.lastName = lastName
        if (!user.sex)
            user.sex = gender
        if (!user.externalImageUrl)
            user.externalImageUrl = image
        user.generateNickname()
        user.save(flush: true)

        loginUser(user)

        if (newUser)
            redirect(controller: 'profile', action: 'edit')
        else
            redirect(uri: '/')

    }

    private def loginUser(User user) {


        def userRole = Role.findByAuthority(RoleHelper.ROLE_USER)

        if (!user.authorities.contains(userRole)) {
            UserRole.create user, userRole
        }

        session['oAuthLogin'] = user

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken('oAuthLogin', '');
        token.setDetails(user);
        //doing actual authentication
        Authentication auth = authenticationManager.authenticate(token);
        log.debug("Login succeeded!");
        //setting principal in context
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private handleWebLogicApplicationContextProblem() {
        String urlParameters = [
                autoImportDomains: false,
                code             : ''
        ].collect { "${it.key}=${it.value}" }.join('&');
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "${createLink(absolute: true, controller: 'console', action: 'execute')}";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream())
        wr.write(postData);
        new DataInputStream(conn.getInputStream()).readLines()

    }
}
