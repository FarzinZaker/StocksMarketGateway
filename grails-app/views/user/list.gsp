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
    <title><g:message code="user.list.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.users'), url: createLink(controller: 'user')],
                    [text: '<i class="fa fa-user"></i> ' + message(code: 'user.list.title'), url: createLink(controller: 'user', action: 'list')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="cyan">--}%
            %{--<i class="fa fa-user"></i>--}%
            %{--<g:message code="user.list.title"/>--}%
            %{--</h1>--}%
            <div class="queryListContainer k-rtl">
                <form:searchBox name="search" action="searchList"/>
                <script language="javascript" type="text/javascript">
                    function searchList() {
                        var queryListView = $('#grid').data('kendoGrid');
                        queryListView.dataSource.read();   // added line
                        queryListView.dataSource.page(1);
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
                <form:linkButton href="${createLink(action: 'build')}" text="${message(code: 'menu.users.add')}"/>
                <input type="button" class="k-button" value="${message(code: 'users.import')}"
                       onclick="btnImportUserClick()"/>
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
                                        id: {type: "number"},
                                        firstName: {type: "string"},
                                        lastName: {type: "string"},
                                        nickname: {type: "string"},
                                        username: {type: "string"},
                                        sex: {type: "string"},
                                        mobile: {type: "string"},
                                        nationalCode: {type: "string"},
                                        city: {type: "string"},
                                        enabled: {type: "boolean"}
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
                            {
                                field: "id",
                                title: "${message(code:'user.id.label')}",
                                width: "100px",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"}
                            },
                            {
                                field: "nickname",
                                title: "${message(code:'user.nickname.label')}",
                                width: "150px"
                            },
                            {
                                field: "firstName",
                                title: "${message(code:'user.firstName.label')}",
                                width: "120px"
                            },
                            {
                                field: "lastName",
                                title: "${message(code:'user.lastName.label')}",
                                width: "150px"
                            },
                            {
                                field: "username",
                                title: "${message(code:'user.email.label')}"
                            },
                            %{--{--}%
                            %{--field: "sex",--}%
                            %{--title: "${message(code:'user.sex.label')}"--}%
                            %{--} ,--}%
                            {
                                field: "mobile",
                                title: "${message(code:'user.mobile.label')}",
                                width: "100px"
                            },
                            {
                                field: "nationalCode",
                                title: "${message(code:'user.nationalCode.label')}",
                                width: "90px"
                            },
                            %{--{--}%
                            %{--field: "city",--}%
                            %{--title: "${message(code:'user.city.label')}"--}%
                            %{--} ,--}%
                            {
                                field: "enabled",
                                title: "${message(code:'user.enabled.label')}",
                                template: "<i class=\"fa #: enabled ? 'fa-thumbs-up' : '' #\"></i>",
                                width: "32px",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"}
                            },
                            {
                                field: "broker",
                                title: "${message(code:'user.broker.label')}",
                                template: "<i class=\"fa #: broker != '' ? 'fa-institution' : '' #\" data-broker='#: broker #'></i>",
                                width: "52px",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"}
                            },
                            %{--{--}%
                            %{--field: "roles",--}%
                            %{--title: "${message(code:'user.roles.label')}",--}%
                            %{--sortable: false--}%
                            %{--} ,--}%
                            {
                                command: {text: "${message(code:'transactions')}", click: manageTransaction},
                                title: "",
                                width: "105px",
                                headerAttributes: {style: "text-align: center"}
                            },
                            {
                                command: {text: "${message(code:'edit')}", click: editGridItem},
                                title: "",
                                width: "85px",
                                headerAttributes: {style: "text-align: center"}
                            }
                        ]
                    });


                    $("#grid").kendoTooltip({
                        filter: ".fa-institution", //this filter selects the first column cells
                        position: "bottom",
                        show: function (e) {
                            var position = e.sender.options.position;
                            e.sender.popup.element.css("margin-top", "7px");
                        },
                        content: function (e) {
                            var content = $(e.target).attr('data-broker');
                            return content;
                        }
                    }).data("kendoTooltip");
                });

                function editGridItem(e) {
                    window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function manageTransaction(e) {
                    window.location.href = "${createLink(controller: 'transaction', action: 'user')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }

                function btnImportUserClick(e) {
                    var win = $('#importWindow').html('')
                            .kendoWindow({
                                title: '${message(code:'users.import')}',
                                width: '500px',
                                content: '${createLink(controller: 'user', action: 'importUsers')}/',
                                modal: true,
                                close: function (e) {
                                }
                            }).data('kendoWindow').center().open();
                    win.bind("refresh", function () {
                        win.center();
                        win.open();
                    });
                }
            </script>
        </div>
    </div>
</div>

<div class="hidden k-rtl">
    <div id="importWindow"></div>
</div>
</body>
</html>