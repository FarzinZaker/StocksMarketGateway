<div id="adjustmentContainer">
    <form:field fieldName="correlation.adjustmentType">
        <span style="width: 510px;display: inline-block">
            <form:select name="adjustmentType" style="width:505px;" value="${stocks.tse.AdjustmentHelper.defaultType}"
                         items="${stocks.tse.AdjustmentHelper.ENABLED_TYPES.collect {
                             [text: message(code: "priceAdjustment.types.${it}"), value: it]
                         }}"/>
        </span>
    </form:field>
</div>

<script language="javascript" type="text/javascript">
    function toggleAdjustmentType() {
        if ($('#sourceGroup').val() == 'stocks.tools.correlation.CompanyCorrelationService'
                || $('#targetGroup').val() == 'stocks.tools.correlation.CompanyCorrelationService')
            $('#adjustmentContainer').show();
        else
            $('#adjustmentContainer').hide();
    }

    $(document).ready(function(){
        toggleAdjustmentType();
    });
</script>