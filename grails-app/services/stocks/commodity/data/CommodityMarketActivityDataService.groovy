package stocks.commodity.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import grails.util.Environment
import groovyx.net.http.HTTPBuilder
import stocks.commodity.CommodityMarketActivity
import stocks.commodity.CommodityMarketHelper
import stocks.commodity.event.CommodityMarketActivityEvent
import stocks.tse.MarketActivity

import java.nio.charset.StandardCharsets

class CommodityMarketActivityDataService {

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 120000l, startDelay: 10000]
                    ]
            ]
    ]


    def commodityEventGateway

    def importData() {

        String urlParameters = [
                ObjectId: 'MarketTotalToday'
        ].collect { "${it.key}=${it.value}" }.join('&');
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "http://www.ime.co.ir/SubSystems/IME/Services/MarketTotal/MarketTotal.asmx/GetTodayGrid";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream())
        wr.write(postData);
        def data = JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map

        return

        def http = new HTTPBuilder("http://www.ime.co.ir/auction-total-report.html")
        def html = http.get([:])
//        def date = parseDate(html?.'**'?.find { it?.@class == 'selectedDate' }?.text()?.toString())

        def rows = html?.'**'?.find { it?.@id == 'TotalMarketTbl' }?.'**'?.findAll {
            it?.name() == 'TR' && it.@class.toString().startsWith('report')
        }

        rows.each { row ->
            def marketActivity = new CommodityMarketActivityEvent()
            marketActivity.date = date
            marketActivity.marketIdentifier = CommodityMarketHelper.marketIdentifier(row.children()[0].text()?.trim())
            marketActivity.internalVolume = row.children()[1].text()?.trim()?.replace(',', '') as Double
            marketActivity.exportVolume = row.children()[2].text()?.trim()?.replace(',', '') as Double
            marketActivity.internalValue = row.children()[3].text()?.trim()?.replace(',', '') as Double
            marketActivity.exportValue = row.children()[4].text()?.trim()?.replace(',', '') as Double
            marketActivity.internalBuyersCount = row.children()[5].text()?.trim()?.replace(',', '') as Integer
            marketActivity.exportBuyersCount = row.children()[6].text()?.trim()?.replace(',', '') as Integer
            marketActivity.internalSellersCount = row.children()[7].text()?.trim()?.replace(',', '') as Integer
            marketActivity.exportSellersCount = row.children()[8].text()?.trim()?.replace(',', '') as Integer
            marketActivity.internalTradeCount = row.children()[9].text()?.trim()?.replace(',', '') as Integer
            marketActivity.exportTradeCount = row.children()[10].text()?.trim()?.replace(',', '') as Integer

            marketActivity.data = CommodityMarketActivity.findByMarketIdentifierAndDate(marketActivity.marketIdentifier, marketActivity.date)
            commodityEventGateway.send(marketActivity)
//            println(marketActivity)
        }

    }


    private static Date parseDate(String date) {
        date = date?.trim()
        def dateParts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        cal.time
    }
}
