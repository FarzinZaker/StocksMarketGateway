package stocks.graph

import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientVertex

class CommonGraphService {

    def messageSource
    def graphDBService

    OrientVertex getSystemUser() {
        graphDBService.findVertex("SELECT FROM Person WHERE identifier = 0")
    }

    Map getSystemUserAndUnwrap() {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Person WHERE identifier = 0"))
    }

    OrientVertex getPublicGroup() {
        graphDBService.findVertex("SELECT FROM Group WHERE title = '${messageSource.getMessage('twitter.publicGroup', null, 'همه کاربران', Locale.ENGLISH)}'")
    }

    Map getPublicGroupAndUnwrap() {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Group WHERE title = '${messageSource.getMessage('twitter.publicGroup', null, 'همه کاربران', Locale.ENGLISH)}'"))
    }

    Map getAndUnwrap(String id) {
        if (id.startsWith('#'))
            id = id.replace('#', '')
        graphDBService.getAndUnwrapVertex(id)
    }

    OrientVertex get(String id) {
        if (id.startsWith('#'))
            id = id.replace('#', '')
        graphDBService.getVertex(id)
    }
}
