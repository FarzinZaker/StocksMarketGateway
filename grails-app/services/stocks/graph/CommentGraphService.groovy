package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User

class CommentGraphService {

    def graphDBService
    def personGraphService

    Map getPersonRateForMaterial(User user, OrientVertex material) {
        graphDBService.queryAndUnwrapEdge("SELECT * FROM Rate WHERE out.identifier = ${user?.id} AND in.@rid = ${material.id}")?.find()
    }

    Map saveComment(User user, String parentId, String body, String id = null) {
        def comment
        if (id) {
            comment = graphDBService.editVertex(id, [
                    body       : body,
                    lastUpdated: new Date()
            ])
        } else {
            comment = graphDBService.addVertex('Comment', [
                    body       : body,
                    dateCreated: new Date(),
                    lastUpdated: new Date()
            ])

            if (comment) {
                def person = personGraphService.ensurePerson(user)
                graphDBService.addEdge('Own', person, comment)
                def parent = graphDBService.getVertex(parentId)
                graphDBService.addEdge('RelatedTo', comment, parent)
            }
        }
        graphDBService.unwrapVertex(comment)
    }

    List<Map> getCommentList(String materialId){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('RelatedTo')) FROM Material WHERE @rid = #${materialId}) WHERE @class = 'Comment' ORDER BY dateCreated DESC").each {
            it.author = getCommentAuthor(it.idNumber as String)
        }
    }

    List<Map> getNewCommentList(String materialId, Date minDate = new Date()){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('RelatedTo')) FROM Material WHERE @rid = #${materialId}) WHERE @class = 'Comment' AND dateCreated > '${minDate.format('yyyy-MM-dd HH:mm:ss')}' ORDER BY dateCreated DESC").each {
            it.author = getCommentAuthor(it.idNumber as String)
        }
    }

    List<Map> getChildCommentList(String commentId){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('RelatedTo')) FROM Comment WHERE @rid = #${commentId}) WHERE @class = 'Comment' ORDER BY dateCreated DESC").each {
            it.author = getCommentAuthor(it.idNumber as String)
        }
    }

    Map getCommentAuthor(String commentId){
        graphDBService.queryAndUnwrapVertex("SELECT EXPAND(IN('Own')) FROM Comment WHERE @rid = #${commentId}")?.find()
    }
}
