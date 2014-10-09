package stocks.codal.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.ccil.cowan.tagsoup.Parser
import org.jsoup.Jsoup
import stocks.FarsiNormalizationFilter
import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent
import stocks.tse.Company
import stocks.tse.Symbol
import groovyx.net.http.*
import sun.misc.GC


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

//        if(!spec)
//            spec = Class.forName('org.watij.webspec.dsl.WebSpec').newInstance().mozilla()
//
//        try {
//            spec.open(url)
////        spec.hide()
//            def counter = 0
//            while (!spec.findWithId('ctl00_ContentPlaceHolder1_gvList').exists()) {
//                Thread.sleep(1000)
//                if (++counter > 60) {
//                    spec = null
//                    GC.collect()
//                    return
//                }
//            }
//            def result = spec.source()
//        }
//        catch(ignored){
//            spec = null
//            GC.collect()
//            return
//        }
//        spec.close()

        def htmlParser = new XmlSlurper(new Parser()).parseText(getList(url))
        def containerDiv = htmlParser.'**'.find { it.@id == 'divLetterFormList' }
        def rows = containerDiv.'**'.findAll { it.name() == 'tr' }
        rows.remove(0)
        rows.each { row ->
            println(row)
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

    private static String getList(String url){
        String userAgent = 'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2' ;

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        method.setRequestHeader("Accept-Language", "en-US,en;q=0.5");
        method.setRequestHeader("Accept-Encoding", "gzip, deflate");
        method.setRequestHeader("Host", "www.codal.ir");
        method.setRequestHeader("Connection", "keep-alive");
        method.setRequestHeader("User-Agent", userAgent);

        client.executeMethod(method);
        def doc = Jsoup.parse(method.getResponseBodyAsString());

        PostMethod postMethod = new PostMethod("http://www.codal.ir/ReportList.aspx");
        postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        postMethod.setRequestHeader("Accept-Language", "en-US,en;q=0.5");
        postMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
        postMethod.setRequestHeader("Host", "www.codal.ir");
        postMethod.setRequestHeader("Connection", "keep-alive");
        postMethod.setRequestHeader("User-Agent", userAgent);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        postMethod.setRequestHeader("Pragma", "no-cache");
        postMethod.setRequestHeader("Referer", "http://www.codal.ir/ReportList.aspx");
        postMethod.setRequestHeader("X-MicrosoftAjax", "Delta=true");
//                    postMethod.setRequestHeader("Content-Length", "2524");


        postMethod.addParameter("ctl00_tvActivity_ExpandState", doc.select("#ctl00_tvActivity_ExpandState").val());
        postMethod.addParameter("ctl00_tvActivity_SelectedNode", doc.select("#ctl00_tvActivity_SelectedNode").val());
        postMethod.addParameter("__EVENTTARGET", 'ctl00$ContentPlaceHolder1$TimerLoad');
        postMethod.addParameter("__EVENTARGUMENT", doc.select("#__EVENTARGUMENT").val());
        postMethod.addParameter("ctl00_tvActivity_PopulateLog", doc.select("#ctl00_tvActivity_PopulateLog").val());
        postMethod.addParameter("ctl00_tvManagement_ExpandState", doc.select("#ctl00_tvManagement_ExpandState").val());
        postMethod.addParameter("ctl00_tvManagement_SelectedNode", doc.select("#ctl00_tvManagement_SelectedNode").val());
        postMethod.addParameter("ctl00_tvManagement_PopulateLog", doc.select("#ctl00_tvManagement_PopulateLog").val());
        postMethod.addParameter("ctl00_TreeView1_ExpandState", doc.select("#ctl00_TreeView1_ExpandState").val());
        postMethod.addParameter("ctl00_TreeView1_SelectedNode", doc.select("#ctl00_TreeView1_SelectedNode").val());
        postMethod.addParameter("ctl00_TreeView1_PopulateLog", doc.select("#ctl00_TreeView1_PopulateLog").val());
        postMethod.addParameter("__VIEWSTATE", doc.select("#__VIEWSTATE").val());
        postMethod.addParameter("__VIEWSTATEENCRYPTED", doc.select("#__VIEWSTATEENCRYPTED").val());

        postMethod.addParameter('ctl00$ContentPlaceHolder1$hdfGridPageGroupIndex', doc.select("#ctl00_ContentPlaceHolder1_hdfGridPageGroupIndex").val());
        postMethod.addParameter('ctl00$ContentPlaceHolder1$hdfGridPageIndex', doc.select("#ctl00_ContentPlaceHolder1_hdfGridPageIndex").val());
        postMethod.addParameter('ctl00$ContentPlaceHolder1$hdfSerial', doc.select("#ctl00_ContentPlaceHolder1_hdfSerial").val());
        postMethod.addParameter('ctl00$ContentPlaceHolder1$hdfThumbPrint', doc.select("#ctl00_ContentPlaceHolder1_hdfThumbPrint").val());

        postMethod.addParameter('ctl00$ContentPlaceHolder1$ScriptManager1', 'ctl00$ContentPlaceHolder1$upMain|ctl00$ContentPlaceHolder1$TimerLoad');
        postMethod.addParameter("__ASYNCPOST", "true");

        client.executeMethod(postMethod);
        postMethod.getResponseBodyAsString()
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
