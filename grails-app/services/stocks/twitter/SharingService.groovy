package stocks.twitter

import groovy.time.TimeCategory
import org.ccil.cowan.tagsoup.Parser
import stocks.User
import stocks.graph.MaterialGraphService
import stocks.twitter.Search.TwitterMaterial

class SharingService {

    def graphDBService
    def materialGraphService
    def propertyGraphService
    def messageSource
    def commonGraphService
    def rateGraphService
    def groupGraphService
    def adjustedPriceSeries9Service
    def coinSeries9Service
    def currencySeries9Service
    def futureSeries9Service
    def indexSeries9Service
    def metalSeries9Service
    def oilSeries9Service

    void shareArticle(User owner, Long identifier, String title, String description, Long imageId, List<Map> properties, List<Map> mentionList, List<String> groups) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Mention WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")

        def materialVertex = materialGraphService.ensureArticle(owner, identifier, title, description, imageId)

        def searchData = TwitterMaterial.findByIdentifier(identifier)

        def propertyTitleList = []
        properties.each { property ->
            def propertyVertex = propertyGraphService.ensureProperty(property.type as String, property.identifier as Long, property.title as String)
            graphDBService.addEdge('About', materialVertex, propertyVertex)
            propertyTitleList << "${messageSource.getMessage("twitter.search.type.${property.type}", null, '', Locale.ENGLISH)} - ${property.title}"
        }
        searchData.propertyTitleList = propertyTitleList

        mentionList.each { mention ->
            def tragetVertex = graphDBService.getVertex(mention.rid as String)
            graphDBService.addEdge('Mention', materialVertex, tragetVertex)
        }

        def groupRidList = []
        groups.each { groupId ->
            def groupVertex = graphDBService.getVertex(groupId)
            graphDBService.addEdge('Share', materialVertex, groupVertex)
            groupRidList << "#${groupId}"
        }
        searchData.groupRidList = groupRidList

        searchData.save()
    }

    void shareTalk(User owner, String description, List<Map> properties, List<Map> mentionList) {

        def materialVertex = materialGraphService.createTalk(owner, description)

        def searchData = TwitterMaterial.findByRid(materialVertex?.id?.toString())

        def propertyTitleList = []
        properties.each { property ->
            def propertyVertex = propertyGraphService.ensureProperty(property.type as String, property.identifier as Long, property.title as String)
            def prediction = property.prediction ?: [:]
            if (prediction?.size())
                prediction = prediction + [applied: false]
            graphDBService.addEdge('About', materialVertex, propertyVertex, prediction)
            propertyTitleList << "${messageSource.getMessage("twitter.search.type.${property.type}", null, '', Locale.ENGLISH)} - ${property.title}"
        }
        searchData.propertyTitleList = propertyTitleList

        mentionList.each { mention ->
            def tragetVertex = graphDBService.getVertex(mention.rid as String)
            graphDBService.addEdge('Mention', materialVertex, tragetVertex)
        }

        def groupVertex = commonGraphService.publicGroup
        graphDBService.addEdge('Share', materialVertex, groupVertex)
        searchData.groupRidList = [groupVertex?.id?.toString()]

        searchData.save()
    }

    void reShareTalk(String rid, String description, List<Map> properties, List<Map> mentionList) {

        graphDBService.executeCommand("DELETE EDGE About WHERE out.@rid = #${rid}")
        graphDBService.executeCommand("DELETE EDGE Mention WHERE out.@rid = ${rid}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.@rid = ${rid}")

        def materialVertex = materialGraphService.editTalk(rid, description)

        def searchData = TwitterMaterial.findByRid(materialVertex?.id?.toString())

        def propertyTitleList = []
        properties.each { property ->
            def propertyVertex = propertyGraphService.ensureProperty(property.type as String, property.identifier as Long, property.title as String)
            graphDBService.addEdge('About', materialVertex, propertyVertex)
            propertyTitleList << "${messageSource.getMessage("twitter.search.type.${property.type}", null, '', Locale.ENGLISH)} - ${property.title}"
        }
        searchData.propertyTitleList = propertyTitleList

        mentionList.each { mention ->
            def tragetVertex = graphDBService.getVertex(mention.rid as String)
            graphDBService.addEdge('Mention', materialVertex, tragetVertex)
        }

        def groupVertex = commonGraphService.publicGroup
        graphDBService.addEdge('Share', materialVertex, groupVertex)
        searchData.groupRidList = [groupVertex?.id?.toString()]

        searchData.save()
    }

    void removeMaterial(Long identifier) {
        materialGraphService.removeMaterial(identifier)
    }

    void removeMaterial(String id) {
        materialGraphService.removeMaterial(id)
    }

    void string(Long id) {
        materialGraphService.removeMaterial(id)
    }

    List<Map> materialShareGroups(Long identifier) {
        graphDBService.queryAndUnwrapVertex("SELECT FROM Group WHERE @rid in (SELECT DISTINCT(in.@rid) FROM Share WHERE out.identifier = ${identifier})")
    }

    List<Map> materialAboutProperties(Long identifier) {
        graphDBService.queryAndUnwrapVertex("SELECT FROM Property WHERE @rid in (SELECT DISTINCT(in.@rid) FROM About WHERE out.identifier = ${identifier})")
    }

    List<Map> suggestFollowList(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT FROM (SELECT EXPAND(\$Total) LET \$Trav = (SELECT @rid as @rid, @class as @class, identifier, title, IN('Follow').size() as followersCount, \$DEPTH as depth FROM (TRAVERSE OUT('Follow') FROM Person WHERE identifier = ${userId}) WHERE \$DEPTH > 1 AND @rid NOT IN (SELECT OUT('Follow') FROM Person WHERE identifier = ${userId})), \$Common = (SELECT @rid as @rid, @class as @class, identifier, title, IN('Follow').size() as followersCount, 9999 as depth FROM Followable WHERE identifier <> 0 AND identifier <> ${userId} AND @rid NOT IN (SELECT OUT('Follow') FROM Person WHERE identifier = ${userId})), \$Total = SET(UNIONALL(\$Trav, \$Common))) ORDER BY depth ASC, followersCount DESC SKIP ${skip} LIMIT ${limit}")
    }

    def extractTextRelations(String text) {

        def items = ['user/wall', 'group/home', 'index/info', 'symbol/info', 'twitter/property']
        items.each {
            text = text.replace('article/' + it, it)
        }

        def htmlParser = new XmlSlurper(new Parser()).parseText(text)

        def mentionList = htmlParser?.'**'?.findAll { it?.@class?.toString() == 'hashAuthor' }?.collect {
            [
                    rid  : it.'@data-id'?.toString(),
                    type : it.'@data-clazz'?.toString(),
                    title: it.text()?.replace('@', '')?.replace('_', ' ')
            ]
        }?.unique() ?: []

        def tagList = htmlParser?.'**'?.findAll { it?.@class?.toString() == 'hashTag' }?.collect {
            [
                    identifier: it.'@data-id'?.toString()?.toLong(),
                    type      : it.'@data-clazz'?.toString(),
                    title     : it.text()?.replace('#', '')?.replace('_', ' ')
            ]
        }?.unique() ?: []

        [
                mentionList: mentionList,
                tagList    : tagList,
                text       : text
        ]
    }

    Map<String, Map> tops() {
        [
                mostActivePerson  : materialGraphService.mostActiveUsers(1, 1)?.find(),
                mostRatedPerson   : rateGraphService.mostRatedPersons(30, 1)?.find(),
                mostActiveProperty: propertyGraphService.mostActiveProperties(7, 1)?.find(),
                largestGroup      : groupGraphService.largestGroups(1)?.find()
        ]
    }

    def applyATwitScore() {
        def date = new Date()
        def calendar = Calendar.getInstance()
        calendar.setTime(date)
        def item = graphDBService.queryAndUnwrapEdge("SELECT * FROM About WHERE applied = false and endDate < '${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} 0:0:0' LIMIT 1")?.find()
        if (!item)
            return
        def property = graphDBService.getAndUnwrapVertex(item.in)
        def twit = graphDBService.getAndUnwrapVertex(item.out)

        def publishDate = twit.publishDate
        def priceList = []
        def startPrice = 0
        switch (property.label) {
            case 'Symbol':
                priceList = adjustedPriceSeries9Service.lastTradePriceList(property.identifier, publishDate, item.endDate)
                startPrice = adjustedPriceSeries9Service.lastLastTradePrice(property.identifier, publishDate)
                break;
            case 'Index':
                priceList = indexSeries9Service.finalIndexValueList(property.identifier, publishDate, item.endDate)
                startPrice = indexSeries9Service.lastFinalIndexValue(property.identifier, publishDate)
                break;
            case 'Coin':
                priceList = coinSeries9Service.priceList(property.identifier, publishDate, item.endDate)
                startPrice = coinSeries9Service.lastPrice(property.identifier, publishDate)
                break;
            case 'Currency':
                priceList = currencySeries9Service.priceList(property.identifier, publishDate, item.endDate)
                startPrice = currencySeries9Service.lastPrice(property.identifier, publishDate)
                break;
            case 'Metal':
                priceList = metalSeries9Service.priceList(property.identifier, publishDate, item.endDate)
                startPrice = metalSeries9Service.lastPrice(property.identifier, publishDate)
                break;
            case 'Oil':
                priceList = oilSeries9Service.priceList(property.identifier, publishDate, item.endDate)
                startPrice = oilSeries9Service.lastPrice(property.identifier, publishDate)
                break;
            case 'Future':
                priceList = futureSeries9Service.closingPriceList(property.identifier, publishDate, item.endDate)
                startPrice = futureSeries9Service.lastClosingPrice(property.identifier, publishDate)
                break;
        }
        if (priceList?.size() == 0) {
            switch (property.label) {
                case 'Symbol':
                    priceList = [adjustedPriceSeries9Service.lastLastTradePrice(property.identifier, item.endDate)]
                    break;
                case 'Index':
                    priceList = [indexSeries9Service.lastFinalIndexValue(property.identifier, item.endDate)]
                    break;
                case 'Coin':
                    priceList = [coinSeries9Service.lastPrice(property.identifier, item.endDate)]
                    break;
                case 'Currency':
                    priceList = [currencySeries9Service.lastPrice(property.identifier, item.endDate)]
                    break;
                case 'Metal':
                    priceList = [metalSeries9Service.lastPrice(property.identifier, item.endDate)]
                    break;
                case 'Oil':
                    priceList = [oilSeries9Service.lastPrice(property.identifier, item.endDate)]
                    break;
                case 'Future':
                    priceList = [futureSeries9Service.lastClosingPrice(property.identifier, item.endDate)]
                    break;
            }
        }

        def daysCount = 1
        def priceListSize = 1
        switch (item.period) {
            case '1w':
                daysCount = 7
                priceListSize = 3
                break
            case '4w':
                daysCount = 4 * 7
                priceListSize = 7
                break
            case '12w':
                daysCount = 12 * 7
                priceListSize = 2 * 7
                break
            case '26w':
                daysCount = 26 * 7
                priceListSize = 4 * 7
                break
        }

        while (priceList?.size() > priceListSize)
            priceList.remove(0)

        def score = 0
        startPrice = startPrice - 100
        if (item.type == 'benefit') {
            def criterionPrice = priceList.collect { it?.value }?.findAll { it }?.max()
            score = item.risk * (((criterionPrice - startPrice) / startPrice) / daysCount)
        } else if (item.type == 'loss') {
            def criterionPrice = priceList.collect { it?.value }?.findAll { it }?.min()
            score = item.risk * (((startPrice - criterionPrice) / startPrice) / daysCount)
        }

        graphDBService.editEdge(item.id, [applied: true, score: score])
    }

}
