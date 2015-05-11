package stocks

import stocks.twitter.Article
import stocks.twitter.Comment
import stocks.twitter.Document

class StocksTagLib {

    def springSecurityService

    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "stocks"


    def tagCloud = { attrs, body ->

        out << asset.javascript(src: 'tagcloud.js')
        out << """
            <div class="tagCloud">
"""

        def tags = stocks.twitter.Document.createCriteria().list({
            projections {
                count('id', 'tagCount')
                tags {
                    property('id')
                    groupProperty('id')
                }
            }
            order('tagCount', 'desc')
        }, max: 20)

        Collections.shuffle(tags)
        tags.each {
            def tag = stocks.twitter.Tag.get(it.last())
            def count = it.first()
            out << """
                <a href="${createLink(controller: "${attrs.type}", action: 'listByTag', id: tag.id)}" rel="${count}">${
                tag.name
            }</a>
"""
        }

        out << """
            </div>
            <script language="javascript" type="text/javascript">
                \$(".tagCloud a").tagcloud({
                    size: {
                        start: 12,
                        end: 24,
                        unit: 'px'
                    },
                    color: {
                        start: "#007cbc",
                        end: "#8ebc00"
                    }
                });
            </script>
"""
    }

    def relatedArticles = { attrs, body ->

        def article = stocks.twitter.Article.get(attrs.id)

        def items = stocks.twitter.Article.createCriteria().listDistinct {
            ne('id', article.id)
            tags {
                'in'('id', article.tags.collect { it.id })
            }
            maxResults(7)
        }

        out << articleList(items)
    }

    def newArticles = { attrs, body ->

        def items = stocks.twitter.Article.createCriteria().listDistinct {
            order('dateCreated', 'desc')
            maxResults(7)
        }

        out << articleList(items)
    }

    def userTopArticles = { attrs, body ->

        def items = stocks.twitter.Article.createCriteria().listDistinct {
            author {
                eq('id', attrs.user?.id)
            }
            projections {
                groupProperty('id')
                rates {
                    avg('value', 'rateAverage')
//                    document{
//                        property('id')
//                    }
                }
            }

            order('rateAverage', 'desc')
            maxResults(7)
        }

        out << articleList(stocks.twitter.Article.findAllByIdInList(items))
    }

    def articleList(def list) {

        if(list?.size()) {
            def result = """
            <ul class="linkList">
"""
            list.each {
                result += """
                <li>${articleListItem(it)}</li>
"""
            }

            result += """
            </ul>
"""

            result += """
            <script language="javascript" type="text/javascript">
                \$('.linkList li p').dotdotdot({
                    ellipsis: '... ',
                    wrap: 'word',
                    fallbackToLetter: true,
                    after: null,
                    watch: false,
                    height: 35,
                    tolerance: 0,
                    callback: function (isTruncated, orgContent) {
                    },
                    lastCharacter: {
                        remove: [ ' ', ',', ';', '.', '!', '?' ],
                        noEllipsis: []
                    }
                });
            </script>
"""

            result
        }
        else{
            "<div class='emptyMessage'>${message(code:'list.nothingFound')}</div>"
        }
    }

    def articleListItem(Article article) {
        """
            <div class="articleLink">
                <img src="${createLink(controller: 'image', id: article.image?.id, params: [size: 80])}" />
                <a href="${createLink(controller: 'article', action: 'thread', id: article.id)}">${article.title}</a>
                <p>${article.summary}</p>
                <span></span>
                <div class='clear-fix'></div>
            </div>
"""
    }

    def rating = { attrs, body ->
        def rate = stocks.twitter.Rate.findByOwnerAndDocument(springSecurityService.currentUser as User, attrs.document)
        if (rate)
            out << render(template: '/rate/result', model: [rate: rate, type: (rate.document.instanceOf(Article) ? 'article' : 'news')])
        else
            out << render(template: '/rate/submit', model: [document: attrs.document])
    }

    def commentList = { attrs, body ->
        if (attrs.document) {
            out << "<div id='cd_${attrs.document?.id}'></div>"
            out << commentList(stocks.twitter.Comment.findAllByDocumentAndRelatedCommentIsNull(attrs.document as Document, [sort: 'dateCreated', order: 'desc']), "<div id='ed_${attrs.document?.id}'>${attrs.emptyMessage}</div>" ?: '')
        }
        if (attrs.comment) {
            out << "<div id='cc_${attrs.comment?.id}'></div>"
            out << commentList(stocks.twitter.Comment.findAllByRelatedComment(attrs.comment as Comment, [sort: 'dateCreated', order: 'desc']))
        }
    }

    def commentList(List<Comment> commentList, def emptyMessage = '') {
        if (commentList && commentList.size() > 0) {
            def result = ""
            commentList.each {
                result += render template: '/comment/view', model: [comment: it]
            }
            result
        } else
            emptyMessage
    }

    def like = { attrs, body ->
        def likes = stocks.twitter.Like.findAllByComment(attrs.comment)
        out << """
            <div class="rateWrapper">
                <span class="rate rateUp ${
            likes.find { it.type == 'LIKE' && it.owner?.id == springSecurityService.currentUser?.id } ? 'active' : ''
        }" data-item="${attrs.comment?.id}">
                    <span class="rateUpN">${likes.count { it.type == 'LIKE' }}</span>
                </span>
                <span class="rate rateDown ${
            likes.find { it.type == 'DISLIKE' && it.owner?.id == springSecurityService.currentUser?.id } ? 'active' : ''
        }" data-item="${attrs.comment?.id}">
                    <span class="rateDownN">${likes.count { it.type == 'DISLIKE' }}</span>
                </span>
            </div>
"""
    }

    def documentController = { attrs, body ->
        if (attrs.document instanceof Article)
            out << 'article'
        else
            out << 'news'
    }
}
