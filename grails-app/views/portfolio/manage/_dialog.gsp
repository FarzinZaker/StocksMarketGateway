<%@ page import="stocks.portfolio.meta.PortfolioAvailItem" %>
<div id="itemDialog" class="k-rtl">
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <form:field border="0" fieldName="portfolioItem.property" showHelp="0">
                    <div id="propertycontainer">
                        <input id="propertyId" class="propertyComboBox" style="width: 300px" required>
                        <form:button id="editItems" name="editItems" style="float:left"
                                     text="${message(code: 'portfolio.edit.items.title')}"/>
                    </div>
                </form:field>
            </div>

            <div class="col-xs-6">
                <form:field border="0" fieldName="portfolioAction.actionDate" showHelp="0">
                    <form:datePickerResources/>
                    <form:datePicker name="actionDate" style="width: 300px"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <form:field border="0" id="sharepricecontainer" fieldName="portfolioAction.sharePrice" showHelp="0">
                    <input id="sharePrice" style="width: 300px">
                </form:field>
            </div>

            <div class="col-xs-6">
                <form:field border="0" id="sharecountcontainer" fieldName="portfolioAction.shareCount" showHelp="0">
                    <input id="shareCount" style="width: 300px">
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <g:if test="${portfolio.useBroker}">
                    <form:field border="0" id="brokerconainer" fieldName="portfolioAction.broker" showHelp="0">
                        <input id="broker" style="width: 300px">
                    </form:field>
                </g:if>
            </div>

            <div class="col-xs-6">
                <g:if test="${portfolio.useWageAndDiscount}">
                    <form:field border="0" id="discountconainer" fieldName="portfolioAction.discount" showHelp="0">
                        <input id="discount" style="width: 300px">
                    </form:field>
                </g:if>
            </div>
        </div>
        <g:set var="availItems"
               value="${PortfolioAvailItem.findAllByPortfolioAndItemInList(portfolio, ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'])?.collect {
                   it.item
               }}"/>
        <g:if test="${availItems.size()}">
            <div class="row">
                <div class="col-xs-12">
                    <form:field border="0" showLabel="0" showHelp="0">
                        <form:checkbox name="isInitialDataEntry"
                                       text="${message(code: 'portfolioAction.isInitialDataEntry')}"
                                       onchange="toggleTransactionSource()"/>
                    </form:field>
                </div>
            </div>

            <div class="row" id="transactionSourceSelectArea">
                <div class="col-xs-6">
                    <form:field border="0" fieldName="portfolioAction.transactionSource" showHelp="0"
                                id="lblTransactionSource">
                        <form:select name="transactionSourceType"
                                     items="${availItems.collect { [text: message(code: "${it}.label"), value: it] }}"
                                     placeholder="${message(code: 'pleaseSelect')}"
                                     preSelect="false"
                                     style="width: 300px" onchange="transactionSourceChanged"/>
                    </form:field>
                </div>

                <div class="col-xs-6">
                    <form:field border="0" fieldName="portfolioAction.transactionSource.empty" showHelp="0">
                        <div id="transactionSourceSelectorContainer"></div>
                    </form:field>
                </div>
            </div>
            <script language="javascript" type="text/javascript">
                function toggleTransactionSource() {
                    $('#transactionSourceSelectArea').slideToggle();
                }

                function transactionSourceChanged(e, value) {
                    var editContainer = $('#transactionSourceSelectorContainer').html('');
                    $('<div><input required class="propertyComboBox" data-text-field="propertyTitle" data-value-field = "propertyId" id="transactionSource" /></div>')
                            .appendTo(editContainer)
                            .find('input')
                            .width('300px')
                            .kendoComboBox({
                                dataTextField: "propertyTitle",
                                dataValueField: "propertyId",
                                filter: "contains",
                                autoBind: true,
                                minLength: 2,
                                placeholder: '${message(code: 'portfolioItem.property.select')}',
                                dataSource: {
                                    type: "json",
                                    serverFiltering: true,
                                    transport: {
                                        read: {
                                            url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}?portfolioId=${portfolio.id}&availOnly=1"
                                        },
                                        parameterMap: function (option, operation) {
                                            var result = option;
                                            result.clazz = value;
                                            return result;
                                        }
                                    }
                                }
                            });
                }
            </script>
        </g:if>

        <div class="row">
            <div class="col-xs-12">
                <div class="toolbar" style="margin:0;padding-top:10px;">
                    <form:button name="submit" text="${message(code: 'portfolioItem.save')}"/>
                    <form:button name="cancel" text="${message(code: 'portfolioItem.cancel')}" style="float:left;"/>
                    <script language="javascript" type="text/javascript">
                        $('span[name=submit]').click(function () {
//                if ($('#portfolioForm').isValid()) {
                            var isInitialDataEntry = currenctAction == 'b' || currenctAction == 's' ? $('#isInitialDataEntry').is(':checked') : null;
                            var hasChildAction = !isInitialDataEntry && (currenctAction == 'b' || currenctAction == 's');
                            $.ajax({
                                url: '${createLink(controller: 'portfolioAction', action: 'portfolioActionSave', id: params.id)}',
                                type: 'post',
                                data: {
                                    models: JSON.stringify([{
                                        id: currentId,
                                        itemType: {clazz: currenctType},
                                        property: {propertyId: propertyId.data('kendoComboBox').value()},
                                        actionType: {actionTypeId: currenctAction},
                                        actionDate: $('#actionDate').val(),
                                        sharePrice: sharePrice.data('kendoNumericTextBox').value(),
                                        shareCount: shareCount.data('kendoNumericTextBox').value(),
                                        isInitialDataEntry: isInitialDataEntry,
                                        transactionSourceType: hasChildAction ? $('#transactionSourceType').data('kendoComboBox').value() : null,
                                        transactionSource: hasChildAction ? $('#transactionSource').data('kendoComboBox').value() : null,
                                        <g:if test="${portfolio.useBroker}">
                                        broker: {brokerId: broker.data("kendoComboBox").value()},
                                        </g:if>
                                        <g:if test="${portfolio.useWageAndDiscount}">
                                        discount: discount.data("kendoNumericTextBox").value()
                                        </g:if>
                                    }])
                                },
                                success: function (e) {
                                    if (e.error) {
                                        alert(e.error.join('\n'))
                                    }
                                    else {
                                        dataSource.read();
                                        itemDialog.kendoWindow("close");
                                    }
                                }
                            });
//                    dataSource.sync().done(function(data){
//                        console.log(data)
//                        dataSource.fetch();
//                        itemDialog.kendoWindow("close");
//                    }).fail(function(){
//                        dataSource.cancelChanges();
//                    });

//                }/
                        });
                        $('span[name=cancel]').click(function () {
                            $(this).closest("[data-role=window]").kendoWindow("close");
                        });
                        $('#editItems').click(function () {
                            propertyContextMenu.data('kendoContextMenu').open()
                        })
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    var propertyContextMenu = $('#propertyContextMenu').clone().appendTo($('body'));
    var itemDialog = $("#itemDialog");
    var propertyId = $("#propertyId");
    var actionDate = $("#actionDate");
    var sharePrice = $("#sharePrice");
    var shareCount = $("#shareCount");
    var currenctType = '';
    var currenctAction = '';
    var currentModifiable = false;
    var currentId = '';

    if (!itemDialog.data("kendoWindow")) {
        itemDialog.kendoWindow({
            width: "660px",
            title: '<g:message code="portfolio.form.label" />',
            modal: true,
            visible: false,
            actions: [
                "Close"
            ]
        });
    }

    propertyId.kendoComboBox({
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
                    url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}"// + currentPropertyId
                },
                parameterMap: function (option, operation) {
                    var result = option;
                    result.clazz = currenctType;
                    result.actionType = currenctAction;
                    result.id =${ params.id};
                    return result;
                }
            }
        }
    });
    propertyContextMenu.kendoContextMenu({
        target: $('#propertycontainer'),
        alignToAnchor: true,
        open: function (e) {
            var combo = $('#propertycontainer').find('input[type!=text].propertyComboBox').data('kendoComboBox');
            if (!combo || !currentModifiable) {
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
            var options = {
                model: {itemType: {clazz: currenctType}}
            };
            switch ($(e.item).attr('data-key')) {
                case 'create':
                    createProperty($('#propertycontainer'), options);
                    break;
                case 'edit':
                    editProperty($('#propertycontainer'), options);
                    break;
                case 'delete':
                    deleteProperty($('#propertycontainer'), options);
                    break;
            }
        }
    });
    sharePrice.kendoNumericTextBox({
        format: 'n0',
        step: 1000
    });
    shareCount.kendoNumericTextBox({
        format: 'n0',
        min: 0,
        step: 1
    });
    <g:if test="${portfolio.useBroker}">
    var broker = $("#broker");
    broker.kendoComboBox({
        autoBind: false,
        dataTextField: "brokerName",
        dataValueField: "brokerId",
        placeholder: "${message(code:'please-select')}",
        dataSource: brokers
    });
    </g:if>
    <g:if test="${portfolio.useWageAndDiscount}">
    var discount = $("#discount");
    discount.kendoNumericTextBox({
        format: 'n2',
        min: 0,
        max: 1,
        step: 0.1
    });
    </g:if>
    function editItem(dataItem) {
        itemDialog.data("kendoWindow").center().open();
        currentId = dataItem.id;
        currenctType = dataItem.itemType.clazz;
        currenctAction = dataItem.actionType.actionTypeId;
        if (currenctAction == 'b' || currenctAction == 's') {
            $('#lblTransactionSource').find('label').html(currenctAction == 'b' ? '${message(code:'portfolioAction.transactionSource.label')}' : '${message(code:'portfolioAction.transactionTarget.label')}');
            $('#isInitialDataEntry').prop('checked', dataItem.isInitialDataEntry).parent().parent().parent().parent().stop().show();
            if (dataItem.isInitialDataEntry) {
                $('#transactionSourceSelectArea').stop().hide();
                $('#transactionSourceType').data("kendoComboBox").value('');
                $('#transactionSourceSelectorContainer').html('');
            }
            else {
                $('#transactionSourceSelectArea').stop().show();
                $('#transactionSourceType').data("kendoComboBox").value(dataItem.transactionSourceType);
                transactionSourceChanged(null, $('#transactionSourceType').data("kendoComboBox").value());
                $('#transactionSource').data("kendoComboBox").value(dataItem.transactionSourceId);
            }
            if(currenctAction == 'b')
                $('#isInitialDataEntry').parent().parent().parent().parent().stop().show();
            else
                $('#isInitialDataEntry').parent().parent().parent().parent().stop().hide();
        }
        else {
            $('#isInitialDataEntry').parent().parent().parent().parent().stop().hide();
            $('#transactionSourceSelectArea').stop().hide();
        }
        currentModifiable = ( [
            'portfolioBondsItem',
            'portfolioBullionItem',
            'portfolioCoinItem',
            'portfolioCurrencyItem',
            'portfolioSymbolItem',
            'portfolioSymbolPriorityItem',
            'portfolioInvestmentFundItem',
            'portfolioHousingFacilitiesItem'
        ].indexOf(dataItem.itemType.clazz) < 0);
        propertyId.data("kendoComboBox").dataSource.read();
        propertyId.data("kendoComboBox").value(dataItem.property.propertyId);
        actionDate.val(dataItem.actionDate);
        sharePrice.data('kendoNumericTextBox').value(dataItem.sharePrice);
        shareCount.data('kendoNumericTextBox').value(dataItem.shareCount);
        <g:if test="${portfolio.useBroker}">
        broker.data("kendoComboBox").value(dataItem.broker.brokerId);
        </g:if>
        <g:if test="${portfolio.useWageAndDiscount}">
        discount.data("kendoNumericTextBox").value(dataItem.discount);
        </g:if>
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(currenctType) >= 0) {
            $('#brokerconainer').show();
            $('#discountconainer').show();
        }
        else {
            $('#brokerconainer').hide();
            $('#discountconainer').hide();
        }
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(currenctType) == -1) {
            $('#sharecountcontainer').show();
        }
        else {
            $('#sharecountcontainer').hide();
        }
        if (currentModifiable) {
            $('#editItems').show()
        }
        else {
            $('#editItems').hide()
        }

    }
    function addNewItem(type, action, modifiable) {
        itemDialog.data("kendoWindow").center().open();
        currentId = '';
        currenctType = type;
        currenctAction = action;
        if (currenctAction == 'b' || currenctAction == 's') {
            $('#lblTransactionSource').find('label').html(currenctAction == 'b' ? '${message(code:'portfolioAction.transactionSource.label')}' : '${message(code:'portfolioAction.transactionTarget.label')}');
            $('#transactionSourceType').data("kendoComboBox").value('');
            $('#transactionSourceSelectorContainer').html('');
            $('#isInitialDataEntry').prop('checked', false);
            if(currenctAction == 'b')
                $('#isInitialDataEntry').parent().parent().parent().parent().stop().show();
            else
                $('#isInitialDataEntry').parent().parent().parent().parent().stop().hide();
            $('#transactionSourceSelectArea').stop().show();
        }
        else {
            $('#isInitialDataEntry').parent().parent().parent().parent().stop().hide();
            $('#transactionSourceSelectArea').stop().hide();
        }
        currentModifiable = modifiable;
        propertyId.data("kendoComboBox").dataSource.read();
        propertyId.data("kendoComboBox").value('');
        actionDate.val('');
        sharePrice.data('kendoNumericTextBox').value('');
        shareCount.data('kendoNumericTextBox').value('');
        <g:if test="${portfolio.useBroker}">
        broker.data("kendoComboBox").value('');
        </g:if>
        <g:if test="${portfolio.useWageAndDiscount}">
        discount.data("kendoNumericTextBox").value('');
        </g:if>
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(type) >= 0) {
            $('#brokerconainer').show();
            $('#discountconainer').show();
        }
        else {
            $('#brokerconainer').hide();
            $('#discountconainer').hide();
        }
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(type) == -1) {
            $('#sharecountcontainer').show();
        }
        else {
            $('#sharecountcontainer').hide();
        }
        if (currentModifiable) {
            $('#editItems').show()
        }
        else {
            $('#editItems').hide()
        }
    }
    function validate() {
        propertyId.data("kendoComboBox").value('');
        actionDate.val('');
        sharePrice.data('kendoNumericTextBox').value('');
        shareCount.data('kendoNumericTextBox').value('');
        <g:if test="${portfolio.useBroker}">
        broker.data("kendoComboBox").value('');
        </g:if>
        <g:if test="${portfolio.useWageAndDiscount}">
        discount.data("kendoNumericTextBox").value('');
        </g:if>
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(type) >= 0) {
            $('#brokerconainer').show();
            $('#discountconainer').show();
        }
        else {
            $('#brokerconainer').hide();
            $('#discountconainer').hide();
        }
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(type) == -1) {
            $('#sharecountcontainer').show();
        }
        else {
            $('#sharecountcontainer').hide();
        }
    }
</script>