<%@ page import="java.text.SimpleDateFormat; stocks.tse.Symbol" contentType="text/html;charset=UTF-8" %>
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
                                url: "${createLink(controller: 'portfolioAction', action: 'jsonPortfolioActions', id: params.id)}",
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
                        schema: {
                            model: {
                                id: "id",
                                fields: {
                                    id: {type: "number"},
                                    symbol: {
                                        defaultValue: {symbolId: "", symbolTitle: ""}
                                    },
                                    actionType: {
                                        defaultValue: {
                                            actionTypeId: "b",
                                            actionTypeTitle: "<g:message code="portfolioAction.actionType.b"/>"
                                        }
                                    },
                                    actionDate: {type: "string", defaultValue: "${ new java.text.SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'").format(new Date()) }"},
                                    sharePrice: {type: "number", defaultValue: 0},
                                    shareCount: {type: "number", defaultValue: 0}
                                }
                            },
                            data: "data",
                            total: "total"
                        }
                    });

                    $("#grid").kendoGrid({
                        dataSource: dataSource,
                        navigatable: true,
                        pageable: true,
                        height: 550,
                        toolbar: [
                            { name: "create", text: "<g:message code="create"/>" },
                            { name: "save", text: "<g:message code="save"/>" },
                            { name: "cancel", text: "<g:message code="cancel"/>" }
                        ],
                        columns: [
                            {
                                field: "symbol",
                                title: "${message(code:'symbol.label')}",
                                editor: symbolDropDownEditor, template: "#=symbol.symbolTitle#"
                            },
                            {
                                field: "actionType",
                                title: "${message(code:'portfolioAction.actionType.label')}",
                                editor: actionTypeDropDownEditor, template: "#=actionType.actionTypeTitle#"
                            },
                            {
                                field: "actionDate",
                                title: "${message(code:'portfolioAction.actionDate.label')}",
                                editor: dateEditor,// template: "#=actionDate#",
                                format: "yyyy/MM/dd"
                            },
                            {
                                field: "sharePrice",
                                title: "${message(code:'portfolioAction.sharePrice.label')}"
                            },
                            {
                                field: "shareCount",
                                title: "${message(code:'portfolioAction.shareCount.label')}"
                            },
                            {command: [ { name: "destroy", text: "<g:message code="delete"/>" } ], title: "&nbsp;", width: "175px"}
                        ],
                        editable: "incell",
                        save: function (e) {
                            var combobox = e.container.find('.symbolComboBox[data-role=combobox]');
                            if (combobox.length > 0) {
                                if (!combobox.data().kendoComboBox.dataItem()) {
                                    e.model.one('change', function (e) {
                                        this.set('symbol', {
                                            symbolId: prevSymbol.symbolId,
                                            symbolTitle: prevSymbol.symbolTitle
                                        });
                                        prevSymbol = {symbolId: "", symbolTitle: ""};
                                    })
                                }
                                //this.refresh();
                            }
                        }
                    });

                    function dateEditor(container, options) {
                        var grid = $("#grid").data("kendoGrid");
                        $('<input class="datePicker" required />')
                                .appendTo(container)
                                .kendoDatePicker({
                                    value: getJalaliDate(grid.dataItem(container.parent()).get("actionDate")),
                                    change: function(e){
                                        var grid = $("#grid").data("kendoGrid");
                                        var selectedItem = grid.dataItem(this.element.parent().parent().parent().parent());
                                        selectedItem.set("actionDate", e.sender.value().gregoriandate);
                                    }
                                });
                    }

                    function actionTypeDropDownEditor(container, options) {
                        $('<input required data-text-field="actionTypeTitle" data-value-field="actionTypeId" data-bind="value:' + options.field + '"/>')
                                .appendTo(container)
                                .kendoDropDownList({
                                    autoBind: false,
                                    dataTextField: "actionTypeTitle",
                                    dataValueField: "actionTypeId",
                                    dataSource: [
                                        {
                                            actionTypeTitle: "<g:message code="portfolioAction.actionType.b"/>",
                                            actionTypeId: "b"
                                        },
                                        {
                                            actionTypeTitle: "<g:message code="portfolioAction.actionType.s"/>",
                                            actionTypeId: "s"
                                        }
                                    ]
                                });
                    }

                    var prevSymbol = {symbolId: "", symbolTitle: ""};

                    function symbolDropDownEditor(container, options) {
                        var currentSymbolId = options.model.symbol.symbolId;
                        if (currentSymbolId != "") {
                            prevSymbol.symbolId = currentSymbolId;
                            prevSymbol.symbolTitle = options.model.symbol.symbolTitle;
                            currentSymbolId = "/" + currentSymbolId;
                        }

                        $('<input required class="symbolComboBox" data-text-field="symbolTitle" data-value-field="symbolId" data-bind="value:' + options.field + '"/>')
                                .appendTo(container)
                                .kendoComboBox({
                                    dataTextField: "symbolTitle",
                                    dataValueField: "symbolId",
                                    filter: "contains",
                                    autoBind: false,
                                    minLength: 2,
                                    dataSource: {
                                        type: "json",
                                        serverFiltering: true,
                                        transport: {
                                            read: {
                                                url: "${createLink(controller: 'portfolioAction', action: 'symbols')}" + currentSymbolId
                                            }
                                        }
                                    }
                                });
                    }
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>