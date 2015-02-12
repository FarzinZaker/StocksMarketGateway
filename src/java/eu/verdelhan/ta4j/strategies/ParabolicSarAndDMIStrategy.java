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

import eu.verdelhan.ta4j.indicators.trackers.DirectionalMovementIndicator;
import eu.verdelhan.ta4j.indicators.trackers.ParabolicSarIndicator;

/**
 * {@link eu.verdelhan.ta4j.indicators.trackers.ParabolicSarIndicator Parabolic SAR} and {@link eu.verdelhan.ta4j.indicators.trackers.DirectionalMovementIndicator DMI} strategy.
 * <p>
 * Enter: according to the provided {@link eu.verdelhan.ta4j.indicators.trackers.ParabolicSarIndicator Parabolic SAR} {@link Strategy strategy}<br>
 * Exit: according to the {@link eu.verdelhan.ta4j.strategies.AndStrategy AND combination} of the provided {@link eu.verdelhan.ta4j.indicators.trackers.ParabolicSarIndicator Parabolic SAR} and {@link eu.verdelhan.ta4j.indicators.trackers.DirectionalMovementIndicator DMI} {@link Strategy strategies}
 */
public class ParabolicSarAndDMIStrategy extends AbstractStrategy {

    private IndicatorCrossedIndicatorStrategy parabolicStrategy;
    
    private IndicatorOverIndicatorStrategy dmiStrategy;

    /**
     * Constructor.
     * @param parabolicStrategy the parabolic SAR strategy
     * @param dmiStrategy the DMI strategy
     */
    public ParabolicSarAndDMIStrategy(IndicatorCrossedIndicatorStrategy parabolicStrategy, IndicatorOverIndicatorStrategy dmiStrategy) {
        this.parabolicStrategy = parabolicStrategy;
        this.dmiStrategy = dmiStrategy;
    }

    @Override
    public boolean shouldEnter(int index) {
        boolean enter = parabolicStrategy.shouldEnter(index);
        traceEnter(index, enter);
        return enter;
    }

    @Override
    public boolean shouldExit(int index) {
        boolean exit = dmiStrategy.and(parabolicStrategy).shouldExit(index);
        traceExit(index, exit);
        return exit;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
