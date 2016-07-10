package stocks.portfolio

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.Image
import stocks.portfolio.portfolioItems.PortfolioBrokerItem
import stocks.tse.Symbol
import stocks.Broker

import java.beans.Introspector
import java.text.DateFormat
import java.text.SimpleDateFormat

class PortfolioActionController {
    def springSecurityService
    def portfolioPropertyManagementService
    def portfolioActionManagementService

    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

    def jsonPortfolioActions() {
        def value = [:]

        def owner = springSecurityService.currentUser
        def _portfolio = Portfolio.get(params.id)
        if (_portfolio.ownerId != owner.id)
            return render([] as JSON)

        def id = params.long("id")
        def list = PortfolioAction.createCriteria().list {
            if (params.parentActionId)
                parentAction {
                    idEq(params.parentActionId as Long)
                }
            else
                isNull('parentAction')
            portfolioItem {
                portfolio {
                    idEq(id)
                }
            }
//            firstResult(params.int("skip") ?: 0)
//            maxResults(params.int("pageSize") ?: 20)
            order(params["sort[0][field]"] ?: "actionDate", params["sort[0][dir]"] ?: "desc")
        }

        value.total = PortfolioAction.createCriteria().get {
            if (params.parentActionId)
                parentAction {
                    idEq(params.parentActionId as Long)
                }
            else
                isNull('parentAction')
            "portfolioItem" {
                "portfolio" {
                    idEq(id)
                }
            }
            projections {
                count('id')
            }
        }

        value.data = list.collect {
            gridJson it
        }

        render value as JSON

    }

    def gridJson = { PortfolioAction action ->
        def clazz = Introspector.decapitalize(action.portfolioItem.itemType.split('\\.').last())
        def transactionSource = ''
        if (!action.isInitialDataEntry && action.transactionSourceType && action.transactionSourceId) {
            transactionSource = "${portfolioPropertyManagementService.getProperty(action.transactionSourceType, action.transactionSourceId)} (${message(code: "${action.transactionSourceType}.label")})"
        }
        [
                id                   : action.id,
                itemType             : [
                        clazz     : clazz,
                        title     : message(code: action.portfolioItem.itemType),
                        modifiable: ![
                                'portfolioBondsItem',
                                'portfolioBullionItem',
                                'portfolioCoinItem',
                                'portfolioCurrencyItem',
                                'portfolioSymbolItem',
                                'portfolioSymbolPriorityItem'
                        ].contains(clazz)
                ],
                property             : [
                        propertyId   : action.portfolioItem.propertyId,
                        propertyTitle: action.portfolioItem.propertyTitle
                ],
                actionType           : [
                        actionTypeId   : action.actionType,
                        actionTypeTitle: message(code: "portfolioAction.actionType.${action.actionType}")
                ],
                actionDate           : format.jalaliDate(date: action.actionDate),
                actionDateNumber     : action.actionDate.time,
                sharePrice           : action.sharePrice,
                shareCount           : ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem', 'portfolioImmovableItem'].contains(clazz) ? null : action.shareCount,
                broker               : [
                        brokerId  : action.broker?.id,
                        brokerName: action.broker?.name
                ],
                discount             : action.discount,
                wage                 : action.wage,
                isInitialDataEntry   : action.isInitialDataEntry,
                transactionSource    : transactionSource,
                transactionSourceType: action.transactionSourceType,
                transactionSourceId  : action.transactionSourceId,
                childActions         : PortfolioAction.findAllByParentAction(action).collect { gridJson(it) }
        ]
    }

    def portfolioActionSave() {

        \

        def result = []
        JSON.parse(params.models).each { model ->
            def action = portfolioActionManagementService.save(params.long("id"), model as Map, params.parentActionId ? PortfolioAction.get(params.parentActionId as Long) : null)
            if (action.errors.allErrors)
                return render([error: action.errors.allErrors] as JSON)
            else {
                result << gridJson(action)

            }
        }
        render([data: result] as JSON)
    }

    def portfolioActionDelete() {
        JSON.parse(params.models).each { model ->
            def res = portfolioActionManagementService.delete(model.id as Long)
            if (res.error)
                return render([error: message(code: "portfolioItem.delete.${res.error}")] as JSON)
        }
        render 0
    }

    def propertyList() {
        if (params.actionType in ['s', 'w']) {
            def properties = PortfolioItem.createCriteria().list {
                eq('portfolio.id', params.id as Long)
                eq('class', 'stocks.portfolio.portfolioItems.' + params.clazz.capitalize())
                gt('shareCount', 0L)
            }.collect { [propertyId: it.getPropertyId(), propertyTitle: it.getPropertyTitle()] }

            render(properties as JSON)
        } else {
            Portfolio portfolio = null
            if (params.portfolioId)
                portfolio = Portfolio.get(params.portfolioId)
            render(portfolioPropertyManagementService.findProperty(params.clazz as String, params.id as Long, params["filter[filters][0][value]"] as String, portfolio, params.availOnly == '1').collect {
                it + [clazz: params.clazz]
            } as JSON)
        }
    }

    def portfolioActionImport() {
        def portfolio = Portfolio.get(params.id)
        render(view: '/portfolio/portfolioActionImport', model: [portfolio: portfolio])
    }

    def portfolioActionImportResult() {
        def portfolio = Portfolio.get(params.id)
        def fileId = session["portfolioActionImport${portfolio?.id}"]
        def path = "${grailsApplication.config.user.files.temp}/${fileId}"
        def result = portfolioActionManagementService.importPortfolioActions(portfolio?.id, path, Broker.get(params.broker))
        render(view: '/portfolio/portfolioActionImportResult', model: [portfolio: portfolio, result: result])
    }

    def uploadFile() {

        def fileId = UUID.randomUUID()?.toString()
        def fileName = "${fileId}-${params.file.fileItem.fileName}"
        def path = "${grailsApplication.config.user.files.temp}/${fileName}"
        def file = new File(path)

        def directory = file.parentFile
        if (!directory.exists())
            directory.mkdirs()

        if (file.exists())
            file.delete()

        params.file.transferTo(file)

        session[params.id] = fileName

        render 1
    }
}
