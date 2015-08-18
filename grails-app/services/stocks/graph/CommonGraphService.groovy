package stocks.graph

import com.tinkerpop.blueprints.Vertex

class CommonGraphService {

    def messageSource
    def graphDBService

    Vertex getSystemUser() {
        graphDBService.getVertex("SELECT FROM Person WHERE identifier = 0")
    }

    Vertex getPublicGroup(){
        graphDBService.getVertex("SELECT FROM Group WHERE title = '${messageSource.getMessage('twitter.publicGroup', null, '??? ???????', Locale.ENGLISH)}'")
    }
}
