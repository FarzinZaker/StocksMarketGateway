<%@ page import="stocks.RateHelper" %>
<div class="lastUpdatedFlag"><g:message code="dashboard.lastUpdated"/> <span id="metal_lastUpdatedFlag"></span></div>
<g:each in="${['copper', 'aluminium', 'nickel', 'tin', 'zinc', 'cobalt']}" var="metal" status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_metal_${metal}">
        <span class="marketViewItem_label">${RateHelper.METALS."${metal}"?.name}</span>
        <span class="sparkLine magenta"></span>
        <span class="marketViewItem_value">-</span>
    </div>
</g:each>


<script language="javascript" type="text/javascript">

    function fillMetalData(data, date) {

        <g:each in="${['copper', 'aluminium', 'nickel', 'tin', 'zinc', 'cobalt']}" var="metal" status="indexer">
        $('#rate_metal_${metal}').find('.marketViewItem_value').html('<span>' + data.${metal.replace('-', '_')}.unit + '</span> ' + formatNumber(data.${metal.replace('-', '_')}.price));
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'metal', action: 'sparkLine')}',
            data: {symbol: '${metal}'}
        }).done(function (response) {
            $('#rate_metal_${metal}').find('.sparkLine')
                    %{--.css('position', 'absolute').css('left', '10px;').css('top','${0 + indexer * 30}px')--}%
                    .kendoSparkline({
                        data: response.value
                    });
        });
        </g:each>
        $('#metal_lastUpdatedFlag').html(date);

    }
</script>
