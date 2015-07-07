<div class="marketViewItem" id="marketView_totalIndex">
    <span class="marketViewItem_label"><g:message code="marketView.totalIndex"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem" id="marketView_priceIndex">
    <span class="marketViewItem_label"><g:message code="marketView.priceIndex"/></span>
    <span class="marketViewItem_value">-</span>
    <span class="marketViewItem_change"></span>
    <span class="marketViewItem_changePercent"></span>
</div>

<div class="marketViewItem" id="marketView_otcTotalIndex">
    <span class="marketViewItem_label"><g:message code="marketView.otcTotalIndex"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_tradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.tradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_otcTradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.otcTradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_totalTradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.totalTradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_totalBuyVolume">
    <span class="marketViewItem_label"><g:message code="marketView.totalBuyVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div style="height:10px;background-color:#3b5998;margin-top:2px;">
    <div style="height:10px;background-color:#d80073;width:0" id="buyIndicator"></div>
</div>

<div class="marketViewItem" id="marketView_totalLegalBuyVolume" style="float:right;font-size:11px;">
    <span class="marketViewItem_label" style="margin-left:5px;"><g:message code="marketView.totalLegalBuyVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_totalIndividualBuyVolume" style="float:left;font-size:11px;">
    <span class="marketViewItem_value" style="margin-left:5px">-</span>
    <span class="marketViewItem_label" style="margin-left:0;"><g:message code="marketView.totalIndividualBuyVolume"/></span>
</div>

<div class="marketViewItem clear-fix" id="marketView_totalSellVolume">
    <span class="marketViewItem_label"><g:message code="marketView.totalSellVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div style="height:10px;background-color:#3b5998;margin-top:2px;">
    <div style="height:10px;background-color:#d80073;width:0" id="sellIndicator"></div>
</div>

<div class="marketViewItem" id="marketView_totalLegalSellVolume" style="float:right;font-size:11px;">
    <span class="marketViewItem_label" style="margin-left:5px"><g:message code="marketView.totalLegalSellVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_totalIndividualSellVolume" style="float:left;font-size:11px;">
    <span class="marketViewItem_value" style="margin-left:5px;">-</span>
    <span class="marketViewItem_label" style="margin-left:0"><g:message code="marketView.totalIndividualSellVolume"/></span>
</div>

<div class="clear-fix"></div>

<script language="javascript" type="text/javascript">

    $.fn.addChangeClass = function (value) {
        if (value > 0)
            $(this).removeClass('negative').addClass('positive');
        else
            $(this).removeClass('positive').addClass('negative');
    };

    function formatNumber(data) {
        if (data.toString().indexOf(".") == -1)
            return formatInteger(data);
        else {
            var parts = data.toString().split(".");
            return formatInteger(parseInt(parts[0])) + "." + (parts[1].length > 2 ? parts[1].substring(0, 2) : parts[1]);
        }
    }

    function formatInteger(data) {
        return (Math.round(data * 100) / 100).toString().replace(/./g, function (c, i, a) {
            return i && c !== "\." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }

    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: '${createLink(action: 'marketView')}'
        }).done(function (data) {
            var totalIndex = $('#marketView_totalIndex');
            totalIndex.find('.marketViewItem_value').html(formatNumber(data.totalIndex.value));
            totalIndex.find('.marketViewItem_change').html(formatNumber(data.totalIndex.change)).addChangeClass(data.totalIndex.change);
            totalIndex.find('.marketViewItem_changePercent').html(formatNumber(data.totalIndex.changePercent) + '%').addChangeClass(data.totalIndex.changePercent);
            var priceIndex = $('#marketView_priceIndex');
            priceIndex.find('.marketViewItem_value').html(formatNumber(data.priceIndex.value));
            priceIndex.find('.marketViewItem_change').html(formatNumber(data.priceIndex.change)).addChangeClass(data.priceIndex.change);
            priceIndex.find('.marketViewItem_changePercent').html(formatNumber(data.priceIndex.changePercent) + '%').addChangeClass(data.priceIndex.changePercent);
            var otcTotalIndex = $('#marketView_otcTotalIndex');
            otcTotalIndex.find('.marketViewItem_value').html(formatNumber(data.otcTotalIndex.value));
            $('#marketView_totalBuyVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalIndividualBuyVolume + data.clientTypes.totalLegalBuyVolume));
            $('#marketView_totalSellVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalIndividualSellVolume + data.clientTypes.totalIndividualSellVolume));
            $('#marketView_totalIndividualBuyVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalIndividualBuyVolume));
            $('#marketView_totalIndividualSellVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalIndividualSellVolume));
            $('#marketView_totalLegalBuyVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalLegalBuyVolume));
            $('#marketView_totalLegalSellVolume').find('.marketViewItem_value').html(formatNumber(data.clientTypes.totalLegalSellVolume));
            $('#buyIndicator').css('width', Math.round(data.clientTypes.totalLegalBuyVolume * 100 / (data.clientTypes.totalLegalBuyVolume + data.clientTypes.totalIndividualBuyVolume)) + '%');
            $('#sellIndicator').css('width', Math.round(data.clientTypes.totalLegalSellVolume * 100 / (data.clientTypes.totalLegalSellVolume + data.clientTypes.totalIndividualSellVolume)) + '%');
        });
    });
</script>