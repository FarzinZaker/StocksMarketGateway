package stocks.feed

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.plugin.cache.Cacheable
import groovyx.net.http.HTTPBuilder
import org.ocpsoft.prettytime.PrettyTime
import stocks.FarsiNormalizationFilter
import stocks.util.EncodingHelper

class ExternalAnalysisService {

    private static String FUNDAMENTAL = 'fundamental'
    private static String TECHNICAL = 'technical'
    private static String TECHNO_FUNDAMENTAL = 'techno_fundamental'
    public static List<String> visibleCategoryList = [FUNDAMENTAL, TECHNICAL]
    public static List<String> categoryList = [FUNDAMENTAL, TECHNICAL, TECHNO_FUNDAMENTAL]

    def messageSource

//    @Cacheable('analysisCache')
    def analysisList() {

        def feeds = ExternalAnalysis.createCriteria().list {
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
                    sourceString: messageSource.getMessage("analysisSource.${it.source}", null, it.source, Locale.ENGLISH),
                    dateString  : new PrettyTime(new Locale('fa')).format(it.date),
                    clickCount  : it.clickCount,
                    imageUrl    : it.imageUrl

            ]
        }
        [
                data      : feeds,
                categories: visibleCategoryList.collect {
                    [
                            value: it,
                            text : messageSource.getMessage("analysisCategory.${it}", null, it, Locale.ENGLISH)
                    ]
                }
        ]
    }

    def refresh() {

        def feeds = []
        try {
            feeds.addAll TSN()
        } catch (ignored) {
        }
        try {
            feeds.addAll EMACO()
        } catch (ignored) {
        }
        try {
            feeds.addAll artaTahlil()
        } catch (ignored) {
        }
        try {
            feeds.addAll persianTahlil()
        } catch (ignored) {
        }
        feeds = feeds.findAll { it.date }
        feeds.each {
            it.identifier = EncodingHelper.MD5("${it.title}-${it.source}")
            def item = ExternalAnalysis.findByIdentifier(it.identifier as String)
            if (!item) {
                item = new ExternalAnalysis()
            }
            item.properties = it
            item.save(flush: true)
//            }
        }
    }

    List<Map> TSN() {
        def list = []
        (
                [] +
                        (1..5).collect {
                            [
                                    url     : "http://www.novinic.com/%D9%85%D8%B7%D8%A7%D9%84%D8%A8-%D9%88%DB%8C%DA%98%D9%87-%D9%86%D9%88%DB%8C%D9%86/PgrID/1021/PageID/${it}",
                                    category: FUNDAMENTAL
                            ]
                        } +
                        (1..5).collect {
                            [
                                    url     : "http://www.novinic.com/%D8%AA%D8%AD%D9%84%DB%8C%D9%84-%D8%B4%D8%B1%DA%A9%D8%AA-%D9%87%D8%A7-%D8%A8%DB%8C%D8%B4%D8%AA%D8%B1/PgrID/1022/PageID/${it}",
                                    category: FUNDAMENTAL
                            ]
                        } +
                        (1..5).collect {
                            [
                                    url     : "http://www.novinic.com/%D8%AA%D8%AD%D9%84%DB%8C%D9%84-%D8%B5%D9%86%D8%B9%D8%AA-%D8%A8%DB%8C%D8%B4%D8%AA%D8%B1/PgrID/1023/PageID/${it}",
                                    category: FUNDAMENTAL
                            ]
                        }
        ).each { Map item ->
            list.addAll TSNInternal(item.url as String, item.category as String)
        }
        list
    }

    List<Map> TSNInternal(String url, String category) {

        def result = []
        def http = new HTTPBuilder(url)
        def html = http.get([:])
        def containerDiv = html?.'**'?.find { it?.@class?.toString()?.contains('news') }
        def rows = containerDiv?.'**'?.findAll { it.@class?.toString()?.contains('article') }
        rows?.each { row ->

            def anchor = row?.'**'?.find { it.name() == 'H1' }?.'**'?.find { it.name() == 'A' }
            def image = row?.'**'?.find { it.name() == 'IMG' }
            def dateString = row?.'**'?.find {
                it.@class.toString() == 'meta_text'
            }?.text()?.split('/')?.first()?.trim()
            if (!dateString)
                dateString = row?.'**'?.find { it.@class.toString() == 'sub-title' }?.text()?.replace('-', '')?.trim()
            if (anchor)
                result << [
                        title   : anchor?.text()?.trim(),
                        date    : dateString ? getFullDateTime(dateString) : new Date(),
                        link    : anchor?.@href?.toString(),
                        category: category,
                        source  : 'tsn',
                        imageUrl: image && ['jpg', 'jpeg', 'gif', 'png'].any{image.@src?.toString()?.toLowerCase()?.endsWith(it)} ? 'http://www.novinic.com' + image.@src : null
                ]
        }

        result
    }

    List<Map> EMACO() {
        def list = []
        (
                [] +
                        (1..5).collect {
                            [
                                    url     : "http://ema-co.ir/index.php/2015-04-27-09-53-52/%D8%AA%D8%AD%D9%84%DB%8C%D9%84?start=${(it - 1) * 10}",
                                    category: FUNDAMENTAL
                            ]
                        }
        ).each { Map item ->
            list.addAll EMACOInternal(item.url as String, item.category as String)
        }
        list
    }

    List<Map> EMACOInternal(String url, String category) {

        def result = []
        def http = new HTTPBuilder(url)
        def html = http.get([:])
        def containerDiv = html?.'**'?.find { it?.@class?.toString()?.contains('category') }
        def rows = containerDiv?.'**'?.findAll { it.name() == 'TR' }
        rows.remove(0)
        rows?.each { row ->

            def anchor = row?.'**'?.find { it.name() == 'A' }
            def dateParts = anchor?.@href?.toString()?.split('/')[2]?.split('-')?.collect { it.toInteger() }
            if (anchor)
                result << [
                        title   : anchor?.text()?.trim(),
                        date    : dateParts ? arrayToDate(dateParts) : new Date(),
                        link    : 'http://ema-co.ir' + anchor?.@href?.toString(),
                        category: category,
                        source  : 'emaco',
                        imageUrl: null
                ]
        }

        result
    }

    List<Map> artaTahlil() {
        def list = []
        (
                [] +
                        [
                                [
                                        url     : "http://www.artatahlil.ir/?search=%D8%AA%D8%AD%D9%84%DB%8C%D9%84+%D8%A8%D9%86%DB%8C%D8%A7%D8%AF%DB%8C",
                                        category: FUNDAMENTAL
                                ]
                        ]
        ).each { Map item ->
            list.addAll artaTahlilInternal(item.url as String, item.category as String)
        }
        list
    }

    List<Map> artaTahlilInternal(String url, String category) {

        def result = []
        def http = new HTTPBuilder(url)
        def html = http.get([:])
        def containerDiv = html?.'**'?.find { it?.@class?.toString() == 'body' }
        def rows = containerDiv?.'**'?.findAll { it.name() == 'TR' }
        rows.remove(0)
        rows?.each { row ->

            def anchor = row?.'**'?.find { it.name() == 'A' }
            if (anchor) {
                def dateString = row?.'**'?.findAll { it.name() == 'TD' }[1].text()
                result << [
                        title   : anchor?.text()?.trim(),
                        date    : dateString ? getFullDateTime(dateString) : new Date(),
                        link    : anchor?.@href?.toString(),
                        category: category,
                        source  : 'artaTahlil',
                        imageUrl: null
                ]
            }
        }

        result
    }

    List<Map> persianTahlil() {
        def list = []
        (
                [] +
                        [
                                [
                                        url     : "http://www.persiantahlil.com/default.aspx?lng=fa&cid=CMSContent&gid=549&content=161",
                                        category: FUNDAMENTAL
                                ],
                                [
                                        url     : "http://www.persiantahlil.com/?lng=fa&cid=CMSContent&gid=549&content=1211",
                                        category: FUNDAMENTAL
                                ]
                        ]
        ).each { Map item ->
            list.addAll persianTahlilInternal(item.url as String, item.category as String)
        }
        list
    }

    List<Map> persianTahlilInternal(String url, String category) {

        def result = []
        def http = new HTTPBuilder(url)
        def html = http.get([:])
        def containerDiv = html?.'**'?.find { it.@class?.toString()?.contains('icontent') }
        def rows = containerDiv?.'**'?.findAll { it.@class.toString() == 'nt5-div' }
        rows?.each { row ->

            def anchor = row?.'**'?.find { it.@class.toString() == 'nt5-com' }?.'**'?.find { it.name() == 'A' }
            def image = row?.'**'?.find { it.name() == 'IMG' }
            def dateString = row?.'**'?.find {
                it.@class.toString() == 'nt5-date'
            }?.text()?.trim()?.replace('|', '#')?.split('#')?.first()?.trim()?.replace(' - ', ' ')?.replace('ماه', '')
            while (dateString.contains('  '))
                dateString = dateString.replace('  ', ' ')
            if (anchor)
                result << [
                        title   : anchor?.text()?.trim(),
                        date    : dateString ? getFullDateTime(dateString) : new Date(),
                        link    : 'http://www.persiantahlil.com' + anchor?.@href?.toString(),
                        category: category,
                        source  : 'persianTahlil',
                        imageUrl: image && ['jpg', 'jpeg', 'gif', 'png'].any{image.@src?.toString()?.toLowerCase()?.endsWith(it)} ? 'http://www.persiantahlil.com/' + image.@src : null
                ]
        }

        result
    }

    private static Date arrayToDate(List<Integer> array) {
        def calendar = Calendar.instance
        calendar.set(Calendar.YEAR, array[0])
        if (array.size() > 1)
            calendar.set(Calendar.MONTH, array[1])
        if (array.size() > 2)
            calendar.set(Calendar.DAY_OF_MONTH, array[2] - 1)
        if (array.size() > 3)
            calendar.set(Calendar.HOUR_OF_DAY, array[3])
        if (array.size() > 4)
            calendar.set(Calendar.MINUTE, array[4])
        if (array.size() > 5)
            calendar.set(Calendar.SECOND, array[5])
        calendar.time
    }

    private static Date getFullDateTime(String dateStr) {

        if (!dateStr || dateStr == '')
            return null

        def months = [
                FarsiNormalizationFilter.apply('فروردین'),
                FarsiNormalizationFilter.apply('اردیبهشت'),
                FarsiNormalizationFilter.apply('خرداد'),
                FarsiNormalizationFilter.apply('تیر'),
                FarsiNormalizationFilter.apply('مرداد'),
                FarsiNormalizationFilter.apply('شهریور'),
                FarsiNormalizationFilter.apply('مهر'),
                FarsiNormalizationFilter.apply('آبان'),
                FarsiNormalizationFilter.apply('آذر'),
                FarsiNormalizationFilter.apply('دی'),
                FarsiNormalizationFilter.apply('بهمن'),
                FarsiNormalizationFilter.apply('اسفند')
        ]
        def dateParts = dateStr.split(' ')
        def startIndex = 0
        while (dateParts[++startIndex].contains('شنبه'));
        ArrayList<Integer> dateArray = []
        dateArray[0] = dateParts[startIndex + 2] as Integer
        def normalizedMonth = FarsiNormalizationFilter.apply(dateParts[startIndex + 1]?.trim())
        for (def i = 0; i < months.size(); i++)
            if (normalizedMonth == months[i])
                dateArray[1] = i + 1
        dateArray[2] = dateParts[startIndex] as Integer
        dateArray[3] = dateArray[4] = dateArray[5] = 0
        if (dateParts.size() > startIndex + 3) {
            def timeParts = dateParts[startIndex + 3].split(':')
            dateArray[3] = timeParts[0] as Integer
            dateArray[4] = timeParts[1] as Integer
            if (timeParts.size() > 2)
                dateArray[5] = timeParts[2] as Integer
        }
        JalaliCalendar jc = new JalaliCalendar(dateArray[0], dateArray[1], dateArray[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        cal.set(Calendar.HOUR_OF_DAY, dateArray[3])
        cal.set(Calendar.MINUTE, dateArray[4])
        if (dateArray.size() > 5)
            cal.set(Calendar.SECOND, dateArray[5])
        cal.time
    }

}
