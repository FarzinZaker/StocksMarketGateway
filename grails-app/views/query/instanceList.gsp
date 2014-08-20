<%--
Created by IntelliJ IDEA.
User: root
Date: 6/22/14
Time: 2:53 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="site"/>
    <title><g:message code="queryInstance.list.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="queryInstance.list.title"/></h1>

            <div class="queryListContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var queryInstanceListView = $('#queryInstanceListView').data('kendoListView');
                        queryInstanceListView.dataSource.read();   // added line
                        queryInstanceListView.refresh();
                    }
                </script>

                <div id="queryInstanceListView"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <script type="text/x-kendo-tmpl" id="queryTemplate">
                <div class="query" item-id="#:id#" >
                    <span class='image'><a href='${createLink(action: 'register')}/#:id#'><img src="#:imageUrl#" alt="#:title#" /></a></span>
                    <div class='text'>
                        <h3>#:title#</h3>
                        <p>#:description#</p>
                        <div class='parameters'>
                        ${message(code:'query.parameters.list')}: <span>#:parameterTags#</span>
                        </div>
                        <div class='toolbar'>
                            <button class='k-button' onclick='#:toggleAction#(#:id#)'>#:toggleActionTitle#</button>
                            <a class='k-button' href='${createLink(action:'register')}/#:id#'>${message(code:'queryInstance.edit.button')}</a>
                            <button class='k-button' onclick='removeItem(#:id#)'>${message(code:'queryInstance.remove.button')}</button>
                        </div>
                    </div>
                    <div class='clear-fix'></div>
                </div>
            </script>

            <script>
                $(document).ready(function () {
                    var dataSource = new kendo.data.DataSource({
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${createLink(action: 'instanceJsonList')}",
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
                            data: "data", // records are returned in the "data" field of the response
                            total: "total" // total number of records is in the "total" field of the response
                        },
                        serverPaging: true,
                        pageSize: 5
                    });

                    $("#pager").kendoPager({
                        dataSource: dataSource
                    });

                    $("#queryInstanceListView").kendoListView({
                        dataSource: dataSource,
                        selectable: "single",
                        template: kendo.template($("#queryTemplate").html()),
                        dataBound: function () {
                            $('#queryInstanceListView').find('.query p').dotdotdot({
                                ellipsis: '... ',
                                wrap: 'word',
                                fallbackToLetter: true,
                                after: null,
                                watch: false,
                                height: 90,
                                tolerance: 0,
                                callback: function (isTruncated, orgContent) {
                                },
                                lastCharacter: {
                                    remove: [ ' ', ',', ';', '.', '!', '?' ],
                                    noEllipsis: []
                                }
                            });
                        }
                    });

                });

                var idForDelete = 0;
                function removeItem(id) {
                    if(idForDelete == 0) {
                        idForDelete = id;
                        confirm('${message(code:'queryInstance.delete.confirm')}', deleteItem, cancelDelete);
                    }

                }

                function cancelDelete(){
                    idForDelete = 0;
                }

                function deleteItem(){
                    if(idForDelete > 0 ) {
                        $.ajax({
                            type: "POST",
                            url: '${createLink(action: 'instanceDelete')}',
                            data: { id: idForDelete }
                        }).done(function (response) {
                            if (response == "1") {
                                var queryInstanceListView = $('#queryInstanceListView').data('kendoListView');
                                queryInstanceListView.dataSource.read();   // added line
                                queryInstanceListView.refresh();
                            }
                            else {
                                window.alert('${message(code:'queryInstance.delete.error')}');
                            }
                        });
                        idForDelete = 0;
                    }
                }

                var idForEnable = 0;
                function enable(id) {
                    if(idForEnable == 0) {
                        idForEnable = id;
                        confirm('${message(code:'queryInstance.enable.confirm')}', enableItem, cancelEnable);
                    }

                }

                function cancelEnable(){
                    idForEnable = 0;
                }

                function enableItem(){
                    if(idForEnable > 0 ) {
                        $.ajax({
                            type: "POST",
                            url: '${createLink(action: 'instanceEnable')}',
                            data: { id: idForEnable }
                        }).done(function (response) {
                            if (response == "1") {
                                var queryInstanceListView = $('#queryInstanceListView').data('kendoListView');
                                queryInstanceListView.dataSource.read();   // added line
                                queryInstanceListView.refresh();
                            }
                            else {
                                window.alert('${message(code:'queryInstance.enable.error')}');
                            }
                        });
                        idForEnable = 0;
                    }
                }

                var idForDisable = 0;
                function disable(id) {
                    if(idForDisable == 0) {
                        idForDisable = id;
                        confirm('${message(code:'queryInstance.disable.confirm')}', disableItem, cancelDisable);
                    }

                }

                function cancelDisable(){
                    idForDisable = 0;
                }

                function disableItem(){
                    if(idForDisable > 0 ) {
                        $.ajax({
                            type: "POST",
                            url: '${createLink(action: 'instanceDisable')}',
                            data: { id: idForDisable }
                        }).done(function (response) {
                            if (response == "1") {
                                var queryInstanceListView = $('#queryInstanceListView').data('kendoListView');
                                queryInstanceListView.dataSource.read();   // added line
                                queryInstanceListView.refresh();
                            }
                            else {
                                window.alert('${message(code:'queryInstance.disable.error')}');
                            }
                        });
                        idForDisable = 0;
                    }
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>