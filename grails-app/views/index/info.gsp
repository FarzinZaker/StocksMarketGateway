<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 6/22/15
  Time: 5:10 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${index.persianName}</title>

    <script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'charting_library.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'chartingLibrary/datafeed/udf', file: 'datafeed.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'addons.js')}"></script>


    <script type="text/javascript">

        var adjustmentTypes = <format:html value="${stocks.tse.AdjustmentHelper.ENABLED_TYPES.collect{[text:message(code:"priceAdjustment.types.${it}"), value: it]} as JSON}"/>;
        var defaultAdjustmentType = "${stocks.tse.AdjustmentHelper.defaultType}";
        var symbolName = '${index.persianName}';

        TradingView.onready(function () {

            var widget = new TradingView.widget({
                fullscreen: true,
                symbol: '${index.persianName}',
                interval: 'D',
                container_id: "tv_chart_container",
                //	BEWARE: no trailing slash is expected in feed URL
                datafeed: new Datafeeds.UDFCompatibleDatafeed("${createLink(uri: '/indexChart')}"),
                library_path: "../../chartingLibrary/",
                locale: "fa_IR",
                //	Regression Trend-related functionality is not implemented yet, so it's hidden for a while
                drawings_access: {type: 'black', tools: [{name: "Regression Trend"}]},
                disabled_features: ["use_localstorage_for_settings", "header_symbol_search", "header_screenshot", "header_saveload"],
                charts_storage_url: 'http://saveload.tradingview.com',
                client_id: 'tradingview.com',
                user_id: 'public_user_id'
            });

            widget.onChartReady(function () {
//                setupAdjustmentButton(this);
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
                    [text: message(code: 'index.info'), url: createLink(controller: 'index')],
                    [text: "<i class=\"fa fa-line-chart\"></i> ${index.persianName}", url: createLink(controller: 'index', action: 'info', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">

            <g:if test="${index}">
                <div style="line-height: 30px;">
                    <span style="margin-left:30px;"><g:message code="symbol.info.lastPrice"/>:</span>
                    <span style="white-space: nowrap">
                        <span style="margin-left:30px;font-size:20px;font-weight: bold">
                            <g:formatNumber number="${index.finalIndexValue}" type="number"/>
                        </span>
                        <g:set var="todayIndexChangeValue"
                               value="${Math.round(index.todayIndexChangePercent / (index.finalIndexValue / 100 + 1))}"/>
                        <span style="margin-left:30px;color:${todayIndexChangeValue > 0 ? 'green' : 'red'}">
                            <g:formatNumber number="${todayIndexChangeValue}" type="number"/>
                        </span>
                        <span style="color:${index.todayIndexChangePercent > 0 ? 'green' : 'red'}">
                            %<g:formatNumber number="${index.todayIndexChangePercent}" type="number"/>
                        </span>
                    </span>
                </div>
            </g:if>


            <div class="k-rtl" style="margin-top:20px;">
                <div id="tabstrip">
                    <ul>
                        <g:if test="${index}">
                            <li class="k-state-active">
                                <g:message code="index.info.status.title"/>
                            </li>
                        </g:if>
                        <li class="${!index? 'k-state-active' : ''}">
                            <g:message code="index.info.chat.title"/>
                        </li>
                    </ul>

                    <g:if test="${index}">
                        <div>
                            <g:render template="status" model="${[index: index]}"/>
                        </div>
                    </g:if>

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
</body>
</html>