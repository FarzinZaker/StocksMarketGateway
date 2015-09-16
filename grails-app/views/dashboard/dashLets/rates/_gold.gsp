<%@ page import="stocks.RateHelper" %>
<div class="lastUpdatedFlag"><g:message code="dashboard.lastUpdated"/> <span id="gold_lastUpdatedFlag"></span></div>
<g:each in="${['ons', 'n-coin', 'o-coin', 'h-coin', 'q-coin', 'geram18']}" var="gold" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_gold_${gold}">
        <span class="marketViewItem_label">${RateHelper.COINS."${gold}"?.name}</span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillGoldData(data, date) {

        <g:each in="${['ons', 'n-coin', 'o-coin', 'h-coin', 'q-coin', 'geram18']}" var="gold" status="indexer">
        $('#rate_gold_${gold}').find('.marketViewItem_value').html('<span>' + data.${gold.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${gold.replace('-', '_')}.price));
        </g:each>
        $('#gold_lastUpdatedFlag').html(date);

    }
</script>
