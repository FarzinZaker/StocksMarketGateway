package stocks.tools

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.util.TypeCast

import java.text.NumberFormat

class CorrelationService {

    def grailsApplication
    def messageSource

    def calculateSeries(String sourceGroup, String source, String targetGroup, String target, Date startDate, Date endDate, String period, String adjustmentType) {

        NumberFormat format = NumberFormat.getNumberInstance()
        format.maximumFractionDigits = 2
        def result = []

        def sourceServices = getServiceClasses(sourceGroup)
        for (def i = 0; i < sourceServices.size(); i++) {
            def sourceService = sourceServices[i]
            def sourceData = sourceService.getItemChangeValues(source, startDate, endDate, period, adjustmentType)
            def sourceDataList = sourceData.itemChangeValues as ArrayList
            if (sourceDataList.size() == 0)
                continue
            def minDate = sourceData.minDate as Date
            def maxDate = sourceData.maxDate as Date

            def core = new Core()
            int start = 0
            int end = sourceDataList.size() - 1
            def MInteger beginIndex
            def MInteger endIndex
            double[] res
            double[] sourceD = TypeCast.toDoubleArray(sourceDataList)

            def targetServices = getServiceClasses(targetGroup)
            for (def j = 0; j < targetServices.size(); j++) {
                def targetService = targetServices[j]
                def targetItems = getTargetItems(targetService, target)
                def cache = targetService.getItemValuesCache(targetItems, startDate, endDate, period, adjustmentType)
                def baseValueCache = targetService.getBaseValueCache(targetItems, startDate, adjustmentType)
                for (def k = 0; k < targetItems.size(); k++) {
                    def targetItem = targetItems[k].toString()
                    def targetDataList = targetService.getItemChangeValues(targetItem, period, cache, baseValueCache, minDate, maxDate).itemChangeValues
                    if (targetDataList.size() == 0)
                        continue

                    end = [end, targetDataList.size() - 1].min()
                    double[] targetD = TypeCast.toDoubleArray(targetDataList)
                    beginIndex = new MInteger()
                    endIndex = new MInteger()
                    res = new double[end + 1]
                    core.correl(
                            start,
                            end,
                            sourceD,
                            targetD,
                            end + 1,
                            beginIndex,
                            endIndex,
                            res)

                    result <<
                            [
                                    targetGroup    : targetService.class.name,
                                    targetGroupName: messageSource.getMessage(targetService.class.name, null, targetService.class.name, Locale.ENGLISH),
                                    targetItem     : targetItem,
                                    targetItemName : targetService.getItemName(targetItem),
                                    correlation    : Math.round(res[0] * 100) / 100D
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
