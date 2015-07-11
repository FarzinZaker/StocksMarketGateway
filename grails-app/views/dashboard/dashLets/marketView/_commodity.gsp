

<div class="marketViewItem odd" id="marketView_commodity_totalValue">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.totalValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_totalVolume">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.totalVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_petroValue">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.petroValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_petroVolume">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.petroVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_petroCount">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.petroCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_industryValue">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.industryValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_industryVolume">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.industryVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_industryCount">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.industryCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_agricultureValue">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.agricultureValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_agricultureVolume">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.agricultureVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_agricultureCount">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.agricultureCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_secondaryValue">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.secondaryValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_commodity_secondaryVolume">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.secondaryVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_commodity_secondaryCount">
    <span class="marketViewItem_label"><g:message code="marketView.commodity.secondaryCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>
<script language="javascript" type="text/javascript">

    function fillCommodityData(data) {

        $('#marketView_commodity_totalValue').find('.marketViewItem_value').html(formatNumber(data.commodity.total.value));
        $('#marketView_commodity_totalVolume').find('.marketViewItem_value').html(formatNumber(data.commodity.total.volume));
        $('#marketView_commodity_petroValue').find('.marketViewItem_value').html(formatNumber(data.commodity.petro.value));
        $('#marketView_commodity_petroVolume').find('.marketViewItem_value').html(formatNumber(data.commodity.petro.volume));
        $('#marketView_commodity_petroCount').find('.marketViewItem_value').html(formatNumber(data.commodity.petro.count));
        $('#marketView_commodity_industryValue').find('.marketViewItem_value').html(formatNumber(data.commodity.industry.value));
        $('#marketView_commodity_industryVolume').find('.marketViewItem_value').html(formatNumber(data.commodity.industry.volume));
        $('#marketView_commodity_industryCount').find('.marketViewItem_value').html(formatNumber(data.commodity.industry.count));
        $('#marketView_commodity_agricultureValue').find('.marketViewItem_value').html(formatNumber(data.commodity.agriculture.value));
        $('#marketView_commodity_agricultureVolume').find('.marketViewItem_value').html(formatNumber(data.commodity.agriculture.volume));
        $('#marketView_commodity_agricultureCount').find('.marketViewItem_value').html(formatNumber(data.commodity.agriculture.count));
        $('#marketView_commodity_secondaryValue').find('.marketViewItem_value').html(formatNumber(data.commodity.secondary.value));
        $('#marketView_commodity_secondaryVolume').find('.marketViewItem_value').html(formatNumber(data.commodity.secondary.volume));
        $('#marketView_commodity_secondaryCount').find('.marketViewItem_value').html(formatNumber(data.commodity.secondary.count));


    }
</script>