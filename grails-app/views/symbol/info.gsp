<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 6/22/15
  Time: 5:10 PM
--%>

<%@ page import="stocks.tse.AdjustmentHelper; grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${symbol.persianCode} - ${symbol.persianName}</title>

    <script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'charting_library.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'chartingLibrary/datafeed/udf', file: 'datafeed.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'addons.js')}"></script>


    <script type="text/javascript">

        var adjustmentTypes = <format:html value="${stocks.tse.AdjustmentHelper.ENABLED_TYPES.collect{[text:message(code:"priceAdjustment.types.${it}"), value: it]} as JSON}"/>;
        var defaultAdjustmentType = "${AdjustmentHelper.defaultType}";
        var symbolName = '${symbol.persianCode}';
        var widget;

        TradingView.onready(function () {

            widget = new TradingView.widget({
                fullscreen: true,
                symbol: '${symbol.persianCode}',
                interval: 'D',
                container_id: "tv_chart_container",
                //	BEWARE: no trailing slash is expected in feed URL
                datafeed: new Datafeeds.UDFCompatibleDatafeed("${createLink(uri: '/symbolChart')}"),
                library_path: "../../chartingLibrary/",
                locale: "fa_IR",
                //	Regression Trend-related functionality is not implemented yet, so it's hidden for a while
                drawings_access: {type: 'black', tools: [{name: "Regression Trend"}]},
                disabled_features: ["use_localstorage_for_settings", "header_symbol_search", "header_screenshot", "header_saveload"],
                charts_storage_url: '${createLink(controller: 'technical', action: 'save')}',
                client_id: 'tradingview.com',
                user_id: 'public_user_id',
                snapshot_url: '${createLink(controller: 'technical', action: 'image')}'
            });

            widget.onChartReady(function () {
                setupAdjustmentButton(this);
            });
        });
    </script>

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'symbol.info'), url: createLink(controller: 'symbol')],
                    [text: "<i class=\"fa fa-line-chart\"></i> ${symbol.persianCode} (${symbol.persianName})", url: createLink(controller: 'symbol', action: 'info', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
        %{--<h1 class="pink">--}%
        %{--<i class="fa fa-line-chart"></i>--}%
        %{--${symbol.persianCode} (${symbol.persianName})--}%
        %{--</h1>--}%


            <g:if test="${lastDailyTrade}">
                <g:set var="lastTradePriceChangePercent"
                       value="${Math.round(lastDailyTrade.priceChange * 10000 / (lastDailyTrade.lastTradePrice - lastDailyTrade.priceChange)) / 100}"/>
                <div class="propertyInfoPrice ${lastTradePriceChangePercent > 0 ? 'positive' : lastTradePriceChangePercent < 0 ? 'negative' : ''}">
                    <h4><g:message code="symbol.info.lastPrice"/></h4>
                    <span>
                        <b><g:formatNumber number="${lastDailyTrade.lastTradePrice}" type="number"/></b>
                    </span>
                    <span>
                        <g:formatNumber number="${lastDailyTrade.priceChange}" type="number"/>
                    </span>
                    <span>
                        <g:formatNumber number="${lastTradePriceChangePercent}" type="number"/>%
                    </span>

                    <div class="clear-fix"></div>
                </div>

                <g:set var="closingPriceChangePercent"
                       value="${Math.round((lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice) * 10000 / (lastDailyTrade.closingPrice - (lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice))) / 100}"/>
                <div class="propertyInfoPrice ${closingPriceChangePercent > 0 ? 'positive' : closingPriceChangePercent < 0 ? 'negative' : 0}">
                    <h4><g:message code="symbol.info.closingPrice"/></h4>
                    <span>
                        <g:formatNumber number="${lastDailyTrade.closingPrice}" type="number"/>
                    </span>
                    <span>
                        <g:formatNumber
                                number="${lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice}"
                                type="number"/>
                    </span>
                    <span>
                        <g:formatNumber number="${closingPriceChangePercent}" type="number"/>%
                    </span>

                    <div class="clear-fix"></div>
                </div>
            </g:if>


            <div class="k-rtl dashLet" style="margin-top:15px;">
                <h2>&nbsp;</h2>

                <div id="tabstrip">
                    <ul>
                        <g:if test="${lastDailyTrade}">
                            <li class="k-state-active">
                                <g:message code="symbol.into.status.title"/>
                            </li>
                        </g:if>
                        <li class="${!lastDailyTrade ? 'k-state-active' : ''}">
                            <g:message code="symbol.into.news.title"/>
                        </li>
                        <li>
                            <g:message code="symbol.into.chat.title"/>
                        </li>
                    </ul>

                    <g:if test="${lastDailyTrade}">
                        <div>
                            <g:render template="status" model="${[lastDailyTrade: lastDailyTrade]}"/>
                        </div>
                    </g:if>

                    <div>
                        <g:render template="news"/>
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
                                effects: "none"
                            }
                        }
                    });
                });
            </script>

            <g:render template="admin"/>
        </div>

        <div class="col-xs-9">
            <div id="tv_chart_container"></div>
            <g:render template="/technical/write"
                      model="${[tag: [type: 'tag', clazz: 'Symbol', title: symbol?.persianCode, id: params.id]]}"/>

        </div>
    </div>
</div>
</body>
</html>