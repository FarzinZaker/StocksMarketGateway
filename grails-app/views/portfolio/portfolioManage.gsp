<%@ page import="grails.converters.JSON; java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.manage"/></title>
    <asset:stylesheet src="portfolioManagement.less"/>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.manage"/></h1>

            <div class="k-rtl k-header">
                <div id="toolbar"></div>
            </div>

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="hidden">
                <ul id="propertyContextMenu">
                    <li data-key="create"><i class="fa fa-icon fa-plus"></i> <g:message code="create"/></li>
                    <li data-key="separator" class="k-separator"></li>
                    <li data-key="edit"><i class="fa fa-icon fa-edit"></i> <g:message code="edit"/></li>
                    <li data-key="delete"><i class="fa fa-icon fa-remove"></i> <g:message code="delete"/></li>
                </ul>
            </div>

            <g:render template="manage/editors"/>

            <script>

                useOldConfirm = false;

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
                                        defaultValue: "${ new java.text.SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'").format(new Date()) }"
                                    },
                                    sharePrice: {type: "number", defaultValue: 0},
                                    shareCount: {type: "number", defaultValue: 0},
                                    discountWage: {
                                        defaultValue: {discountValue: 0, wageValue: 0}
                                    },
                                    brokerPortion: {
                                        defaultValue: {brokerId: "", brokerName: "", brokerValue: null}
                                    },
                                    bankPortion: {
                                        defaultValue: {propertyId: "", propertyTitle: "", propertyValue: null}
                                    },
                                    businessPartnerPortion: {
                                        defaultValue: {propertyId: "", propertyTitle: "", propertyValue: null}
                                    }
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
                            {name: "create", text: "<g:message code="create"/>"},
                            {name: "save", text: "<g:message code="save"/>"},
                            {name: "cancel", text: "<g:message code="cancel"/>"}
                        ],
                        columns: [
                            {
                                field: "itemType",
                                title: "${message(code:'portfolioItem.type.label')}",
                                editor: itemTypeDropDownEditor,
                                template: "#=itemType.title#",
                                width: "200px"
                            },
                            {
                                field: "property",
                                title: "${message(code:'portfolioItem.property.label')}",
                                editor: propertyDropDownEditor, template: "#=property.propertyTitle#",
                                width: "300px"
                            },
                            {
                                field: "actionType",
                                title: "${message(code:'portfolioAction.actionType.label')}",
                                editor: actionTypeDropDownEditor,
                                template: "#=actionType.actionTypeTitle#"
                            },
                            {
                                field: "actionDate",
                                title: "${message(code:'portfolioAction.actionDate.label')}",
                                editor: dateEditor,// template: "#=actionDate#",
                                format: "yyyy/MM/dd"
                            },
                            {
                                field: "sharePrice",
                                title: "${message(code:'portfolioAction.sharePrice.label')}",
                                editor: priceEditor,
                                template: "#=formatNumber(sharePrice)#"
                            },
                            {
                                field: "shareCount",
                                title: "${message(code:'portfolioAction.shareCount.label')}",
                                editor: countEditor,
                                template: "#=formatNumber(shareCount)#"
                            },
                            {
                                field: "discountWage",
                                title: "${message(code:'portfolioAction.discountWage.label')}",
                                editor: discountWageEditor,
                                template: "#=discountWage.discountValue ? '${message(code:'portfolioAction.discountWage.discountLabel')}: ' + (discountWage.discountValue * 100) + ' %' : ''#"
                                + "#=discountWage.discountValue && discountWage.wageValue ? ' , ' : ''#"
                                + "#=discountWage.wageValue ? '${message(code:'portfolioAction.discountWage.wageLabel')}: ' + (discountWage.wageValue * 100) + ' %' : ''#"
                            },
                            {
                                field: "brokerPortion",
                                title: "${message(code:'portfolioAction.brokerPortion.label')}",
                                editor: brokerPortionEditor,
                                template: "#=brokerPortion.brokerName + (brokerPortion.brokerValue ? ': ' +formatNumber(brokerPortion.brokerValue) : '')#"
                            },
                            {
                                field: "bankPortion",
                                title: "${message(code:'portfolioAction.bankPortion.label')}",
                                editor: bankPortionEditor,
                                template: "#=(bankPortion.propertyTitle ? bankPortion.propertyTitle : '') + (bankPortion.propertyValue ? ': ' +formatNumber(bankPortion.propertyValue) : '')#"
                            },
                            {
                                field: "businessPartnerPortion",
                                title: "${message(code:'portfolioAction.businessPartnerPortion.label')}",
                                editor: businessPartnerPortionEditor,
                                template: "#=(businessPartnerPortion.propertyTitle ? businessPartnerPortion.propertyTitle : '') + (businessPartnerPortion.propertyValue ? ': ' +formatNumber(businessPartnerPortion.propertyValue) : '')#"
                            },
                            {
                                command: [{name: "destroy", text: "<g:message code="delete"/>"}],
                                title: "&nbsp;",
                                width: "95px"
                            }
                        ],
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

                    $("#toolbar").kendoToolBar({
                        items: [
                            {type: "button", text: "<g:message code="portfolio.list.title"/>"},
                            {type: "button", text: "<g:message code="portfolio.property.manage"/>"}
                        ]
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
                                    $('#grid_active_cell').find('input[type!=text].propertyComboBox').data('kendoComboBox').dataSource.read();
                                }
                            }).data('kendoWindow').open();
                }

                function editProperty(container, options) {

                    var combo = $('#grid_active_cell').find('input[type!=text].propertyComboBox').data('kendoComboBox');
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
                }

                var idForDelete = 0;
                var clazzForDelete = '';
                function deleteProperty(container, options) {
                    var combo = $('#grid_active_cell').find('input[type!=text].propertyComboBox').data('kendoComboBox');
                    if (!combo || !combo.value() || combo.value() == '')
                        return;

                    if (idForDelete == 0) {
                        idForDelete = parseInt(combo.value());
                        clazzForDelete = options.model.itemType.clazz;
                        if (confirm('${message(code:'property.delete.confirm')}'))
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
                                window.alert('${message(code:'property.delete.error')}');
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
        </div>
    </div>
</div>
</body>
</html>