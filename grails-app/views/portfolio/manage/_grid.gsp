<%@ page import="java.text.SimpleDateFormat" %>
<script language="javascript" type="text/javascript">

    var idSequence = -1;
    var dataSource = new kendo.data.DataSource({
        transport: {
            type: 'odata',
            read: {
                url: "${createLink(controller: 'portfolioAction', action: 'jsonPortfolioActions', id: params.id)}",
                dataType: "json",
                type: "POST"
            },
            update: {
                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionSave', id: params.id)}",
                dataType: "json",
                type: "POST"
            },
            destroy: {
                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionDelete', id: params.id)}",
                dataType: "json",
                type: "POST"
            },
            create: {
                url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionSave', id: params.id)}",
                dataType: "json",
                type: "POST"
            },
            parameterMap: function (options, operation) {
                if (operation !== "read" && options.models) {
                    var models = options.models;
                    if (operation !== "destroy") {
                        models = $('#grid').data('kendoGrid').dataSource._data;
                        if (operation === 'create')
                            models = $.grep(models, function (dataItem, index) {
                                return dataItem.id == 0
                            });
                        else if (operation === 'update')
                            models = $.grep(models, function (dataItem, index) {
                                return dataItem.dirty && dataItem.id > 0
                            });
                        $.each(models, function (index, dataItem) {
                            if (detailDataSources[dataItem.uid])
                                dataItem.set('childActions', detailDataSources[dataItem.uid]._data);
                            else
                                dataItem.set('childActions', []);
                        });
                    }
                    return {models: kendo.stringify(models)};
                }
            }
        },
        batch: true,
        pageSize: 20,
        schema: {
            model: {
                id: "id",
                fields: {
                    id: {
                        type: "number"
                    },
                    itemType: {
                        defaultValue: {clazz: "", title: ""}
                    },
                    property: {
                        defaultValue: {propertyId: "", propertyTitle: ""}
                    },
                    actionType: {
                        defaultValue: {actionTypeId: "", actionTypeTitle: ""}
                    },
                    actionDate: {
                        type: "string",
                        defaultValue: "${new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'").format(new Date())}"
                    },
                    sharePrice: {type: "number", defaultValue: null},
                    shareCount: {type: "number", defaultValue: null},
                    broker: {
                        defaultValue: {brokerId: "", brokerName: ""}
                    },
                    discount: {type: "number", defaultValue: null},
                    wage: {type: "number", defaultValue: null}
                }
            },
            data: "data",
            total: "total"
        }
    });

    $(document).ready(function () {

        $("#grid").kendoGrid({
            dataSource: dataSource,
            navigatable: true,
            pageable: true,
//            height: 550,
            toolbar: [
                {name: "create", text: "<g:message code="create"/>"},
                {name: "save", text: "<g:message code="save"/>"},
                {name: "cancel", text: "<g:message code="cancel"/>"}
            ],
            columns: [
                {
                    field: "itemType",
                    title: "${message(code: 'portfolioItem.type.label')}",
                    editor: itemTypeDropDownEditor,
                    template: "#=itemType.title#",
                    width: "200px"
                },
                {
                    field: "property",
                    title: "${message(code: 'portfolioItem.property.label')}",
                    editor: propertyDropDownEditor, template: "#=property.propertyTitle#",
                    width: "300px"
                },
                {
                    field: "actionType",
                    title: "${message(code: 'portfolioAction.actionType.label')}",
                    editor: actionTypeDropDownEditor,
                    template: "#=actionType.actionTypeTitle#"
                },
                {
                    field: "actionDate",
                    title: "${message(code: 'portfolioAction.actionDate.label')}",
                    editor: dateEditor,// template: "#=actionDate#",
                    format: "yyyy/MM/dd"
                },
                {
                    field: "sharePrice",
                    title: "${message(code: 'portfolioAction.sharePrice.label')}",
                    editor: priceEditor,
                    template: "#=sharePrice ? formatNumber(sharePrice) : ''#"
                },
                {
                    field: "shareCount",
                    title: "${message(code: 'portfolioAction.shareCount.label')}",
                    editor: countEditor,
                    template: "#=shareCount ? formatNumber(shareCount) : ''#"
                },
                {
                    field: "broker",
                    title: "${message(code: 'portfolioAction.broker.label')}",
                    editor: brokerEditor,
                    template: "#=broker.brokerName ? broker.brokerName : ''#"
                },
                {
                    field: "discount",
                    title: "${message(code: 'portfolioAction.discount.label')}",
                    editor: discountEditor,
                    template: "#=discount?formatNumber(discount * 100) + ' %':''#"
                },
                {
                    field: "wage",
                    title: "${message(code: 'portfolioAction.wage.label')}",
                    editor: wageEditor,
                    template: "#=wage?formatNumber(wage * 100) + ' %':''#"
                },
                {
                    command: [{name: "destroy", text: "<g:message code="delete"/>"}],
                    title: "&nbsp;",
                    width: "95px"
                }
            ],
            detailInit: detailInit,
            editable: "incell",
            save: function (e) {
                var combobox = e.container.find('.propertyComboBox[data-role=combobox]');
                if (combobox.length > 0) {
                    if (!combobox.data().kendoComboBox.dataItem()) {
                        e.model.one('change', function (e) {
                            this.set('property', {
                                propertyId: prevProperty.propertyId,
                                propertyTitle: prevProperty.propertyTitle
                            });
                            prevProperty = {propertyId: "", propertyTitle: ""};
                        })
                    }
                    //this.refresh();
                }
            }
        });
    });

    function createProperty(container, options) {
        $('<div id="propertyWindow"/>')
                .data('caller', $(this))
                .appendTo(container)
                .addClass('k-rtl')
                .kendoWindow({
                    width: '500px',
                    title: false,
                    content: '${createLink(action: 'propertyForm')}?clazz=' + options.model.itemType.clazz,
                    modal: true,
                    close: function (e) {
                        $(container).find('input[type!=text].propertyComboBox').data('kendoComboBox').dataSource.read();
                    }
                }).data('kendoWindow').open();
    }

    function editProperty(container, options) {

        var combo = $(container).find('input[type!=text].propertyComboBox').data('kendoComboBox');
        if (!combo || !combo.value() || combo.value() == '')
            return;
        $('<div id="propertyWindow"/>')
                .data('caller', $(this))
                .appendTo(container)
                .addClass('k-rtl')
                .kendoWindow({
                    width: '500px',
                    title: false,
                    content: '${createLink(action: 'propertyForm')}/' + combo.value() + '?clazz=' + options.model.itemType.clazz,
                    modal: true,
                    close: function (e) {
                    }
                }).data('kendoWindow').open();
    }

    function clearRow(row) {
        var data = $("#grid").data("kendoGrid").dataItem(row);
        data.set("property", {propertyId: "", propertyTitle: ""});
        data.set("actionType", {actionTypeId: "", actionTypeTitle: ""});
        data.set("broker", {brokerId: "", brokerName: ""});
        data.set("discount", null);
        data.set("wage", null);
        data.set("shareCount", null);
        data.set("sharePrice", null);
    }

    var idForDelete = 0;
    var clazzForDelete = '';
    function deleteProperty(container, options) {
        var combo = $(container).find('input[type!=text].propertyComboBox').data('kendoComboBox');
        if (!combo || !combo.value() || combo.value() == '')
            return;

        if (idForDelete == 0) {
            idForDelete = parseInt(combo.value());
            clazzForDelete = options.model.itemType.clazz;
            if (confirm('${message(code: 'property.delete.confirm')}'))
                doDelete();
            else
                cancelDelete();
        }
    }

    function cancelDelete() {
        idForDelete = 0;
        clazzForDelete = ''
    }

    function doDelete() {
        if (idForDelete > 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'deleteProperty')}',
                data: {
                    id: idForDelete,
                    clazz: clazzForDelete
                }
            }).done(function (response) {
                if (response == 1) {
                    $('#grid_active_cell').find('input[type!=text].propertyComboBox').data('kendoComboBox').dataSource.read();
                }
                else {
                    window.alert('${message(code: 'property.delete.error')}');
                }
            });
            idForDelete = 0;
        }
    }

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }

</script>