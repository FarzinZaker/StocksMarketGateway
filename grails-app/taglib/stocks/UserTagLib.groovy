package stocks

class UserTagLib {

    def springSecurityService

    static namespace = "userInfo"

    def articleCount = { attrs, body ->
        out << stocks.twitter.Article.countByAuthor(attrs.user)
    }

    def newsCount = { attrs, body ->
        out << stocks.twitter.News.countByAuthor(attrs.user)
    }

    def rateAverage = { attrs, body ->
        out << (stocks.twitter.Rate.createCriteria().get {
            document {
                author {
                    eq('id', attrs.user?.id)
                }
            }
            projections {
                avg('value')
            }
        } ?: 0)
    }

    def commentCount = { attrs, body ->
        out << stocks.twitter.Comment.countByAuthor(attrs.user)
    }

    def likeCount = { attrs, body ->
        out << stocks.twitter.Like.createCriteria().get {
            comment {
                author {
                    eq('id', attrs.user?.id)
                }
            }
            eq('type', 'LIKE')
            projections {
                count('id')
            }
        }
    }

    def dislikeCount = { attrs, body ->
        out << stocks.twitter.Like.createCriteria().get {
            comment {
                author {
                    eq('id', attrs.user?.id)
                }
            }
            eq('type', 'DISLIKE')
            projections {
                count('id')
            }
        }
    }

    def follow = { attrs, body ->
        def currentUser = User.get(springSecurityService.currentUser?.id)
        def user = attrs.user as User
        if (user.id == currentUser.id)
            return
        out << """
            <button id="follow_${attrs.user?.id}" class='k-button ${
            currentUser.followList.any { it.id == user.id } ? 'k-red' : ''
        }' data-follow='${
            currentUser.followList.any { it.id == user.id } ? '0' : '1'
        }'>
                ${
            message(code: "user.followList.${currentUser.followList.any { it.id == user.id } ? 'remove' : 'add'}")
        }
            </button>
"""
        out << form.loading(id: "follow_${attrs.user?.id}_loading")
        out << """
            <script>
                \$('#follow_${attrs.user?.id}').click(function(){
                    \$('#follow_${attrs.user?.id}_loading').fadeIn();
                    \$.ajax({
                        url: "${createLink(controller: 'user', action: 'follow')}",
                        data: { id: ${user?.id},type:\$(this).attr('data-follow') },
                        dataType: "json",
                        success: function( data ) {
                            if(data == '1'){
                                \$('#follow_${attrs.user?.id}').attr('data-follow', '0')
                                    .addClass('k-red')
                                    .html('${message(code: 'user.followList.remove')}');
                            }
                            else if(data == '0'){
                                \$('#follow_${attrs.user?.id}').attr('data-follow', '1')
                                    .removeClass('k-red')
                                    .html('${message(code: 'user.followList.add')}');
                            }
                            \$('#follow_${attrs.user?.id}_loading').hide();
                        }
                    });
                });
            </script>
"""
    }

    def wall = {attrs, body ->
        out << """
            <a class='k-button' href="${createLink(controller: 'user', action: 'wall', id: attrs.user?.id)}">
                ${message(code: 'user.wall.button')}
            </a>
"""
    }
}
