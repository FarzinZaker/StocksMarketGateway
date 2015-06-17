package stocks.util

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method
import groovyx.net.http.*
import groovyx.net.http.Method

/**
 * Created by farzin on 01/06/2015.
 */
public class HttpHelper {
    static def postText(String baseUrl, String path, data) {
//        println(data)
        try {
            def http = new HTTPBuilder(baseUrl + path)
            http.request(Method.POST, ContentType.TEXT) { req ->
                body = data

                response.success = { resp, json ->
//                    println(json)
//                    println(resp)
                }
            }
//
        } catch (HttpResponseException ex) {
//            ex.printStackTrace()
            throw ex
        } catch (ConnectException ex) {
//            ex.printStackTrace()
            throw ex
        }
    }

    static def getText(String baseUrl, String path, Map query) {

        try {
            def http = new HTTPBuilder(baseUrl + path)
            http.request(Method.GET, ContentType.JSON) { req ->
                uri.query = query
                response.success = { resp, json ->
//                    println(json)
//                    println(resp)
                    return json
                }
            }
//
        } catch (HttpResponseException ex) {
            ex.printStackTrace()
            throw ex
        } catch (ConnectException ex) {
            ex.printStackTrace()
            throw ex
        }
    }
}
