<div id="summaryTimer"></div>
<g:if test="${summary}">

    <div class="row-fluid">
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.initialValue'), value: "${formatNumber(number: summary.initialValue, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.finalValue'), value: "${formatNumber(number: summary.finalValue, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.profitableTradesCount'), value: "${formatNumber(number: summary.profitableTradesCount, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.lossingTradesCount'), value: "${formatNumber(number: summary.lossingTradesCount, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.successRate'), value: "<span class='${summary.returnOfInvestment > 50 ? 'positive' : 'negative'}'>${formatNumber(number: summary.successRate, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.returnOfInvestment'), value: "<span class='${summary.returnOfInvestment > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.returnOfInvestment, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
    </div>

    <div class="row-fluid">
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.maxDrawDown'), value: "${formatNumber(number: summary.maxDrawDown, type: 'number', maxFractionDigits: 2)} %"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.yearlyBenefit'), value: "<span class='${summary.returnOfInvestment > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.yearlyBenefit, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.dailyBenefit'), value: "<span class='${summary.returnOfInvestment > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.dailyBenefit, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.totalWage'), value: "${formatNumber(number: summary.totalWage, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.totalTax'), value: "${formatNumber(number: summary.totalTax, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.indexYearlyBenefit'), value: "<span class='${summary.indexYearlyBenefit > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.indexYearlyBenefit, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
    </div>

    <div class="row-fluid">
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.indexDailyBenefit'), value: "<span class='${summary.indexDailyBenefit > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.indexDailyBenefit, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.performanceCompareToIndex'), value: "<span class='${summary.performanceCompareToIndex > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.performanceCompareToIndex, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.dailyBenefitInSimpleHoldingCondition'), value: "<span class='${summary.dailyBenefitInSimpleHoldingCondition > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.dailyBenefitInSimpleHoldingCondition, type: 'number', maxFractionDigits: 2)} %</span>"]}"/>
    </div>
</g:if>