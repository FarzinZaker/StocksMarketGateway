<div class="symbolSearchBox">
    <input id="symbolSearch"/>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {

        $("#symbolSearch").val('').width('250px').kendoComboBox({
            dataTextField: "name",
            dataValueField: "value",
            filter: "contains",
            placeholder: '${message(code:'symbolSearch.placeHolder')}',
            dataSource: {
                serverFiltering: true,
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(controller: 'symbol', action: 'search')}",
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
                if (this.value())
                    window.location.href = "${createLink(controller: 'symbol', action: 'info')}/" + this.value();
            }
        });
    });
</script>