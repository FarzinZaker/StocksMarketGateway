
<div id="bourse_lastUpdatedFlag" class="lastUpdatedFlag"></div>
<div class="marketViewItem odd" id="marketView_totalIndex">
    <span class="marketViewItem_label"><g:message code="marketView.totalIndex"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem even" id="marketView_priceIndex">
    <span class="marketViewItem_label"><g:message code="marketView.priceIndex"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem odd" id="marketView_otcTotalIndex">
    <span class="marketViewItem_label"><g:message code="marketView.otcTotalIndex"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem even" id="marketView_tradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.tradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_otcTradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.otcTradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_totalTradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.totalTradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_marketValue">
    <span class="marketViewItem_label"><g:message code="marketView.marketValue"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem even" id="marketView_otcMarketValue">
    <span class="marketViewItem_label"><g:message code="marketView.otcMarketValue"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem odd clear-fix">
    <div class="marketViewItem" id="marketView_totalBuyVolume">
        <span class="marketViewItem_label"><g:message code="marketView.totalBuyVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div style="height:10px;background-color:#3b5998;margin-top:2px;">
        <div style="height:10px;background-color:#d80073;width:0" id="buyIndicator"></div>
    </div>

    <div class="marketViewItem" id="marketView_totalLegalBuyVolume" style="float:right;font-size:11px;">
        <span class="marketViewItem_label" style="margin-left:5px;"><g:message
                code="marketView.totalLegalBuyVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div class="marketViewItem" id="marketView_totalIndividualBuyVolume" style="float:left;font-size:11px;">
        <span class="marketViewItem_value" style="margin-left:5px">-</span>
        <span class="marketViewItem_label" style="margin-left:0;"><g:message
                code="marketView.totalIndividualBuyVolume"/></span>
    </div>

    <div class="clear-fix"></div>
</div>

<div class="marketViewItem even clear-fix">
    <div class="marketViewItem clear-fix" id="marketView_totalSellVolume">
        <span class="marketViewItem_label"><g:message code="marketView.totalSellVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div style="height:10px;background-color:#3b5998;margin-top:2px;">
        <div style="height:10px;background-color:#d80073;width:0" id="sellIndicator"></div>
    </div>

    <div class="marketViewItem" id="marketView_totalLegalSellVolume" style="float:right;font-size:11px;">
        <span class="marketViewItem_label" style="margin-left:5px"><g:message
                code="marketView.totalLegalSellVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div class="marketViewItem" id="marketView_totalIndividualSellVolume" style="float:left;font-size:11px;">
        <span class="marketViewItem_value" style="margin-left:5px;">-</span>
        <span class="marketViewItem_label" style="margin-left:0"><g:message
                code="marketView.totalIndividualSellVolume"/></span>
    </div>

    <div class="clear-fix"></div>
</div>

<script language="javascript" type="text/javascript">

    function fillBourseData(data) {

        var totalIndex = $('#marketView_totalIndex');
        totalIndex.find('.marketViewItem_value').html(formatNumber(data.bourse.totalIndex.value));
        totalIndex.find('.marketViewItem_change').html(formatNumber(data.bourse.totalIndex.change)).addChangeClass(data.bourse.totalIndex.change);
        totalIndex.find('.marketViewItem_changePercent').html(formatNumber(data.bourse.totalIndex.changePercent) + '%').addChangeClass(data.bourse.totalIndex.changePercent);
        var priceIndex = $('#marketView_priceIndex');
        priceIndex.find('.marketViewItem_value').html(formatNumber(data.bourse.priceIndex.value));
        priceIndex.find('.marketViewItem_change').html(formatNumber(data.bourse.priceIndex.change)).addChangeClass(data.bourse.priceIndex.change);
        priceIndex.find('.marketViewItem_changePercent').html(formatNumber(data.bourse.priceIndex.changePercent) + '%').addChangeClass(data.bourse.priceIndex.changePercent);
        var otcTotalIndex = $('#marketView_otcTotalIndex');
        otcTotalIndex.find('.marketViewItem_value').html(formatNumber(data.bourse.otcTotalIndex.value));
        otcTotalIndex.find('.marketViewItem_change').html(formatNumber(data.bourse.otcTotalIndex.change)).addChangeClass(data.bourse.otcTotalIndex.change);
        otcTotalIndex.find('.marketViewItem_changePercent').html(formatNumber(data.bourse.otcTotalIndex.changePercent) + '%').addChangeClass(data.bourse.otcTotalIndex.changePercent);
        $('#marketView_totalBuyVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalIndividualBuyVolume + data.bourse.clientTypes.totalLegalBuyVolume));
        $('#marketView_totalSellVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalIndividualSellVolume + data.bourse.clientTypes.totalLegalSellVolume));
        $('#marketView_totalIndividualBuyVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalIndividualBuyVolume));
        $('#marketView_totalIndividualSellVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalIndividualSellVolume));
        $('#marketView_totalLegalBuyVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalLegalBuyVolume));
        $('#marketView_totalLegalSellVolume').find('.marketViewItem_value').html(formatNumber(data.bourse.clientTypes.totalLegalSellVolume));
        $('#buyIndicator').css('width', Math.round(data.bourse.clientTypes.totalLegalBuyVolume * 100 / (data.bourse.clientTypes.totalLegalBuyVolume + data.bourse.clientTypes.totalIndividualBuyVolume)) + '%');
        $('#sellIndicator').css('width', Math.round(data.bourse.clientTypes.totalLegalSellVolume * 100 / (data.bourse.clientTypes.totalLegalSellVolume + data.bourse.clientTypes.totalIndividualSellVolume)) + '%');
        $('#marketView_tradeValue').find('.marketViewItem_value').html(formatNumber(data.bourse.market.tradeValue));
        $('#marketView_otcTradeValue').find('.marketViewItem_value').html(formatNumber(data.bourse.otcMarket.tradeValue));
        $('#marketView_totalTradeValue').find('.marketViewItem_value').html(formatNumber(data.bourse.market.tradeValue + data.bourse.otcMarket.tradeValue));
        var marketValue = $('#marketView_marketValue');
        marketValue.find('.marketViewItem_value').html(formatNumber(data.bourse.market.value));
        marketValue.find('.marketViewItem_change').html(formatNumber(data.bourse.market.change)).addChangeClass(data.bourse.market.change);
        marketValue.find('.marketViewItem_changePercent').html(formatNumber(data.bourse.market.changePercent) + '%').addChangeClass(data.bourse.market.changePercent);
        var otcMarketValue = $('#marketView_otcMarketValue');
        otcMarketValue.find('.marketViewItem_value').html(formatNumber(data.bourse.otcMarket.value));
        otcMarketValue.find('.marketViewItem_change').html(formatNumber(data.bourse.otcMarket.change)).addChangeClass(data.bourse.otcMarket.change);
        otcMarketValue.find('.marketViewItem_changePercent').html(formatNumber(data.bourse.otcMarket.changePercent) + '%').addChangeClass(data.bourse.otcMarket.changePercent);
        $('#bourse_lastUpdatedFlag').html(data.bourse.date);
    }
</script>