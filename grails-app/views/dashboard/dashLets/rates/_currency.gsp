<%@ page import="stocks.RateHelper" %>
<g:each in="${['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey']}" var="currency" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_currency_${currency}">
        <span class="marketViewItem_label">${RateHelper.CURRENCIES."${currency}".name}</span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillCurrencyData(data) {

        <g:each in="${['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey']}" var="currency" status="indexer">
        $('#rate_currency_${currency}').find('.marketViewItem_value').html('<span>' + data.${currency.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${currency.replace('-', '_')}.price));
        </g:each>

    }
</script>
