package stocks.twitter

import grails.plugins.springsecurity.Secured
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
        sharingService.shareTalk(owner, tags.text,
                tags.tagList,
                tags.mentionList)
        render '1'

    }

    @Secured([RoleHelper.ROLE_USER])
    def delete(String id) {
        try {
            sharingService.removeMaterial(id)
            render '1'
        }
        catch(ignored){
            render '0'
        }
    }
}
