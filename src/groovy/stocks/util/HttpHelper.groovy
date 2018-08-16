package stocks.util

import grails.converters.JSON
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method
import sun.misc.BASE64Encoder

/**
 * Created by farzin on 01/06/2015.
 */
class HttpHelper {
    static postText(String baseUrl, String path, data, String userName, String password) {

        try {
            def http = new HTTPBuilder(baseUrl + path)
            http.request(Method.POST, ContentType.TEXT) { req ->
                headers.'Authorization' =
                        "Basic ${"${userName}:${password}".bytes.encodeBase64().toString()}"
                body = data

                response.success = { resp, json ->
                }
            }
//
        } catch (HttpResponseException ex) {
            throw ex
        } catch (ConnectException ex) {
            throw ex
        }
    }

    static getText(String baseUrl, String path, Map query, String userName, String password) {

        def url = new URL(baseUrl + path + '&' + query.collect {
            "${URLEncoder.encode(it.key as String)}=${URLEncoder.encode(it.value as String)}"
        }.join('&'))
        def connection = (HttpURLConnection)url.openConnection()
        String userCredentials = "${userName}:${password}";
        String basicAuth = "Basic " + new String(new BASE64Encoder().encode(userCredentials.getBytes()));
        connection.setRequestProperty ("Authorization", basicAuth);
        connection.setRequestMethod("GET")
        connection.doOutput = true
        connection.connect()
        JSON.parse(connection.content.text?.toString())
    }
}
