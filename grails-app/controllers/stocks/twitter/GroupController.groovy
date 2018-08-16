package stocks.twitter

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.AccountingHelper
import stocks.AuthenticationProvider
import stocks.Message
import stocks.User
import stocks.accounting.Transaction
import stocks.commodity.Group
import stocks.graph.GroupGraphService
import stocks.Image

class GroupController {

    def groupGraphService
    def commonGraphService
    def springSecurityService
    def accountingService
    def materialGraphService
    def graphDBService

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
                    params.authorType as String,
                    params.membershipType as String,
                    params.membership1MonthPrice as Integer,
                    params.membership3MonthPrice as Integer,
                    params.membership6MonthPrice as Integer,
                    params.membership12MonthPrice as Integer,
                    params?.allowExceptionalUsers == 'on',
                    params?.allowNewPosts == 'on')
        } else
            groupGraphService.create(
                    params.title as String,
                    params.description as String,
                    params.image?.id ? params.image?.id as Long : 0l,
                    params.authorType as String,
                    params.membershipType as String,
                    params.membership1MonthPrice as Integer,
                    params.membership3MonthPrice as Integer,
                    params.membership6MonthPrice as Integer,
                    params.membership12MonthPrice as Integer,
                    params?.allowExceptionalUsers == 'on',
                    params?.allowNewPosts == 'on',
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
                    authorType            : message(code: "twitter.group.authorType.${it.authorType}"),
                    membershipType        : message(code: "twitter.group.membershipType.${it.membershipType}"),
                    membership1MonthPrice : it.membership1MonthPrice,
                    membership3MonthPrice : it.membership3MonthPrice,
                    membership6MonthPrice : it.membership6MonthPrice,
                    membership12MonthPrice: it.membership12MonthPrice,
                    allowExceptionalUsers : it.allowExceptionalUsers,
                    allowNewPosts         : it.allowNewPosts
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
                    authorType            : message(code: "twitter.group.authorType.${it.authorType}"),
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
                    authorType            : message(code: "twitter.group.authorType.${it.authorType}"),
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
                    authorType            : message(code: "twitter.group.authorType.${it.authorType}"),
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
                    id        : it.id.replace('#', ''),
                    title     : it.title,
                    authorType: message(code: "twitter.group.authorType.${it.authorType}"),
                    type      : message(code: "twitter.group.membership.type.${it.type}"),
                    startDate : it.startDate ? format.jalaliDate(date: it.startDate, hm: true) : '',
                    endDate   : it.endDate ? format.jalaliDate(date: it.endDate, hm: true) : ''
            ]
        }
        render([total: groupGraphService.memberCount(params.id as String), data: result] as JSON)
    }

    def addMember() {
        groupGraphService.addMember(params.id as String, User.get(params.memberId as Long), parseDate(params.startDate as String), parseDate(params.endDate as String), GroupGraphService.MEMBERSHIP_TYPE_EXCEPTIONAL)
        render '1'
    }

    def inviteMember() {
        def group = graphDBService.getAndUnwrapVertex(params.id)
        def user = User.get(params.memberId as Long)
        def startDate = params.startDate
        def endDate = params.endDate
        def code = "${group?.idNumber}-${user?.id}-${startDate ?: '_'}-${endDate ?: '_'}"
        code = "${code}-${AuthenticationProvider.md5(code)}"
        def link = createLink(controller: 'group', action: 'invitation', params: [code: code])
        def msg = new Message()
        msg.body = message(code: 'group.membership.invitation.message', args: [message(code: "user.title.${user?.sex}")?.toString(), user?.toString(), group?.title, link])?.toString()
        println(msg?.body)
        msg.sender = springSecurityService.currentUser as User
        msg.receiver = user
        msg?.save(flush: true)
        render '1'
    }

    def invitation() {
        def parts = params.code?.split('-')
        def code = "${parts[0]}-${parts[1]}-${parts[2]}-${parts[3]}"
        def md5 = parts[4]
        if (md5 != AuthenticationProvider.md5(code))
            flash.message = message(code: 'group.membership.invitation.wrongCode')
        else {
            def group = graphDBService.getAndUnwrapVertex(parts[0])
            def user = User.get(parts[1])
            def startDate = parts[2] == '_' ? null : parseDate(parts[2])
            def endDate = parts[3] == '_' ? null : parseDate(parts[3])
            groupGraphService.addMember(group?.idNumber, user, startDate, endDate, GroupGraphService.MEMBERSHIP_TYPE_EXCEPTIONAL)
            flash.message = message(code: 'group.membership.invitation.added', args: [group?.title])
        }
        redirect(action: 'list')
    }

    def deleteMember() {
        groupGraphService.deleteMember(params.memberId as String)
        render '1'
    }

    def select() {

    }

    def publicUnregisteredList() {
        def user = springSecurityService.currentUser as User
        def result = groupGraphService.publicUnregisteredList(user?.id, params.skip as Integer, params.take as Integer)
                .collect {
            [
                    id                    : it.id.replace('#', ''),
                    title                 : it.title,
                    description           : it.description?.replaceAll("<(.|\n)*?>", '') ?: '-',
                    imageId               : it.imageId ?: 0,
                    authorType            : message(code: "twitter.group.authorType.${it.authorType}"),
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

        def price = group."membership${params.period}MonthPrice"
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
        if (group) {
            def user = springSecurityService.currentUser as User
            group.image = Image.get(group.imageId as Long)
            def groupOwner = groupGraphService.getOwner(params.id as String)
            def authorList = groupGraphService.authorList(params.id as String)
            def editorList = groupGraphService.editorList(params.id as String)
            [
                    group     : group,
                    groupOwner: groupOwner,
                    membership: groupGraphService.getUserMembershipInGroup(params.id as String, user?.id),
                    authorList: authorList,
                    editorList: editorList,
                    hasAccess : ([groupOwner?.identifier] + authorList?.collect {
                        it.identifier
                    } + editorList?.collect {
                        it.identifier
                    }).contains(user?.id)
            ]
        } else
            render(status: 404, text: 'NOT FOUND!')
    }

    def homeJson() {
        def list = materialGraphService.listByGroup(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true])
        } as JSON)
    }

    private static Date parseDate(String date) {
        if (!date || date == '')
            return null
        def parts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(parts[0], parts[1], parts[2])
        jc.toJavaUtilGregorianCalendar().time
    }

    def transfer() {
        def group = graphDBService.getAndUnwrapVertex("#${params.id}")
        [group: group]
    }

    def doTransfer() {
        groupGraphService.transfer(params.id, User.get(params.receiver))
        flash.message = message(code: 'group.transfer.succeed')
        redirect(action: 'list')
    }

    def topMaterials() {
        def daysCount = params.period as Integer
        def id = params.id as String
        render([
                topScored       : groupGraphService.topScoredMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                mostVisited  : groupGraphService.mostVisitedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                topRated     : groupGraphService.topRatedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join(''),
                mostCommented: groupGraphService.mostCommentedMaterials(id, daysCount, 5).collect {
                    "<li>${g.render(template: "/twitter/material/${it.label}", model: [material: it])}</li>"
                }?.join('')
        ] as JSON)
    }

}
