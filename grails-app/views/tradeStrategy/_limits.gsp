<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.lossLimit">
                <form:numericTextBox name="lossLimit" id="lossLimitInput" value="${tradeStrategy?.lossLimit ?: 0}" style="width: 450px;"
                                     format="#,## ${message(code: 'rial')}" step="10000" min="0" decimals="0"/>
            </form:field>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.profitLimit">
                <form:numericTextBox name="profitLimit" id="profitLimitInput" value="${tradeStrategy?.profitLimit ?: 0}" style="width: 450px;"
                                     format="#,## ${message(code: 'rial')}" step="10000" min="0" decimals="0"/>
            </form:field>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.timeLimit">
                <form:numericTextBox name="timeLimit" id="timeLimitInput" value="${tradeStrategy?.timeLimit ?: 0}" style="width: 450px;"
                                     format="#,## ${message(code: 'day')}" step="1" min="0" decimals="0"/>
            </form:field>
        </div>
    </div>
</div>