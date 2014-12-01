<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolioItem.view.title"/></title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolioItem.view.title"/></h1>

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
                                    url: "${createLink(action: 'jsonPortfolioItemView', params: [id: params.id])}",
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
                                    id: "id",
                                    fields: {
                                        id: { type: "number" },
                                        actionType: { type: "string" },
                                        actionDate: { type: "string" },
                                        sharePrice: { type: "number" },
                                        shareCount: { type: "number" }
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
                                field: "actionType",
                                title: "${message(code:'portfolioAction.actionType.label')}"
                            } ,
                            {
                                field: "actionDate",
                                title: "${message(code:'portfolioAction.actionDate.label')}"
                            } ,
                            {
                                field: "sharePrice",
                                title: "${message(code:'portfolioAction.sharePrice.label')}"
                            } ,
                            {
                                field: "shareCount",
                                title: "${message(code:'portfolioAction.shareCount.label')}"
                            }
                        ]
                    });
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>