<%@ page import="grails.converters.JSON" %>
<div id="grid"></div>
<div id="details"></div>

<script>

    var wnd;
    $(document).ready(function () {
        $("#grid").kendoGrid({
            dataSource: {
                data: <format:html value="${data as JSON}"/>,
                schema: {
                    model: {
                        fields: {
                            targetGroup: {type: "string"},
                            targetGroupName: {type: "string"},
                            targetItem: {type: "string"},
                            targetItemName: {type: "string"},
                            correlation: {type: "number"}
                        }
                    }
                },
                pageSize: 10,

                group: {
                    field: "targetGroupName",
                    title: "${message(code:'tools.correlation.grid.targetItemName')}",
                    aggregates: [
                        {field: "correlation", aggregate: "average"}
                    ]
                },

                aggregate: [
                    {field: "correlation", aggregate: "average"}
                ]
            },
            scrollable: true,
            sortable: true,
            filterable: false,
            pageable: true,

            columns: [
                {
                    field: "targetGroupName",
                    title: "${message(code:'tools.correlation.grid.targetGroup')}",
                    hidden: true
                },
                {
                    field: "targetItemName",
                    title: "${message(code:'tools.correlation.grid.targetItemName')}"
                },
                {
                    field: "correlation",
                    title: "${message(code:'tools.correlation.grid.correlation')}",
                    width: "130px",
                    aggregates: ["average"],
                    groupFooterTemplate: "${message(code:'tools.correlation.grid.correlation.average')}: #=kendo.toString(average, 'n')#"
                },
                {
                    command: {text: "${message(code:'tools.correlation.grid.details')}", click: showDetails},
                    title: " ",
                    width: "180px"
                }
            ]
        });

        wnd = $("#details")
                .kendoWindow({
                    title: "",
                    modal: true,
                    visible: false,
                    resizable: false,
                    width: 720
                }).data("kendoWindow");

    });


    function showDetails(e) {
        e.preventDefault();

        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        var params = '?targetGroup=' + dataItem.targetGroup;
        params += '&targetItem=' + dataItem.targetItem;
        params += '&sourceGroup=' + $('#sourceGroup').data('kendoComboBox').value();
        params += '&sourceItem=' + $('#sourceItem').data('kendoComboBox').value();
        params += '&startDate=' + $('#startDate').val();
        params += '&endDate=' + $('#endDate').val();
        params += '&period=' + $('#period').val();
        wnd.content('<iframe src="${createLink(action: 'correlationChart')}' + params + '" frameborder="0" style="width:700px;height:400px;"/>');
        wnd.title('${message(code:'tools.correlation.compare.title.part1')} ' + $('#sourceItem').data('kendoComboBox').text() + ' ${message(code:'tools.correlation.compare.title.part2')} ' + dataItem.targetItemName);
        wnd.center().open();
    }
</script>

