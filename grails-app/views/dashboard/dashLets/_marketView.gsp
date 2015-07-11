<div class="dashLet orange">
    <h2 style="float:right"><i class="fa fa-line-chart"></i> <g:message code="marketView.title"/></h2>

    <div id="marketViewTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="tabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="marketView.bourse.title"/>
                </li>
                <li>
                    <g:message code="marketView.commodity.title"/>
                </li>
                <li>
                    <g:message code="marketView.future.title"/>
                </li>
                <li>
                    <g:message code="marketView.energy.title"/>
                </li>
            </ul>

            <div>
                <g:render template="dashLets/marketView/bourse"/>
            </div>

            <div>
                <g:render template="dashLets/marketView/commodity"/>
            </div>

            <div>
                <g:render template="dashLets/marketView/future"/>
            </div>

            <div>
                <g:render template="dashLets/marketView/energy"/>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">


    $.fn.addChangeClass = function (value) {
        if (value > 0)
            $(this).removeClass('negative').addClass('positive');
        else
            $(this).removeClass('positive').addClass('negative');
    };

    function formatNumber(data) {
        var flag = '';
        if (Math.abs(data) > 1000000) {
            data /= 1000000;
            flag = 'M';
            if (Math.abs(data) > 1000) {
                data /= 1000;
                flag = 'B';
            }
        }
        if (data.toString().indexOf(".") == -1)
            return formatInteger(data) + flag;
        else {
            var parts = data.toString().split(".");
            return formatInteger(parseInt(parts[0])) + "." + (parts[1].length > 2 ? parts[1].substring(0, 2) : parts[1]) + flag;
        }
    }

    function formatInteger(data) {
        return (Math.round(data * 100) / 100).toString().replace(/./g, function (c, i, a) {
            return i && c !== "\." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }

    function loadMarketView(){

        $.ajax({
            type: "POST",
            url: '${createLink(action: 'marketView')}?t=' + new Date().getTime()
        }).done(function (data) {
            fillBourseData(data);
            fillCommodityData(data);
            fillFutureData(data);
            fillEnergyData(data);
            $('#marketViewTimer').timer('start');
        });
    }

    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });
        $('#marketViewTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#marketViewTimer').timer('stop');
            }
        });
        loadMarketView();
    });
</script>