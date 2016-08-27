<%@ page import="grails.converters.JSON; stocks.tse.AdjustmentHelper" %>
<script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'charting_library.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'chartingLibrary/datafeed/udf', file: 'datafeed.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'chartingLibrary', file: 'addons.js')}"></script>

<g:set var="symbol" value="${stocks.tse.Symbol.get(params.id)}"/>
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
<div id="tv_chart_container" style="margin-bottom:7px;"></div>