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
    <title><g:message code="query.list.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.list.title"/></h1>

            <div class="queryListContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var queryListView = $('#queryListView').data('kendoListView');
                        queryListView.dataSource.read();   // added line
                        queryListView.refresh();
                    }
                </script>

                <div id="queryListView"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>
            <script type="text/x-kendo-tmpl" id="queryTemplate">
                <div class="query" item-id="#:id#" >
                    <span class='image'><a href='${createLink(action: 'register')}?query=#:id#'><img src="${asset.assetPath(src:'btn-select.png')}" alt="#:title#" /></a></span>
                    <div class='text'>
                        <h3>#:title#</h3>
                        <p>#:description#</p>
                        <div class='parameters'>
                        ${message(code:'query.parameters.list')}: <span>#:parameterTags#</span>
                        </div>
                        <div class='toolbar'>
                            <a class='k-button' href='${createLink(action:'register')}?query=#:id#'>${message(code:'query.register.button')}</a>
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
                            data: "data", // records are returned in the "data" field of the response
                            total: "total" // total number of records is in the "total" field of the response
                        },
                        serverPaging: true,
                        pageSize: 5
                    });

                    $("#pager").kendoPager({
                        dataSource: dataSource
                    });

                    $("#queryListView").kendoListView({
                        dataSource: dataSource,
                        selectable: "single",
                        template: kendo.template($("#queryTemplate").html()),
                        dataBound: function () {
                            $('#queryListView').find('.query p').dotdotdot({
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
            </script>
        </div>
    </div>
</div>
</body>
</html>