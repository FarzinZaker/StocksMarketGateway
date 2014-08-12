package stocks.codal.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import org.ccil.cowan.tagsoup.Parser
import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent
import stocks.tse.Company
import stocks.tse.Symbol

class AnnouncementDataService {

    def codalEventGateway

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

    private void parseData(String url) {
        def htmlParser = new XmlSlurper(new Parser()).parse(url)
        def containerDiv = htmlParser.'**'.find { it.@id == 'divLetterFormList' }
        def rows = containerDiv.'**'.findAll { it.name() == 'tr' }
        rows.remove(0)
        rows.each { row ->
            def announcementEvent = new AnnouncementEvent()
            def cells = row.children()
            announcementEvent.symbolPersianCode = cells[0].text()
            announcementEvent.symbol = Symbol.findByPersianCode(announcementEvent.symbolPersianCode)
            announcementEvent.companyName = cells[1].text()
            announcementEvent.company = Company.findByName(announcementEvent.companyName)
            announcementEvent.title = cells[2].text()
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
