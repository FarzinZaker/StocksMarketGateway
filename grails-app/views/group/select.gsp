<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/22/14
  Time: 2:53 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.select"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: '<i class="fa fa-users"></i> ' + message(code: 'twitter.group.select'), url: createLink(controller: 'group', action: 'select')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <div class="groupListContainer k-rtl">
                %{--<form:searchBox name="search" action="searchList"/>--}%
                %{--<script language="javascript" type="text/javascript">--}%
                %{--function searchList() {--}%
                %{--var groupListView = $('#groupListView').data('kendoListView');--}%
                %{--groupListView.dataSource.read();--}%
                %{--groupListView.refresh();--}%
                %{--}--}%
                %{--</script>--}%

                <div id="groupListView"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>

            <script type="text/x-kendo-tmpl" id="groupTemplate">
                <div class="group" item-id="#:id#" >
                    <span class='image'><img src="${createLink(controller: 'image', action: 'index')}/#:imageId#?&size=250" alt="#:title#" /></span>
                    <h3>#:title#</h3>
                    <p>#:description#</p>
                    <span class='price'><g:message code="twitter.group.membershipPeriod.1Month"/> #:membership1MonthPrice#</span>
                    <div class='toolbar'>
                        <a href='${createLink(action: 'home')}/#:id#'><g:message code="twitter.group.view.button"/></a><span onclick='registerInGroup("#:id#", "#:title#")'><g:message code="twitter.group.register.button"/></span>
                    </div>
                </div>
            </script>

            <script>
                $(document).ready(function () {
                    var dataSource = new kendo.data.DataSource({
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${createLink(controller: 'group', action: 'publicUnregisteredList')}",
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
                        pageSize: 12
                    });

                    $("#pager").kendoPager({
                        dataSource: dataSource
                    });

                    $("#groupListView").kendoListView({
                        dataSource: dataSource,
                        selectable: "single",
                        template: kendo.template($("#groupTemplate").html()),
                        dataBound: function () {
                            $('#groupListView').find('.group p').dotdotdot({
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
                                    remove: [' ', ',', ';', '.', '!', '?'],
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

<g:render template="registerWindow"/>
</body>
</html>