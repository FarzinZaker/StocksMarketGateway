/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Marc de Verdelhan & respective authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.indicators.helpers;

import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.TADecimal;
import eu.verdelhan.ta4j.indicators.CachedIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;

/**
 * Standard deviation indicator.
 * <p>
 * @see http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:standard_deviation_volatility
 */
public class StandardDeviationIndicator extends CachedIndicator<TADecimal> {

    private Indicator<? extends TADecimal> indicator;

    private int timeFrame;

    private SMAIndicator sma;

    /**
     * Constructor.
     * @param indicator the indicator
     * @param timeFrame the time frame
     */
    public StandardDeviationIndicator(Indicator<? extends TADecimal> indicator, int timeFrame) {
        super(indicator);
        this.indicator = indicator;
        this.timeFrame = timeFrame;
        sma = new SMAIndicator(indicator, timeFrame);
    }

    @Override
    protected TADecimal calculate(int index) {
        final int startIndex = Math.max(0, index - timeFrame + 1);
        final int numberOfObservations = index - startIndex + 1;
        TADecimal standardDeviation = TADecimal.ZERO;
        TADecimal average = sma.getValue(index);
        for (int i = startIndex; i <= index; i++) {
            TADecimal pow = indicator.getValue(i).minus(average).pow(2);
            standardDeviation = standardDeviation.plus(pow);
        }
        standardDeviation = standardDeviation.dividedBy(TADecimal.valueOf(numberOfObservations));
        return standardDeviation.sqrt();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " timeFrame: " + timeFrame;
    }
}
