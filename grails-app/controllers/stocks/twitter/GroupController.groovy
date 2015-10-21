package stocks.twitter

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.AccountingHelper
import stocks.User
import stocks.accounting.Transaction
import stocks.graph.GroupGraphService
import stocks.Image

class GroupController {

    def groupGraphService
    def commonGraphService
    def springSecurityService
    def accountingService
    def materialGraphService

    def index() {
        redirect(action: 'list')
    }

    def build() {
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        if (group)
            group.image = Image.get(group.imageId as Long)
        [
                group: group
        ]
    }

    def save() {

        if (!springSecurityService.loggedIn)
            render 'login plz'

        if (params.id) {
            groupGraphService.update(
                    params.id as String,
                    params.title as String,
                    params.description as String,
                    params.image?.id ? params.image?.id as Long : 0l,
                    params.membershipType as String,
                    params.membership1MonthPrice as Integer,
                    params.membership3MonthPrice as Integer,
                    params.membership6MonthPrice as Integer,
                    params.membership12MonthPrice as Integer,
                    params?.allowExceptionalUsers == 'on')
        } else
            groupGraphService.create(
                    params.title as String,
                    params.description as String,
                    params.image?.id ? params.image?.id as Long : 0l,
                    params.membershipType as String,
                    params.membership1MonthPrice as Integer,
                    params.membership3MonthPrice as Integer,
                    params.membership6MonthPrice as Integer,
                    params.membership12MonthPrice as Integer,
                    params?.allowExceptionalUsers == 'on',
                    springSecurityService.currentUser as User)

        redirect(action: 'list')
    }

    def list() {
    }

    def ownerJsonList() {
        def result = groupGraphService.listForOwner(springSecurityService.currentUser as User).collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice,
                    membership3MonthPrice : it.membership3MonthPrice,
                    membership6MonthPrice : it.membership6MonthPrice,
                    membership12MonthPrice: it.membership12MonthPrice,
                    allowExceptionalUsers : it.allowExceptionalUsers
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def ownerDelete() {
        groupGraphService.delete(params.id as String)
        render 1
    }

    def editorJsonList() {
        def result = groupGraphService.listForEditor(springSecurityService.currentUser as User).collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice,
                    membership3MonthPrice : it.membership3MonthPrice,
                    membership6MonthPrice : it.membership6MonthPrice,
                    membership12MonthPrice: it.membership12MonthPrice,
                    allowExceptionalUsers : it.allowExceptionalUsers
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def editorDelete() {
        groupGraphService.deleteEditor(params.id as String, springSecurityService.currentUser as User)
        render 1
    }

    def authorJsonList() {
        def result = groupGraphService.listForAuthor(springSecurityService.currentUser as User).collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice,
                    membership3MonthPrice : it.membership3MonthPrice,
                    membership6MonthPrice : it.membership6MonthPrice,
                    membership12MonthPrice: it.membership12MonthPrice,
                    allowExceptionalUsers : it.allowExceptionalUsers
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def authorDelete() {
        groupGraphService.deleteAuthor(params.id as String, springSecurityService.currentUser as User)
        render 1
    }

    def memberJsonList() {
        def result = groupGraphService.listForMember(springSecurityService.currentUser as User).findAll {
            it.ownerType != 'system'
        }.collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice,
                    membership3MonthPrice : it.membership3MonthPrice,
                    membership6MonthPrice : it.membership6MonthPrice,
                    membership12MonthPrice: it.membership12MonthPrice,
                    allowExceptionalUsers : it.allowExceptionalUsers,
                    type                  : message(code: "twitter.group.membership.type.${it.type}"),
                    startDate             : it.startDate ? format.jalaliDate(date: it.startDate, hm: true) : '',
                    endDate               : it.endDate ? format.jalaliDate(date: it.endDate, hm: true) : ''
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def memberDelete() {
        groupGraphService.deleteMember(params.id as String)
        render 1
    }

    def manageEditors() {
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        [
                group: group
        ]
    }

    def manageEditorsJsonList() {
        def result = groupGraphService.editorList(params.id as String).collect {
            [
                    id   : it.id.replace('#', ''),
                    title: it.title
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def addEditor() {
        groupGraphService.addEditor(params.id as String, User.get(params.editorId as Long))
        render '1'
    }

    def deleteEditor() {
        groupGraphService.deleteEditor(params.id as String, params.editorId as String)
        render '1'
    }

    def manageAuthors() {
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        [
                group: group
        ]
    }

    def manageAuthorsJsonList() {
        def result = groupGraphService.authorList(params.id as String).collect {
            [
                    id   : it.id.replace('#', ''),
                    title: it.title
            ]
        }
        render([total: result.size(), data: result] as JSON)
    }

    def addAuthor() {
        groupGraphService.addAuthor(params.id as String, User.get(params.authorId as Long))
        render '1'
    }

    def deleteAuthor() {
        groupGraphService.deleteAuthor(params.id as String, params.authorId as String)
        render '1'
    }

    def manageMembers() {
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        [
                group: group
        ]
    }

    def manageMembersJsonList() {
        def result = groupGraphService.memberList(params.id as String, params.skip as Integer, params.take as Integer).collect {
            [
                    id       : it.id.replace('#', ''),
                    title    : it.title,
                    type     : message(code: "twitter.group.membership.type.${it.type}"),
                    startDate: it.startDate ? format.jalaliDate(date: it.startDate, hm: true) : '',
                    endDate  : it.endDate ? format.jalaliDate(date: it.endDate, hm: true) : ''
            ]
        }
        render([total: groupGraphService.memberCount(params.id as String), data: result] as JSON)
    }

    def addMember() {
        groupGraphService.addMember(params.id as String, User.get(params.memberId as Long), parseDate(params.startDate as String), parseDate(params.endDate as String), GroupGraphService.MEMBERSHIP_TYPE_EXCEPTIONAL)
        render '1'
    }

    def deleteMember() {
        groupGraphService.deleteMember(params.memberId as String)
        render '1'
    }

    def select() {

    }

    def publicUnregisteredList() {
        def user = springSecurityService.currentUser as User
        def result = groupGraphService.publicUnregisteredList(user.id, params.skip as Integer, params.take as Integer)
                .collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    description           : it.description?.replaceAll("<(.|\n)*?>", '') ?: '-',
                    imageId               : it.imageId ?: 0,
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice ? formatNumber(type: 'number', number: it.membership1MonthPrice) + ' ' + message(code: 'rial') : message(code: 'free'),
                    membership3MonthPrice : it.membership3MonthPrice ? formatNumber(type: 'number', number: it.membership3MonthPrice) + ' ' + message(code: 'rial') : message(code: 'free'),
                    membership6MonthPrice : it.membership6MonthPrice ? formatNumber(type: 'number', number: it.membership6MonthPrice) + ' ' + message(code: 'rial') : message(code: 'free'),
                    membership12MonthPrice: it.membership12MonthPrice ? formatNumber(type: 'number', number: it.membership12MonthPrice) + ' ' + message(code: 'rial') : message(code: 'free')
            ]
        }
        render([total: groupGraphService.publicCount(), data: result] as JSON)
    }

    def register() {
        def user = springSecurityService.currentUser as User
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        def balance = accountingService.userBalance(user?.id)
        if (group)
            group.image = Image.get(group.imageId as Long)
        [
                group  : group,
                balance: balance,
                maxDept: user?.maxDept ?: 0
        ]
    }

    def saveRegistration() {
        def group = commonGraphService.getAndUnwrap(params.id as String)
        def user = springSecurityService.currentUser as User

        def startDate = new Date()
        def endDate = null
        use(TimeCategory) {
            endDate = startDate + (params.period as Integer).months
        }
        groupGraphService.addMember(params.id as String, user, startDate, endDate)

        def price = group."membership${params.priod}MonthPrice"
        if (price > 0) {
            def transaction = new Transaction()
            transaction.date = new Date()
            transaction.accountId = grailsApplication.config.accounts.find { it.default }.id
            transaction.creator = user
            transaction.customer = user
            transaction.type = AccountingHelper.TRANSACTION_TYPE_WITHDRAWAL
            transaction.value = price
            transaction.description = message(code: 'transaction.description.group.register', args: [group.title])
            transaction.save()
        }

        render '1'
    }

    def home() {
        def group = params.id ? commonGraphService.getAndUnwrap(params.id as String) : null
        def user = springSecurityService.currentUser as User
        group.image = Image.get(group.imageId as Long)
        [
                group     : group,
                membership: groupGraphService.getUserMembershipInGroup(params.id as String, user?.id),
                authorList: groupGraphService.authorList(params.id as String)
        ]
    }

    def homeJson() {
        def list = materialGraphService.listByGroup(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect { g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true]) } as JSON)
    }

    private static Date parseDate(String date) {
        if (!date || date == '')
            return null
        def parts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(parts[0], parts[1], parts[2])
        jc.toJavaUtilGregorianCalendar().time
    }

}
