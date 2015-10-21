package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
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

    Double getAverageRate(String materialId){
        graphDBService.queryVertex("SELECT AVG(value.asFloat()) FROM Rate WHERE in.@rid=#${materialId}")?.find()?.getProperty('AVG') as Double
    }
}
