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
    <title><g:message code="user.list.title"/> ${broker.name}</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.broker'), url: createLink(controller: 'broker')],
                    [text: broker?.name, url: createLink(controller: 'broker', action: 'build', id: broker?.id)],
                    [text: '<i class="fa fa-user"></i> ' + message(code: 'user.list.title'), url: createLink(controller: 'broker', action: 'userList', id:params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
                %{--<i class="fa fa-user"></i>--}%
                %{--<g:message code="user.list.title"/> ${broker.name}--}%
            %{--</h1>--}%
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
                <form:linkButton href="${createLink(action: 'user', params: [brokerId: params.id])}" text="${message(code:'broker.users.add')}"/>
            </div>

            <script>
                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'userJsonList')}",
                                    dataType: "json",
                                    type: "POST"

                                },
                                parameterMap: function (data, action) {
                                    if (action === "read") {
                                        data.search = $('#search').val();
                                        data.id = ${params.id};
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
                                        nickname: { type: "string" },
                                        firstName: { type: "string" },
                                        lastName: { type: "string" },
                                        username: { type: "string" },
                                        sex: { type: "string" },
                                        mobile: { type: "string" },
                                        nationalCode: { type: "string" },
                                        city: { type: "string" },
                                        enabled: { type: "boolean" }
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
                                title: "${message(code:'user.id.label')}",
                                width: "70px",
                                attributes: { style: "text-align: center"},
                                headerAttributes: { style: "text-align: center"}
                            },
                            {
                                field: "nickname",
                                title: "${message(code:'user.nickname.label')}"
                            } ,
                            {
                                field: "firstName",
                                title: "${message(code:'user.firstName.label')}"
                            } ,
                            {
                                field: "lastName",
                                title: "${message(code:'user.lastName.label')}"
                            } ,
                            {
                                field: "username",
                                title: "${message(code:'user.email.label')}"
                            } ,
                            {
                                field: "sex",
                                title: "${message(code:'user.sex.label')}"
                            } ,
                            {
                                field: "mobile",
                                title: "${message(code:'user.mobile.label')}"
                            } ,
                            {
                                field: "nationalCode",
                                title: "${message(code:'user.nationalCode.label')}"
                            } ,
                            {
                                field: "city",
                                title: "${message(code:'user.city.label')}"
                            } ,
                            {
                                field: "enabled",
                                title: "${message(code:'user.enabled.label')}",
                                template: "<i class=\"fa #: enabled ? 'fa-thumbs-up' : '' #\"></i>",
                                width: "60px",
                                attributes: { style: "text-align: center"},
                                headerAttributes: { style: "text-align: center"}
                            } ,
                            {
                                field: "roles",
                                title: "${message(code:'user.roles.label')}",
                                sortable: false
                            } ,
                            { command: { text: "${message(code:'edit')}", click: editGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'user')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>