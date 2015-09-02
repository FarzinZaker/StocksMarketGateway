package stocks.accounting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.AccountingHelper
import stocks.User

class TransactionController {

    def springSecurityService

    def list() {

    }

    def user() {
        [user: User.get(params.id as Long)]
    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "date", order: params["sort[0][dir]"] ?: "desc"]

        def list = Transaction.createCriteria().list(parameters, {
            eq('deleted', false)
            if (params.user) {
                customer {
                    eq('id', params.user as Long)
                }
            }
        })
        value.total = Transaction.createCriteria().count {
            eq('deleted', false)
            if (params.user) {
                customer {
                    eq('id', params.user as Long)
                }
            }
        }

        value.data = list.collect { transaction ->
            [
                    id         : transaction.id,
                    value      : transaction.value * (transaction.type == AccountingHelper.TRANSACTION_TYPE_WITHDRAWAL ? -1 : 1),
                    type       : message(code: "transaction.type.${transaction.type}"),
                    date       : format.jalaliDate(date: transaction.date, hm: true),
                    account    : message(code: "bank.${grailsApplication.config.accounts.find { it.id == transaction.accountId }.bankName}"),
                    customer   : transaction.customer?.toString(),
                    creator    : transaction.creator?.toString(),
                    description: transaction.description?.toString()
            ]
        }

        render value as JSON
    }

    def build() {
        def sourceList = 'list'
        if (params.user)
            sourceList = 'user'
        if (params.id)
            [
                    transaction: Transaction.get(params.id as Long),
                    sourceList : sourceList]
        else {
            def transaction = new Transaction()
            if (params.user)
                transaction.customer = User.get(params.user as Long)
            [
                    transaction: transaction,
                    sourceList : sourceList
            ]
        }
    }

    def save() {
        def transaction
        if (params.id)
            transaction = Transaction.get(params.id as Long)
        else
            transaction = new Transaction()
        transaction.value = params.value as Integer
        transaction.accountId = params.accountId as Long
        transaction.type = params.type
        transaction.date = parseDateTime(params.date?.toString(), params.time?.toString())
        transaction.customer = User.get(params.customer.id as Long)
        transaction.creator = springSecurityService.currentUser as User
        transaction.description = message(code: "transaction.description.admin.${transaction.type}")

        transaction.save(flush: true)

        def parameters = [:]
        if (params.sourceList == 'user')
            parameters.id = transaction.customer.id

        redirect(action: params.sourceList, params: parameters)
    }

    def delete() {
        def transaction = Transaction.get(params.id)
        transaction.deleted = true
        if (transaction?.save(flush: true))
            render '1'
        else
            render '-1'
    }

    def parseDateTime(String date, String time) {
        def dateParts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        def timeParts = time.split(":")
        cal.set(Calendar.HOUR_OF_DAY, timeParts[0] as Integer)
        cal.set(Calendar.MINUTE, timeParts[1] as Integer)
        cal.time
    }
}
