package stocks

import stocks.accounting.Transaction

class AccountingService {

    def userBalance(Long id) {
        def deposit = Transaction.createCriteria().list {
            eq('deleted', false)
            not {
                eq('type', AccountingHelper.TRANSACTION_TYPE_WITHDRAWAL)
            }
            customer {
                eq('id', id)
            }
            projections {
                sum('value')
            }
        }?.find() ?: 0
        def withdraw = Transaction.createCriteria().list {
            eq('deleted', false)
            eq('type', AccountingHelper.TRANSACTION_TYPE_WITHDRAWAL)
            customer {
                eq('id', id)
            }
            projections {
                sum('value')
            }
        }?.find() ?: 0
        deposit - withdraw
    }
}
