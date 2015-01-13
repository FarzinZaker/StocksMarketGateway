<div class="row-fluid">
    <div class="col-xs-12">
        <select id="sourceGroup" name="sourceGroup">
            <g:each in="${groups}" var="group">
                <option value="${group.value}"><g:message code="${group.text}"/></option>
            </g:each>
        </select>
        <span id="sourceItemContainer"></span>
    </div>
</div>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#sourceGroup").kendoComboBox({
            change: function (e) {
                initSourceItemSelector();
            }
        });
        initSourceItemSelector();
    });

    function initSourceItemSelector(){
        $('#sourceItemContainer').empty().append('<input name="sourceItem" id="sourceItem"/>');
        $("#sourceItem").kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            filter: "contains",
            dataSource: {
                serverFiltering: true,
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'correlationAutoComplete')}?group=" + $('#sourceGroup').val(),
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    data: "data" // records are returned in the "data" field of the response
                }
            },
            change:function(e){
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });
    }
</script>