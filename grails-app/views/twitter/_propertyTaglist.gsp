<form:field fieldName="correlation.source">
    <span style="width: 510px;display: inline-block">
        <select id="sourceGroup" name="sourceGroup" style="width:200px;">
            <g:each in="${groups}" var="group">
                <option value="${group.value}"><g:message code="${group.text}"/></option>
            </g:each>
        </select>
        <span id="sourceItemContainer"></span>
    </span>
</form:field>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#sourceGroup").kendoComboBox({
            change: function (e) {
                toggleAdjustmentType();
                initSourceItemSelector();
            }
        });
        initSourceItemSelector();
    });

    function initSourceItemSelector() {
        $('#sourceItemContainer').empty().append('<input name="sourceItem" id="sourceItem" type="hidden"/>');
        $("#sourceItem").width('300px').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            filter: "contains",
            index: 0,
            dataSource: {
                serverFiltering: true,
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'correlationAutoComplete')}?group=" + $('#sourceGroup').val() + "&role=source",
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