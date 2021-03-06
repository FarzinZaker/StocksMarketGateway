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
    <title><g:message code="broker.list.title"/></title>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.broker'), url:createLink(controller: 'broker')],
                    [text: '<i class="fa fa-institution"></i> ' + message(code:'broker.list.title'), url:createLink(controller: 'broker', action: 'list')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
                %{--<i class="fa fa-institution"></i>--}%
                %{--<g:message code="broker.list.title"/>--}%
            %{--</h1>--}%

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build')}" text="${message(code:'menu.broker.add')}"/>
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
                                        name: { type: "string" },
                                        englishName: { type: "string" }
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
                        height: 550,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'broker.id.label')}"
                            },
                            {
                                field: "name",
                                title: "${message(code:'broker.name.label')}"
                            } ,
                            {
                                field: "englishName",
                                title: "${message(code:'broker.englishName.label')}"
                            } ,
                            { command: { text: "${message(code:'broker.users.add')}", click: addUser }, title: "", width: "110px", headerAttributes: { style: "text-align: center"} } ,
                            { command: { text: "${message(code:'broker.users.list')}", click: showUsers }, title: "", width: "130px", headerAttributes: { style: "text-align: center"} } ,
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function addUser(e){
                    window.location.href = "${createLink(action: 'user')}?brokerId=" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function showUsers(e){
                    window.location.href = "${createLink(action: 'userList')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'broker.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'broker.delete.error')}');
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