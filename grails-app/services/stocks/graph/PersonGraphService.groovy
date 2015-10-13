package stocks.graph

import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User

class PersonGraphService {

    def graphDBService
    def commonGraphService

    OrientVertex getSystemUser() {
        graphDBService.findVertex("SELECT FROM Person WHERE identifier = 0")
    }

    OrientVertex ensurePerson(User user) {

        def person = graphDBService.findVertex("SELECT FROM Person WHERE identifier = ${user?.id}")
        if (!person) {
            person = graphDBService.addVertex('Person', [
                    identifier: user.id,
                    title     : "${user.firstName} ${user.lastName}"
            ])

            graphDBService.addEdge('Member', person, commonGraphService.publicGroup, [type: 'normal', startDate: new Date()], null)
        }
        person
    }
}
