package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User
//import stocks.twitter.Like
import grails.converters.JSON

class LikeController {

    def springSecurityService

    @Secured([RoleHelper.ROLE_USER])
    def like() {
//        def user = springSecurityService.currentUser as User
//        def comment = stocks.twitter.Comment.get(params.item)
//        def like = stocks.twitter.Like.findByCommentAndOwner(comment, user)
//        def type
//        if (like) {
//            if (like?.type == params.type) {
//                like.delete(flush: true)
//                type = ''
//            } else {
//                like.type = params.type
//                like.save()
//                type = params.type
//            }
//        } else {
//
//            like = new Like()
//            like.comment = comment
//            like.type = params.type
//            like.owner = user
//            like.save()
//            type = params.type
//        }
//        def likes = Like.findAllByComment(comment)
//        render([
//                type    : type,
//                likes   : likes.count { it.type == 'LIKE' },
//                dislikes: likes.count { it.type == 'DISLIKE' }
//        ] as JSON)
    }
}
