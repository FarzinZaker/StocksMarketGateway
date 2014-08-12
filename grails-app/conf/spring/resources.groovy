import stocks.CodalEventService
import stocks.TSEEventService

// Place your Spring DSL code here
beans = {
    codalEventGateway(CodalEventService) { bean -> bean.autowire = 'byName' }
    tseEventGateway(TSEEventService) { bean -> bean.autowire = 'byName' }
}
