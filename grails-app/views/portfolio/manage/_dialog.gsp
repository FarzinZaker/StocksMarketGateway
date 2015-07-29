<div id="itemDialog" class="k-rtl">
    <form:field id="propertycontainer" fieldName="portfolioItem.property" showHelp="0">
        <input id="propertyId" class="propertyComboBox" style="width: 300px" required>
    </form:field>
    <form:field fieldName="portfolioAction.actionDate" showHelp="0">
        <input id="actionDate" style="width: 300px">
    </form:field>
    <form:field id="sharepricecontainer" fieldName="portfolioAction.sharePrice" showHelp="0">
        <input id="sharePrice" style="width: 300px">
    </form:field>
    <form:field id="sharecountcontainer" fieldName="portfolioAction.shareCount" showHelp="0">
        <input id="shareCount" style="width: 300px">
    </form:field>
    <g:if test="${portfolio.useBroker}">
            <form:field id="brokerconainer" fieldName="portfolioAction.broker" showHelp="0">
                <input id="broker" style="width: 300px">
            </form:field>
    </g:if>
    <g:if test="${portfolio.useWageAndDiscount}">
            <form:field id="discountconainer" fieldName="portfolioAction.discount" showHelp="0">
                <input id="discount" style="width: 300px">
            </form:field>
            <form:field id="wageconainer" fieldName="portfolioAction.wage" showHelp="0">
                <input id="wage" style="width: 300px">
            </form:field>
    </g:if>
    <div class="toolbar" style="margin:0;padding-top:10px;">
        <form:button name="submit" text="${message(code: 'portfolioItem.save')}"/>
        <form:button name="cancel" text="${message(code: 'portfolioItem.cancel')}" style="float:left;"/>
        <script language="javascript" type="text/javascript">
            $('span[name=submit]').click(function () {
//                if ($('#portfolioForm').isValid()) {
                    dataSource.insert( 0,{
                        itemType:{clazz:currenctType},
                        property:{propertyId:propertyId.data('kendoComboBox').value()},
                        actionType:{actionTypeId:currenctAction},
                        actionDate:actionDate.data('kendoDatePicker').value().gregoriandate.toString(),
                        sharePrice:sharePrice.data('kendoNumericTextBox').value(),
                        shareCount:shareCount.data('kendoNumericTextBox').value(),
                        <g:if test="${portfolio.useBroker}">
                            broker:{brokerId:broker.data("kendoComboBox").value()},
                        </g:if>
                        <g:if test="${portfolio.useWageAndDiscount}">
                            discount:discount.data("kendoNumericTextBox").value(),
                            wage:wage.data("kendoNumericTextBox").value()
                        </g:if>
                    });
                    dataSource.sync().done(function(){
                        dataSource.fetch();
                        itemDialog.kendoWindow("close");
                    }).fail(function(){
                        dataSource.cancelChanges();
                    });

//                }/
            });
            $('span[name=cancel]').click(function () {
                $(this).closest("[data-role=window]").kendoWindow("close");
            });
        </script>
    </div>
</div>
<script language="javascript" type="text/javascript">
    var propertyContextMenu=$('#propertyContextMenu').clone().appendTo($('body'));
    var itemDialog = $("#itemDialog");
    var propertyId = $("#propertyId");
    var actionDate = $("#actionDate");
    var sharePrice = $("#sharePrice");
    var shareCount = $("#shareCount");
    var currenctType='';
    var currenctAction='';
    var currentModifiable=false;

    if (!itemDialog.data("kendoWindow")) {
        itemDialog.kendoWindow({
            width: "50%",
            title: '<g:message code="portfolio.form.label" />',
            modal: true,
            visible: false,
            position: {
                top: '20%',
                left: '25%'
            },
            actions: [
                "Close"
            ]
        });
    }

    propertyId .kendoComboBox({
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
                    return result;
                }
            }
        }
    });
    propertyContextMenu.kendoContextMenu({
        target:$( '#propertycontainer'),
        alignToAnchor: true,
        open: function (e) {
            var combo = $( '#propertycontainer').find('input[type!=text].propertyComboBox').data('kendoComboBox');
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
            var options={
                model:{itemType:{clazz:currenctType}}
            };
            switch ($(e.item).attr('data-key')) {
                case 'create':
                    createProperty($( '#propertycontainer'), options);
                    break;
                case 'edit':
                    editProperty($( '#propertycontainer'), options);
                    break;
                case 'delete':
                    deleteProperty($( '#propertycontainer'), options);
                    break;
            }
        }
    });
    actionDate.kendoDatePicker();
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
        var wage = $("#wage");
        discount.kendoNumericTextBox({
            format: 'n2',
            min: 0,
            max: 1,
            step: 0.1
        });
        wage.kendoNumericTextBox({
            format: 'n2',
            min: 0,
            max: 1,
            step: 0.1
        });
    </g:if>
    function addNewItem(type,action,modifiable) {
        itemDialog.data("kendoWindow").open();
        currenctType=type;
        currenctAction=action;
        currentModifiable=modifiable;
        propertyId.data("kendoComboBox").value('');
        actionDate.data('kendoDatePicker').value('');
        sharePrice.data('kendoNumericTextBox').value('');
        shareCount.data('kendoNumericTextBox').value('');
        <g:if test="${portfolio.useBroker}">
            broker.data("kendoComboBox").value('');
        </g:if>
        <g:if test="${portfolio.useWageAndDiscount}">
            discount.data("kendoNumericTextBox").value('');
            wage.data("kendoNumericTextBox").value('');
        </g:if>
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(type) >= 0) {
            $('#brokerconainer').show();
            $('#wageconainer').show();
            $('#discountconainer').show();
        }
        else{
            $('#brokerconainer').hide();
            $('#wageconainer').hide();
            $('#discountconainer').hide();
        }
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(type) == -1){
            $('#sharecountcontainer').show();
        }
        else{
            $('#sharecountcontainer').hide();
        }
    }
    function validate(){
        propertyId.data("kendoComboBox").value('');
        actionDate.data('kendoDatePicker').value('');
        sharePrice.data('kendoNumericTextBox').value('');
        shareCount.data('kendoNumericTextBox').value('');
        <g:if test="${portfolio.useBroker}">
        broker.data("kendoComboBox").value('');
        </g:if>
        <g:if test="${portfolio.useWageAndDiscount}">
        discount.data("kendoNumericTextBox").value('');
        wage.data("kendoNumericTextBox").value('');
        </g:if>
        if (['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem'].indexOf(type) >= 0) {
            $('#brokerconainer').show();
            $('#wageconainer').show();
            $('#discountconainer').show();
        }
        else{
            $('#brokerconainer').hide();
            $('#wageconainer').hide();
            $('#discountconainer').hide();
        }
        if (['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioImmovableItem', 'portfolioBrokerItem'].indexOf(type) == -1){
            $('#sharecountcontainer').show();
        }
        else{
            $('#sharecountcontainer').hide();
        }
    }
</script>