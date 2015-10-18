package stocks.graph

import com.jniwrapper.Bool
import stocks.User

class LikeGraphService {

    def graphDBService
    def personGraphService

    def saveLike(User user, String commentId){

        graphDBService.executeCommand("DELETE EDGE Like WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}")
        graphDBService.executeCommand("DELETE EDGE Dislike WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}")

        def person = personGraphService.ensurePerson(user)
        def comment = graphDBService.getVertex(commentId)
        graphDBService.addEdge('Like', person, comment)
    }

    def saveDislike(User user, String commentId){

        graphDBService.executeCommand("DELETE EDGE Like WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}")
        graphDBService.executeCommand("DELETE EDGE Dislike WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}")

        def person = personGraphService.ensurePerson(user)
        def comment = graphDBService.getVertex(commentId)
        graphDBService.addEdge('Dislike', person, comment)
    }

    Integer getLikesCount(String commentId){
        graphDBService.count("SELECT COUNT(*) FROM Like WHERE in.@rid = #${commentId}")
    }

    Integer getDislikesCount(String commentId){
        graphDBService.count("SELECT COUNT(*) FROM Dislike WHERE in.@rid = #${commentId}")
    }

    Boolean hasLiked(User user, String commentId){
        graphDBService.count("SELECT COUNT(*) FROM Like WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}") > 0
    }

    Boolean hasDisliked(User user, String commentId){
        graphDBService.count("SELECT COUNT(*) FROM Dislike WHERE in.@rid = #${commentId} AND out.identifier = ${user?.id}") > 0
    }
}
