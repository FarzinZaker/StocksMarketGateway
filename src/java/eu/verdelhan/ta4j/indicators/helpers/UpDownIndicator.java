package eu.verdelhan.ta4j.indicators.helpers;

import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.TADecimal;
import eu.verdelhan.ta4j.indicators.CachedIndicator;

/**
 * Created by farzin on 01/02/2015.
 */
public class UpDownIndicator extends CachedIndicator<Boolean> {

    /**
     * Upper indicator
     */
    private final Indicator<? extends TADecimal> up;
    /**
     * Lower indicator
     */
    private final Indicator<? extends TADecimal> low;

    public UpDownIndicator(Indicator<? extends TADecimal> up, Indicator<? extends TADecimal> low) {

        super(up);
        this.up = up;
        this.low = low;
    }

    @Override
    protected Boolean calculate(int index) {
        return index == 0 || up.getValue(index).isGreaterThanOrEqual(low.getValue(index));
    }
}
