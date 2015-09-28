package stocks

import stocks.accounting.Transaction
import stocks.messaging.Invitation

class InvitationFilters {

    def springSecurityService

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                if (params.invitation) {
                    def invitation = Invitation.findByIdentifier(params.invitation.toString())
                    if (invitation) {

                        if (!invitation.visitRecorded) {
                            invitation.visitRecorded = true
                            invitation.save()

                            //grant points to invitation sender
                            def transaction = new Transaction()
                            transaction.date = new Date()
                            transaction.accountId = grailsApplication.config.accounts.find { it.giftAccount }.id
                            transaction.creator = springSecurityService.currentUser as User ?: invitation.sender
                            transaction.customer = invitation.sender
                            transaction.type = AccountingHelper.TRANSACTION_TYPE_DEPOSIT
                            transaction.value = grailsApplication.config.gift.invite.visit
                            transaction.description = "هدیه بازدید ${invitation.receiverAddress} از 4تابلو"
                            transaction.save()
                        }
                    }
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
