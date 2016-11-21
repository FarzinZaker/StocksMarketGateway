package stocks.graph

import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientEdge
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import groovy.time.TimeCategory
import stocks.User
import stocks.twitter.Search.TwitterGroup
import stocks.twitter.Search.TwitterMaterial

class GroupGraphService {

    def graphDBService
    def messageSource
    def personGraphService

    public static String MEMBERSHIP_TYPE_NORMAL = 'normal'
    public static String MEMBERSHIP_TYPE_EXCEPTIONAL = 'exceptional'

    OrientVertex create(String title, String description, Long imageId, String authorType, String membershipType, Integer membership1MonthPrice, Integer membership3MonthPrice, Integer membership6MonthPrice, Integer membership12MonthPrice, Boolean allowExceptionalUsers, Boolean allowNewPosts, User owner) {
        def groupVertex = graphDBService.addVertex('Group', [
                title                 : title,
                description           : description,
                imageId               : imageId,
                authorType            : authorType,
                membershipType        : membershipType,
                membership1MonthPrice : membership1MonthPrice,
                membership3MonthPrice : membership3MonthPrice,
                membership6MonthPrice : membership6MonthPrice,
                membership12MonthPrice: membership12MonthPrice,
                allowExceptionalUsers : allowExceptionalUsers,
                allowNewPosts         : allowNewPosts,
                ownerType             : 'user'
        ])

        Vertex person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('Own', person, groupVertex, [:])

        def rid = groupVertex?.id?.toString()
        def searchData = TwitterGroup.findByRid(rid)
        if (!searchData)
            searchData = new TwitterGroup(rid: rid)
        searchData.title = title
        searchData.description = description?.replaceAll("<(.|\n)*?>", '')
        searchData.imageId = imageId
        searchData.save()

        groupVertex
    }

    OrientVertex update(String id, String title, String description, Long imageId, String authorType, String membershipType, Integer membership1MonthPrice, Integer membership3MonthPrice, Integer membership6MonthPrice, Integer membership12MonthPrice, Boolean allowExceptionalUsers, Boolean allowNewPosts) {
        def groupVertex = graphDBService.editVertex(id, [
                title                 : title,
                description           : description,
                imageId               : imageId,
                authorType            : authorType,
                membershipType        : membershipType,
                membership1MonthPrice : membership1MonthPrice,
                membership3MonthPrice : membership3MonthPrice,
                membership6MonthPrice : membership6MonthPrice,
                membership12MonthPrice: membership12MonthPrice,
                allowExceptionalUsers : allowExceptionalUsers,
                allowNewPosts         : allowNewPosts,
                ownerType             : 'user'
        ])

        def rid = groupVertex?.id?.toString()
        def searchData = TwitterGroup.findByRid(rid)
        if (!searchData)
            searchData = new TwitterGroup(rid: rid)
        searchData.title = title
        searchData.description = description?.replaceAll("<(.|\n)*?>", '')
        searchData.imageId = imageId
        searchData.save()

        groupVertex
    }

    OrientVertex delete(String id) {
        graphDBService.deleteVertex(id)
    }

    List<Map> listForOwner(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
    }

    List<Map> openAuthorList(User user) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Group WHERE authorType = 'open'")
    }

    Map getOwner(String groupId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Own')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) WHERE @class = 'Person'")?.find()
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

    List<Map> memberGroups(User user) {
        def person = personGraphService.ensurePerson(user)
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Member')) FROM Person WHERE identifier = ${user.id}) WHERE @class = 'Group'")
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
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Editor')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) WHERE @class = 'Person'")
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
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Author')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) WHERE @class = 'Person'")
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
        def list = graphDBService.queryVertex("SELECT * FROM (SELECT EXPAND(IN('Member')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) WHERE @class = 'Person' SKIP ${skip} LIMIT ${limit}")
        list.collect { OrientVertex person ->
            def membership = getMemberEdge(graphDBService.getVertex(groupId), person)
            if (membership)
                graphDBService.unwrapVertex(person) + graphDBService.unwrapEdge(membership)
            else
                graphDBService.unwrapVertex(person)
        }
    }

    Long memberCount(String groupId) {
        graphDBService.count("SELECT COUNT(*) FROM (SELECT EXPAND(IN('Member')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) WHERE @class = 'Person'")
    }

    Long postCount(String groupId) {
        graphDBService.count("select inE('Share').size() as COUNT from Group where @rid= #${groupId?.replace('#', '')}")
    }

    List mostActiveGroups(int count = 4) {
        graphDBService.queryAndUnwrapVertex("select inE('Share').size() as count, @rid as @rid, title, imageId from Group order by count desc limit ${count}")
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

    void membershipPurge() {
        def date = new Date().format('yyyy-MM-dd HH:mm:ss')
        graphDBService.executeCommand("DELETE EDGE Member WHERE endDate < '${date}'")
    }

    Long publicCount() {
        graphDBService.count("SELECT COUNT(*) FROM Group WHERE membershipType = 'open' AND ownerType <> 'system'")
    }

    Map getUserMembershipInGroup(String groupId, Long userId) {
        graphDBService.queryAndUnwrapEdge("SELECT * FROM Member WHERE in.@rid = #${groupId?.replace('#', '')} AND out.identifier = ${userId}")?.find()
    }

    List<Map> groupMaterialList(String groupId, Integer skip = 0, Integer limit = 20) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId?.replace('#', '')}) SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> propertyCloud(String groupId) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property WHERE @rid in (SELECT in.@rid FROM About WHERE out.@rid in (SELECT out.@rid FROM Share WHERE in.@rid = #${groupId?.replace('#', '')})) GROUP BY @rid ORDER BY count DESC LIMIT 200")
    }

    List<Map> largestGroups(Integer count = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT in('Member').size() as size, @rid as @rid, title, imageId FROM Group WHERE ownerType = 'user') ORDER BY size DESC LIMIT ${count}")
    }

    List<Map> topScoredMaterials(String groupId, Integer daysCount, Integer count) {
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
        graphDBService.queryAndUnwrapVertex("SELECT AVG(OutE('About').score) as score, * FROM (SELECT EXPAND(IN('Share')) FROM #${groupId?.replace('#', '')}) ${dateParameter} GROUP BY @rid ORDER BY score DESC LIMIT ${count}")
    }

    List<Map> mostVisitedMaterials(String groupId, Integer daysCount, Integer count) {
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
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM #${groupId?.replace('#', '')}) ${dateParameter} ORDER BY visitCount DESC LIMIT ${count}")
    }

    List<Map> topRatedMaterials(String groupId, Integer daysCount, Integer count) {
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
        graphDBService.queryAndUnwrapVertex("SELECT AVG(InE('Rate').value) as rate, * FROM (SELECT EXPAND(IN('Share')) FROM #${groupId?.replace('#', '')}) ${dateParameter} GROUP BY @rid ORDER BY rate DESC LIMIT ${count}")
    }

    List<Map> mostCommentedMaterials(String groupId, Integer daysCount, Integer count) {
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
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, publishDate, title, description, imageId, visitCount, in('RelatedTo').size() as comments FROM (SELECT EXPAND(IN('Share')) FROM #${groupId?.replace('#', '')}) ${dateParameter} ORDER BY comments DESC LIMIT ${count}")
    }

    void transfer(String id, User user) {
        graphDBService.executeCommand("DELETE EDGE Own WHERE in.@rid = #${id}")
        def group = graphDBService.getVertex("#${id}")
        def person = personGraphService.ensurePerson(user)
        graphDBService.addEdge('Own', person, group)
    }
}

