package stocks.indicators

import eu.verdelhan.ta4j.indicators.AbstractIndicator

/**
 * Created by farzin on 03/02/2015.
 */
interface IndicatorServiceBase<T, K> {

    Boolean getEnabled()

    List<K> getCommonParameters()

    Double calculate(T item, K parameter, Date date)

    Map bulkCalculate(T item, K parameter)
}