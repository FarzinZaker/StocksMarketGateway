package stocks.feed

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.params.HttpClientParams
import org.jsoup.Jsoup
import org.ocpsoft.prettytime.PrettyTime
import stocks.RandomUserAgent
import stocks.util.EncodingHelper

class ExternalNewsService {

    private static String POLITICAL = 'political'
    private static String ECONOMIC = 'economic'
    public static List<String> categoryList = [ECONOMIC, POLITICAL]

    def messageSource

//    @Cacheable('newsCache')
    def newsList(String type) {

        def feeds = ExternalNews.createCriteria().list {
            if (type != 'all')
                eq('category', type)
            order('date', ORDER_DESCENDING)
            maxResults(10)
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
        try {
            feeds.addAll farsNews()
        } catch (ignored) {
        }
        try {
            feeds.addAll asrIran()
        } catch (ignored) {
        }
        try {
            feeds.addAll bourseNews()
        } catch (ignored) {
        }
        try {
            feeds.addAll tabnak()
        } catch (ignored) {
        }
        try {
            feeds.addAll tasnim()
        } catch (ignored) {
        }
        try {
            feeds.addAll irna()
        } catch (ignored) {
        }
        try {
            feeds.addAll isna()
        } catch (ignored) {
        }
//        try{feeds.addAll sena()}catch(ignored){}
        try {
            feeds.addAll boursePress()
        } catch (ignored) {
        }
        try {
            feeds.addAll mellatBazar()
        } catch (ignored) {
        }
        feeds = feeds.findAll { it.date }
        feeds.each {
            it.identifier = EncodingHelper.MD5("${it.title}-${it.source}")
            def item = ExternalNews.findByIdentifier(it.identifier as String)
            if (!item) {
                item = new ExternalNews()
                item.properties = it
                if (item.title)
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

    List<Map> sena() {
        readRSS(
                [
                        [
                                url     : 'http://www.sena.ir/RSS.ashx',
                                category: ECONOMIC
                        ]
                ], 'sena')
    }

    List<Map> boursePress() {
        def result = readRSS(
                [
                        [
                                url     : 'http://boursepress.ir/page/rss',
                                category: ECONOMIC
                        ]
                ], 'boursePress')

        result.each { item ->
            use(TimeCategory) {
                item.date = item.date - 4.hours - 30.minutes
            }
        }
        result
    }

    List<Map> mellatBazar() {
        readRSS(
                [
                        [
                                url     : 'http://mellatbazar.ir/fa/rss/2',
                                category: ECONOMIC
                        ],
                        [
                                url     : 'http://mellatbazar.ir/fa/rss/3',
                                category: ECONOMIC
                        ],
                        [
                                url     : 'http://mellatbazar.ir/fa/rss/8',
                                category: ECONOMIC
                        ],
                        [
                                url     : 'http://mellatbazar.ir/fa/rss/9',
                                category: POLITICAL
                        ]
                ], 'mellatBazar')
    }


    List<Map> readRSS(List<Map> feeds, String source) {
        def list = []
        feeds.each { rss ->
            try {
                def url = new URL(rss.url)
                def html = url.getText([connectTimeout: 3000, readTimeout: 10000], "UTF-8")
//                def stream = url.openStream()
//                def scanner = new Scanner(stream, "UTF-8")
//                def pre = scanner.useDelimiter("\\A")
//                def html = pre.next()
                list.addAll(new XmlSlurper().parseText(html).channel.item.collect {
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


