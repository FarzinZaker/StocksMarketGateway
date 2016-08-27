<div class="dashLet hideBeforeLoad noPlaceReservation">
    <h2 style="float:right"><i class="fa fa-line-chart"></i> <g:message code="topUsers.title"/></h2>

    <div id="marketViewTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="tabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="topUsers.mostFollowers"/>
                    %{--</li>--}%
                <li>
                    <g:message code="topUsers.mostPosts"/>
                </li>
                <li>
                    <g:message code="topUsers.topUserRank"/>
                </li>
                <li>
                    <g:message code="topUsers.top4tabloRank"/>
                </li>
            </ul>

            %{--<div class="twitter-top-list row">--}%
                %{--<twitter:mostFollowedUsers/>--}%
            %{--</div>--}%

            %{--<div class="twitter-top-list row">--}%
                %{--<twitter:mostActiveUsers/>--}%
            %{--</div>--}%

            %{--<div class="twitter-top-list row">--}%
                %{--<twitter:mostRatedUsers/>--}%
            %{--</div>--}%

            %{--<div class="twitter-top-list row">--}%
                %{--<twitter:mostRated4tabloUsers/>--}%
            %{--</div>--}%
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
        if (Math.abs(data) > 1000000000) {
//            data /= 1000000;
//            flag = 'M';
//            if (Math.abs(data) > 1000) {
            data /= 1000000000;
            flag = 'B';
//            }
        }
        if (data.toString().indexOf(".") == -1)
            return formatInteger(data) + flag;
        else {
            var parts = data.toString().split(".");
            return formatInteger(parseInt(parts[0])) + "." + (parts[1].length > 2 ? parts[1].substring(0, 2) : parts[1]) + flag;
        }
    }

    function formatInteger(data) {
        return (Math.round(Math.abs(data) * 100) / 100).toString().replace(/./g, function (c, i, a) {
            return i && c !== "\." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }

    %{--function loadMarketView(){--}%

        %{--$.ajax({--}%
            %{--type: "POST",--}%
            %{--url: '${createLink(action: 'marketView')}?t=' +  Math.round(new Date().getTime()/30000)--}%
        %{--}).done(function (data) {--}%
            %{--fillBourseData(data);--}%
            %{--fillCommodityData(data);--}%
            %{--fillFutureData(data);--}%
            %{--fillEnergyData(data);--}%
            %{--$('#marketViewTimer').timer('start');--}%
        %{--});--}%
    %{--}--}%
    function showLoading(e) {
        var tabstrip = e.sender;

        window.setTimeout(function(){
            kendo.ui.progress(tabstrip.element, true);
        });
    }

    function hideLoading(e) {
        var tabstrip = e.sender;

        window.setTimeout(function(){
            kendo.ui.progress(tabstrip.element, false);
        });
    }
    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            },
            select: showLoading,
            contentLoaded: hideLoading,
            activate: hideLoading,
            contentUrls:[
                '<g:createLink controller="user" action="mostCards" id="mostFollowedUsers" />',
                '<g:createLink controller="user" action="mostCards" id="mostActiveUsers" />',
                '<g:createLink controller="user" action="mostCards" id="mostRatedUsers" />',
                '<g:createLink controller="user" action="mostCards" id="mostRated4tabloUsers" />'
            ]
        });
//        $('#marketViewTimer').timer({
//            delay: 120000,
//            repeat: true,
//            autostart: false,
//            callback: function (index) {
//                $('#marketViewTimer').timer('stop');
//                loadMarketView();
//            }
//        });
//        loadMarketView();
    });
</script>