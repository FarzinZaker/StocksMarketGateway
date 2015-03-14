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
                  model="${[name: message(code: 'backTest.summary.yearlyBenefit'), value: "<span class='${summary.returnOfInvestment > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.yearlyBenefit, type: 'number')}</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.dailyBenefit'), value: "<span class='${summary.returnOfInvestment > 0 ? 'positive' : 'negative'}'>${formatNumber(number: summary.dailyBenefit, type: 'number')}</span>"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.totalWage'), value: "${formatNumber(number: summary.totalWage, type: 'number')}"]}"/>
        <g:render template="summaryItem"
                  model="${[name: message(code: 'backTest.summary.totalTax'), value: "${formatNumber(number: summary.totalTax, type: 'number')}"]}"/>
    </div>
</g:if>