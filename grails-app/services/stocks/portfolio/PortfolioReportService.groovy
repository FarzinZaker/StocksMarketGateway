package stocks.portfolio

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.PortfolioReportHelper

import java.beans.Introspector

class PortfolioReportService {

    def portfolioPropertyManagementService
    def grailsApplication

    def updateReport(PortfolioReport report) {
        def data = report.data
        switch (report.type) {
            case PortfolioReportHelper.REPORT_BENEFIT_LOSS:
                data = updateBenefitLossReport(report?.portfolio)
        }
        PortfolioReport.withNewTransaction {
            report = PortfolioReport.get(report.id)
            report.data = data
            report.isUpdated = true
            report.save(flush: true)
        }
    }

    private def updateBenefitLossReport(Portfolio portfolio) {
        def items = PortfolioItem.findAllByPortfolio(portfolio)
        def actions = PortfolioAction.findAllByPortfolioAndActionTypeInList(portfolio, ['b', 's'])
        if (!actions?.size())
            return ([
                    data : [],
                    total: 0
            ] as JSON)
        def minDate = actions.collect { it.actionDate }?.min()?.clearTime()
        def dates = []
        while (minDate < new Date()) {
            minDate = minDate?.clearTime()
            dates.add(minDate)
            use(TimeCategory) {
                minDate = minDate + 1.day
            }
        }
//        def dates = actions.collect { it.actionDate?.clearTime() }.unique { a, b -> a <=> b }
        Map<PortfolioItem, Map<Long, Map<String, Long>>> report = [:]
        items.each { item ->
            report.put(item, [:])
            def clazz = Introspector.decapitalize(item.itemType.split('\\.').last())
            dates.sort().each { Date date ->
                def time = date?.time
                report[item].put(time, [:])
                report[item][time]["realPrice"] = (portfolioPropertyManagementService.getValueOfProperty(clazz, item.propertyId, date, dates.min() as Date, new Date())) as Long
            }
        }

        actions.sort { it.actionDate }.each {
            action ->
                def row = report[action.portfolioItem][action?.actionDate?.clearTime()?.time]

                if (action.actionType == 'b') {
                    report[action.portfolioItem].findAll { it.key >= action.actionDate?.clearTime()?.time }.each {
                        increaseMapItemValue(it.value, "totalShareCount", action.shareCount)
                        increaseMapItemValue(it.value, "totalBuyCount", action.shareCount)
                        increaseMapItemValue(it.value, "totalBuyPrice", action.shareCount * action.sharePrice)
                    }
                } else {
                    report[action.portfolioItem].findAll { it.key >= action.actionDate?.clearTime()?.time }.each {
                        increaseMapItemValue(it.value, "totalShareCount", -action.shareCount)
                    }
                    increaseMapItemValue(row, "totalSellCount", action.shareCount)
                    increaseMapItemValue(row, "totalSellPrice", action.shareCount * action.sharePrice)
                }
        }
        items.each {
            item ->
                dates.each { date ->
                    def row = report[item][date?.time]
                    def averageBuyPrice = (row["totalBuyPrice"] ?: 0) / (row["totalBuyCount"] ?: 1)
                    def averageSellPrice = (row["totalSellPrice"] ?: 1) / (row["totalSellCount"] ?: 1)
                    def realPrice = row["realPrice"] ?: 0
                    row["potentialBenefitLoss"] = (row["totalShareCount"] ?: 0) * (realPrice - averageBuyPrice)
                    row["actualBenefitLoss"] = (row["totalSellCount"] ?: 0) * (averageSellPrice - averageBuyPrice)
                    row["totalBenefitLoss"] = row["potentialBenefitLoss"] + row["actualBenefitLoss"]
                }
        }

        Map<Date, Map<String, Long>> totalReport = [:]
        dates.each {
            date ->
                totalReport[date?.time] = [:]
                def yesterday = dates.findAll { it < date }?.sort { -it.time }?.find()
                totalReport[date?.time]["actualBenefitLoss"] = items.sum { item -> report[item][date?.time]["actualBenefitLoss"] ?: 0 } + (yesterday ? totalReport[yesterday?.time]["actualBenefitLoss"] : 0)
                totalReport[date?.time]["potentialBenefitLoss"] = items.sum { item -> report[item][date?.time]["potentialBenefitLoss"] ?: 0 }
                totalReport[date?.time]["totalBenefitLoss"] = totalReport[date?.time]["actualBenefitLoss"] + totalReport[date?.time]["potentialBenefitLoss"]
        }

        return ([
                data : totalReport.sort {
                    -it.key
                }.collect {
                    [
                            time                : it.key,
                            date                : jalaliDate(new Date(it.key), false, false),
                            actualBenefitLoss   : it.value["actualBenefitLoss"],
                            potentialBenefitLoss: it.value["potentialBenefitLoss"],
                            totalBenefitLoss    : it.value["totalBenefitLoss"]
                    ]
                },
                total: totalReport.size()
        ] as JSON)
    }

    def jalaliDate = { date, hm, timeOnly ->
        def result = ''
        if (date) {
            def cal = Calendar.getInstance()
            cal.setTime(date)

            def jc = new JalaliCalendar(cal)
            if ((hm && Boolean.parseBoolean(hm?.toString())) || (timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
            if ((hm && Boolean.parseBoolean(hm?.toString())) && !(timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += ' '
            if (!(timeOnly && Boolean.parseBoolean(timeOnly?.toString())))
                result += String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        }
        result
    }

    private static void increaseMapItemValue(Map<String, Long> map, String key, Long value) {
        if (!map.containsKey(key))
            map.put(key, 0)
        map[key] += value
    }

    def ensureEmptyReports(Portfolio portfolio) {
        PortfolioReportHelper.REPORTS.each { reportType ->
            def report = PortfolioReport.findByPortfolioAndType(portfolio, reportType)
            if (!report)
                report = new PortfolioReport(portfolio: portfolio, type: reportType, data: getEmptyReportData(reportType))
            report.isUpdated = false
            report.save(flush: true)
        }
    }

    def invalidateReports(Portfolio portfolio) {
        ensureEmptyReports(portfolio)
        PortfolioReport.findAllByPortfolio(portfolio).each {
            it.isUpdated = false
            it.save(flush: true)
        }
    }

    String getEmptyReportData(String reportType) {
        switch (reportType) {
            case PortfolioReportHelper.REPORT_BENEFIT_LOSS:
                return ([data: [], total: 0] as JSON)
        }
        ''
    }
}
