<%@ page import="grails.converters.JSON" %>
<script language="javascript" type="text/javascript">

    var prevProperty = {propertyId: "", propertyTitle: ""};

    var propertyTypes = <format:html value="${propertyTypes as JSON}"/>;
    var accountTypes = <format:html value="${accountTypes as JSON}"/>;

    function itemTypeDropDownEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        %{--$("<label/>")--}%
        %{--.html("${message(code:'select')} ${message(code:'portfolioItem.type.label')}")--}%
        %{--.appendTo(editContainer);--}%

        $('<div><input required class="itemTypeComboBox" data-text-field="title" data-value-field="clazz" data-bind = "value:' + options.field + '" / ></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoComboBox({
                    dataTextField: "title",
                    dataValueField: "clazz",
                    dataSource: propertyTypes,
                    placeholder: '${message(code: 'portfolioItem.type.select')}',
                    change: function () {
                        clearRow(container.parent());
                    }
                });
    }
    function itemTypeDropDownFilter(container) {
        container.kendoComboBox({
            dataTextField: "title",
            dataValueField: "clazz",
            dataSource: propertyTypes,
            placeholder: '${message(code: 'portfolioItem.type.select')}'
        });
    }
    function accountTypeDropDownEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        $('<div><input required class="itemTypeComboBox" data-text-field="title" data-value-field="clazz" data-bind = "value:' + options.field + '" / ></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoComboBox({
                    dataTextField: "title",
                    dataValueField: "clazz",
                    dataSource: accountTypes,
                    placeholder: '${message(code: 'portfolioItem.child.type.select')}'
                });
    }

    function propertyDropDownEditor(container, options) {
        var currentPropertyId = options.model.property.propertyId;
        if (currentPropertyId != "") {
            prevProperty.propertyId = currentPropertyId;
            prevProperty.propertyTitle = options.model.property.propertyTitle;
            currentPropertyId = "/" + currentPropertyId;
        }
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        %{--$("<label/>")--}%
        %{--.html("${message(code:'select')} " + options.model.itemType.title)--}%
        %{--.appendTo(editContainer);--}%

        var editor = $('<div><input required class="propertyComboBox" data-text-field="propertyTitle" data-value-field = "propertyId" data-bind = "value:' + options.field + '" / ></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoComboBox({
                    dataTextField: "propertyTitle",
                    dataValueField: "propertyId",
                    filter: "contains",
                    autoBind: false,
                    minLength: 2,
                    placeholder: '${message(code: 'portfolioItem.property.select')}',
                    dataSource: {
                        type: "json",
                        serverFiltering: true,
                        transport: {
                            read: {
                                url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}" + currentPropertyId
                            },
                            parameterMap: function (option, operation) {
                                var result = option;
                                result.clazz = options.model.itemType.clazz;
                                return result;
                            }
                        }
                    }
                });
        if (options.model.itemType.modifiable) {
            $('#propertyContextMenu').clone().appendTo($('body')).kendoContextMenu({
                target: container,
                alignToAnchor: true,
                open: function (e) {
                    var combo = $(container).find('input[type!=text].propertyComboBox').data('kendoComboBox');
                    if (!combo) {
                        e.preventDefault();
                        return false;
                    }
                    if (combo.value() && combo.value() != '') {
                        this.enable('[data-key=edit]', true);
                        this.enable('[data-key=delete]', true);
                    } else {
                        this.enable('[data-key=edit]', false);
                        this.enable('[data-key=delete]', false);
                    }
                },
                select: function (e) {
                    switch ($(e.item).attr('data-key')) {
                        case 'create':
                            createProperty(container, options);
                            break;
                        case 'edit':
                            editProperty(container, options);
                            break;
                        case 'delete':
                            deleteProperty(container, options);
                            break;
                    }
                }
            });
        }
    }

    function actionTypeDropDownEditor(container, options) {
        var actionList = [];
        if ($.inArray(options.model.itemType.clazz, ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem']) < 0) {
            actionList[actionList.length] = {
                actionTypeTitle: "<g:message code="portfolioAction.actionType.b"/>",
                actionTypeId: "b"
            };
            actionList[actionList.length] = {
                actionTypeTitle: "<g:message code="portfolioAction.actionType.s"/>",
                actionTypeId: "s"
            };
        }
        else {
            actionList[actionList.length] = {
                actionTypeTitle: "<g:message code="portfolioAction.actionType.d"/>",
                actionTypeId: "d"
            };
            actionList[actionList.length] = {
                actionTypeTitle: "<g:message code="portfolioAction.actionType.w"/>",
                actionTypeId: "w"
            };
        }

        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        %{--$("<label/>")--}%
        %{--.html("${message(code:'select')} ${message(code:'portfolioAction.actionType.label')}")--}%
        %{--.appendTo(editContainer);--}%

        $('<div><input required data-text-field="actionTypeTitle" data-value-field="actionTypeId" data-bind = "value:' + options.field + '" / ></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoComboBox({
                    autoBind: false,
                    dataTextField: "actionTypeTitle",
                    dataValueField: "actionTypeId",
                    placeholder: '${message(code: 'please-select')}',
                    dataSource: actionList
                });
    }
    function actionTypeDropDownFilter(container) {
        var actionList = [];
        actionList[actionList.length] = {
            actionTypeTitle: "<g:message code="portfolioAction.actionType.b"/>",
            actionTypeId: "b"
        };
        actionList[actionList.length] = {
            actionTypeTitle: "<g:message code="portfolioAction.actionType.s"/>",
            actionTypeId: "s"
        };
        actionList[actionList.length] = {
            actionTypeTitle: "<g:message code="portfolioAction.actionType.d"/>",
            actionTypeId: "d"
        };
        actionList[actionList.length] = {
            actionTypeTitle: "<g:message code="portfolioAction.actionType.w"/>",
            actionTypeId: "w"
        };
        container.kendoComboBox({
            autoBind: false,
            dataTextField: "actionTypeTitle",
            dataValueField: "actionTypeId",
            placeholder: '${message(code: 'please-select')}',
            dataSource: actionList
        });
    }

    function dateEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        %{--$("<label/>")--}%
        %{--.html("${message(code:'portfolioAction.actionDate.label')} " + options.model.actionType.actionTypeTitle)--}%
        %{--.appendTo(editContainer);--}%

        var grid = $("#grid").data("kendoGrid");
        $('<div><input class="datePicker" required/></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoDatePicker({
//                    value: getJalaliDate(grid.dataItem(container.parent()).get("actionDate")),
//                    change: function (e) {
//                        var grid = $("#grid").data("kendoGrid");
//                        var selectedItem = grid.dataItem(this.element.parent().parent().parent().parent());
//                        selectedItem.set("actionDate", e.sender.value().gregoriandate);
//                    }
                });
    }
    function dateFilter(container) {
        container.hide()
        var type=container.prev()
        $('<div><input class="datePicker"/></div>')
                .insertAfter(container)
                .find('input')
                .kendoDatePicker({
                    change: function (e) {
                        applyFilter('actionDateNumber',e.sender.value().gregoriandate.getTime(),type.val())
                    }
                });

    }
    function applyFilter(filterField, filterValue,filterType) {
        var currFilterObj = dataSource.filter();
        var currentFilters = currFilterObj ? currFilterObj.filters : [];
        if (currentFilters && currentFilters.length > 0) {
            for (var i = 0; i < currentFilters.length; i++) {
                if (currentFilters[i].field == filterField && currentFilters[i].operator==filterType) {
                    currentFilters.splice(i, 1);
                    break;
                }
            }
        }
        if (filterValue) {
            currentFilters.push({
                field: filterField,
                operator: filterType,
                value: filterValue
            });
        }
        dataSource.filter({
            logic: "and",
            filters: currentFilters
        });

    }

    function priceEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        %{--if (['b', 's'].indexOf(options.model.actionType.actionTypeId) >= 0)--}%
        %{--$("<label/>")--}%
        %{--.html("${message(code:'portfolioAction.sharePrice.tradeLabel')} " + options.model.actionType.actionTypeTitle)--}%
        %{--.appendTo(editContainer);--}%
        %{--else--}%
        %{--$("<label/>")--}%
        %{--.html("${message(code:'portfolioAction.sharePrice.transferLabel')} " + options.model.actionType.actionTypeTitle)--}%
        %{--.appendTo(editContainer);--}%

        $('<div><input required data-bind="value:' + options.field + '"/></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoNumericTextBox({
                    format: 'n0',
                    step: 1000
                });
    }

    function countEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);
        %{--$("<label/>")--}%
        %{--.html("${message(code:'portfolioAction.shareCount.label')} " + options.model.actionType.actionTypeTitle)--}%
        %{--.appendTo(editContainer);--}%
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(options.model.itemType.clazz) == -1)
            $('<div><input required data-bind="value:' + options.field + '"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n0',
                        min: 0,
                        step: 1
                    });
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    function discountEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(options.model.itemType.clazz) >= 0) {
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.discountWage.discountLabel')}")--}%
            %{--.appendTo(editContainer);--}%
            $('<div><input required data-bind="value:' + options.field + '"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n2',
                        min: 0,
                        max: 1,
                        step: 0.1
                    });
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.discountWage.wageLabel')}")--}%
            %{--.appendTo(editContainer);--}%
            %{--$('<div><input required data-bind="value:' + options.field + '.wageValue"/></div>')--}%
            %{--.appendTo(editContainer)--}%
            %{--.find('input')--}%
            %{--.width('100%')--}%
            %{--.kendoNumericTextBox({--}%
            %{--format: 'n2',--}%
            %{--min: 0,--}%
            %{--max: 1,--}%
            %{--step: 0.1--}%
            %{--});--}%
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    function wageEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(options.model.itemType.clazz) >= 0) {
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.discountWage.discountLabel')}")--}%
            %{--.appendTo(editContainer);--}%
            $('<div><input required data-bind="value:' + options.field + '"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n2',
                        min: 0,
                        max: 1,
                        step: 0.1
                    });
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.discountWage.wageLabel')}")--}%
            %{--.appendTo(editContainer);--}%
            %{--$('<div><input required data-bind="value:' + options.field + '.wageValue"/></div>')--}%
            %{--.appendTo(editContainer)--}%
            %{--.find('input')--}%
            %{--.width('100%')--}%
            %{--.kendoNumericTextBox({--}%
            %{--format: 'n2',--}%
            %{--min: 0,--}%
            %{--max: 1,--}%
            %{--step: 0.1--}%
            %{--});--}%
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    var brokers = <format:html value="${brokers as JSON}"/>;

    function brokerEditor(container, options) {
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(options.model.itemType.clazz) >= 0) {
            var editContainer = $("<div/>")
                    .addClass('portfolioFieldEditorContainer')
                    .appendTo(container);
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.broker.name')}")--}%
            %{--.appendTo(editContainer);--}%
            $('<div><input required data-text-field="brokerName" data-value-field="brokerId" data-bind = "value:' + options.field + '" / ></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoComboBox({
                        autoBind: false,
                        dataTextField: "brokerName",
                        dataValueField: "brokerId",
                        placeholder: "${message(code:'please-select')}",
                        dataSource: brokers
                    });
            %{--$("<label/>")--}%
            %{--.html("${message(code:'portfolioAction.brokerPortion.value')}")--}%
            %{--.appendTo(editContainer);--}%
            %{--$('<div><input data-bind="value:' + options.field + '.brokerValue"/></div>')--}%
            %{--.appendTo(editContainer)--}%
            %{--.find('input')--}%
            %{--.width('100%')--}%
            %{--.kendoNumericTextBox({--}%
            %{--format: 'n0',--}%
            %{--min: 0,--}%
            %{--step: 10000--}%
            %{--});--}%
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }
    function brokerDropDownFilter(container) {
        container.kendoComboBox({
            autoBind: false,
            dataTextField: "brokerName",
            dataValueField: "brokerName",
            placeholder: "${message(code:'please-select')}",
            dataSource: brokers
        });
    }
</script>