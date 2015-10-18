package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User

//import stocks.twitter.Comment

class CommentController {

    def springSecurityService
    def commentGraphService
    def graphDBService

    @Secured([RoleHelper.ROLE_USER])
    def save() {
        def comment = commentGraphService.saveComment(springSecurityService.currentUser as User, params.parentId as String, params.body as String)
        render template: 'view', model: [comment: comment]
    }

    @Secured([RoleHelper.ROLE_USER])
    def editor() {
        render template: 'submit', model: [comment: graphDBService.getAndUnwrapVertex(params.id as String)]
    }
}
