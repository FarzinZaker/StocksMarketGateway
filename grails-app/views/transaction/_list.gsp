<div class="k-rtl">
    <div id="grid"></div>
</div>

<script>
    function formatNumber(data) {
        var result = Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
        if (data < 0)
            return result + '-'
        else
            return result
    }

    $(document).ready(function () {
        $("#grid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(controller: 'transaction', action: 'jsonList')}?" + dataUrlParams,
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            value: {type: "number"},
                            type: {type: "string"},
                            date: {type: "string"},
                            account: {type: "string"},
                            customer: {type: "string"},
                            creator: {type: "string"}
                        }
                    },
                    data: "data", // records are returned in the "data" field of the response
                    total: "total"
                },
                pageSize: 20,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true,

                aggregate: [
                    {field: "id", aggregate: "count"},
                    {field: "value", aggregate: "sum"}
                ]
            },
            height: 550,
            filterable: false,
            sortable: true,
            pageable: true,
            groupable: true,
            columns: [
                {
                    field: "id",
                    title: "${message(code:'transaction.id.label')}",
                    width: "100px",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    aggregates: ["count"],
                    footerTemplate: "<div style='text-align:center;'>#=formatNumber(count)# ${message(code:'item')}</div>",
                    groupFooterTemplate: "<div style='text-align:center;'>#=formatNumber(count)# ${message(code:'item')}</div>"
                },
                {
                    field: "value",
                    title: "${message(code:'transaction.value.label')}",
                    template: "#=formatNumber(value)#",
                    aggregates: ["sum"],
                    footerTemplate: "<div style='text-align:center;'>${message(code:'profile.balance')}: #=formatNumber(sum)# ${message(code:'rial')}</div>",
                    groupFooterTemplate: "<div style='text-align:center;'>${message(code:'transaction.value.sum')}: #=formatNumber(sum)# ${message(code:'rial')}</div>"
                },
                {
                    field: "type",
                    title: "${message(code:'transaction.type.label')}"
                },
                {
                    field: "date",
                    title: "${message(code:'transaction.date.label')}",
                    groupable: false
                },
                {
                    field: "account",
                    title: "${message(code:'transaction.account.label')}"
                },
                {
                    field: "customer",
                    title: "${message(code:'transaction.customer.label')}"
                },
                {
                    field: "creator",
                    title: "${message(code:'transaction.creator.label')}"
                }
                <g:if test="${!readOnly}">
                ,{
                    command: {text: "${message(code:'edit')}", click: editGridItem},
                    title: "",
                    width: "105px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'delete')}", click: removeGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
                </g:if>
            ]
        });
    });

    function editGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id + "?" + editUrlParams
    }

    var idForDelete = 0;
    function removeGridItem(e) {
        if (idForDelete == 0) {
            idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'transaction.delete.confirm')}', deleteItem, cancelDelete);
        }

    }

    function cancelDelete() {
        idForDelete = 0;
    }

    function deleteItem() {
        if (idForDelete > 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'delete')}',
                data: {id: idForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#grid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'transaction.delete.error')}');
                }
            });
            idForDelete = 0;
        }
    }
</script>