package stocks.graph

import stocks.User

class FollowGraphService {

    def graphDBService
    def personGraphService

    Map getUserFollowshipForItem(String itemId, Long userId) {
        graphDBService.queryAndUnwrapEdge("SELECT * FROM Follow WHERE in.@rid = #${itemId} AND out.identifier = ${userId}")?.find()
    }

    void follow(User user, String itemId) {
        unfollow(user?.id, itemId)
        def person = personGraphService.ensurePerson(user)
        def item = graphDBService.getVertex(itemId)
        graphDBService.addEdge('Follow', person, item)
    }

    void unfollow(Long userId, String itemId) {
        graphDBService.executeCommand("DELETE EDGE Follow WHERE out.identifier = ${userId} AND in.@rid = #${itemId}")
    }
}
