package stocks.twitter

import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory
import stocks.RoleHelper
import stocks.User
import stocks.graph.MaterialGraphService

class TalkController {

    def springSecurityService
    def sharingService

    @Secured([RoleHelper.ROLE_USER])
    def save() {

        def owner = springSecurityService.currentUser as User

        def tags = sharingService.extractTextRelations(params.body as String)

        if (params.predictionType != '-' && params.predictionItems) {
            if (params.predictionItems instanceof String)
                params.predictionItems = [params.predictionItems]
            params.predictionItems.each { String item ->
                def itemParts = item?.trim()?.split('-')
                def tag = tags.tagList.find {
                    it.type == itemParts?.first() && it.identifier == itemParts?.last()?.toLong()
                }
                if (tag) {
                    tag.prediction = [
                            type  : params.predictionType?.trim(),
                            period: params.predictionPeriod?.trim()?.toLowerCase(),
                            risk  : params.predictionRiskLevel?.trim()?.toInteger()
                    ]
                    def daysCount = 1
                    switch (tag.prediction.period) {
                        case '1w':
                            daysCount = 7
                            break;
                        case '4w':
                            daysCount = 4 * 7
                            break;
                        case '12w':
                            daysCount = 12 * 7
                            break;
                        case '26w':
                            daysCount = 26 * 7
                            break;
                    }
                    use(TimeCategory) {
                        tag.prediction.endDate = new Date().clearTime() + (1 + daysCount).days
                    }
                }
            }
        }

        sharingService.shareTalk(owner, tags.text,
                tags.tagList,
                tags.mentionList,
                params.findAll { it.key.toString().startsWith('share_group_') }.collect {
                    it.key.toString().replace('share_group_#', '')
                })
        render '1'

    }

    @Secured([RoleHelper.ROLE_USER])
    def delete(String id) {
        try {
            sharingService.removeMaterial(id)
            render '1'
        }
        catch (ignored) {
            render '0'
        }
    }
}
