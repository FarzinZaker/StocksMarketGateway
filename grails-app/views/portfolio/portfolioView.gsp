<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.view.title"/></title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.view.title"/></h1>

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
                                    url: "${createLink(action: 'jsonPortfolioView', params: [id: params.id])}",
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
                                        symbol: { type: "string" },
                                        shareCount: { type: "number" },
                                        cost: { type: "number" },
                                        avgPrice: { type: "number" },
                                        shareValue: {type: "number" },
                                        currentValue: { type: "number" }
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
                                field: "symbol",
                                title: "${message(code:'symbol.label')}"
                            } ,
                            {
                                field: "shareCount",
                                title: "${message(code:'portfolioItem.shareCount.label')}"
                            } ,
                            {
                                field: "cost",
                                title: "${message(code:'portfolioItem.cost.label')}"
                            } ,
                            {
                                field: "avgPrice",
                                title: "${message(code:'portfolioItem.avgPrice.label')}"
                            } ,
                            {
                                field: "shareValue",
                                title: "${message(code:'portfolioItem.shareValue.label')}"
                            } ,
                            {
                                field: "currentValue",
                                title: "${message(code:'portfolioItem.currentValue.label')}"
                            } ,
                            { command: { text: "${message(code:'view')}", click: viewGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
                        ]
                    });
                });

                function viewGridItem(e) {
                    window.location.href = "${createLink(action: 'portfolioItemView')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>