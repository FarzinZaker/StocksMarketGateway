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
    <title><g:message code="tradeStrategy.list.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.strategy'), url:createLink(controller: 'tradeStrategy')],
                    [text: '<i class="fa fa-magic"></i> ' + message(code:'menu.strategy.list'), url:createLink(controller: 'tradeStrategy', action: 'list')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
                %{--<h1 class="magenta">--}%
                %{--<i class="fa fa-magic"></i>--}%
                %{--<g:message code="tradeStrategy.list.title"/>--}%
                %{--</h1>--}%
            <div class="tradeStrategyGridContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var tradeStrategyGrid = $('#grid').data('kendoGrid');
                        tradeStrategyGrid.dataSource.read();   // added line
                        tradeStrategyGrid.refresh();
                    }
                </script>

                <div id="tradeStrategyGrid"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build')}" text="${message(code:'tradeStrategy.create')}"/>
            </div>

            <script>
                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'jsonList')}",
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
                                        title: { type: "string" },
                                        lastUpdated: { type: "string" }
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            },
                            pageSize: 20,
                            serverPaging: true,
                            serverFiltering: true,
                            serverSorting: true
                        },
//                        height: 550,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            %{--{--}%
                                %{--field: "id",--}%
                                %{--title: "${message(code:'tradeStrategy.id.label')}",--}%
                                %{--width: "70px",--}%
                                %{--attributes: { style: "text-align: center"},--}%
                                %{--headerAttributes: { style: "text-align: center"}--}%
                            %{--},--}%
                            {
                                field: "title",
                                title: "${message(code:'tradeStrategy.title.label')}"
                            } ,
                            {
                                field: "lastUpdated",
                                title: "${message(code:'tradeStrategy.lastUpdated.label')}"
                            },
                            { command: { text: "${message(code:'test')}", click: testGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'testList')}", click: testListGridItem }, title: "", width: "135px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function testGridItem(e) {
                    window.location.href = "${createLink(controller: 'backTest',action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function testListGridItem(e) {
                    window.location.href = "${createLink(controller: 'backTest',action: 'list')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'tradeStrategy.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'tradeStrategy.delete.error')}');
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