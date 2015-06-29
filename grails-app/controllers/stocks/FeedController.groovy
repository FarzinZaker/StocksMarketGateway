package stocks

import grails.converters.JSON

class FeedController {

    def news() {
        def list = []
        [
                [
                        category: 'اقتصادی',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=4'
                ],
                [
                        category: 'سیاسی',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=3'
                ],
                [
                        category: 'ورزشی',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=8'
                ],
                [
                        category: 'حوادث',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=5'
                ],
                [
                        category: 'فناوری',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=6'
                ],
                [
                        category: 'هنری',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=7'
                ],
                [
                        category: 'جهان',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=2'
                ],
                [
                        category: 'پزشکی و سلامت',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=10'
                ],
                [
                        category: 'گردشگری',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=12'
                ],
                [
                        category: 'گوناگون',
                        rss     : 'http://www.shahrekhabar.com/rss.jsp?type=9'
                ],
        ].each { source ->
            list.addAll(new XmlSlurper().parseText(source.rss.toURL().text).channel.item.collect {
                [
                        title   : it.title?.toString(),
                        source  : it.description?.toString(),
                        link    : it.link?.toString(),
                        category: source.category
                ]

            })
        }


        render(list as JSON)
    }
}
