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
                                        persianName: {type: "persianName"}
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            },
                            pageSize: 10
                        },
                        height: 450,
                        filterable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            %{--{--}%
                            %{--field: "id",--}%
                            %{--title: "${message(code:'symbol.id.label')}",--}%
                            %{--width: "70px",--}%
                            %{--attributes: {style: "text-align: center"},--}%
                            %{--headerAttributes: {style: "text-align: center"}--}%
                            %{--},--}%
                            {
                                field: "persianName",
                                title: "${message(code:'symbol.persianName.label')}"
                            },
                            {
                                field: "persianCode",
                                title: "${message(code:'symbol.persianCode.label')}"
                            },
                            {
                                field: "companyName",
                                title: "${message(code:'symbol.companyName.label')}"
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