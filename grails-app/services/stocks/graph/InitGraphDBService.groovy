package stocks.graph

import com.orientechnologies.orient.core.metadata.schema.OType

class InitGraphDBService {

    def graphDBService
    def messageSource
    def commonGraphService

    def init() {
        try {
            initPerson()
            initGroup()
            initProperty()
            initMaterial()
            initSharing()
            initComment()
            initRate()
            initLike()
            initMembership()
            initFollow()
        }
        catch (ignored){
            log.error('error initializing graph db')
        }
    }

    def initPerson() {
        def personClass = graphDBService.ensureVertexClass('Person')
        graphDBService.ensureProperty(personClass, 'identifier', OType.LONG, true)
        graphDBService.ensureProperty(personClass, 'title', OType.STRING, true)

        def systemUser = commonGraphService.systemUser
        if (!systemUser)
            graphDBService.addVertex('Person', [
                    identifier: 0,
                    title     : messageSource.getMessage('twitter.systemUser', null, '4?????', Locale.ENGLISH)
            ])
    }

    def initGroup() {
        def personClass = graphDBService.ensureVertexClass('Group')
        graphDBService.ensureProperty(personClass, 'title', OType.STRING, true)
        graphDBService.ensureProperty(personClass, 'description', OType.STRING, true)
        graphDBService.ensureProperty(personClass, 'imageId', OType.LONG, true)
        graphDBService.ensureProperty(personClass, 'membershipType', OType.STRING, true)
//        graphDBService.ensureProperty(personClass, 'membershipPeriod', OType.STRING, true)
        graphDBService.ensureProperty(personClass, 'membership1MonthPrice', OType.INTEGER, true)
        graphDBService.ensureProperty(personClass, 'membership3MonthPrice', OType.INTEGER, true)
        graphDBService.ensureProperty(personClass, 'membership6MonthPrice', OType.INTEGER, true)
        graphDBService.ensureProperty(personClass, 'membership12MonthPrice', OType.INTEGER, true)
        graphDBService.ensureProperty(personClass, 'allowExceptionalUsers', OType.BOOLEAN, true)
        graphDBService.ensureProperty(personClass, 'ownerType', OType.STRING, true)

        graphDBService.ensureEdgeClass('Own')
        graphDBService.ensureEdgeClass('Editor')
        graphDBService.ensureEdgeClass('Author')

        def publicGroup = commonGraphService.publicGroup
        if (!publicGroup)
            graphDBService.addVertex('Group', [
                    title                :  messageSource.getMessage('twitter.publicGroup', null, 'همه کاربران', Locale.ENGLISH),
                    membershipType       : 'open',
                    membershipPeriod     : 'unlimited',
                    membershipPrice      : 0,
                    allowExceptionalUsers: false,
                    ownerType            : 'system'
            ])
    }

    def initMaterial() {
        def materialClass = graphDBService.ensureVertexClass('Material', null, true)
        graphDBService.ensureProperty(materialClass, 'identifier', OType.LONG, true)
        graphDBService.ensureProperty(materialClass, 'publishDate', OType.DATETIME, true)
        graphDBService.ensureProperty(materialClass, 'title', OType.STRING, true)
        graphDBService.ensureProperty(materialClass, 'imageId', OType.LONG, true)
        graphDBService.ensureProperty(materialClass, 'description', OType.STRING, true)
        [
                'Article',
                'Portfolio',
                'BackTest',
                'Screener',
                'Analysis'
        ].each {
            clazz ->
                graphDBService.ensureVertexClass(clazz, materialClass)
        }
    }

    def initProperty(){
        def propertyClass = graphDBService.ensureVertexClass('Property', null, true)
        graphDBService.ensureProperty(propertyClass, 'identifier', OType.LONG, true)
        graphDBService.ensureProperty(propertyClass, 'title', OType.STRING, true)
        [
                'Symbol',
                'Currency',
                'Coin',
                'Metal',
                'Oil',
                'Future',
                'Index'
        ].each {
            clazz ->
                graphDBService.ensureVertexClass(clazz, propertyClass)
        }
    }

    def initSharing(){
        graphDBService.ensureEdgeClass('About')
        graphDBService.ensureEdgeClass('Share')
    }

    def initComment(){
        def commentClass = graphDBService.ensureVertexClass('Comment')
        graphDBService.ensureProperty(commentClass, 'body', OType.STRING, true)
        graphDBService.ensureProperty(commentClass, 'dateCreated', OType.DATETIME, true)
        graphDBService.ensureProperty(commentClass, 'lastUpdated', OType.DATETIME, true)
        graphDBService.ensureEdgeClass('RelatedTo')
    }

    def initRate(){
        def rateClass = graphDBService.ensureEdgeClass('Rate')
        graphDBService.ensureProperty(rateClass, 'value', OType.INTEGER, true)
        graphDBService.ensureProperty(rateClass, 'date', OType.DATETIME, true)
    }

    def initLike(){
        def likeClass = graphDBService.ensureEdgeClass('Like')
        graphDBService.ensureProperty(likeClass, 'date', OType.DATETIME, true)
        def dislikeClass = graphDBService.ensureEdgeClass('Dislike')
        graphDBService.ensureProperty(dislikeClass, 'date', OType.DATETIME, true)
    }

    def initFollow(){
        def followClass = graphDBService.ensureEdgeClass('Follow')
//        graphDBService.ensureProperty(followClass, 'startDate', OType.DATETIME, true)
//        [
//                'FollowArticle',
//                'FollowBackTest',
//                'FollowScreener',
//                'FollowPortfolio',
//                'FollowAnalysis'
//        ].each {
//            clazz ->
//                graphDBService.ensureEdgeClass(clazz, followClass)
//                graphDBService.ensureProperty(followClass, 'properties', OType.LINKSET, false, 'Property')
//
//        }
    }

    def initMembership(){
        def memberClass = graphDBService.ensureEdgeClass('Member')
        graphDBService.ensureProperty(memberClass, 'type', OType.STRING, true)
        graphDBService.ensureProperty(memberClass, 'startDate', OType.DATETIME, true)
        graphDBService.ensureProperty(memberClass, 'endDate', OType.DATETIME, false)
        graphDBService.ensureProperty(memberClass, 'autoExtend', OType.INTEGER, false)
    }
}
