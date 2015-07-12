
<%@ page import="stocks.RateHelper" %>
<g:each in="${['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']}" var="oil" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_oil_${oil}">
        <span class="marketViewItem_label">${RateHelper.OILS."${oil}"?.name}</span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillOilData(data) {

        <g:each in="${['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']}" var="oil" status="indexer">
        $('#rate_oil_${oil}').find('.marketViewItem_value').html('<span>' + data.${oil.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${oil.replace('-', '_')}.price));
        </g:each>

    }
</script>

