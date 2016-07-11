<div class="dashLet cyan dark hideBeforeLoad noPlaceReservation">
    <h2 style="float:right"><i class="fa fa-money"></i> <g:message code="rates.title"/></h2>

    <div id="ratesTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="ratesTabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="rates.currency.title"/>
                </li>
                <li>
                    <g:message code="rates.gold.title"/>
                </li>
                <li>
                    <g:message code="rates.metal.title"/>
                </li>
                <li>
                    <g:message code="rates.oil.title"/>
                </li>
            </ul>

            <div style="height:256px !important;overflow: hidden;">
                <g:render template="dashLets/rates/currency"/>
            </div>

            <div style="height:256px !important;overflow: hidden;">
                <g:render template="dashLets/rates/gold"/>
            </div>

            <div style="height:256px !important;overflow: hidden;">
                <g:render template="dashLets/rates/metal"/>
            </div>

            <div style="height:256px !important;overflow: hidden;">
                <g:render template="dashLets/rates/oil"/>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function loadRates(){

        $.ajax({
            type: "POST",
            url: '${createLink(action: 'rates')}?t=' +  Math.round(new Date().getTime()/30000)
        }).done(function (data) {
            $('#ratesTimer').timer('start');
            fillCurrencyData(data.currency, data.currencyDate);
            fillGoldData(data.gold, data.goldDate);
            fillMetalData(data.metal, data.metalDate);
            fillOilData(data.oil, data.oilDate);
        });
    }

    $(document).ready(function () {
        $("#ratesTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            }
        });
        $('#ratesTimer').timer({
            delay: 30000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                $('#ratesTimer').timer('stop');
                loadRates();
            }
        });
        loadRates();
    });
</script>