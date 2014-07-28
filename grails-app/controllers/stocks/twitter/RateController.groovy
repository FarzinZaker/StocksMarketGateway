package stocks.twitter

import stocks.User
import stocks.twitter.Article
import stocks.twitter.Rate

class RateController {

    def springSecurityService

    def save() {
        def rate = new Rate()
        rate.document = stocks.twitter.Document.get(params.id)
        rate.value = params.rating.toInteger()
        rate.owner = springSecurityService.currentUser as User
        rate.save()

        render template: 'result', model: [rate: rate, type: (rate.document.instanceOf(Article) ? 'article' : 'news')]
    }
}
