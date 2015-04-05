<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="backTest.list.title" args="${[tradeStrategy.name]}"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="backTest.list.title" args="${[tradeStrategy.name]}"/></h1>
            <div class="backTestGridContainer k-rtl">

                <div id="backTestGrid"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build', id: tradeStrategy.id)}" text="${message(code:'backTest.create')}"/>
                <form:linkButton href="${createLink(controller: 'tradeStrategy', action: 'list')}" text="${message(code:'tradeStrategy.list.title')}"/>
            </div>

            <script>
                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'jsonList', id: tradeStrategy.id)}",
                                    dataType: "json",
                                    type: "POST"

                                },
                                parameterMap: function (data, action) {
                                    if (action === "read") {
                                        data.search = $('#search').val();
                                        return data;
                                    } else {
                                        return data;
                                    }
                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: { type: "number" },
                                        symbol: { type: "string" },
                                        startDate: { type: "string" },
                                        endDate: { type: "string" },
                                        outlay: { type: "number" },
                                        buyWage: { type: "number" },
                                        sellWage: { type: "number" },
                                        buyTax: { type: "number" },
                                        sellTax: { type: "number" },
                                        status: { type: "string" },
                                        dateCreated: { type: "string" }
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            },
                            pageSize: 10,
                            serverPaging: true,
                            serverFiltering: true,
                            serverSorting: true
                        },
                        height: 550,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'backTest.id.label')}",
                                width: "70px",
                                attributes: { style: "text-align: center"},
                                headerAttributes: { style: "text-align: center"}
                            },
                            {
                                field: "symbol",
                                title: "${message(code:'backTest.symbol.label')}"
                            } ,
                            {
                                field: "startDate",
                                title: "${message(code:'backTest.startDate.label')}"
                            },
                            {
                                field: "endDate",
                                title: "${message(code:'backTest.endDate.label')}"
                            },
                            {
                                field: "outlay",
                                title: "${message(code:'backTest.outlay.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "buyWage",
                                title: "${message(code:'backTest.buyWage.label')}",
                                format: '{0:p0}'
                            },
                            {
                                field: "sellWage",
                                title: "${message(code:'backTest.sellWage.label')}",
                                format: '{0:p0}'
                            },
                            {
                                field: "buyTax",
                                title: "${message(code:'backTest.buyTax.label')}",
                                format: '{0:p0}'
                            },
                            {
                                field: "sellTax",
                                title: "${message(code:'backTest.sellTax.label')}",
                                format: '{0:p0}'
                            },
                            {
                                field: "status",
                                title: "${message(code:'backTest.status.label')}"
                            },
                            {
                                field: "dateCreated",
                                title: "${message(code:'backTest.dateCreated.label')}"
                            },
                            { command: { text: "${message(code:'results')}", click: viewGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function viewGridItem(e) {
                    window.location.href = "${createLink(action: 'view')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'backTest.delete.confirm')}', deleteItem, cancelDelete);
                    }

                }

                function cancelDelete(){
                    idForDelete = 0;
                }

                function deleteItem(){
                    if(idForDelete > 0 ) {
                        $.ajax({
                            type: "POST",
                            url: '${createLink(action: 'delete')}',
                            data: { id: idForDelete }
                        }).done(function (response) {
                            if (response == "1") {
                                var documentListView = $('#grid').data('kendoGrid');
                                documentListView.dataSource.read();   // added line
                                documentListView.refresh();
                            }
                            else {
                                window.alert('${message(code:'backTest.delete.error')}');
                            }
                        });
                        idForDelete = 0;
                    }
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>