package stocks.feed

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.ocpsoft.prettytime.PrettyTime
import stocks.util.EncodingHelper

class ExternalAnalysisService {

    private static String POLITICAL = 'political'
    private static String ECONOMIC = 'economic'
    public static List<String> categoryList = [ECONOMIC, POLITICAL]

    def messageSource

    @Cacheable('newsCache')
    def news() {

        def feeds = ExternalNews.createCriteria().list {
            order('date', ORDER_DESCENDING)
            maxResults(100)
        }.collect {
            [
                    identifier  : it.identifier,
                    title       : it.title,
                    time        : it.date.time,
                    link        : it.link,
                    category    : it.category,
                    source      : it.source,
                    sourceString: messageSource.getMessage("newsSource.${it.source}", null, it.source, Locale.ENGLISH),
                    dateString  : new PrettyTime(new Locale('fa')).format(it.date),
                    clickCount  : it.clickCount

            ]
        }
        [
                data      : feeds,
                categories: categoryList.collect {
                    [
                            value: it,
                            text : messageSource.getMessage("newsCategory.${it}", null, it, Locale.ENGLISH)
                    ]
                }
        ]
    }

    def refresh() {

        def feeds = []
        try{feeds.addAll farsNews()}catch(ignored){}
        feeds = feeds.findAll { it.date }
        feeds.each {
            it.identifier = EncodingHelper.MD5("${it.title}-${it.source}")
            def item = News.findByIdentifier(it.identifier as String)
            if (!item) {
                item = new ExternalNews()
                item.properties = it
                item.save(flush: true)
            }
        }
    }

    List<Map> farsNews() {
        readRSS([
                [
                        url     : 'http://farsnews.com/rss.php?srv=1',
                        category: POLITICAL
                ],
                [
                        url     : 'http://farsnews.com/rss.php?srv=13',
                        category: POLITICAL
                ],
                [
                        url     : 'http://farsnews.com/rss.php?srv=2',
                        category: ECONOMIC
                ]
        ], 'farsNews')
    }

    List<Map> readRSS(List<Map> feeds, String source) {
        def list = []
        feeds.each { rss ->
            try {
                list.addAll(new XmlSlurper().parse(rss.url as String).channel.item.collect {
                    [
                            title   : it.title.text(),
                            date    : new Date(it.pubDate.text() as String),
                            link    : it.link.text(),
                            category: rss.category,
                            source  : source
                    ]
                })
            }
            catch (ignored) {
                throw ignored
            }
        }
        list.findAll { it } as List<Map>
    }
}
