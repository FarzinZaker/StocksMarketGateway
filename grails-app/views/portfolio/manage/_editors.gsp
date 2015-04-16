<%@ page import="grails.converters.JSON" %>
<script language="javascript" type="text/javascript">

    var prevProperty = {propertyId: "", propertyTitle: ""};

    var propertyTypes = <format:html value="${propertyTypes as JSON}"/>;

    function itemTypeDropDownEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        $("<label/>")
                .html("${message(code:'select')} ${message(code:'portfolioItem.type.label')}")
                .appendTo(editContainer);

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

        $("<label/>")
                .html("${message(code:'select')} " + options.model.itemType.title)
                .appendTo(editContainer);

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
            $('#propertyContextMenu').kendoContextMenu({
                target: '#grid_active_cell',
                alignToAnchor: true,
                open: function (e) {
                    var combo = $('#grid_active_cell').find('input[type!=text].propertyComboBox').data('kendoComboBox');
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
        if ($.inArray(options.model.itemType.clazz, ['portfolioBankItem', 'portfolioBusinessPartnerItem'])) {
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
            actionList[actionList.length] = {
                actionTypeTitle: "<g:message code="portfolioAction.actionType.t"/>",
                actionTypeId: "t"
            };
        }

        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        $("<label/>")
                .html("${message(code:'select')} ${message(code:'portfolioAction.actionType.label')}")
                .appendTo(editContainer);

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

    function dateEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        $("<label/>")
                .html("${message(code:'portfolioAction.actionDate.label')} " + options.model.actionType.actionTypeTitle)
                .appendTo(editContainer);

        var grid = $("#grid").data("kendoGrid");
        $('<div><input class="datePicker" required/></div>')
                .appendTo(editContainer)
                .find('input')
                .width('100%')
                .kendoDatePicker({
                    value: getJalaliDate(grid.dataItem(container.parent()).get("actionDate")),
                    change: function (e) {
                        var grid = $("#grid").data("kendoGrid");
                        var selectedItem = grid.dataItem(this.element.parent().parent().parent().parent());
                        selectedItem.set("actionDate", e.sender.value().gregoriandate);
                    }
                });
    }

    function priceEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);

        if (['b', 's'].indexOf(options.model.actionType.actionTypeId) >= 0)
            $("<label/>")
                    .html("${message(code:'portfolioAction.sharePrice.tradeLabel')} " + options.model.actionType.actionTypeTitle)
                    .appendTo(editContainer);
        else
            $("<label/>")
                    .html("${message(code:'portfolioAction.sharePrice.transferLabel')} " + options.model.actionType.actionTypeTitle)
                    .appendTo(editContainer);

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
        $("<label/>")
                .html("${message(code:'portfolioAction.shareCount.label')} " + options.model.actionType.actionTypeTitle)
                .appendTo(editContainer);
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioimmovableItem'].indexOf(options.model.itemType.clazz) == -1)
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

    function discountWageEditor(container, options) {
        var editContainer = $("<div/>")
                .addClass('portfolioFieldEditorContainer')
                .appendTo(container);
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(options.model.itemType.clazz) >= 0) {
            $("<label/>")
                    .html("${message(code:'portfolioAction.discountWage.discountLabel')}")
                    .appendTo(editContainer);
            $('<div><input required data-bind="value:' + options.field + '.discountValue"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n2',
                        min: 0,
                        max: 1,
                        step: 0.1
                    });
            $("<label/>")
                    .html("${message(code:'portfolioAction.discountWage.wageLabel')}")
                    .appendTo(editContainer);
            $('<div><input required data-bind="value:' + options.field + '.wageValue"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n2',
                        min: 0,
                        max: 1,
                        step: 0.1
                    });
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    var brokers = <format:html value="${brokers as JSON}"/>;

    function brokerPortionEditor(container, options) {
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(options.model.itemType.clazz) >= 0) {
            var editContainer = $("<div/>")
                    .addClass('portfolioFieldEditorContainer')
                    .appendTo(container);
            $("<label/>")
                    .html("${message(code:'portfolioAction.brokerPortion.name')}")
                    .appendTo(editContainer);
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
            $("<label/>")
                    .html("${message(code:'portfolioAction.brokerPortion.value')}")
                    .appendTo(editContainer);
            $('<div><input data-bind="value:' + options.field + '.brokerValue"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n0',
                        min: 0,
                        step: 10000
                    });
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    function bankPortionEditor(container, options) {
        if (['d', 'w'].indexOf(options.model.actionType.actionTypeId) == -1) {
            var editContainer = $("<div/>")
                    .addClass('portfolioFieldEditorContainer')
                    .appendTo(container);
            $("<label/>")
                    .html("${message(code:'portfolioAction.bankPortion.name')}")
                    .appendTo(editContainer);
            $('<div><input required data-text-field="propertyTitle" data-value-field="propertyId" data-bind = "value:' + options.field + '" / ></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoComboBox({
                        autoBind: false,
                        dataTextField: "propertyTitle",
                        dataValueField: "propertyId",
                        placeholder: "${message(code:'please-select')}",
                        dataSource: {
                            type: "json",
                            serverFiltering: true,
                            transport: {
                                read: {
                                    url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}?clazz=portfolioBankItem"
                                }
                            }
                        }
                    });
            $("<label/>")
                    .html(options.model.actionType.actionTypeId == 'b' ? "${message(code:'portfolioAction.bankPortion.withdrawValue')}" : "${message(code:'portfolioAction.bankPortion.depositValue')}")
                    .appendTo(editContainer);
            $('<div><input data-bind="value:' + options.field + '.propertyValue"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n0',
                        min: 0,
                        step: 10000
                    });
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }

    function businessPartnerPortionEditor(container, options) {
        if (['d', 'w'].indexOf(options.model.actionType.actionTypeId) == -1) {
            var editContainer = $("<div/>")
                    .addClass('portfolioFieldEditorContainer')
                    .appendTo(container);
            $("<label/>")
                    .html("${message(code:'portfolioAction.businessPartnerPortion.name')}")
                    .appendTo(editContainer);
            $('<div><input required data-text-field="propertyTitle" data-value-field="propertyId" data-bind = "value:' + options.field + '" / ></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoComboBox({
                        autoBind: false,
                        dataTextField: "propertyTitle",
                        dataValueField: "propertyId",
                        placeholder: "${message(code:'please-select')}",
                        dataSource: {
                            type: "json",
                            serverFiltering: true,
                            transport: {
                                read: {
                                    url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}?clazz=portfolioBusinessPartnerItem"
                                }
                            }
                        }
                    });
            $("<label/>")
                    .html(options.model.actionType.actionTypeId == 'b' ? "${message(code:'portfolioAction.businessPartnerPortion.withdrawValue')}" : "${message(code:'portfolioAction.businessPartnerPortion.depositValue')}")
                    .appendTo(editContainer);
            $('<div><input data-bind="value:' + options.field + '.propertyValue"/></div>')
                    .appendTo(editContainer)
                    .find('input')
                    .width('100%')
                    .kendoNumericTextBox({
                        format: 'n0',
                        min: 0,
                        step: 10000
                    });
        }
        else
            $('#grid').data('kendoGrid').closeCell(container);
    }
</script>