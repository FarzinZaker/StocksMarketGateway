package stocks

import stocks.twitter.Comment

class CommentController {

    def springSecurityService

    def save() {

        def comment = new Comment()
        comment.body = params.body
        comment.author = springSecurityService.currentUser as User
        if (params.type == 'comment') {
            comment.relatedComment = Comment.get(params.parentId as Long)
            comment.document = comment.relatedComment.document
        } else
            comment.document = stocks.twitter.Document.get(params.parentId as Long)
        comment.save()
        render template: 'view', model: [comment: comment]
    }

    def editor() {
        render template: 'submit', model: [comment: Comment.get(params.id)]
    }
}
