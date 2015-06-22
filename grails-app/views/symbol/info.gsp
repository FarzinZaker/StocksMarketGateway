<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 6/22/15
  Time: 5:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>

    <script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'charting_library.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'chartingLibrary/datafeed/udf', file: 'datafeed.js')}"></script>


    <script type="text/javascript">

        TradingView.onready(function () {
            var widget = new TradingView.widget({
                fullscreen: true,
                symbol: '${symbol.persianCode}',
                interval: 'D',
                container_id: "tv_chart_container",
                //	BEWARE: no trailing slash is expected in feed URL
                datafeed: new Datafeeds.UDFCompatibleDatafeed("${createLink(uri: '/chart')}"),
                library_path: "../../chartingLibrary/",
                locale: "fa_IR",
                //	Regression Trend-related functionality is not implemented yet, so it's hidden for a while
                drawings_access: {type: 'black', tools: [{name: "Regression Trend"}]},
                disabled_features: ["use_localstorage_for_settings"],
                charts_storage_url: 'http://saveload.tradingview.com',
                client_id: 'tradingview.com',
                user_id: 'public_user_id'
            });
        })

    </script>

</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-3">
            base info

        </div>

        <div class="col-xs-9">
            <div id="tv_chart_container"></div>
        </div>
    </div>
</div>

</body>
</html>