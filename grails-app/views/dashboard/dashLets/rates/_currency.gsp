<%@ page import="stocks.RateHelper" %>
<div class="lastUpdatedFlag"><g:message code="dashboard.lastUpdated"/> <span id="currency_lastUpdatedFlag"></span></div>
<g:each in="${['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey']}" var="currency" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_currency_${currency}">
        <span class="marketViewItem_label">${RateHelper.CURRENCIES."${currency}".name}</span>
        <span class="sparkLine cyan"></span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillCurrencyData(data, date) {

        <g:each in="${['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey']}" var="currency" status="indexer">
        $('#rate_currency_${currency}').find('.marketViewItem_value').html('<span>' + data.${currency.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${currency.replace('-', '_')}.price));
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'currency', action: 'sparkLine')}',
            data: {symbol: '${currency}'}
        }).done(function (response) {
            $('#rate_currency_${currency}').find('.sparkLine').kendoSparkline({
                data: response.value
            });
        });
        </g:each>
        $('#currency_lastUpdatedFlag').html(date);

    }
</script>
