<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.list.title"/></title>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.portfolios'), url:createLink(controller: 'portfolio')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code:'portfolio.build.title'), url:createLink(controller: 'portfolio', action: 'build')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
                %{--<h1 class="darkBlue">--}%
                    %{--<i class="fa fa-shopping-cart"></i>--}%
                    %{--<g:message code="portfolio.list.title"/>--}%
                %{--</h1>--}%

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build')}" text="${message(code:'menu.portfolio.add')}"/>
            </div>

            <script>
                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
//                            type: "odata",
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'jsonList')}",
                                    dataType: "json",
                                    type: "POST"

                                },
                                parameterMap: function (data, action) {
                                    if (action === "read") {
                                        data.parent = $('#parent').val();
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
                                        title: { type: "string" }
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
                                %{--title: "${message(code:'portfolio.id.label')}"--}%
                            %{--},--}%
                            {
                                field: "name",
                                title: "${message(code:'portfolio.name.label')}"
                            } ,
                            { command: { text: "${message(code:'view')}", click: viewGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'portfolio.manage')}", click: manageGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function viewGridItem(e) {
                    window.location.href = "${createLink(action: 'portfolioView')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function manageGridItem(e) {
                    window.location.href = "${createLink(action: 'portfolioManage')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'portfolio.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'portfolio.delete.error')}');
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