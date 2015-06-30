package stocks

import grails.converters.JSON

class LandingController {

    def feedService

    def index() {

    }

    def news(){
        def result = feedService.news()
        result.categories = result.categoryList.collect {
            [
                    value: it,
                    text : message(code: "newsCategory.${it}")
            ]
        }
        render (result as JSON)
    }
}
