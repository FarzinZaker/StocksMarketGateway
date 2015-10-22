package stocks.graph

import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientEdge
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User

class GroupGraphService {

    def graphDBService
    def messageSource
    def personGraphService

    public static String MEMBERSHIP_TYPE_NORMAL = 'normal'
    public static String MEMBERSHIP_TYPE_EXCEPTIONAL = 'exceptional'

    OrientVertex create(String title, String description, Long imageId, String membershipType, Integer membership1MonthPrice, Integer membership3MonthPrice, Integer membership6MonthPrice, Integer membership12MonthPrice, Boolean allowExceptionalUsers, User owner) {
        def groupVertex = graphDBService.addVertex('Group', [
                title                 : title,
                description           : description,
                imageId               : imageId,
                membershipType        : membershipType,
                membership1MonthPrice : membership1MonthPrice,
                membership3MonthPrice : membership3MonthPrice,
                membership6MonthPrice : membership6MonthPrice,
                membership12MonthPrice: membership12MonthPrice,
                allowExceptionalUsers : allowExceptionalUsers,
                ownerType             : 'user'
        ])

        Vertex person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('Own', person, groupVertex, [:])
        groupVertex
    }

    OrientVertex update(String id, String title, String description, Long imageId, String membershipType, Integer membership1MonthPrice, Integer membership3MonthPrice, Integer membership6MonthPrice, Integer membership12MonthPrice, Boolean allowExceptionalUsers) {
        graphDBService.editVertex(id, [
                title                 : title,
                description           : description,
                imageId               : imageId,
                membershipType        : membershipType,
                membership1MonthPrice : membership1MonthPrice,
                membership3MonthPrice : membership3MonthPrice,
                membership6MonthPrice : membership6MonthPrice,
                membership12MonthPrice: membership12MonthPrice,
                allowExceptionalUsers : allowExceptionalUsers,
                ownerType             : 'user'
        ])
    }

    OrientVertex delete(String id) {
        graphDBService.deleteVertex(id)
    }

    List<Map> listForOwner(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
    }

    List<Map> listForEditor(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Editor')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
    }

    List<Map> listForAuthor(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Author')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
    }

    List<Map> listForHome(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Member')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group' AND ownerType <> 'system'")
    }

    List<Map> listForMember(User user) {
        def person = personGraphService.ensurePerson(user)
        def list = graphDBService.queryVertex("SELECT * FROM (SELECT EXPAND(OUT('Member')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
        list.collect { OrientVertex group ->
            def membership = getMemberEdge(graphDBService.getVertex(group.id?.toString()?.replace('#', '')), person)
            if (membership)
                graphDBService.unwrapVertex(group) + graphDBService.unwrapEdge(membership)
            else
                graphDBService.unwrapVertex(group)
        }
    }

    OrientEdge getEditorEdge(OrientVertex group, OrientVertex person) {
        graphDBService.findEdgeByLabel(person, group, 'Editor')
    }

    OrientEdge addEditor(String groupId, User user) {
        def group = graphDBService.getVertex(groupId)
        def person = personGraphService.ensurePerson(user)
        def edge = getEditorEdge(group, person)
        if (!edge)
            edge = graphDBService.addEdge('Editor', person, group, [:])
        edge
    }

    List<Map> editorList(String groupId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Editor')) FROM Group WHERE @rid = #${groupId}) WHERE @class = 'Person'")
    }

    List<Map> deleteEditor(String groupId, User user) {
        def group = graphDBService.getVertex(groupId)
        def person = personGraphService.ensurePerson(user)
        def edge = getEditorEdge(group, person)
        graphDBService.deleteEdge(edge)
    }

    List<Map> deleteEditor(String groupId, String userId) {
        def group = graphDBService.getVertex(groupId)
        def person = graphDBService.getVertex(userId)
        def edge = getEditorEdge(group, person)
        graphDBService.deleteEdge(edge)
    }

    OrientEdge getAuthorEdge(OrientVertex group, OrientVertex person) {
        graphDBService.findEdgeByLabel(person, group, 'Author')
    }

    OrientEdge addAuthor(String groupId, User user) {
        def group = graphDBService.getVertex(groupId)
        def person = personGraphService.ensurePerson(user)
        def edge = getAuthorEdge(group, person)
        if (!edge)
            edge = graphDBService.addEdge('Author', person, group, [:])
        edge
    }

    List<Map> authorList(String groupId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Author')) FROM Group WHERE @rid = #${groupId}) WHERE @class = 'Person'")
    }

    List<Map> deleteAuthor(String groupId, User user) {
        def group = graphDBService.getVertex(groupId)
        def person = personGraphService.ensurePerson(user)
        def edge = getAuthorEdge(group, person)
        graphDBService.deleteEdge(edge)
    }

    List<Map> deleteAuthor(String groupId, String userId) {
        def group = graphDBService.getVertex(groupId)
        def person = graphDBService.getVertex(userId)
        def edge = getAuthorEdge(group, person)
        graphDBService.deleteEdge(edge)
    }

    OrientEdge getMemberEdge(OrientVertex group, OrientVertex person) {
        graphDBService.findEdgeByLabel(person, group, 'Member')
    }

    OrientEdge addMember(String groupId, User user, Date startDate, Date endDate, String type = MEMBERSHIP_TYPE_NORMAL) {
        def group = graphDBService.getVertex(groupId)
        def person = personGraphService.ensurePerson(user)
        def edge = getMemberEdge(group, person)
        if (!edge) {
            def propertyMap = [type: type]
            if (startDate)
                propertyMap.startDate = startDate
            if (endDate)
                propertyMap.endDate = endDate
            edge = graphDBService.addEdge('Member', person, group, propertyMap)
        }
        edge
    }

    List<Map> memberList(String groupId, Integer skip = 0, Integer limit = 10) {
        def list = graphDBService.queryVertex("SELECT * FROM (SELECT EXPAND(IN('Member')) FROM Group WHERE @rid = #${groupId}) WHERE @class = 'Person' SKIP ${skip} LIMIT ${limit}")
        list.collect { OrientVertex person ->
            def membership = getMemberEdge(graphDBService.getVertex(groupId), person)
            if (membership)
                graphDBService.unwrapVertex(person) + graphDBService.unwrapEdge(membership)
            else
                graphDBService.unwrapVertex(person)
        }
    }

    Long memberCount(String groupId) {
        graphDBService.count("SELECT COUNT(*) FROM (SELECT EXPAND(IN('Member')) FROM Group WHERE @rid = #${groupId}) WHERE @class = 'Person'")
    }

    List<Map> deleteMember(String membershipId) {
        def edge = graphDBService.getEdge(membershipId)
        graphDBService.deleteEdge(edge)
    }

    List<Map> publicList(Integer skip = 0, Integer limit = 20) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Group WHERE membershipType = 'open' AND ownerType <> 'system' SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> publicUnregisteredList(Long userId, Integer skip = 0, Integer limit = 20) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Group WHERE membershipType = 'open' AND ownerType <> 'system' and @rid not in (SELECT @rid FROM (SELECT EXPAND(OUT('Member')) FROM Person WHERE identifier = ${userId}) WHERE @class = 'Group') SKIP ${skip} LIMIT ${limit}")
    }

    void membershipPurge(){
        def date = new Date().format('yyyy-MM-dd HH:mm:ss')
        graphDBService.executeCommand("DELETE EDGE Member WHERE endDate < '${date}'")
    }

    Long publicCount() {
        graphDBService.count("SELECT COUNT(*) FROM Group WHERE membershipType = 'open' AND ownerType <> 'system'")
    }

    Map getUserMembershipInGroup(String groupId, Long userId){
        graphDBService.queryAndUnwrapEdge("SELECT * FROM Member WHERE in.@rid = #${groupId} AND out.identifier = ${userId}")?.find()
    }

    List<Map> groupMaterialList(String groupId, Integer skip = 0, Integer limit = 20) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> propertyCloud(String groupId){
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property WHERE @rid in (SELECT in.@rid FROM About WHERE out.@rid in (SELECT out.@rid FROM Share WHERE in.@rid = #${groupId})) GROUP BY @rid ORDER BY count DESC")
    }

}
