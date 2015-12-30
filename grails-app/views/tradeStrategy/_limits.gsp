<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.lossLimit">
                <form:numericTextBox name="lossLimit" id="lossLimitInput" value="${tradeStrategy?.lossLimit ?: 0}" style="width: 450px;"
                                     format="p0" step="0.01" min="0"/>
            </form:field>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.profitLimit">
                <form:numericTextBox name="profitLimit" id="profitLimitInput" value="${tradeStrategy?.profitLimit ?: 0}" style="width: 450px;"
                                     format="p0" step="0.01" min="0"/>
            </form:field>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.timeLimit">
                <form:numericTextBox name="timeLimit" id="timeLimitInput" value="${tradeStrategy?.timeLimit ?: 0}" style="width: 450px;"
                                     format="#,## ${message(code: 'day')}" step="1" min="0" decimals="0"/>
            </form:field>
        </div>
    </div>
</div>