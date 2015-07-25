<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page import="stocks.tse.AdjustmentHelper; grails.util.Environment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${screener.title}</title>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
    <asset:javascript src="jquery.color.js"/>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="pink" style="float:right;">
                <i class="fa fa-filter"></i>
                ${screener.title}
            </h1>
            <div style="float:left;padding-top:45px;">
                <form:select name="adjustmentType" style="width:300px;" value="${AdjustmentHelper.defaultType}"
                             items="${AdjustmentHelper.ENABLED_TYPES.collect {
                                 [text: message(code: "priceAdjustment.types.${it}"), value: it]
                             }}" onchange="reloadGrid"/>
            </div>
            <div class="clear-fix"></div>

            <div id="timer"></div>

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

                function reloadGrid(){

                    var documentListView = $('#grid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }

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

                function getChangeColor(value) {
                    if (value < 0)
                        return 'flashRedCell';
                    if (value > 0)
                        return 'flashGreenCell';
                    return '';
                }

                function formatNumericField(model, fieldName) {
                    return "<div class='" + getChangeColor(eval('model.' + fieldName + 'Change')) + "'>" + formatNumber(eval('model.' + fieldName)) + "</div>";
                }

                var changableColumns = [
                    'closingPrice',
                    'firstTradePrice',
                    'lastTradePrice',
                    'maxPrice',
                    'minPrice',
                    'priceChange',
                    'totalTradeCount',
                    'totalTradeValue',
                    'totalTradeVolume',
                    'yesterdayPrice',
                    <g:each in="${indicatorColumns.keySet()}" var="indicatorColumn">
                    '${indicatorColumn.replace(',','_')}',
                    </g:each>

                ];

                var oldData = [];

                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'jsonView', id: params.id)}",
                                    dataType: "json",
                                    type: "POST"

                                },
                                parameterMap: function (data, action) {
                                    if (action === "read") {
                                        data.adjustmentType = $('#adjustmentType').val();
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
                                        '${indicatorColumn.replace(',','_')}': {type: "number"},
                                        </g:each>
                                    }
                                },
                                data: "data", // records are returned in the "data" field of the response
                                total: "total"
                            }
//                            pageSize: 10
                        },
//                        height: 450,
                        filterable: false,
                        sortable: true,
//                        pageable: true,
                        columns: [
                            <g:if test="${Environment.developmentMode}">
                            {
                                field: "id",
                                title: "#",
                                width: "70px",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"}
                            },
                            </g:if>
                            {
                                field: "symbol",
                                title: "${message(code:'symbol.title.label')}",
                                template: "<a target='_blank' href='${createLink(controller: 'symbol', action: 'info')}/#=data.id#'>#=data.symbol#</a>"
                            },
                            {
                                field: "closingPrice",
                                title: "${message(code:'symbol.closingPrice.label')}",
                                template: "#=formatNumericField(data, 'closingPrice')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "firstTradePrice",
                                title: "${message(code:'symbol.firstTradePrice.label')}",
                                template: "#=formatNumericField(data, 'firstTradePrice')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "lastTradePrice",
                                title: "${message(code:'symbol.lastTradePrice.label')}",
                                template: "#=formatNumericField(data, 'lastTradePrice')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "maxPrice",
                                title: "${message(code:'symbol.maxPrice.label')}",
                                template: "#=formatNumericField(data, 'maxPrice')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "minPrice",
                                title: "${message(code:'symbol.minPrice.label')}",
                                template: "#=formatNumericField(data, 'minPrice')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "priceChange",
                                title: "${message(code:'symbol.priceChange.label')}",
                                template: "#= formatPriceChange(data) #"

                            },
                            {
                                field: "totalTradeCount",
                                title: "${message(code:'symbol.totalTradeCount.label')}",
                                template: "#=formatNumericField(data, 'totalTradeCount')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeValue",
                                title: "${message(code:'symbol.totalTradeValue.label')}",
                                template: "#=formatNumericField(data, 'totalTradeValue')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeVolume",
                                title: "${message(code:'symbol.totalTradeVolume.label')}",
                                template: "#=formatNumericField(data, 'totalTradeVolume')#"
//                                format: '{0:n0}'
                            },
                            {
                                field: "yesterdayPrice",
                                title: "${message(code:'symbol.yesterdayPrice.label')}",
                                template: "#=formatNumericField(data, 'yesterdayPrice')#"
//                                format: '{0:n0}'
                            },

                            <g:each in="${indicatorColumns}" var="indicatorColumn">
                            {
                                field: "${indicatorColumn.key.replace(',', '_')}",
                                title: "${indicatorColumn.value}",
                                template: "#=formatNumericField(data, '${indicatorColumn.key.replace(',', '_')}')#"
//                                format: '{0:n0}'
                            },
                            </g:each>
                        ]
                    });

                    $('#timer').timer({
                        delay: 5000,
                        repeat: true,
                        autostart: true,
                        callback: function (index) {
                            var grid = $('#grid').data('kendoGrid');
                            oldData = grid.dataSource._data;
                            grid.dataSource.read();
                            for (var i = 0; i < grid.dataSource._data.length; i++) {
                                var oldRecord = false;
                                for (var j = 0; j < oldData.length; j++)
                                    if (grid.dataSource._data[i].id == oldData[j].id)
                                        oldRecord = oldData[j];

                                if (oldRecord) {
                                    for (j = 0; j < changableColumns.length; j++) {
                                        if (eval('grid.dataSource._data[i].' + changableColumns[j] + ' < oldRecord.' + changableColumns[j]))
                                            eval('grid.dataSource._data[i].' + changableColumns[j] + 'Change = -1');
                                        else if (eval('grid.dataSource._data[i].' + changableColumns[j] + ' > oldRecord.' + changableColumns[j]))
                                            eval('grid.dataSource._data[i].' + changableColumns[j] + 'Change = +1');
                                        else
                                            eval('grid.dataSource._data[i].' + changableColumns[j] + 'Change = 0');
                                    }
                                }
                            }
                            grid.refresh();
                            $('.flashGreenCell').each(function(){
                                var item = $(this).parent();
                                item.css('background-color', '#a4c400');
                                item.animate({backgroundColor: jQuery.Color("#a4c400").transition("transparent", 1)}, 4000);
                            });
                            $('.flashRedCell').each(function(){
                                var item = $(this).parent();
                                item.css('background-color', '#fa6800');
                                item.animate({backgroundColor: jQuery.Color("#fa6800").transition("transparent", 1)}, 4000);
                            });
                        }
                    });
                });

                function formatNumber(data) {
                    return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>