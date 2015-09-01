import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine
import stocks.BulkDataService
import stocks.CodalEventService
import stocks.CommodityEventService
import stocks.RateEventService
import stocks.TSEEventService

// Place your Spring DSL code here
beans = {
    codalEventGateway(CodalEventService) { bean -> bean.autowire = 'byName' }
    tseEventGateway(TSEEventService) { bean -> bean.autowire = 'byName' }
    commodityEventGateway(CommodityEventService) { bean -> bean.autowire = 'byName' }
    rateEventGateway(RateEventService) { bean -> bean.autowire = 'byName' }
    bulkDataGateway(BulkDataService) { bean -> bean.autowire = 'byName' }
    groovyPagesTemplateEngine(GroovyPagesTemplateEngine) { bean -> bean.autowire = 'byName' }
}
