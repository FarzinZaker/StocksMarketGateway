package stocks

import stocks.twitter.Article
import stocks.twitter.Tag
import grails.converters.JSON

class ArticleController {

    def springSecurityService

    def index() {}

    def create() {
    }

    def edit() {
        [article: stocks.twitter.Article.get(params.id)]
    }

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
        article.image = Image.get(params.image?.id)
        article.author = User.get(springSecurityService.currentUser?.id)


        if (article.validate() && article.save(flush: true)) {
            article.tags?.clear()
            params.tags.split(',').collect {
                def tag = stocks.twitter.Tag.findByName(it as String)
                if (!tag)
                    tag = new Tag(name: it).save(flush: true)
                tag
            }.each {
                article.addToTags(it)
            }

            if (article.validate() && article.save(flush: true))
                redirect(action: 'list')
            else
                render view: 'create', model: [article: article]

        } else
            render view: 'create', model: [article: article]

    }

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
            value.total = stocks.twitter.Document.countByAuthor(springSecurityService.currentUser as User).toString()
        }
        render value as JSON
    }

    def delete() {
        def article = Article.get(params.id)
        article.tags?.clear()
        article.save(flush: true)
        if (article.delete())
            render '1'
        else render '0'
    }

    def list() {

    }

    def thread() {
        [article: Article.get(params.id)]
    }
}

