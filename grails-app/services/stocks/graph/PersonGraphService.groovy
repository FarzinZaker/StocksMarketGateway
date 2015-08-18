package stocks.graph

import com.tinkerpop.blueprints.Vertex
import stocks.User

class PersonGraphService {

    def graphDBService
    def commonGraphService

    Vertex getSystemUser() {
        graphDBService.getVertex("SELECT FROM Person WHERE identifier = 0")
    }

    Vertex ensurePerson(User user) {

        def person = graphDBService.getVertex("SELECT FROM Person WHERE identifier = ${user?.id}")
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
