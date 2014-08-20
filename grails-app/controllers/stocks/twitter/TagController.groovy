package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper

class TagController {

    @Secured([RoleHelper.ROLE_USER])
    def jsonSearch() {

        render "[${stocks.twitter.Tag.findAllByNameLike("%${params.term ?: ''}%").collect { "{\"name\":\"${it.name}\", \"id\":\"${it.id}\"}" }.join(',')}]"
    }

    @Secured([RoleHelper.ROLE_USER])
    def documentCount(){
        render stocks.twitter.Document.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }

    @Secured([RoleHelper.ROLE_USER])
    def articleCount(){
        render stocks.twitter.Article.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }

    @Secured([RoleHelper.ROLE_USER])
    def newsCount(){
        render stocks.twitter.News.createCriteria().count{
            tags{
                eq('id', params.id?.toLong())
            }
        }
    }
}
