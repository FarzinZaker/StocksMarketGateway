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
        def user = springSecurityService.currentUser as User
        def comment = commentGraphService.saveComment(user, params.parentId as String, (params.body ?: params.commentBody) as String)
        comment.author = [title: user.toString(), identifier: user.id]
        render template: params.template ?: 'view', model: [comment: comment, showProperties: true]
    }

    @Secured([RoleHelper.ROLE_USER])
    def edit() {
        def user = springSecurityService.currentUser as User
        def comment = commentGraphService.saveComment(user, null, (params.body ?: params.commentBody) as String, params.id as String)
        comment.author = [title: user.toString(), identifier: user.id]
        render template: params.template ?: 'view', model: [comment: comment, showProperties: true]
    }

    @Secured([RoleHelper.ROLE_USER])
    def editor() {
        render template: 'submit', model: [commentId: params.id]
//: graphDBService.getAndUnwrapVertex(params.id as String)]
    }
}
