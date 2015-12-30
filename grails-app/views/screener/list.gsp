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
    <title><g:message code="screener.list.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.screener'), url:createLink(controller: 'screener')],
                    [text: '<i class="fa fa-filter"></i> ' + message(code:"menu.screener.list"), url:createLink(controller: 'screener', action: 'list')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="pink">--}%
                %{--<i class="fa fa-filter"></i>--}%
                %{--<g:message code="screener.list.title"/>--}%
            %{--</h1>--}%
            <div class="screenerGridContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var screenerGrid = $('#grid').data('kendoGrid');
                        screenerGrid.dataSource.read();   // added line
                        screenerGrid.refresh();
                    }
                </script>

                <div id="screenerGrid"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build')}" text="${message(code:'screener.create')}"/>
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
                                %{--title: "${message(code:'screener.id.label')}",--}%
                                %{--width: "70px",--}%
                                %{--attributes: { style: "text-align: center"},--}%
                                %{--headerAttributes: { style: "text-align: center"}--}%
                            %{--},--}%
                            {
                                field: "title",
                                title: "${message(code:'screener.title.label')}"
                            } ,
                            {
                                field: "lastUpdated",
                                title: "${message(code:'screener.lastUpdated.label')}"
                            },
                            { command: { text: "${message(code:'view')}", click: viewGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function viewGridItem(e) {
                    window.location.href = "${createLink(action: 'view')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'screener.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'screener.delete.error')}');
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