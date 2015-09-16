
<div id="energy_lastUpdatedFlag" class="lastUpdatedFlag"></div>

<div class="marketViewItem odd" id="marketView_energy_power_tradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.energy.power.tradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_energy_power_tradeVolume">
    <span class="marketViewItem_label"><g:message code="marketView.energy.power.tradeVolume"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_energy_power_tradeCount">
    <span class="marketViewItem_label"><g:message code="marketView.energy.power.tradeCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem even" id="marketView_energy_physical_tradeValue">
    <span class="marketViewItem_label"><g:message code="marketView.energy.physical.tradeValue"/></span>
    <span class="marketViewItem_value">-</span>
</div>

<div class="marketViewItem odd" id="marketView_energy_physical_tradeCount">
    <span class="marketViewItem_label"><g:message code="marketView.energy.physical.tradeCount"/></span>
    <span class="marketViewItem_value">-</span>
</div>



<script language="javascript" type="text/javascript">

    function fillEnergyData(data) {

        $('#marketView_energy_power_tradeVolume').find('.marketViewItem_value').html(formatNumber(data.energy.power.tradeVolume));
        $('#marketView_energy_power_tradeValue').find('.marketViewItem_value').html(formatNumber(data.energy.power.tradeValue));
        $('#marketView_energy_power_tradeCount').find('.marketViewItem_value').html(formatNumber(data.energy.power.tradeCount));
        $('#marketView_energy_physical_tradeValue').find('.marketViewItem_value').html(formatNumber(data.energy.physical.tradeValue));
        $('#marketView_energy_physical_tradeCount').find('.marketViewItem_value').html(formatNumber(data.energy.physical.tradeCount));
        $('#energy_lastUpdatedFlag').html(data.energy.date);
    }
</script>