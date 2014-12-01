<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.manage"/></title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.manage"/></h1>

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <script>
                useOldConfirm = true;

                $(document).ready(function () {
                    var dataSource = new kendo.data.DataSource({
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${createLink(action: 'jsonPortfolioActions', id: params.id)}",
                                dataType: "json",
                                type: "POST"
                            },
                            update: {
                                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionUpdate', id: params.id)}",
                                dataType: "json",
                                type: "POST"
                            },
                            destroy: {
                                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionDelete', id: params.id)}",
                                dataType: "json",
                                type: "POST"
                            },
                            create: {
                                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionCreate', id: params.id)}",
                                dataType: "json",
                                type: "POST"
                            },
                            parameterMap: function (options, operation) {
                                if (operation !== "read" && options.models) {
                                    return {models: kendo.stringify(options.models)};
                                }
                            }
                        },
                        batch: true,
                        pageSize: 20,
                        serverPaging: true,
                        serverFiltering: true,
                        serverSorting: true,
                        schema: {
                            model: {
                                id: "id",
                                fields: {
                                    id: { type: "number" },
                                    symbol: { type: "string" },
                                    actionType: { type: "string" },
                                    actionDate: { type: "string" },
                                    sharePrice: { type: "number" },
                                    shareCount: { type: "number" }
                                }
                            },
                            data: "data",
                            total: "total"
                        }
                    });
                    $("#grid").kendoGrid({
                        dataSource: dataSource,
                        pageable: true,
                        height: 550,
                        toolbar: ["create"],
//              selectable: true,
                        %{--change: onChange_${parameter.id},--}%
                        columns: [
                            {
                                field: "symbol",
                                title: "${message(code:'symbol.label')}"
                            } ,
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
                            },
                            { command: ["edit", "destroy"], title: "&nbsp;", width: "175px" }
                        ],
                        editable: "inline"
                    });
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>