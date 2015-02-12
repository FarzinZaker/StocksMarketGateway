package stocks.indicators

import eu.verdelhan.ta4j.indicators.AbstractIndicator

/**
 * Created by farzin on 03/02/2015.
 */
public abstract class IndicatorServiceBase<T, K> {

    public abstract List<K> getCommonParameters()

    public abstract Double calculate(T item, K parameter, Date date)

}