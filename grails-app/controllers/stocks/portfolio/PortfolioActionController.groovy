package stocks.portfolio

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.tse.Symbol

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
            firstResult(params.int("skip") ?: 0)
            maxResults(params.int("pageSize") ?: 20)
            order(params["sort[0][field]"] ?: "actionDate", params["sort[0][dir]"] ?: "desc")
        }

        value.total = PortfolioAction.createCriteria().get {
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

    def gridJson = {
        def clazz = Introspector.decapitalize(it.portfolioItem.itemType.split('\\.').last())
        [
                id        : it.id,
                itemType  : [
                        clazz     : clazz,
                        title     : message(code: it.portfolioItem.itemType),
                        modifiable: ![
                                'portfolioBondsItem',
                                'portfolioBullionItem',
                                'portfolioCoinItem',
                                'portfolioCurrencyItem',
                                'portfolioSymbolItem',
                                'portfolioSymbolPriorityItem'
                        ].contains(clazz)
                ],
                property  : [
                        propertyId   : it.portfolioItem.propertyId,
                        propertyTitle: it.portfolioItem.propertyTitle
                ],
                actionType: [
                        actionTypeId   : it.actionType,
                        actionTypeTitle: message(code: "portfolioAction.actionType.${it.actionType}")
                ],
                actionDate: df.format(it.actionDate),
                actionDateNumber:it.actionDate.time,
                sharePrice: it.sharePrice,
                shareCount: ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem', 'portfolioImmovableItem'].contains(clazz) ? null : it.shareCount,
                broker    : [
                        brokerId  : it.broker?.id,
                        brokerName: it.broker?.name
                ],
                discount  : it.discount,
                wage      : it.wage,
        ]
    }

    def portfolioActionSave() {

        def result = []
        JSON.parse(params.models).each { model ->
            def action = portfolioActionManagementService.save(params.long("id"), model as Map, params.parentActionId ? PortfolioAction.get(params.parentActionId as Long) : null)
            if(action.errors.allErrors)
                return render( [error:action.errors.allErrors] as JSON)
            else{
                result << gridJson(action)

            }
        }
        render([data: result] as JSON)
    }

    def portfolioActionDelete() {
        JSON.parse(params.models).each { model ->
            portfolioActionManagementService.delete(model.id as Long)
        }
        render 0
    }

    def propertyList() {
        if(params.actionType in ['s','w']){
            def properties=PortfolioItem.createCriteria().list {
                eq('portfolio.id',params.id as Long)
                eq('class','stocks.portfolio.portfolioItems.'+params.clazz.capitalize())
            }.collect {[propertyId:it.getPropertyId(),propertyTitle:it.getPropertyTitle()]}

            render(properties as JSON)
        }
        else {
            render(portfolioPropertyManagementService.findProperty(params.clazz as String, params.id as Long, params["filter[filters][0][value]"] as String).collect {it+[clazz:params.clazz]} as JSON)
        }
    }
}
