<%@ page import="java.text.SimpleDateFormat" %>
<script id="toolbartemplate" type="text/x-kendo-template">

<ul id="gridtoolbar">
    <g:if test="${propertyTypes.find {
        ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
    }}">
        <li><g:message code="portfolioAction.actionType.d"/>
            <ul>
                <g:each in="${propertyTypes.findAll {
                    ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
                }}">
                    <li onclick="addNewItem('${it.clazz}', 'd', ${it.modifiable})">${it.title}</li>
                </g:each>
            </ul>
        </li>
        <li><g:message code="portfolioAction.actionType.w"/>
            <ul>
                <g:each in="${propertyTypes.findAll {
                    ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
                }}">
                    <li onclick="addNewItem('${it.clazz}', 'w', ${it.modifiable})">${it.title}</li>
                </g:each>
            </ul>
        </li>
    </g:if>
    <g:if test="${propertyTypes.find {
        !['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
    }}">
        <li><g:message code="portfolioAction.actionType.b"/>
            <ul>
                <g:each in="${propertyTypes.findAll {
                    !['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
                }}">
                    <li onclick="addNewItem('${it.clazz}', 'b', ${it.modifiable})">${it.title}</li>
                </g:each>
            </ul>
        </li>
        <li><g:message code="portfolioAction.actionType.s"/>
            <ul>
                <g:each in="${propertyTypes.findAll {
                    !['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'].contains(it.clazz)
                }}">
                    <li onclick="addNewItem('${it.clazz}', 's', ${it.modifiable})">${it.title}</li>
                </g:each>
            </ul>
        </li>
    </g:if>
</ul>
</script>

<div id="confirmWindow"></div>
<script type="text/x-kendo-template" id="confirmWindowTemplate">
<p><g:message code="property.delete.confirm.message"/></p>
<button class="k-button" id="yesButton"><g:message code="confirm.dialog.ok"/></button>
<button class="k-button" id="noButton"><g:message code="confirm.dialog.cancel"/></button>
</script>
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
        pageSize: 10,
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
                    actionDateNumber: {
                        type: "number"
                    },
                    sharePrice: {type: "number", defaultValue: null},
                    shareCount: {type: "number", defaultValue: null},
                    broker: {
                        defaultValue: {brokerId: 0, brokerName: ""}
                    },
                    discount: {type: "number", defaultValue: null},
                    wage: {type: "number", defaultValue: null},
                    transactionSource: {type: "string", defaultValue: ""}
                }
            },
            data: "data",
            total: "total"
        }
    });
    var confirmWindow = $("#confirmWindow").kendoWindow({
        title: "<g:message code="property.delete.confirm" />",
        visible: false, //the window will not appear before its .open method is called
        width: "500px",
        height: "80px",
        actions: false,
    }).data("kendoWindow");
    var confirmWindowTemplate = kendo.template($("#confirmWindowTemplate").html());

    $(document).ready(function () {

        $("#grid").kendoGrid({
            dataSource: dataSource,
            navigatable: true,
            pageable: true,
//            height: 550,
            filterable: {
                extra: false,
                operators: {
                    string: {
                        eq: "${message(code:'stocks.filters.operators.equal')}",
                        neq: "${message(code:'stocks.filters.operators.not-equal')}",
                        contains: "${message(code:'stocks.filters.operators.contains')}"
                    },
                    date: {
                        gte: "${message(code:'stocks.filters.operators.greater-than-or-equal')}",
                        lte: "${message(code:'stocks.filters.operators.less-than-or-equal')}"
                    },
                    number: {
                        gte: "${message(code:'stocks.filters.operators.greater-than-or-equal')}",
                        lte: "${message(code:'stocks.filters.operators.less-than-or-equal')}"
                    }
                }
            },
            toolbar: [
                {template: kendo.template($("#toolbartemplate").html())}

                %{--{name: "create", text: "<g:message code="create"/>"},--}%
                %{--{name: "save", text: "<g:message code="save"/>"},--}%
                %{--{name: "cancel", text: "<g:message code="cancel"/>"}--}%
            ],
            columns: [
                {
                    field: "itemType.clazz",
                    title: "${message(code: 'portfolioItem.type.label')}",
                    editor: itemTypeDropDownEditor,
                    template: "#=itemType.title#",
//                    width: "150px",
                    type: 'string',
                    filterable: {
                        ui: itemTypeDropDownFilter,
                        operators: {
                            string: {
                                eq: "${message(code:'stocks.filters.operators.equal')}",
                                neq: "${message(code:'stocks.filters.operators.not-equal')}",
                            }
                        }
                    }
                },
                {
                    field: "property.propertyTitle",
                    title: "${message(code: 'portfolioItem.property.label')}",
                    editor: propertyDropDownEditor, template: "#=property.propertyTitle#",
                    type: 'string',
                    filterable: {
                        operators: {
                            string: {
                                contains: "${message(code:'stocks.filters.operators.contains')}"
                            }
                        }
                    },
//                    width: "200px"
                },
                {
                    field: "actionType.actionTypeId",
                    title: "${message(code: 'portfolioAction.actionType.label')}",
                    editor: actionTypeDropDownEditor,
                    type: 'string',
                    filterable: {
                        ui: actionTypeDropDownFilter,
                        operators: {
                            string: {
                                eq: "${message(code:'stocks.filters.operators.equal')}",
                                neq: "${message(code:'stocks.filters.operators.not-equal')}"
                            }
                        }
                    },
                    template: "#=actionType.actionTypeTitle#"
                },
                {
                    field: "actionDateNumber",
                    title: "${message(code: 'portfolioAction.actionDate.label')}",
                    editor: dateEditor,// template: "#=actionDate#",
                    filterable: {
                        ui: dateFilter,
                        extra: true,
                        operators: {
                            number: {
                                gte: "${message(code:'stocks.filters.operators.greater-than-or-equal')}",
                                lte: "${message(code:'stocks.filters.operators.less-than-or-equal')}"
                            }
                        }
                    },
                    template: "#=getJalaliDate(new Date(actionDateNumber))#"
                },
                {
                    field: "sharePrice",
                    title: "${message(code: 'portfolioAction.sharePrice.label')}",
                    editor: priceEditor,
                    filterable: {
                        extra: true,
                    },
                    template: "#=sharePrice ? formatNumber(sharePrice) : ''#"
                },
                {
                    field: "shareCount",
                    title: "${message(code: 'portfolioAction.shareCount.label')}",
                    editor: countEditor,
                    filterable: {
                        extra: true,
                    },
                    template: "#=shareCount ? formatNumber(shareCount) : ''#"
                },
                <g:if test="${portfolio.useBroker}">
                {
                    field: "broker.brokerName",
                    title: "${message(code: 'portfolioAction.broker.label')}",
                    editor: brokerEditor,
                    filterable:{
                        ui: brokerDropDownFilter,
                        operators: {
                            string: {
                                eq: "${message(code:'stocks.filters.operators.equal')}",
                                neq: "${message(code:'stocks.filters.operators.not-equal')}",
                            }
                        }
                    },
                    template: "#=broker.brokerName ? broker.brokerName : ''#"
                },
                </g:if>
                <g:if test="${portfolio.useWageAndDiscount}">
                {
                    field: "discount",
                    title: "${message(code: 'portfolioAction.discount.label')}",
                    editor: discountEditor,
                    filterable:false,
                    template: "#=discount?formatNumber(discount * 100) + ' %':''#"
                },
                {
                    field: "wage",
                    title: "${message(code: 'portfolioAction.wage.label')}",
                    editor: wageEditor,
                    filterable:false,
                    template: "#=wage?formatNumber(wage * 100) + ' %':''#"
                },
                </g:if>
                {
                    field: "transactionSource",
                    title: "${message(code: 'portfolioAction.transactionSourceTarget.label')}",
                    filterable: {
                        extra: true,
                    }
                },
                {
                    command: [
                        {text: "<g:message code="delete"/>", click: deletePortfolioItem},
                        {text: "<g:message code="edit"/>", click: editPortfolioItem},
                    ],
                    title: "&nbsp;",
                    width: "160px"
                }
            ],
            %{--<g:if test="${portfolio.fullAccounting}">--}%
//            detailInit: detailInit,
            %{--</g:if>--}%
//            editable: "incell",
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
            },
            filterMenuInit: filterMenuInit
        });
        $('#gridtoolbar').kendoMenu();
    });

    function createProperty(container, options) {
        $('<div id="propertyWindow"/>')
                .data('caller', $(this))
                .appendTo(container)
                .addClass('k-rtl')
                .kendoWindow({
                    width: '500px',
                    title: false,
                    content: '${createLink(action: 'propertyForm')}?clazz=' + options.model.itemType.clazz+'&portfolioId=${params.id}',
                    modal: true,
                    close: function (e) {
                        $(container).find('input[type!=text].propertyComboBox').data('kendoComboBox').dataSource.read();
                    }
                }).data('kendoWindow').open();
    }
    function deletePortfolioItem(e) {
        e.preventDefault();

        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        confirmWindow.content(confirmWindowTemplate(dataItem)); //send the row data object to the template and render it
        confirmWindow.open().center();
        $("#yesButton").click(function () {
            $.ajax({url:'${createLink(action: 'portfolioActionDelete',controller: 'portfolioAction')}',data:{models:JSON.stringify([dataItem])},type:'post',success:function(data){
                if(data.error)
                    alert(data.error);
                else
                    dataSource.read();
                confirmWindow.close();
            }});

        });
        $("#noButton").click(function () {
            confirmWindow.close();
        })
    }
    function editPortfolioItem(e) {
        e.preventDefault();

        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        editItem(dataItem)
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
                    content: '${createLink(action: 'propertyForm')}/' + combo.value() + '?clazz=' + options.model.itemType.clazz+'&portfolioId=${params.id}',
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
                    clazz: clazzForDelete,
                portfolioId:${params.id}
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