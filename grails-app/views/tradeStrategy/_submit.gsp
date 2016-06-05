<g:form action="save">
    <g:hiddenField name="id" value="${params.id}"/>
    <g:hiddenField name="name" id="form_name"/>
    <g:hiddenField name="buyRule" id="form_buyRule"/>
    <g:hiddenField name="sellRule" id="form_sellRule"/>
    <g:hiddenField name="lossLimit" id="form_lossLimit"/>
    <g:hiddenField name="profitLimit" id="form_profitLimit"/>
    <g:hiddenField name="timeLimit" id="form_timeLimit"/>
    <div class="toolbar" style="margin0top:10px;padding-top:10px;" onclick="gatherData()">
        <g:if test="${params.id}">
            %{--<form:submitButton name="submit" text="${message(code: 'tradeStrategy.save')}"/>--}%
            <form:submitButton name="submitAndExit" text="${message(code: 'tradeStrategy.saveAndExit')}"/>
        </g:if>
        <g:else>
            <form:submitButton name="submitAndContinue" text="${message(code: 'tradeStrategy.saveAndContinue')}"/>
        </g:else>
    </div>
</g:form>

<script language="javascript" type="text/javascript">
    function gatherData() {
        $('#form_name').val($('#nameInput').val());
        $('#form_lossLimit').val($('#lossLimitInput').val());
        $('#form_profitLimit').val($('#profitLimitInput').val());
        $('#form_timeLimit').val($('#timeLimitInput').val());
        var buyRule = [];
        $.each($('#frmBuyRule').contents().find('#filter-query-panel').find('form'), function (index, item) {
            buyRule[buyRule.length] = $(item).serializeArray();
        });
        $('#form_buyRule').val(JSON.stringify(buyRule));
        var sellRule = [];
        $.each($('#frmSellRule').contents().find('#filter-query-panel').find('form'), function (index, item) {
            sellRule[sellRule.length] = $(item).serializeArray();
        });
        $('#form_sellRule').val(JSON.stringify(sellRule));
    }
</script>