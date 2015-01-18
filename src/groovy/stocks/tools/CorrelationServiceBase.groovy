package stocks.tools

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory

/**
 * Created by farzin on 06/01/2015.
 */
public abstract class CorrelationServiceBase {

    abstract List searchItems(String term)

    abstract String getItemName(String item)

    abstract def all()

    abstract Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period)

    abstract List getItemValues(String item, Date startDate, Date endDate, String period)

    abstract Double getBaseValue(String item, Date startDate)

    abstract Map<String, Double> getBaseValueCache(List<String> items, Date startDate)

    static List normalizeItemValues(List itemValues, Double baseValue, String period, Date minDate = null, Date maxDate = null) {

        if (!itemValues || itemValues.size() == 0)
            return []

        if (!maxDate)
            maxDate = itemValues.collect { it.date }.max() as Date
        if (!minDate)
            minDate = itemValues.collect { it.date }.min() as Date

        def currentDate = minDate
        def lastValue = baseValue ?: 0
        while (currentDate <= maxDate) {
            def item = itemValues.find { it.date == currentDate }
            if (!item)
                itemValues.add([date: currentDate, value: lastValue])
            else
                lastValue = item.value
            switch (period) {
                case 'daily':
                    use(TimeCategory) {
                        currentDate = currentDate + 1.day
                    }
                    break
                case 'weekly':
                    use(TimeCategory) {
                        currentDate = currentDate + 7.day
                    }
                    break
                case 'monthly':
                    def cal = Calendar.getInstance()
                    cal.setTime(currentDate)
                    def jc = new JalaliCalendar(cal as GregorianCalendar)
                    use(TimeCategory) {
                        currentDate = currentDate + (jc.getLastDayOfMonth(jc.getYear(), jc.getMonth())).day
                    }
                    break
            }
        }
        itemValues
    }

    static
    def getItemChangeValues(String item, String period, Map<String, List> cache, Map<String, Double> baseValueCache, Date minDate, Date maxDate) {
        def itemValues = normalizeItemValues(cache[item], baseValueCache[item], period, minDate, maxDate)?.sort {
            it.date
        }
        def itemChangeValues = []
        for (def i = 1; i < itemValues.size(); i++)
            itemChangeValues << ((itemValues[i].value ?: 0) - (itemValues[i - 1].value ?: 0)) / (itemValues[i - 1].value ?: 1)

        [
                itemChangeValues: itemChangeValues?.collect {
                    it == Double.NaN || it == Double.POSITIVE_INFINITY || it == Double.NEGATIVE_INFINITY ? 0 : it
                },
                minDate         : itemValues.collect { it.date }.min(),
                maxDate         : itemValues.collect { it.date }.max()
        ]
    }

    def getItemChangeValues(String item, Date startDate, Date endDate, String period) {
        def itemValues = normalizeItemValues(getItemValues(item, startDate, endDate, period), getBaseValue(item, startDate), period).sort {
            it.date
        }
        def itemChangeValues = []
        for (def i = 1; i < itemValues.size(); i++)
            itemChangeValues << (itemValues[i].value - itemValues[i - 1].value) / itemValues[i - 1].value

        [
                itemChangeValues: itemChangeValues,
                minDate         : itemValues.collect { it.date }.min(),
                maxDate         : itemValues.collect { it.date }.max()
        ]
    }
}
