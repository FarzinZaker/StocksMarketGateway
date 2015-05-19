<%@ page import="stocks.tse.AdjustmentHelper" %>
<div id="defaultAdjustmentTypePanel" class="k-rtl">
    <label for="defaultAdjustmentType"><g:message code="priceAdjustment.menu.label"/>:</label>
    <select name="defaultAdjustmentType" id="defaultAdjustmentType" style="width:260px;">
        <g:each in="${AdjustmentHelper.ENABLED_TYPES}" var="type">
            <option value="${type}" ${type == AdjustmentHelper.globalAdjustmentType ? 'selected' : ''}><g:message code="priceAdjustment.types.${type}"/></option>
        </g:each>
    </select>
</div>
<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#defaultAdjustmentType').kendoComboBox({
            change: function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }

                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'globals', action: 'adjustmentType')}',
                    data: { id: this.value() }
                }).done(function (response) {
                    if (response == 1) {
                        location.reload();
                    }
                });
            }
        });
    });
</script>