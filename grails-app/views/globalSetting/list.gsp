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
    <title><g:message code="globalSetting.list.title"/></title>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.basicInfo'), url: createLink(controller: 'baseInfo')],
                    [text: '<i class="fa fa-cog"></i> ' + message(code: 'globalSetting.list.title'), url: createLink(controller: 'globalSetting', action: 'list')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <div class="k-rtl">
                <div id="grid"></div>
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
                                update: {
                                    url: "${createLink(action: 'save')}",
                                    dataType: "json"
                                },
                                parameterMap: function (options, operation) {
                                    if (operation !== "read" && options.models) {
                                        return {models: kendo.stringify(options.models)};
                                    }
                                }
                            },
                            schema: {
                                model: {
                                    id: "id",
                                    fields: {
                                        name: {type: "string", validation: {required: true}},
                                        value: {type: "string", validation: {required: true}}
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            },
                            batch: true,
                            pageSize: 20,
                            serverPaging: true,
                            serverFiltering: true,
                            serverSorting: true
                        },
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        editable: "inline",
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'globalSetting.id.label')}",
                                width: "90px",
                                editable: false
                            },
                            {
                                field: "name",
                                title: "${message(code:'globalSetting.name.label')}"
                            },
                            {
                                field: "value",
                                title: "${message(code:'globalSetting.value.label')}"
                            },
                            {command: ["edit"], title: "&nbsp;", width: "250px"}
                        ]
                    });
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if (idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'province.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'province.delete.error')}');
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