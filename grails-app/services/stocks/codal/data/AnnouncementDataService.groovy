package stocks.codal.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import org.ccil.cowan.tagsoup.Parser
import org.quartz.JobDetail
import org.xml.sax.InputSource
import stocks.FarsiNormalizationFilter
import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent
import stocks.tse.Company
import stocks.tse.Symbol
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*


class AnnouncementDataService {

    def codalEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]

    void importData() {
        parseData("http://codal.ir/ReportList.aspx")
    }

    private static Announcement find(AnnouncementEvent object) {
        Announcement.findBySymbolPersianCodeAndCompanyNameAndTitleAndSendDateAndPublishDate(
                object.symbolPersianCode,
                object.companyName,
                object.title,
                object.sendDate,
                object.publishDate
        )
    }

    static def spec

    private void parseData(String url) {

        if(!spec)
            spec = Class.forName('org.watij.webspec.dsl.WebSpec').newInstance().mozilla()

        spec.open(url)
//        spec.hide()
        while (!spec.findWithId('ctl00_ContentPlaceHolder1_gvList').exists())
            Thread.sleep(1000)
        def result = spec.source()
//        spec.close()

        def htmlParser = new XmlSlurper(new Parser()).parseText(result)
        def containerDiv = htmlParser.'**'.find { it.@id == 'divLetterFormList' }
        def rows = containerDiv.'**'.findAll { it.name() == 'tr' }
        rows.remove(0)
        rows.each { row ->
            def announcementEvent = new AnnouncementEvent()
            def cells = row.children()
            announcementEvent.symbolPersianCode = FarsiNormalizationFilter.apply(cells[0].text() as String)
            announcementEvent.symbol = Symbol.findByPersianCode(FarsiNormalizationFilter.apply(announcementEvent.symbolPersianCode as String))
            announcementEvent.companyName = FarsiNormalizationFilter.apply(cells[1].text() as String)
            announcementEvent.company = Company.findByName(announcementEvent.companyName)
            announcementEvent.title = FarsiNormalizationFilter.apply(cells[2].text() as String)
            announcementEvent.sendDate = parseDateTime(cells[3].text())
            announcementEvent.publishDate = parseDateTime(cells[4].text())
            def linkUrl = cells[5].@onclick[0].text().replace('window.open(\'', '').replace('\');', '')
            if (linkUrl && linkUrl != '')
                announcementEvent.detailsUrl = "http://codal.ir/" + linkUrl
            linkUrl = cells[6].a.@href
            if (linkUrl && linkUrl != '')
                announcementEvent.pdfUrl = "http://codal.ir/" + linkUrl
            linkUrl = cells[7].a.@href
            if (linkUrl && linkUrl != '')
                announcementEvent.excelUrl = "http://codal.ir/" + linkUrl
            linkUrl = cells[8].a.@href
            if (linkUrl && linkUrl != '')
                announcementEvent.xmlUrl = "http://codal.ir/" + linkUrl
            linkUrl = cells[9].a.@href
            if (linkUrl && linkUrl != '')
                announcementEvent.xbrlUrl = linkUrl
            linkUrl = cells[10].@onclick[0].text().replace('window.open(\'', '').replace('\');', '')
            if (linkUrl && linkUrl != '')
                announcementEvent.attachmentsUrl = "http://codal.ir/" + linkUrl

            announcementEvent.data = find(announcementEvent)
            codalEventGateway.send(announcementEvent)
        }
    }


    private static Date parseDateTime(String dateTime) {
        def parts = dateTime.split(' ')
        def date = parts[0]
        def time = parts[1]
        def dateParts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        def timeParts = time.split(":")
        cal.set(Calendar.HOUR_OF_DAY, timeParts[0] as Integer)
        cal.set(Calendar.MINUTE, timeParts[1] as Integer)
        cal.set(Calendar.SECOND, timeParts[2] as Integer)
        cal.time
    }
}
