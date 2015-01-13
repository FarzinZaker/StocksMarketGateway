package stocks.tools

import org.springframework.web.servlet.i18n.SessionLocaleResolver

import java.text.NumberFormat

class CorrelationService {

    def grailsApplication
    SessionLocaleResolver localeResolver
    def messageSource

    def calculateSeries(String sourceGroup, String source, String targetGroup, String target, Date startDate, Date endDate, String period) {

        NumberFormat format = NumberFormat.getNumberInstance()
        format.maximumFractionDigits = 2
        def result = []

        def sourceServices = getServiceClasses(sourceGroup)
        for (def i = 0; i < sourceServices.size(); i++) {
            def sourceService = sourceServices[i]
            def sourceData = sourceService.getItemChangeValues(source, startDate, endDate, period)
            def sourceDataList = sourceData.itemChangeValues as ArrayList
            if (sourceDataList.size() == 0)
                continue
            def minDate = sourceData.minDate as Date
            def maxDate = sourceData.maxDate as Date
            def sourceAverage = sourceDataList.sum() / sourceDataList.size()
            def sourceDiffList = sourceDataList.collect { it - sourceAverage }
            def sourceDiffPowSum = sourceDiffList.sum { Math.pow(it as Double, 2) }

            def targetServices = getServiceClasses(targetGroup)
            for (def j = 0; j < targetServices.size(); j++) {
                def targetService = targetServices[j]
                def targetItems = getTargetItems(targetService, target)
                def cache = targetService.getItemValuesCache(targetItems, startDate, endDate, period)
                def baseValueCache = targetService.getBaseValueCache(targetItems, startDate)
                for (def k = 0; k < targetItems.size(); k++) {
                    def targetItem = targetItems[k].toString()
                    def targetDataList = targetService.getItemChangeValues(targetItem, period, cache, baseValueCache, minDate, maxDate).itemChangeValues
                    if (targetDataList.size() == 0)
                        continue
                    def targetAverage = targetDataList.sum() / targetDataList.size()
                    def targetDiffList = sourceDataList.collect { it - targetAverage }
                    def targetDiffPowSum = targetDiffList.sum { Math.pow(it as Double, 2) }
                    def sourceDiffAndTargetDiffMultiplicationSum = 0
                    for (def l = 0; l < sourceDiffList.size(); l++)
                        sourceDiffAndTargetDiffMultiplicationSum += sourceDiffList[l] * targetDiffList[l]

                    def correlation = sourceDiffAndTargetDiffMultiplicationSum / (Math.pow(sourceDiffPowSum as Double, 0.5) * Math.pow(targetDiffPowSum as Double, 0.5))
                    result <<
                            [
                                    targetGroup    : targetService.class.name,
                                    targetGroupName: messageSource.getMessage(targetService.class.name, null, localeResolver.defaultLocale),
                                    targetItem     : targetItem,
                                    targetItemName : targetService.getItemName(targetItem),
                                    correlation    : correlation == Double.NaN ? '0' : format.format(correlation)
                            ]
                }
            }
        }

        result = result.sort { it.correlation == 'NaN' ? 2 : -(it.correlation as Double) }
        result
    }

    ArrayList<CorrelationServiceBase> getServiceClasses(group) {
        def serviceClasses
        if (group == 'all') {
            serviceClasses = grailsApplication.serviceClasses.findAll { service ->
                service.fullName.contains('stocks.tools.correlation')
            }
        } else {
            serviceClasses = grailsApplication.serviceClasses.find { service ->
                service.fullName == group
            }
        }
        serviceClasses.collect { grailsApplication.mainContext[it.propertyName] as CorrelationServiceBase }
    }

    ArrayList<String> getTargetItems(CorrelationServiceBase correlationService, String target) {
        if (!target || target == '' || target == 'all')
            correlationService.all()
        else
            [target]
    }
}
