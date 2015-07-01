package stocks

import groovy.time.TimeCategory
import groovyx.net.http.HTTPBuilder
import org.springframework.cache.annotation.Cacheable
import stocks.util.EncodingHelper

class FeedService {

    private static String POLITICAL = 'political'
    private static String ECONOMIC = 'economic'
    public static String[] categories = [ECONOMIC, POLITICAL]

    @Cacheable('newsCache')
    def news() {
        def feeds = farsNews() + asrIran() + bourseNews() + tabnak() + tasnim() + irna() + isna()
        feeds = feeds.findAll { it.date }.sort { -it.date.time }
        if (feeds.size() > 500)
            feeds = feeds[0..499]
        feeds.each {
                it.id = EncodingHelper.MD5("${it.title}-${it.source}")
                it.time = it.date.time

            }
        [
                data        : feeds,
                categoryList: categories]
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

    List<Map> asrIran() {
        readRSS([
                [
                        url     : 'http://www.asriran.com/fa/rss/1/1',
                        category: POLITICAL
                ],
                [
                        url     : 'http://www.asriran.com/fa/rss/1/2',
                        category: POLITICAL
                ],
                [
                        url     : 'http://www.asriran.com/fa/rss/1/4',
                        category: ECONOMIC
                ]
        ], 'asrIran')
    }

    List<Map> bourseNews() {
        readRSS([
                [
                        url     : 'http://www.boursenews.ir/fa/rss/3',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.boursenews.ir/fa/rss/4',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.boursenews.ir/fa/rss/5',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.boursenews.ir/fa/rss/6',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.boursenews.ir/fa/rss/7',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.boursenews.ir/fa/rss/8',
                        category: ECONOMIC
                ]
        ], 'bourseNews')
    }

    List<Map> tabnak() {
        readRSS([
                [
                        url     : 'http://www.tabnak.ir/fa/rss/6',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.tabnak.ir/fa/rss/5',
                        category: POLITICAL
                ],
                [
                        url     : 'http://www.tabnak.ir/fa/rss/11',
                        category: POLITICAL
                ]
        ], 'tabnak')
    }

    List<Map> tasnim() {
        readRSS([
                [
                        url     : 'http://www.tasnimnews.com/rss/feed/?c=1&m=6&cat=1',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.tasnimnews.com/rss/feed/?c=1&m=6&cat=7',
                        category: POLITICAL
                ]
        ], 'tasnim')
    }

    List<Map> irna() {
        def result = readRSS([
                [
                        url     : 'http://www.irna.ir/fa/rss.aspx?kind=20',
                        category: ECONOMIC
                ],
                [
                        url     : 'http://www.irna.ir/fa/rss.aspx?kind=5',
                        category: POLITICAL
                ]
        ], 'irna')

        result.each { item ->
            use(TimeCategory) {
                item.date = item.date - 4.hours - 30.minutes
            }
        }
        result
    }

    List<Map> isna() {
        readRSS(
                [
                        [
                                url     : 'http://www.isna.ir/fa/Economy/feed',
                                category: ECONOMIC
                        ],
                        [
                                url     : 'http://www.isna.ir/fa/Politics/feed',
                                category: POLITICAL
                        ]
                ], 'isna')
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
            }
        }
        list.findAll { it } as List<Map>
    }
}


