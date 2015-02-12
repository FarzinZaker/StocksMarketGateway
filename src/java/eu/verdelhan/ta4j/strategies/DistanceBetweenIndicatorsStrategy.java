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
package eu.verdelhan.ta4j.strategies;

import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.TADecimal;
import eu.verdelhan.ta4j.indicators.simple.ConstantIndicator;

/**
 * Distance between {@link eu.verdelhan.ta4j.Indicator indicators} strategy.
 * <p>
 * Enter: when the distance between the two indicators is above the difference<br>
 * Exit: when the distance between the two indicators is below the difference
 * <p>
 *
 */
public class DistanceBetweenIndicatorsStrategy extends AbstractStrategy {

    private Indicator<? extends TADecimal> upper;

    private Indicator<? extends TADecimal> lower;

    private double distance;

    private double difference;

    /**
     * Constructor.
     * @param indicator the indicator
     * @param constant a numerical constant (will be a {@link eu.verdelhan.ta4j.indicators.simple.ConstantIndicator})
     * @param distance the distance
     * @param difference the difference
     */
    public <T extends TADecimal> DistanceBetweenIndicatorsStrategy(Indicator<? extends TADecimal> indicator, T constant,
            double distance, double difference) {
        this.upper = indicator;
        this.lower = new ConstantIndicator<T>(constant);
        this.distance = distance;
        this.difference = difference;
    }

    /**
     * Constructor.
     * @param upper the upper indicator
     * @param lower the lower indicator
     * @param distance the distance
     * @param difference the difference
     */
    public DistanceBetweenIndicatorsStrategy(Indicator<? extends TADecimal> upper, Indicator<? extends TADecimal> lower,
            double distance, double difference) {
        this.upper = upper;
        this.lower = lower;
        this.distance = distance;
        this.difference = difference;
    }

    @Override
    public boolean shouldEnter(int index) {
        TADecimal threshold = TADecimal.valueOf((difference + 1.0) * distance);
        boolean enter = upper.getValue(index).minus(lower.getValue(index)).isGreaterThanOrEqual(threshold);
        traceEnter(index, enter);
        return enter;
    }

    @Override
    public boolean shouldExit(int index) {
        TADecimal threshold = TADecimal.valueOf((1.0 - difference) * distance);
        boolean exit = upper.getValue(index).minus(lower.getValue(index)).isLessThanOrEqual(threshold);
        traceExit(index, exit);
        return exit;
    }

    @Override
    public String toString() {
        return String.format("%s upper: %s lower: %s", this.getClass().getSimpleName(), upper, lower);
    }
}
