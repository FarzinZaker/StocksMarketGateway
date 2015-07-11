<div class="dashLet cyan">
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

            <div>
                <g:render template="dashLets/rates/currency"/>
            </div>

            <div>
                <g:render template="dashLets/rates/gold"/>
            </div>

            <div>
                <g:render template="dashLets/rates/metal"/>
            </div>

            <div>
                <g:render template="dashLets/rates/oil"/>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function loadRates(){

        $.ajax({
            type: "POST",
            url: '${createLink(action: 'rates')}?t=' + new Date().getTime()
        }).done(function (data) {
            $('#ratesTimer').timer('start');
            fillCurrencyData(data.currency);
            fillGoldData(data.gold);
            fillMetalData(data.metal);
        });
    }

    $(document).ready(function () {
        $("#ratesTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });
        $('#ratesTimer').timer({
            delay: 10000,
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