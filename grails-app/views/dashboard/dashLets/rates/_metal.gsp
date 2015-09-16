<%@ page import="stocks.RateHelper" %>
<div id="metal_lastUpdatedFlag" class="lastUpdatedFlag"></div>
<g:each in="${['copper', 'aluminium', 'nickel', 'tin', 'zinc']}" var="metal" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_metal_${metal}">
        <span class="marketViewItem_label">${RateHelper.METALS."${metal}"?.name}</span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillMetalData(data, date) {

        <g:each in="${['copper', 'aluminium', 'nickel', 'tin', 'zinc']}" var="metal" status="indexer">
        $('#rate_metal_${metal}').find('.marketViewItem_value').html('<span>' + data.${metal.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${metal.replace('-', '_')}.price));
        </g:each>
        $('#metal_lastUpdatedFlag').html(date);

    }
</script>
