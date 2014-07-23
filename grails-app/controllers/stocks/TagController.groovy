package stocks

class TagController {

    def index() {}

    def jsonSearch() {

        render "[${stocks.twitter.Tag.findAllByNameLike("%${params.term ?: ''}%").collect { "{\"name\":\"${it.name}\", \"id\":\"${it.id}\"}" }.join(',')}]"
    }

    def documentCount(){
        render stocks.twitter.Document.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }

    def articleCount(){
        render stocks.twitter.Article.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }

    def newsCount(){
        render stocks.twitter.News.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }
}
