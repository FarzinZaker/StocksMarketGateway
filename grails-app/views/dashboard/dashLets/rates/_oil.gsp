<%@ page import="stocks.RateHelper" %>
<div class="lastUpdatedFlag"><g:message code="dashboard.lastUpdated"/> <span id="oil_lastUpdatedFlag"></span></div>
<g:each in="${['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']}" var="oil"
        status="indexer">
    <div class="marketViewItem ${indexer % 2 ? 'even' : 'odd'}" id="rate_oil_${oil}">
        <span class="marketViewItem_label"><g:message code="oil.${oil}.title"/></span>
        <span class="sparkLine steel"></span>
        <span class="marketViewItem_value" style="direction: ltr;display: inline-block">-</span>

        <div class="clear-fix"></div>
    </div>
</g:each>

<div class="footNote">
    <h6>
        <g:message code="oil.markets"/>
    </h6>
    <ul>
        <g:each in="${['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']}" var="oil">
            <li>${RateHelper.OILS."${oil}".name}</li>
        </g:each>
    </ul>
</div>


<script language="javascript" type="text/javascript">

    function fillOilData(data, date) {

        <g:each in="${['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']}" var="oil" status="indexer">
        $('#rate_oil_${oil}').find('.marketViewItem_value').html(formatNumber(data.${oil.replace('-', '_')}.price) + ' <span>' + data.${oil.replace('-', '_')}.unit + '</span>');
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'oil', action: 'sparkLine')}',
            data: {symbol: '${oil}'}
        }).done(function (response) {
            $('#rate_oil_${oil}').find('.sparkLine')
                    %{--.css('position', 'absolute').css('left', '10px;').css('top','${0 + indexer * 30}px')--}%
                    .kendoSparkline({
                        data: response.value
                    });
        });
        </g:each>
        $('#oil_lastUpdatedFlag').html(date);
    }
</script>

