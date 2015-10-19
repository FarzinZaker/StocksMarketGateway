package stocks.twitter

import grails.plugins.springsecurity.Secured
import stocks.RoleHelper
import stocks.User
//import stocks.twitter.Like
import grails.converters.JSON

class LikeController {

    def springSecurityService
    def likeGraphService

    @Secured([RoleHelper.ROLE_USER])
    def like() {
        def user = springSecurityService.currentUser as User
        if(params.type == 'LIKE'){
            likeGraphService.saveLike(user, params.item as String)
        }
        else{
            likeGraphService.saveDislike(user, params.item as String)
        }
        render([
                type    : params.type,
                likes   : likeGraphService.getLikesCount(params.item as String),
                dislikes: likeGraphService.getDislikesCount(params.item as String)
        ] as JSON)
    }
}
