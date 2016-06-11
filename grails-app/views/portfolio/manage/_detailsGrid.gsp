<%@ page import="java.text.SimpleDateFormat" %>
<script language="javascript" type="text/javascript">
    var detailDataSources = {};
    function detailInit(e) {
        var parentId = e.data.uid;
        detailDataSources[parentId] = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: "${createLink(controller: 'portfolioAction', action: 'jsonPortfolioActions', id: params.id)}?parentActionId=" + e.data.id,
                    dataType: "json",
                    type: "POST"
                },
                update: {
                    url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionSave', id: params.id)}?parentActionId=" + e.data.id,
                    dataType: "json",
                    type: "POST"
                },
                destroy: {
                    url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionDelete', id: params.id)}",
                    dataType: "json",
                    type: "POST"
                },
                create: {
                    url: "${createLink(controller: 'portfolioAction', action: 'portfolioActionSave', id: params.id)}?parentActionId=" + e.data.id,
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
                        parentActionId: {type: "number"},
                        itemType: {
                            defaultValue: {clazz: "", title: ""}
                        },
                        property: {
                            defaultValue: {propertyId: "", propertyTitle: ""}
                        },
                        actionType: {
                            defaultValue: {actionTypeId: "", actionTypeTitle: ""}
                        },
                        sharePrice: {type: "number", defaultValue: 0}
                    }
                },
                data: "data",
                total: "total"
            }
            //filter: {field: "parentActionId", operator: "eq", value: e.data.id}
        });
        var toolbar = [];
            %{--{name: "create", text: "<g:message code="create"/>"}--}%
        %{--];--}%
        %{--if(e.data.id){--}%
            %{--toolbar[toolbar.length] = {name: "save", text: "<g:message code="save"/>"};--}%
            %{--toolbar[toolbar.length] = {name: "cancel", text: "<g:message code="cancel"/>"};--}%
        %{--}--}%
        $("<div/>").appendTo(e.detailCell).kendoGrid({
                    dataSource: detailDataSources[parentId],
                    navigatable: false,
                    pageable: false,
//                    height: 550,
//                    toolbar: toolbar,
                    columns: [
                        {
                            field: "itemType",
                            title: "${message(code: 'portfolioItem.child.type.label')}",
                            editor: accountTypeDropDownEditor,
                            template: "#=itemType.title#",
                            width: "200px"
                        },
                        {
                            field: "property",
                            title: "${message(code: 'portfolioItem.child.property.label')}",
                            editor: propertyDropDownEditor, template: "#=property.propertyTitle#",
                            width: "300px"
                        },
                        {
                            field: "actionType",
                            title: "${message(code: 'portfolioAction.child.actionType.label')}",
                            editor: actionTypeDropDownEditor,
                            template: "#=actionType.actionTypeTitle#"
                        },
                        {
                            field: "sharePrice",
                            title: "${message(code: 'portfolioAction.child.sharePrice.label')}",
                            editor: priceEditor,
                            template: "#=formatNumber(sharePrice)#"
                        },
                        %{--{--}%
                            %{--command: [{name: "destroy", text: "<g:message code="delete"/>"}],--}%
                            %{--title: "&nbsp;",--}%
                            %{--width: "95px"--}%
                        %{--}--}%
                    ],
                    editable: false,
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
                }
        )
        ;
    }
</script>