package stocks.commodity.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import groovy.util.slurpersupport.GPathResult
import org.ccil.cowan.tagsoup.Parser
import org.hibernate.SessionFactory
import stocks.DataServiceState
import stocks.FarsiNormalizationFilter
import stocks.commodity.Commodity
import stocks.commodity.Group
import stocks.commodity.MainGroup
import stocks.commodity.Producer
import stocks.commodity.Provider
import stocks.commodity.Subgroup
import stocks.commodity.TradeStatistics
import stocks.commodity.event.TradeStatisticsEvent

class TradeStatisticsDataService {

//    static schedules = [
//            [
//                    method : 'importData',
//                    trigger: [
//                            type      : 'Simple',
//                            parameters: [repeatInterval: 120000l, startDelay: 10000]
//                    ]
//                    trigger: [
//                            type      : 'Cron',
//                            parameters: [cronExpression: '0 10 12 2 OCT ? 2014']
//                    ]
//            ]
//    ]

    def commodityEventGateway

    def importData() {
        def startDate = new Date()
        def endDate = new Date()
        use(TimeCategory) {
            startDate = startDate - 20.years
        }

        parseData(startDate, endDate)
    }

    private void parseData(Date startDate, Date endDate) {
        try {
            def checkPointReached = false
            def state = getLastState()
            if (!state) {
                checkPointReached = true
                state.queryDate = startDate
            } else if (state.status == 'successful') {
                use(TimeCategory) {
                    state.queryDate = state.queryDate + 1.day
                }
            } else if (state.status == 'finished') {
                return
            }
            def stopped = false
            def mainGroups = getSelectOptions('grouhAsli')
            mainGroups.each { mainGroup ->
                if(stopped)
                    return
                if (checkPointReached || mainGroup.id == state.mainGroup.id) {
                    def groups = getSelectOptions('grouh', mainGroup.id)
                    groups.each { group ->
                        if(stopped)
                            return
                        if (checkPointReached || group.id == state.group.id) {
                            def subgroups = getSelectOptions('zirGrouh', mainGroup.id, group.id)
                            subgroups.each { subgroup ->
                                if(stopped)
                                    return
                                if (checkPointReached || subgroup.id == state.subgroup.id) {
                                    def producers = getSelectOptions('tolidKonande', mainGroup.id, group.id, subgroup.id)
                                    producers.each { producer ->
                                        if(stopped)
                                            return
                                        if (checkPointReached || producer.id == state.producer.id) {
                                            checkPointReached = true
                                            Thread.start {
                                                DataServiceState.withTransaction {
                                                    logState([mainGroup: mainGroup, group: group, subgroup: subgroup, producer: producer, queryDate: state.queryDate, status: 'running'])
                                                }
                                            }.join()

                                            if (state.queryDate < endDate) {
                                                try {
                                                    use(TimeCategory) {
                                                        extractData(mainGroup, group, subgroup, producer, state.queryDate, state.queryDate)
                                                    }
                                                    Thread.start {
                                                        DataServiceState.withTransaction {
                                                            logState([mainGroup: mainGroup, group: group, subgroup: subgroup, producer: producer, queryDate: state.queryDate, status: 'successful'])
                                                        }
                                                    }.join()
                                                } catch (ex) {
                                                    Thread.start {
                                                        DataServiceState.withTransaction {
                                                            logState([mainGroup: mainGroup, group: group, subgroup: subgroup, producer: producer, queryDate: state.queryDate, status: 'failed', message: ex.message, stackTrace: ex.stackTrace])
                                                        }
                                                    }.join()
                                                }
                                            } else {
                                                Thread.start {
                                                    DataServiceState.withTransaction {
                                                        logState([mainGroup: mainGroup, group: group, subgroup: subgroup, producer: producer, queryDate: state.queryDate, status: 'finished'])
                                                    }
                                                }.join()
                                            }
                                            stopped = true

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        catch (ignored) {
        }
    }

    private def extractData(mainGroup, group, subgroup, producer, Date startDate, Date endDate) {
        def stillHasRecords = true
        def currentPage = 1
        while (stillHasRecords) {
            def htmlParser = loadUrl(mainGroup.id, group.id, subgroup.id, producer.id, startDate, endDate, currentPage, 100)
            def containerDiv = htmlParser?.'**'?.find { it.@class == 'divScroll' }
            def rows = containerDiv?.'**'?.findAll { it.name() == 'tr' }
            if (!rows || rows?.size() == 1)
                stillHasRecords = false
            rows?.remove(0)
            rows?.each { row ->
                def tradeStatisticsEvent = new TradeStatisticsEvent()

                tradeStatisticsEvent.subgroup = findSubgroup(mainGroup, group, subgroup)
                tradeStatisticsEvent.producer = findProducer(producer)

                def cells = row.children()
//                println(cells)
                if (!cells[0].text().contains('داده ای در جدول وجود ندارد') && !cells[0].text().contains('تاریخ آخرین بروزرسانی')) {
                    tradeStatisticsEvent.commodity = findCommodity(FarsiNormalizationFilter.apply(cells[0].text() as String))
                    tradeStatisticsEvent.contractType = FarsiNormalizationFilter.apply(cells[2].text() as String)
                    tradeStatisticsEvent.lowestPrice = parseInteger(cells[3].text() as String)
                    tradeStatisticsEvent.closingWeightedAveragePrice = parseInteger(cells[4].text() as String)
                    tradeStatisticsEvent.highestPrice = parseInteger(cells[5].text() as String)
                    tradeStatisticsEvent.offering = parseInteger(cells[6].text() as String)
                    tradeStatisticsEvent.bestOfferingPrice = parseInteger(cells[7].text() as String)
                    tradeStatisticsEvent.demand = parseInteger(cells[8].text() as String)
                    tradeStatisticsEvent.tradeVolume = parseInteger(cells[9].text() as String)
                    tradeStatisticsEvent.tradeValue = parseLong(cells[10].text() as String)
                    tradeStatisticsEvent.tradeDate = parseDate(cells[11].text() as String)
                    tradeStatisticsEvent.deliveryDate = parseDate(cells[12].text() as String)
                    tradeStatisticsEvent.deliveryPoint = FarsiNormalizationFilter.apply(cells[13].text() as String)
                    tradeStatisticsEvent.provider = findProvider(cells[14].text() as String)
                    tradeStatisticsEvent.settlementMaturityDate = parseDate(cells[15].text() as String)

                    tradeStatisticsEvent.data = find(tradeStatisticsEvent)
                    commodityEventGateway.send(tradeStatisticsEvent)

                } else if (cells[0].text().contains('داده ای در جدول وجود ندارد')) {
                    stillHasRecords = false
                }
            }
            currentPage++
        }

    }

    private static TradeStatistics find(TradeStatisticsEvent object) {
        TradeStatistics.findBySubgroupAndProducerAndCommodityAndProviderAndTradeDate(
                object.subgroup,
                object.producer,
                object.commodity,
                object.provider,
                object.tradeDate
        )
    }

    private static Commodity findCommodity(commodity) {
        def result = Commodity.findByName(commodity as String)
        if (!result) {
            Thread.start {
                Commodity.withTransaction {
                    result = new Commodity()
                    result.name = commodity as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static Provider findProvider(provider) {
        def result = Provider.findByName(provider as String)
        if (!result) {
            Thread.start {
                Provider.withTransaction {
                    result = new Provider()
                    result.name = provider as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static Producer findProducer(producer) {
        def result = Producer.findByCodeAndName(producer.id as Integer, producer.name as String)
        if (!result) {
            Thread.start {
                Producer.withTransaction {
                    result = new Producer()
                    result.code = producer.id as Integer
                    result.name = producer.name as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static Subgroup findSubgroup(mainGroup, group, subgroup) {
        def parent = findGroup(mainGroup, group)
        def result = Subgroup.findByGroupAndCodeAndName(parent, subgroup.id as Integer, subgroup.name as String)
        if (!result) {
            Thread.start {
                Subgroup.withTransaction {
                    result = new Subgroup()
                    result.group = parent
                    result.code = subgroup.id as Integer
                    result.name = subgroup.name as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static Group findGroup(mainGroup, group) {
        def parent = findMainGroup(mainGroup)
        def result = Group.findByMainGroupAndCodeAndName(parent, group.id as Integer, group.name as String)
        if (!result) {
            Thread.start {
                Group.withTransaction {
                    result = new Group()
                    result.mainGroup = parent
                    result.code = group.id as Integer
                    result.name = group.name as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static MainGroup findMainGroup(mainGroup) {
        def result = MainGroup.findByCodeAndName(mainGroup.id as Integer, mainGroup.name as String)
        if (!result) {
            Thread.start {
                MainGroup.withTransaction {
                    result = new MainGroup()
                    result.code = mainGroup.id as Integer
                    result.name = mainGroup.name as String
                    result.save()
                }
            }.join()
        }
        result
    }

    private static def getSelectOptions(String name, mainGroup = 0, group = 0, subgroup = 0, Boolean skipFirst = true) {
        def htmlParser = loadUrl(mainGroup, group, subgroup)
        def select = htmlParser?.'**'?.find { it.@name == name }?.'**'?.findAll { it.name() == 'option' }?.collect {
            [id: it?.@value?.text(), name: it?.text()?.trim()]
        }
        if (skipFirst)
            if (select?.size() > 1)
                select = select[1..select.size() - 1]
            else
                select = []
        select
    }

    private
    static GPathResult loadUrl(mainGroup = 0, group = 0, subgroup = 0, producer = 0, startDate = new Date() - 1, endDate = new Date(), page = 1, pageSize = 1) {
        def result = null
        def tryCount = 0
        while (!result && tryCount < 5) {
            try {
                URLConnection connection = new URL("http://www.ime.co.ir/report.dispatcher?lang=fa&reportId=rep3&randomId=rep3&pageNumber=${page}&pageSize=${pageSize}&grouhAsli=${mainGroup}&grouh=${group}&zirGrouh=${subgroup}&tolidKonande=${producer}&fromDate=${formatDate(startDate)}&toDate=${formatDate(endDate)}").openConnection()
                connection.connectTimeout = 60000
                connection.readTimeout = 60000
                result = new XmlSlurper(new Parser()).parse(connection.inputStream)
//                result = new XmlSlurper(new Parser()).parse("http://www.ime.co.ir/report.dispatcher?lang=fa&reportId=rep3&randomId=rep3&pageNumber=${page}&pageSize=${pageSize}&grouhAsli=${mainGroup}&grouh=${group}&zirGrouh=${subgroup}&tolidKonande=${producer}&fromDate=${formatDate(startDate)}&toDate=${formatDate(endDate)}")
            }
            catch (ignored) {
                tryCount++
            }
        }
        result
    }

    private static def formatDate(Date date) {

        if (!date)
            return '-'

        def cal = Calendar.getInstance()
        cal.setTime(date)
        def jc = new JalaliCalendar(cal as GregorianCalendar)
        String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }

    private static Integer parseInteger(String input) {
        if (!input || input.trim() == '' || input.trim() == 'null')
            return null
        input.replace(',', '').toInteger()
    }

    private static Long parseLong(String input) {
        if (!input || input.trim() == '' || input.trim() == 'null')
            return null
        input.replace(',', '').toLong()
    }
}
