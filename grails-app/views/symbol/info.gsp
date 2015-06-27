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
    <title>${symbol.persianCode} - ${symbol.persianName}</title>

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
                disabled_features: ["use_localstorage_for_settings", "header_symbol_search", "header_screenshot", "header_saveload"],
                charts_storage_url: 'http://saveload.tradingview.com',
                client_id: 'tradingview.com',
                user_id: 'public_user_id',
            });
            %{--$(frames[0]).load(function(){--}%

                %{--var cssLink = document.createElement("link");--}%
                %{--cssLink.href = "${resource(dir: 'chartingLibrary/static', file: 'tv-chart-readonly.css')}";--}%
                %{--cssLink .rel = "stylesheet";--}%
                %{--cssLink .type = "text/css";--}%
                %{--frames[0].document.body.appendChild(cssLink);--}%
            %{--})--}%
        });

    </script>

</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'symbol.info'), url: createLink(controller: 'symbol')],
                    [text: "${symbol.persianCode} (${symbol.persianName})", url: createLink(controller: 'symbol', action: 'info', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">
            <h1>${symbol.persianCode} (${symbol.persianName})</h1>

            <div style="line-height: 30px;">
                <span><g:message code="symbol.info.lastPrice"/>:</span>
                <span style="margin-right:30px;font-size:20px;font-weight: bold"><g:formatNumber
                        number="${lastDailyTrade.lastTradePrice}" type="number"/></span>
                <g:set var="lastTradePriceChangePercent"
                       value="${Math.round(lastDailyTrade.priceChange * 10000 / (lastDailyTrade.lastTradePrice - lastDailyTrade.priceChange)) / 100}"/>
                <span style="margin-right:30px;color:${lastTradePriceChangePercent > 0 ? 'green' : 'red'}"><g:formatNumber
                        number="${lastDailyTrade.priceChange}" type="number"/></span>
                <span style="margin-right:30px;color:${lastTradePriceChangePercent > 0 ? 'green' : 'red'}">%<g:formatNumber
                        number="${lastTradePriceChangePercent}" type="number"/></span>
            </div>

            <div style="line-height: 30px;">
                <span><g:message code="symbol.info.closingPrice"/>:</span>
                <span style="margin-right:30px;"><g:formatNumber number="${lastDailyTrade.closingPrice}"
                                                                 type="number"/></span>
                <g:set var="closingPriceChangePercent"
                       value="${Math.round((lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice) * 10000 / (lastDailyTrade.closingPrice - (lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice))) / 100}"/>
                <span style="margin-right:30px;color:${closingPriceChangePercent > 0 ? 'green' : 'red'}"><g:formatNumber
                        number="${lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice}"
                        type="number"/></span>
                <span style="margin-right:30px;color:${closingPriceChangePercent > 0 ? 'green' : 'red'}">%<g:formatNumber
                        number="${closingPriceChangePercent}" type="number"/></span>
            </div>


            <div class="k-rtl" style="margin-top:20px;">
                <div id="tabstrip">
                    <ul>
                        <li class="k-state-active">
                            <g:message code="symbol.into.status.title"/>
                        </li>
                        <li>
                            <g:message code="symbol.into.news.title"/>
                        </li>
                        <li>
                            <g:message code="symbol.into.chat.title"/>
                        </li>
                    </ul>

                    <div>
                        <g:render template="status" model="${[lastDailyTrade: lastDailyTrade]}"/>
                    </div>

                    <div>
                        <div id="news"></div>
                    </div>

                    <div>
                        <g:message code="underConstruction"/>
                    </div>
                </div>
            </div>

            <script>
                $(document).ready(function () {
                    $("#tabstrip").kendoTabStrip({
                        animation: {
                            open: {
                                effects: "fadeIn"
                            }
                        }
                    });
                });
            </script>

            <g:render template="admin"/>
        </div>

        <div class="col-xs-9">
            <div id="tv_chart_container" style="margin-top:25px;"></div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    $(document).ready(function () {

        $.ajax({
            type: "POST",
            url: '${createLink(action: 'news')}',
            data: {id: ${params.id}}
        }).done(function (response) {
            $('#news').html(response);
        });
    })
</script>
</body>
</html>