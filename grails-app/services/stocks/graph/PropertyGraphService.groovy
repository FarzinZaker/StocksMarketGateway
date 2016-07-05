package stocks.graph

import com.google.gdata.data.analytics.StartDate
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import groovy.time.TimeCategory
import stocks.twitter.Search.TwitterPerson
import stocks.twitter.Search.TwitterProperty

class PropertyGraphService {

    def graphDBService
    def messageSource

    public static String TYPE_COIN = 'Coin'
    public static String TYPE_CURRENCY = 'Currency'
    public static String TYPE_FUTURE = 'Future'
    public static String TYPE_METAL = 'Metal'
    public static String TYPE_OIL = 'Oil'
    public static String TYPE_SYMBOL = 'Symbol'

    OrientVertex ensureProperty(String type, Long identifier, String title) {

        def property = graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${identifier}")
        if (!property) {
            property = graphDBService.addVertex(type, [
                    identifier: identifier,
                    title     : title
            ])
        }

        def rid = property?.id?.toString()
        def searchData = TwitterProperty.findByRid(rid)
        if (!searchData)
            searchData = new TwitterProperty(rid: rid)
        searchData.title = title
        searchData.identifier = identifier
        searchData.type = messageSource.getMessage("twitter.search.type.${type}", null, '', Locale.ENGLISH)
        searchData.save()

        property
    }

    List<Map> propertyCloud() {
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property GROUP BY @rid ORDER BY count DESC")
    }

    OrientVertex getByIdentifier(Long id) {
        graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${id}")
    }

    Map getAndUnwrapByIdentifier(Long id) {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${id}"))
    }

    List<Map> authorList(String propertyId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Person WHERE @rid IN (SELECT IN('About').IN('Own').@rid FROM Property WHERE @rid = #${propertyId})")
    }

    List<Map> propertyCloud(String propertyId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('About').OUT('About')) FROM Property WHERE @rid = #${propertyId}) WHERE @rid <> #${propertyId}")
    }

    List<Map> mostActiveProperties(Integer daysCount, Integer count) {
        def startDate = new Date()?.clearTime()
        use(TimeCategory) {
            startDate = startDate - daysCount.days
        }
        def calendar = Calendar.getInstance()
        calendar.setTime(startDate)
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT @rid, @class, identifier, title, count(*) as count FROM (SELECT EXPAND(OUT('About')) FROM Material WHERE publishDate > '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0') GROUP BY @rid) ORDER BY count DESC LIMIT ${count}")
    }

}
