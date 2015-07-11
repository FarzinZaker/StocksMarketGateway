

<div class="marketViewItem" id="marketView_future_tradeVolume">
    <span class="marketViewItem_label"><g:message code="marketView.future.tradeVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_future_tradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.future.tradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_future_openInterests">
    <span class="marketViewItem_label"><g:message code="marketView.future.openInterests"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem" id="marketView_future_tradeCount">
    <span class="marketViewItem_label"><g:message code="marketView.future.tradeCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<script language="javascript" type="text/javascript">

    function fillFutureData(data) {

        $('#marketView_future_tradeVolume').find('.marketViewItem_value').html(formatNumber(data.future.tradeVolume));
        $('#marketView_future_tradeValue').find('.marketViewItem_value').html(formatNumber(data.future.tradeValue));
        $('#marketView_future_openInterests').find('.marketViewItem_value').html(formatNumber(data.future.openInterests));
        $('#marketView_future_tradeCount').find('.marketViewItem_value').html(formatNumber(data.future.tradeCount));


    }
</script>