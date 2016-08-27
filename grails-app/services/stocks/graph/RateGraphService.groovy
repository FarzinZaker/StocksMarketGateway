package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import groovy.time.TimeCategory
import stocks.User

class RateGraphService {

    def graphDBService
    def personGraphService

    Map getPersonRateForMaterial(User user, OrientVertex material) {
        graphDBService.queryAndUnwrapEdge("SELECT * FROM Rate WHERE out.identifier = ${user?.id} AND in.@rid = ${material.id}")?.find()
    }

    void saveRate(User user, String materialId, Integer value) {
        def person = personGraphService.ensurePerson(user)
        def material = graphDBService.getVertex(materialId)
        graphDBService.addEdge('Rate', person, material, [
                value: value,
                date : new Date()
        ])
    }

    Double getAverageRate(String materialId) {
        graphDBService.queryVertex("SELECT AVG(value.asFloat()) FROM Rate WHERE in.@rid=#${materialId}")?.find()?.getProperty('AVG') as Double
    }

    List<Map> mostRatedPersons(Integer daysCount, Integer count = 1000) {
        def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)
        graphDBService.queryAndUnwrapVertex("SELECT SUM(value) as rate, owner.@rid as @rid, owner.identifier as identifier, owner.title as title FROM (SELECT value, first(in.in('Own')) as owner FROM (SELECT * FROM (SELECT EXPAND(inE('Rate')) FROM Material) WHERE date > '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0')) GROUP BY owner  ORDER BY rate DESC LIMIT ${count}")
    }
    List<Map> mostRated4tabloPersons(Integer daysCount, Integer count = 1000) {
        /*def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)*/
        graphDBService.queryAndUnwrapVertex("SELECT SUM(score) as rate, owner.@rid as @rid, owner.identifier as identifier, owner.title as title  from (select outE('About').score as score,first(in('Own')) as owner from Material)  group by owner.@rid order by rate desc LIMIT  ${count}")
    }
    List<Map> mostRated4tabloGroups(Integer daysCount, Integer count = 1000) {
        /*def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)*/
        graphDBService.queryAndUnwrapVertex("SELECT SUM(score) as rate, owner.@rid as @rid, owner.identifier as identifier, owner.title as title, owner.imageId as imageId  from (select outE('About').score as score,first(out('Share')) as owner from Material)  group by owner.@rid order by rate desc LIMIT  ${count}")
    }
    List<Map> mostRatedGroups(Integer daysCount, Integer count = 1000) {
        /*def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)*/
        graphDBService.queryAndUnwrapVertex("select sum(out.inE('Rate').value) as rate,in.@rid as @rid,in.title as title,in.identifier as idNumber,in.imageId as imageId from Share where in.@class='Group' group by in order by rate desc limit ${count}")
    }

    def personRate(def personId) {
        graphDBService.queryAndUnwrapVertex("SELECT SUM(INE('Rate').value) as rate, COUNT(*) as count FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE identifier = ${personId}) WHERE @class = 'Article' OR  @class = 'Talk'")?.find()
    }
    def personRate4Tablo(def personId) {
        graphDBService.queryAndUnwrapVertex("select sum(score) as rate from (select outE('About').score as score,first(in('Own').identifier) as owner from Material ) where owner= ${personId}")?.find()
    }
}
