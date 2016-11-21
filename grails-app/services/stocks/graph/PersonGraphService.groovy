package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import groovy.time.TimeCategory
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
                    title     : user?.toString()
            ])

            graphDBService.addEdge('Member', person, commonGraphService.publicGroup, [type: 'normal', startDate: new Date()], null)
        } else {
            graphDBService.editVertex(person?.id?.toString(), [title: user?.toString()])
        }

        def rid = person?.id?.toString()
        def searchData = TwitterPerson.findByRid(rid)
        if (!searchData)
            searchData = new TwitterPerson(rid: rid)
        searchData.title = user?.toString()
        searchData.identifier = user?.id
        searchData.save()

        person
    }


    Map ensureAndUnwrapPerson(User user) {

        def person = graphDBService.findVertex("SELECT FROM Person WHERE identifier = ${user?.id}")
        if (!person) {
            person = graphDBService.addVertex('Person', [
                    identifier: user.id,
                    title     : user?.toString()
            ])

            graphDBService.addEdge('Member', person, commonGraphService.publicGroup, [type: 'normal', startDate: new Date()], null)
        }

        def rid = person?.id?.toString()
        def searchData = TwitterPerson.findByRid(rid)
        if (!searchData)
            searchData = new TwitterPerson(rid: rid)
        searchData.title = user?.toString()
        searchData.identifier = user?.id
        searchData.save()

        graphDBService.unwrapVertex(person)
    }

    List<Map> propertyCloud(String personId) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property WHERE @rid in (SELECT DISTINCT(@rid) FROM (SELECT EXPAND(OUT('About')) FROM (SELECT EXPAND(OUT('OWN')) FROM #${personId?.replace('#', '')}) WHERE @class <> 'Comment')) ORDER BY count DESC LIMIT 200")
    }

    Map authorInfo(String personId) {
        def articles = graphDBService.queryAndUnwrapVertex("SELECT AVG(INE('Rate').value) as rate, COUNT(*) as count FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${personId}) WHERE @class = 'Article' OR  @class = 'Talk' OR @class = 'Analysis'")?.find()
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

    List<Map> topScoredMaterials(String authorId, Integer daysCount, Integer count) {
        def dateParameter = ''
        if (daysCount > 0) {
            def startDate = new Date()?.clearTime()
            use(TimeCategory) {
                startDate = startDate - (daysCount - 1).days
            }
            def calendar = Calendar.getInstance()
            calendar.setTime(startDate)
            dateParameter = "WHERE publishDate >= '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0'"
        }
        graphDBService.queryAndUnwrapVertex("SELECT AVG(OutE('About').score) as score, * FROM (SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM #${authorId?.replace('#', '')}) WHERE @class=='Talk' OR @class=='Article') ${dateParameter} GROUP BY @rid ORDER BY score DESC LIMIT ${count}")
    }

    List<Map> mostVisitedMaterials(String authorId, Integer daysCount, Integer count) {
        def dateParameter = ''
        if (daysCount > 0) {
            def startDate = new Date()?.clearTime()
            use(TimeCategory) {
                startDate = startDate - (daysCount - 1).days
            }
            def calendar = Calendar.getInstance()
            calendar.setTime(startDate)
            dateParameter = "WHERE publishDate >= '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0'"
        }
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM #${authorId?.replace('#', '')}) WHERE @class=='Talk' OR @class=='Article') ${dateParameter} ORDER BY visitCount DESC LIMIT ${count}")
    }

    List<Map> topRatedMaterials(String authorId, Integer daysCount, Integer count) {
        def dateParameter = ''
        if (daysCount > 0) {
            def startDate = new Date()?.clearTime()
            use(TimeCategory) {
                startDate = startDate - (daysCount - 1).days
            }
            def calendar = Calendar.getInstance()
            calendar.setTime(startDate)
            dateParameter = "WHERE publishDate >= '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0'"
        }
        graphDBService.queryAndUnwrapVertex("SELECT AVG(InE('Rate').value) as rate, * FROM (SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM #${authorId?.replace('#', '')}) WHERE @class=='Talk' OR @class=='Article') ${dateParameter} GROUP BY @rid ORDER BY rate DESC LIMIT ${count}")
    }

    List<Map> mostCommentedMaterials(String authorId, Integer daysCount, Integer count) {
        def dateParameter = ''
        if (daysCount > 0) {
            def startDate = new Date()?.clearTime()
            use(TimeCategory) {
                startDate = startDate - (daysCount - 1).days
            }
            def calendar = Calendar.getInstance()
            calendar.setTime(startDate)
            dateParameter = "WHERE publishDate >= '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0'"
        }
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, publishDate, title, description, imageId, visitCount, in('RelatedTo').size() as comments FROM (SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM #${authorId?.replace('#', '')}) WHERE @class=='Talk' OR @class=='Article') ${dateParameter} ORDER BY comments DESC LIMIT ${count}")
    }

}
