package stocks

import grails.converters.JSON
import org.apache.commons.codec.binary.Base64

import java.nio.charset.StandardCharsets

class SocialController {

    def static consumers = [
            google: [
                    client_id    : '784184303586-693j0vqr1gmut4ucdudsphjdcv5peqqc.apps.googleusercontent.com',
                    client_secret: 'A0KK3lcOErybHzhzuI7_815u'
            ],
            yahoo : [
                    client_id    : 'dj0yJmk9cnhnQmFZclFtYjlwJmQ9WVdrOVdqTXhjWGxvTjJjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05Yw--',
                    client_secret: '7dfc0469b2cd07fb78e42935c52025cc693015f6'
            ]
    ]

    def google() {
        redirect(url: String.format('https://accounts.google.com/o/oauth2/auth?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s&access_type=%s',
                URLEncoder.encode(consumers.google.client_id),
                URLEncoder.encode("${createLink(url: 'http://www.4tablo.ir/social/googleCallback')}"),
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
                redirect_uri : "${createLink(url: 'http://www.4tablo.ir/social/googleCallback')}",
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

        def contactsData = "https://www.google.com/m8/feeds/contacts/default/full?alt=json&access_token=${data.access_token}&max-results=700&v=3.0".toURL().text
        def contacts = JSON.parse(contactsData).feed.entry.collect {
            [
                    name   : it.gd$name?.gd$fullName?.find()?.value ?: '',
                    address: it.gd$email?.address?.find(),
                    photo  : it.link?.find {
                        it.rel.toString().endsWith('#photo')
                    }?.href + '&access_token=' + data.access_token ?: ''
            ]
        }.findAll { it.address }
        session['invite_google_contacts'] = contacts
        redirect(controller: 'invitation', action: 'contacts', params: [provider: 'google'])
    }

    def yahoo() {
        redirect(url: String.format('https://api.login.yahoo.com/oauth2/request_auth?client_id=%s&redirect_uri=%s&response_type=%s',
                URLEncoder.encode(consumers.yahoo.client_id),
                URLEncoder.encode("${createLink(url: 'http://www.4tablo.ir/social/yahooCallback')}"),
                URLEncoder.encode('code')
        ))
    }


    def yahooCallback() {
        if (params.error == 'access_denied') {
            redirect(uri: '/')
            return
        }
        String urlParameters = [
                code         : params.code,
                client_id    : consumers.yahoo.client_id,
                client_secret: consumers.yahoo.client_secret,
                redirect_uri : "${createLink(url: 'http://www.4tablo.ir/social/yahooCallback')}",
                grant_type   : 'authorization_code'
        ].collect { "${it.key}=${it.value}" }.join('&');
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "https://api.login.yahoo.com/oauth2/get_token";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization", "Basic ${Base64.encodeBase64String("${consumers.yahoo.client_id}:${consumers.yahoo.client_secret}".bytes)}");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream())
        wr.write(postData);
        def data = JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map

        request = "https://social.yahooapis.com/v1/user/me/contacts?format=json";
        url = new URL(request);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer ${data.access_token}");
        conn.setUseCaches(false);
        println conn.getResponseCode();

        data = JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map

        def contacts = data.contacts.contact.collect {
            def nameMap = it?.fields?.find { it.type == 'name' }?.value as HashMap
            def firstName = nameMap?.containsKey('givenName') ? nameMap.givenName : ''
            def lastName = nameMap?.containsKey('familyName') ? nameMap.familyName : ''
            [
                    name   : firstName || lastName ? "${firstName} ${lastName}".trim() : '',
                    address: it?.fields?.find { it.type == 'email' }?.value,
                    photo  : ''
            ]
        }.findAll { it.address }
        session['invite_yahoo_contacts'] = contacts
        redirect(controller: 'invitation', action: 'contacts', params: [provider: 'yahoo'])
    }
}
