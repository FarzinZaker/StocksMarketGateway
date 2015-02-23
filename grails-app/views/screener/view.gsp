<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page import="grails.util.Environment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${screener.title}</title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1>${screener.title}</h1>

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div id="filter-query-panel">
                <g:set var="index" value="${0}"/>
                <g:each in="${rules}" var="rule">
                    <div class="queryItem readonly" id="queryItem_${index++}">
                        <g:render template="queryItem"
                                  model="${[filter: rule.filter, operator: rule.operator, value: rule.value, text: rule.text, parameter: rule.parameter]}"/>
                    </div>
                </g:each>
            </div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build', id: params.id)}"
                                 text="${message(code: 'screener.edit')}"/>
            </div>

            <script>

                function formatPriceChange(model) {
                    if (model.priceChange > 0) {
                        return Math.abs(model.priceChange).toString().replace(/./g, function (c, i, a) {
                                    return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                                }) + "<i class='fa fa-icon fa-arrow-up' style='color: green;float:left;line-height:22px;'></i>";
                    } else if (model.priceChange < 0) {
                        return Math.abs(model.priceChange).toString().replace(/./g, function (c, i, a) {
                                    return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                                }) + "<i class='fa fa-icon fa-arrow-down' style='color: red;float:left;;line-height:22px;'></i>";
                    } else {
                        return "-";
                    }
                }

                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'jsonView', id: params.id)}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: {type: "number"},
                                        symbol: {type: "string"},
                                        closingPrice: {type: "number"},
                                        firstTradePrice: {type: "number"},
                                        lastTradePrice: {type: "number"},
                                        maxPrice: {type: "number"},
                                        minPrice: {type: "number"},
                                        priceChange: {type: "number"},
                                        totalTradeCount: {type: "number"},
                                        totalTradeValue: {type: "number"},
                                        totalTradeVolume: {type: "number"},
                                        yesterdayPrice: {type: "number"},
                                        <g:each in="${indicatorColumns.keySet()}" var="indicatorColumn">
                                        '${indicatorColumn}': {type: "number"},
                                        </g:each>
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            },
                            pageSize: 10
                        },
//                        height: 450,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            <g:if test="${Environment.developmentMode}">
                            {
                                field: "id",
                                title: "#",
                                width: "70px",
                                attributes: { style: "text-align: center"},
                                headerAttributes: { style: "text-align: center"}
                            },
                            </g:if>
                            {
                                field: "symbol",
                                title: "${message(code:'symbol.title.label')}"
                            },
                            {
                                field: "closingPrice",
                                title: "${message(code:'symbol.closingPrice.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "firstTradePrice",
                                title: "${message(code:'symbol.firstTradePrice.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "lastTradePrice",
                                title: "${message(code:'symbol.lastTradePrice.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "maxPrice",
                                title: "${message(code:'symbol.maxPrice.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "minPrice",
                                title: "${message(code:'symbol.minPrice.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "priceChange",
                                title: "${message(code:'symbol.priceChange.label')}",
                                template: "#= formatPriceChange(data) #"

                            },
                            {
                                field: "totalTradeCount",
                                title: "${message(code:'symbol.totalTradeCount.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeValue",
                                title: "${message(code:'symbol.totalTradeValue.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeVolume",
                                title: "${message(code:'symbol.totalTradeVolume.label')}",
                                format: '{0:n0}'
                            },
                            {
                                field: "yesterdayPrice",
                                title: "${message(code:'symbol.yesterdayPrice.label')}",
                                format: '{0:n0}'
                            },

                            <g:each in="${indicatorColumns}" var="indicatorColumn">
                            {
                                field: "${indicatorColumn.key}",
                                title: "${indicatorColumn.value}",
                                format: '{0:n0}'
                            },
                            </g:each>
                        ]
                    });
                });

            </script>
        </div>
    </div>
</div>
</body>
</html>