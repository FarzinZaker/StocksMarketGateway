package stocks

import groovy.time.TimeCategory
import groovyx.net.http.HTTPBuilder
import org.springframework.cache.annotation.Cacheable
import stocks.util.EncodingHelper

class FeedService {

    @Cacheable('newsCache')
    def news() {
        def categories = [
                [
                        name: 'economic',
                        id  : 4
                ],
                [
                        name: 'political',
                        id  : 3
                ],
//                [
//                        name: 'athletic',
//                        id  : 8
//                ],
//                [
//                        name: 'accident',
//                        id  : 5
//                ],
//                [
//                        name: 'technology',
//                        id  : 6
//                ],
//                [
//                        name: 'art',
//                        id  : 7
//                ],
//                [
//                        name: 'world',
//                        id  : 2
//                ],
//                [
//                        name: 'medicine',
//                        id  : 10
//                ],
//                [
//                        name: 'tourism',
//                        id  : 12
//                ],
//                [
//                        name: 'various',
//                        id  : 9
//                ]
        ]
        def list = []
        categories.each { category ->
            list.addAll(new XmlSlurper().parseText("http://www.shahrekhabar.com/rss.jsp?type=${category.id}".toURL().text).channel.item.collect {
                def sourceParams = it.description?.toString()?.split('-')
                def date = new Date()
                while (sourceParams[1].contains('  '))
                    sourceParams[1] = sourceParams[1].replace('  ', ' ')
                def dateParams = sourceParams[1].trim().split(' ')
                use(TimeCategory) {
                    if (dateParams[1] == 'دقيقه')
                        date = date - (dateParams[0].toInteger()).minutes
                    else if (dateParams[1] == 'ساعت')
                        date = date - (dateParams[0].toInteger()).hours
                    else if (dateParams[1] == 'روز')
                        date = date - (dateParams[0].toInteger()).days
                    else
                        throw new Exception(dateParams[1])
                }

                [
                        id        : EncodingHelper.MD5("${it.title?.toString()}-${sourceParams[0]}"),
                        title     : it.title?.toString(),
                        source    : sourceParams[0],
                        dateString: sourceParams[1],
                        time      : date.time,
                        link      : new HTTPBuilder(it.link)?.get([:])?."**"?.find {
                            it.name().toLowerCase() == 'iframe'
                        }?.@src?.toString() ?: it.link,
                        category  : category.name
                ]

            })
        }


        [
                data      : list.sort { -it.time },
                categoryList: categories.collect { it.name }]
    }
}


