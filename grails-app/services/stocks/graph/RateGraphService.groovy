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

    List<Map> mostRatedPersons(Integer daysCount, Integer count) {
        def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)
        graphDBService.queryAndUnwrapVertex("SELECT first(person.@rid) as @rid, first(person.identifier) as identifier, first(person.title) as title, avg as rate FROM (SELECT AVG(value) as avg, person FROM (SELECT in.in('Own') as person, value FROM Rate WHERE date > '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0') GROUP BY person ORDER BY avg DESC LIMIT 10)")
    }
}
