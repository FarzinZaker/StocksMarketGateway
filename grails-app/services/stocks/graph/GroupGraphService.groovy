package stocks.graph

import com.tinkerpop.blueprints.Vertex
import stocks.User

class GroupGraphService {

    def graphDBService
    def messageSource
    def personGraphService

    def create(String title, String membershipType, String membershipPeriod, Integer membershipPrice, Boolean allowExceptionalUsers, User owner) {
        def groupVertex = graphDBService.addVertex('Group', [
                title                : title,
                membershipType       : membershipType,
                membershipPeriod     : membershipPeriod,
                membershipPrice      : membershipPrice,
                allowExceptionalUsers: allowExceptionalUsers,
                ownerType            : 'user'
        ])

        Vertex person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('own', person, groupVertex)
    }

    def list(User user){
        graphDBService.query("SELECT * FROM (SELECT EXPAND(OUT('own')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
    }
}
