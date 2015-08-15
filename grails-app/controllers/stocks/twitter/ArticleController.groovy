package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User
import stocks.Image
import stocks.twitter.Article

//import stocks.twitter.Tag
import grails.converters.JSON

class ArticleController {

    def springSecurityService

    @Secured([RoleHelper.ROLE_USER])
    def create() {
    }

    @Secured([RoleHelper.ROLE_USER])
    def edit() {
        [article: Article.get(params.id)]
    }

    @Secured([RoleHelper.ROLE_USER])
    def save() {
        Article article

        if (params.id) {
            article = Article.get(params.id)
            if (article.authorId != springSecurityService.currentUser?.id)
                redirect('/denied')
        } else
            article = new Article()

        article.title = params.title
        article.summary = params.summary
        article.body = params.body
        article.image = Image.get(params.image?.id as Long)
        article.author = User.get(springSecurityService.currentUser?.id as Long)

        if (article.validate() && article.save(flush: true))
            redirect(action: 'list')
        else
            render view: 'create', model: [article: article]

    }

    @Secured([RoleHelper.ROLE_USER])
    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: "lastUpdated", order: "desc"]
        if (params.search) {
            def searchResult = Article.search(params.search?.toString())

            value.data =
                    Article.findAllByAuthorAndIdInList(springSecurityService.currentUser as User, searchResult.results.collect {
                        it.id
                    }, parameters).collect {
                        [
                                itemId   : it.id,
                                title    : it.title,
                                summary  : it.summary?.replaceAll("<(.|\n)*?>", ''),
                                date     : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                                imageName: it.image?.name,
                                imageId  : it.image?.id
                        ]
                    }
            value.total = Article.countByAuthorAndIdInList(springSecurityService.currentUser as User, searchResult.results.collect {it.id})?.toString()
        } else {
            value.data =
                    Article.findAllByAuthor(springSecurityService.currentUser as User, parameters).collect {
                        [
                                itemId   : it.id,
                                title    : it.title,
                                summary  : it.summary?.replaceAll("<(.|\n)*?>", ''),
                                date     : format.jalaliDate(date: it.lastUpdated, hm: 'true'),
                                imageName: it.image?.name,
                                imageId  : it.image?.id
                        ]
                    }
            value.total = Article.countByAuthor(springSecurityService.currentUser as User).toString()
        }
        render value as JSON
    }

    @Secured([RoleHelper.ROLE_USER])
    def delete() {
//        def article = Article.get(params.id)
//        article.tags?.clear()
//        article.save(flush: true)
//        if (article.delete())
//            render '1'
//        else render '0'
    }

    @Secured([RoleHelper.ROLE_USER])
    def list() {

    }

    def thread() {
        [article: Article.get(params.id as Long)]
    }
}

