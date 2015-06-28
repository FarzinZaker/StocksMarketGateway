<form:field fieldName="correlation.target">
    <span style="width: 510px;display: inline-block">
        <select id="targetGroup" name="targetGroup" style="width:200px;">
            %{--<option value="all"><g:message code="tools.correlation.allGroups"/></option>--}%
            <g:each in="${groups}" var="group">
                <option value="${group.value}"><g:message code="${group.text}"/></option>
            </g:each>
        </select>
        <span id="targetItemContainer"></span>
    </span>
</form:field>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#targetGroup").kendoComboBox({
            change: function (e) {
                toggleAdjustmentType();
                initTargetItemSelector();
            }
        });
        initTargetItemSelector();
    });

    function initTargetItemSelector() {
        $('#targetItemContainer').empty().append('<input name="targetItem" id="targetItem" type="hidden"/>');
        if ($('#targetGroup').data('kendoComboBox').value() == 'all')
            return;
        $("#targetItem").width('300px').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            filter: "contains",
            index: 0,
            dataSource: {
                serverFiltering: true,
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'correlationAutoComplete')}?group=" + $('#targetGroup').val() + "&role=target",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    data: "data" // records are returned in the "data" field of the response
                }
            },
            change: function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });
    }
</script>