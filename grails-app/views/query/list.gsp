<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/9/14
  Time: 4:59 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="query.list.title"/></title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-3" style="padding-top:100px;">
            <h2><g:message code="query.category.select"/></h2>
            <div class="k-rtl">
                <script id="categoryTree-template" type="text/kendo-ui-template">
                <span class='treeNode-text'>#:item.text#</span><span class='treeNode-value'>#:item.id#</span>
                </script>

                <div id="categoryTree"></div>
                <input type="hidden" id="selectedCategory" value="0"/>
            </div>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    var categoryTree = $("#categoryTree").kendoTreeView({
                        dataSource: [<format:html value="${categoryTree as JSON}"/>],
                        template: kendo.template($('#categoryTree-template').html()),
                        select: function (e) {
                            $("#selectedCategory").val($(e.node).children("div").find(".treeNode-value").text());
                            var documentListView = $('#grid').data('kendoGrid');
                            documentListView.dataSource.read();   // added line
                            documentListView.refresh();
                        }
                    }).data('kendoTreeView');
                    categoryTree.select(categoryTree.findByUid(categoryTree.dataSource.get(0).uid))
                });
            </script>
        </div>
        <div class="col-xs-9">
            <h1><g:message code="query.list.title"/></h1>
            <div class="queryListContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var queryListView = $('#grid').data('kendoGrid');
                        queryListView.dataSource.read();   // added line
                        queryListView.refresh();
                    }
                </script>

                <div id="queryListView"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'selectData')}" text="${message(code:'menu.newsletters.add')}"/>
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
                                        data.search = $('#search').val();
                                        data.category = $('#selectedCategory').val();
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
                                        description: { type: "string" },
                                        domainClazz: { type: "string" },
                                        scheduleTemplate: { type: "string" },
                                        owner: { type: "string" },
                                        dateCreated: { type: "string" },
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
                        height: 550,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'query.id.label')}"
                            },
                            {
                                field: "title",
                                title: "${message(code:'query.title.label')}"
                            } ,
                            {
                                field: "description",
                                title: "${message(code:'query.description.label')}"
                            },
                            {
                                field: "domainClazz",
                                title: "${message(code:'query.domainClazz.label')}"
                            },
                            {
                                field: "scheduleTemplate",
                                title: "${message(code:'query.scheduleTemplate.label')}"
                            },
                            {
                                field: "owner",
                                title: "${message(code:'query.owner.label')}"
                            },
                            {
                                field: "dateCreated",
                                title: "${message(code:'query.dateCreated.label')}"
                            },
                            {
                                field: "lastUpdated",
                                title: "${message(code:'query.lastUpdated.label')}"
                            } ,
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} },
                            { command: { text: "${message(code:'remove')}", click: removeGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                var idForDelete = 0;
                function removeGridItem(e) {
                    if(idForDelete == 0) {
                        idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'query.delete.confirm')}', deleteItem, cancelDelete);
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
                                window.alert('${message(code:'query.delete.error')}');
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