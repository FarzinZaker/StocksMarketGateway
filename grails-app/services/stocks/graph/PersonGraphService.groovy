package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User
import stocks.twitter.Search.TwitterPerson

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
        } else {
            graphDBService.editVertex(person?.id?.toString(), [title: "${user.firstName} ${user.lastName}"])
        }

        def rid = person?.id?.toString()
        def searchData = TwitterPerson.findByRid(rid)
        if (!searchData)
            searchData = new TwitterPerson(rid: rid)
        searchData.title = "${user.firstName} ${user.lastName}"
        searchData.identifier = user?.id
        searchData.save()

        person
    }


    Map ensureAndUnwrapPerson(User user) {

        def person = graphDBService.findVertex("SELECT FROM Person WHERE identifier = ${user?.id}")
        if (!person) {
            person = graphDBService.addVertex('Person', [
                    identifier: user.id,
                    title     : "${user.firstName} ${user.lastName}"
            ])

            graphDBService.addEdge('Member', person, commonGraphService.publicGroup, [type: 'normal', startDate: new Date()], null)
        }

        def rid = person?.id?.toString()
        def searchData = TwitterPerson.findByRid(rid)
        if (!searchData)
            searchData = new TwitterPerson(rid: rid)
        searchData.title = "${user.firstName} ${user.lastName}"
        searchData.identifier = user?.id
        searchData.save()

        graphDBService.unwrapVertex(person)
    }

    List<Map> propertyCloud(String personId) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property WHERE @rid in (SELECT in.@rid FROM About WHERE out.@rid in (SELECT in.@rid FROM Own WHERE out.@rid = #${personId} WHERE in.@class = 'Article')) GROUP BY @rid ORDER BY count DESC")
    }

    Map authorInfo(String personId) {
        def articles = graphDBService.queryAndUnwrapVertex("SELECT AVG(INE('Rate').value) as rate, COUNT(*) as count FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${personId}) WHERE @class = 'Article'")?.find()
        def comments = graphDBService.queryAndUnwrapVertex("SELECT COUNT(*) as count FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${personId}) WHERE @class = 'Comment'")?.find()
        def likes = graphDBService.queryAndUnwrapVertex("SELECT SUM(IN('Like').size()) as likes, SUM(IN('Dislike').size()) as dislikes, COUNT(*) as count FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${personId}) WHERE @class = 'Comment' AND (IN('Like').size() > 0 OR IN('Dislike').size() > 0)")?.find()

        [
                articlesCount            : articles.count,
                averageArticlesRate      : articles.rate,
                commentsCount            : comments.count,
                commentsLikes            : likes.likes,
                commentsDislikes         : likes.dislikes,
                commentsWithLikeOrDislike: likes.count
        ]
    }
}
