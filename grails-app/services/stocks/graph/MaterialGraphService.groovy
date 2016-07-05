package stocks.graph

import com.google.common.base.CaseFormat
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.ocpsoft.prettytime.PrettyTime
import stocks.User
import stocks.twitter.Search.TwitterMaterial
import stocks.twitter.Search.TwitterProperty

class MaterialGraphService {

    def graphDBService
    def personGraphService
    def messageSource

    public static String TYPE_ARTICLE = 'Article'
    public static String TYPE_TALK = 'Talk'
    public static String TYPE_SCREENER = 'Screener'
    public static String TYPE_BACKTEST = 'BackTest'
    public static String TYPE_PORTFOLIO = 'Portfolio'
    public static String TYPE_ANALYSIS = 'Analysis'

    public static TYPES = [TYPE_ARTICLE, TYPE_SCREENER, TYPE_BACKTEST, TYPE_PORTFOLIO, TYPE_ANALYSIS]
    public static ENABLED_TYPES = [TYPE_ARTICLE]

    OrientVertex ensureArticle(User owner, Long identifier, String title, String description, Long imageId) {

        graphDBService.executeCommand("DELETE EDGE Own WHERE in.identifier = ${identifier}")

        graphDBService.executeCommand("UPDATE Material SET title = '${title}', description = '${description?.replaceAll("<(.|\n)*?>", '') ?: '-'}', imageId = ${imageId ?: 0} WHERE identifier = ${identifier}")

        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        if (!material) {
            material = graphDBService.addVertex(TYPE_ARTICLE, [
                    identifier : identifier,
                    publishDate: new Date(),
                    title      : title,
                    description: description?.replaceAll("<(.|\n)*?>", '') ?: '-',
                    imageId    : imageId ?: 0,
                    visitCount : 0
            ])
        }

        def person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('Own', person, material)

        def rid = material?.id?.toString()
        def searchData = TwitterMaterial.findByRid(rid)
        if (!searchData)
            searchData = new TwitterMaterial(rid: rid)
        searchData.title = title
        searchData.summary = description?.replaceAll("<(.|\n)*?>", '')
        searchData.identifier = identifier
        searchData.type = messageSource.getMessage("twitter.search.type.${TYPE_ARTICLE}", null, '', Locale.ENGLISH)
        searchData.imageId = imageId
        searchData.authorRid = person.id?.toString()
        searchData.save()

        material
    }

    OrientVertex createTalk(User owner, String description) {
        def material = graphDBService.addVertex(TYPE_TALK, [
                publishDate: new Date(),
                description: description ?: '-',
                lastUpdated: new Date()
        ])

        def person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('Own', person, material)

        def rid = material?.id?.toString()
        def searchData = new TwitterMaterial(rid: rid)
        searchData.summary = description?.replaceAll("<(.|\n)*?>", '')
        searchData.type = messageSource.getMessage("twitter.search.type.${TYPE_TALK}", null, '', Locale.ENGLISH)
        searchData.authorRid = person.id?.toString()
        searchData.save()

        material
    }

    OrientVertex editTalk(String id, String description) {
        def talk = graphDBService.editVertex(id, [
                description       : description,
                lastUpdated: new Date()
        ])

        def rid = talk?.id?.toString()
        def searchData = TwitterMaterial.findByRid(rid)
        searchData.summary = description?.replaceAll("<(.|\n)*?>", '')
        searchData.save()

        talk
    }

    OrientVertex removeMaterial(Long identifier) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")
        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        graphDBService.deleteVertex(material.id.toString().replace('#', ''))
    }

    OrientVertex removeMaterial(String id) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.@rid = #${id?.replace('#', '')}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.@rid = #${id?.replace('#', '')}")
        graphDBService.deleteVertex("#" + id?.replace('#', ''))
    }

    List<Map> listByGroup(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByGroup(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) WHERE (@class = 'Article' OR @class = 'Talk') GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listByAuthor(Long authorId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE identifier = ${authorId}) WHERE (@class = 'Article' OR @class = 'Talk') ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listByAuthor(String authorId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${authorId}) WHERE (@class = 'Article' OR @class = 'Talk') ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByAuthor(String authorId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${authorId}) WHERE (@class = 'Article' OR @class = 'Talk') GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listOldForHome(Long userId, Date maxDate = new Date(), Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(SET(UNIONALL(OUT('Follow').OUT('Own'), OUT('Follow').IN('About')))) From Person WHERE identifier = ${userId}) WHERE (@class = 'Article' OR @class = 'Talk') AND publishDate < '${maxDate.format('yyyy-MM-dd HH:mm:ss')}' ORDER BY publishDate DESC LIMIT ${limit}")
    }

    List<Map> listNewForHome(Long userId, Date minDate = new Date(), Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(SET(UNIONALL(OUT('Follow').OUT('Own'), OUT('Follow').IN('About')))) From Person WHERE identifier = ${userId}) WHERE (@class = 'Article' OR @class = 'Talk') AND publishDate > '${minDate.format('yyyy-MM-dd HH:mm:ss')}' ORDER BY publishDate DESC LIMIT ${limit}")
    }

    List<Map> topForHome(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(SET(UNIONALL(OUT('Follow').OUT('Own'), OUT('Follow').IN('About')))) From Person WHERE identifier = ${userId}) WHERE (@class = 'Article' OR @class = 'Talk') GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listByProperty(String propertyId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('About')).@rid FROM Property WHERE @rid = #${propertyId}) WHERE (@class = 'Article' OR @class = 'Talk') ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByProperty(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(IN('About')) FROM Property WHERE @rid = #${groupId}) WHERE (@class = 'Article' OR @class = 'Talk') GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getPropertyList(String materialId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('About')) FROM Material WHERE @rid = #${materialId})")
    }

    OrientVertex getByIdentifier(Long id) {
        graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}")
    }

    Map getAndUnwrapByIdentifier(Long id) {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}"))
    }

    List<Map> getRelatedMaterials(String id, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid in (SELECT out.@rid FROM About WHERE in.@rid in (SELECT in.@rid FROM About WHERE out.@rid = #${id})) AND @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getNewMaterials(String id, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getFollowList(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT FROM (SELECT EXPAND(OUT('Follow')) FROM Person WHERE identifier = ${userId}) SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getMeta(String id) {
        graphDBService.queryAndUnwrapVertex("SELECT EXPAND(UNIONALL(IN('Own'), OUT('Share'))) FROM #${id}")
    }

    void recordVisit(String id) {
        graphDBService.executeCommand("UPDATE Material INCREMENT visitCount = 1 WHERE @rid = #${id}")
    }

    def twitList(String type) {

        graphDBService.queryAndUnwrapVertex("SELECT * FROM ${type} ORDER BY publishDate DESC SKIP 0 LIMIT 5").collect {
            [
                    identifier : it.identifier,
                    title      : it.title,
                    time       : it.publishDate.time,
                    link       : "/${CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, it.label)}/thread/${it.identifier}",
                    dateString : jalaliDate(it.publishDate, true, false),//new PrettyTime(new Locale('fa')).format(it.publishDate as Date),
                    clickCount : it.visitCount,
                    description: it.description,
                    imageId    : it.imageId

            ]
        }
    }

    List<Map> mostActiveUsers(Integer daysCount, Integer count){
        def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)
        graphDBService.queryAndUnwrapVertex("SELECT first(owner.@rid) as @rid, first(owner.title) as title, first(owner.identifier) as identifier, count FROM (SELECT owner, COUNT(*) as count FROM (SELECT In('Own') as owner, @rid FROM Material WHERE publishDate > '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0') GROUP BY owner) ORDER BY count DESC LIMIT ${count}")
    }

    def jalaliDate = { date, hm, timeOnly ->
        def result = ''
        if (date) {
            def cal = Calendar.getInstance()
            cal.setTime(date)

            def jc = new JalaliCalendar(cal)
            if ((hm && Boolean.parseBoolean(hm?.toString())) || (timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
            if ((hm && Boolean.parseBoolean(hm?.toString())) && !(timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += ' '
            if (!(timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        }
        result
    }
}
