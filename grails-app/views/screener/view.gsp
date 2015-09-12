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
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.screener'), url: createLink(controller: 'screener')],
                    [text: message(code: "menu.screener.list"), url: createLink(controller: 'screener', action: 'list')],
                    [text: '<i class="fa fa-filter"></i> ' + screener.title, url: createLink(controller: 'screener', action: 'view', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">

            <div id="filter-query-panel" style="float: right;margin-bottom:10px;margin-top:0;min-width: 40%;">
                <span id="btnShowConditions" style="cursor: pointer;"><i class="fa fa-plus"></i> <g:message
                        code="screener.view.showConditions"/></span>
                <span id="btnHideConditions" style="display: none;cursor:pointer;"><i
                        class="fa fa-minus"></i> <g:message code="screener.view.hideConditions"/></span>
                <g:set var="index" value="${0}"/>
                <div id="filterList" style="display: none">
                    <g:each in="${rules}" var="rule">
                        <div class="queryItem readonly" id="queryItem_${index++}">
                            <g:render template="queryItem"
                                      model="${[filter: rule.filter, operator: rule.operator, value: rule.value, text: rule.text, parameter: rule.parameter]}"/>
                        </div>
                    </g:each>
                </div>
            </div>

            <div class="toolbar" style="float: right;margin-right: 10px;margin-top: 3px;">
                <form:linkButton href="${createLink(action: 'build', id: params.id)}"
                                 text="${message(code: 'screener.edit')}"/>
            </div>

            <div class="clear-fix"></div>

            <div id="timer"></div>

            <div class="k-rtl">
                <div style="float:left;margin-top:-40px;">
                    <form:select name="adjustmentType" style="width:300px;" value="${AdjustmentHelper.defaultType}"
                                 items="${AdjustmentHelper.ENABLED_TYPES.collect {
                                     [text: message(code: "priceAdjustment.types.${it}"), value: it]
                                 }}" onchange="reloadGrid"/>
                </div>

                <div id="grid">
                    <form:loading id="screenerLoading"/>
                </div>
            </div>

            <script>

                $(document).ready(function () {
                    $('#btnShowConditions').click(function () {
                        $('#btnShowConditions').hide();
                        $('#btnHideConditions').show();
                        $('#filterList').slideDown();
                    });
                    $('#btnHideConditions').click(function () {
                        $('#btnHideConditions').hide();
                        $('#btnShowConditions').show();
                        $('#filterList').slideUp();
                    });
                });

                function reloadGrid() {

                    var documentListView = $('#grid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }

                function formatPriceChange(model) {
                    if (model.priceChange > 0) {
                        return '<div style="float:right;">' + Math.abs(model.priceChange).toString().replace(/./g, function (c, i, a) {
                                    return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                                }) + "</div><i class='fa fa-icon fa-arrow-up' style='color: green;float:left;line-height:22px;'></i><div style='float:left;margin-left:10px;'>" + Math.round(Math.abs(model.priceChange) * 10000 / model.yesterdayPrice) / 100 + "%</div>";
                    } else if (model.priceChange < 0) {
                        return '<div style="float:right;">' + Math.abs(model.priceChange).toString().replace(/./g, function (c, i, a) {
                                    return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                                }) + "</div><i class='fa fa-icon fa-arrow-down' style='color: red;float:left;;line-height:22px;'></i><div style='float:left;margin-left:10px;'>" + Math.round(Math.abs(model.priceChange) * 10000 / model.yesterdayPrice) / 100 + "%</div>";
                    } else {
                        return "0";
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
                function formatNumericFieldIndicator(model, fieldName) {
//                    if(eval('model.' + fieldName)>0)
//                        return "<div style='color:green' class='" + getChangeColor(eval('model.' + fieldName )) + "Change'>" + formatNumber(eval('model.' + fieldName)) + "</div>";
//                    else if(eval('model.' + fieldName)<0)
//                        return "<div style='color:red' class='" + getChangeColor(eval('model.' + fieldName )) + "Change'>" + formatNumber(eval('model.' + fieldName)) + "</div>";
//                    else
                    return "<div class='" + getChangeColor(eval('model.' + fieldName)) + "Change'>" + formatNumber(eval('model.' + fieldName)) + ((eval('model.' + fieldName) < 0) ? '-' : '') + "</div>";
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

                var firstTime = true;
                var initialData = [];

                $('#screenerLoading').show();
                $.ajax({
                    url: "${createLink(action: 'jsonView', id: params.id)}",
                    dataType: "json",
                    data: {adjustmentType: $('#adjustmentType').val()},
                    success: function (result) {
                        // Return loaded data
                        initialData = result;
                        createGrid();
                        $("#grid").slideDown();
                        startTimer();
                    },
                    error: function (result) {
                    }
                });

                function createGrid() {

                    $("#grid").html('').kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: function (op) {
                                    if (firstTime) {
                                        // If it is first return initial content and toggle first
                                        op.success(initialData);
                                        firstTime = false;
                                    } else {
                                        // Subsequent runs use jquery.ajax for loading the data
                                        $.ajax({
                                            url: "${createLink(action: 'jsonView', id: params.id)}",
                                            dataType: "json",
                                            data: {adjustmentType: $('#adjustmentType').val()},
                                            success: function (result) {
                                                // Return loaded data
                                                op.success(result);
                                            },
                                            error: function (result) {
                                                op.error(result);
                                            }
                                        });
                                    }
                                },
                                %{--{--}%
                                %{--url: "${createLink(action: 'jsonView', id: params.id)}",--}%
                                %{--dataType: "json",--}%
                                %{--type: "POST"--}%

                                %{--},--}%
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
                                        symbolName: {type: "string"},
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
                        height: $(window).height() - 310,
                        filterable: false,
                        sortable: true,
                        scrollable: true,
//                        pageable: true,
                        columns: [
                            <g:if test="${Environment.developmentMode}">
                            {
                                field: "id",
                                title: "#",
                                width: "70px",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                locked: true,
                                lockable: false
                            },
                            </g:if>
                            {
                                field: "symbolName",
                                title: "",
                                width: "0px",
                                locked: true,
                                template: "<div style='white-space: nowrap'>#=data.symbolName#</div>",
//                                lockable: false
                            },
                            {
                                field: "symbol",
                                title: "${message(code:'symbol.title.label')}",
                                template: "<a target='_blank' href='${createLink(controller: 'symbol', action: 'info')}/#=data.id#'>#=data.symbol#</a>",
                                width: "80px",
                                locked: true
//                                lockable: false
                            },
                            {
                                field: "lastTradePrice",
                                title: "${message(code:'symbol.lastTradePrice.label')}",
                                template: "#=formatNumericField(data, 'lastTradePrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "120px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "closingPrice",
                                title: "${message(code:'symbol.closingPrice.label')}",
                                template: "#=formatNumericField(data, 'closingPrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "100px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "priceChange",
                                title: "${message(code:'symbol.priceChange.label')}",
                                template: "#= formatPriceChange(data) #",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "120px"
                            },
                            {
                                field: "firstTradePrice",
                                title: "${message(code:'symbol.firstTradePrice.label')}",
                                template: "#=formatNumericField(data, 'firstTradePrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "120px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "maxPrice",
                                title: "${message(code:'symbol.maxPrice.label')}",
                                template: "#=formatNumericField(data, 'maxPrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "100px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "minPrice",
                                title: "${message(code:'symbol.minPrice.label')}",
                                template: "#=formatNumericField(data, 'minPrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "100px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "yesterdayPrice",
                                title: "${message(code:'symbol.yesterdayPrice.label')}",
                                template: "#=formatNumericField(data, 'yesterdayPrice')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "100px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeValue",
                                title: "${message(code:'symbol.totalTradeValue.label')}",
                                template: "#=formatNumericField(data, 'totalTradeValue')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "150px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeVolume",
                                title: "${message(code:'symbol.totalTradeVolume.label')}",
                                template: "#=formatNumericField(data, 'totalTradeVolume')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "150px"
//                                format: '{0:n0}'
                            },
                            {
                                field: "totalTradeCount",
                                title: "${message(code:'symbol.totalTradeCount.label')}",
                                template: "#=formatNumericField(data, 'totalTradeCount')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "100px"
//                                format: '{0:n0}'
                            },

                            <g:each in="${indicatorColumns}" var="indicatorColumn">
                            {
                                field: "${indicatorColumn.key.replace(',', '_')}",
                                title: "${indicatorColumn.value}",
                                template: "#=formatNumericFieldIndicator(data, '${indicatorColumn.key.replace(',', '_')}')#",
                                attributes: {style: "text-align: center"},
                                headerAttributes: {style: "text-align: center"},
                                width: "120px"
//                                format: '{0:n0}'
                            },
                            </g:each>
                        ]
                    });
                    $("#grid").kendoTooltip({
                        filter: ".k-grid-content-locked td", //this filter selects the first column cells
                        position: "left",
                        content: function (e) {
                            var content = '<div class="screener-tooltip-content">' + $(e.target.closest("tr").find('td')[${Environment.isDevelopmentMode() ? 1 : 0}]).text() + '</div>';
                            return content;
                        }
                    }).data("kendoTooltip");
                }

                function startTimer() {
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
                            $('.flashGreenCell').each(function () {
                                var item = $(this).parent();
                                item.css('background-color', '#a4c400');
                                item.animate({backgroundColor: jQuery.Color("#a4c400").transition("transparent", 1)}, 4000);
                            });
                            $('.flashRedCell').each(function () {
                                var item = $(this).parent();
                                item.css('background-color', '#fa6800');
                                item.animate({backgroundColor: jQuery.Color("#fa6800").transition("transparent", 1)}, 4000);
                            });
                        }
                    });
                }

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