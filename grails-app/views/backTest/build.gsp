<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 02/03/2015
  Time: 19:49
--%>

<%@ page import="stocks.tse.AdjustmentHelper" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${'backTest.build.title'}"
                      args="${[tradeStrategy?.name]}"/></title>
    <form:datePickerResources/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.strategy'), url: createLink(controller: 'tradeStrategy')],
                    [text: tradeStrategy?.name, url: createLink(controller: 'tradeStrategy', action: 'build', id: tradeStrategy?.id)],
                    [text: '<i class="fa fa-magic"></i> ' + message(code: "${'backTest.build.title'}", args: ['']), url: createLink(controller: 'backTest', action: 'build', id: tradeStrategy?.id)]
            ]}"/>
        </div>
    </div>

%{--<div class="row">--}%
%{--<div class="col-xs-12">--}%
%{--<h1 class="magenta">--}%
%{--<i class="fa fa-magic"></i>--}%
%{--<g:message code="${'backTest.build.title'}"--}%
%{--args="${[tradeStrategy?.name]}"/>--}%
%{--</h1>--}%

%{--<p><g:message code="backTest.description"/></p>--}%
%{--</div>--}%
%{--</div>--}%
    <form:form name="backTestForm" controller="backTest" action="save">

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="backTest.symbol">
                    <div class="k-rtl">
                        <form:textBox name="symbol.id" id="symbol" validation="required" style="width:450px"/>
                    </div>
                </form:field>
                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {
                        $("#symbol").removeClass('k-textbox').width('440px').kendoComboBox({
                            dataTextField: "name",
                            dataValueField: "value",
                            filter: "contains",
                            dataSource: {
                                serverFiltering: true,
                                transport: {
                                    type: 'odata',
                                    read: {
                                        url: "${createLink(action: 'symbolAutoComplete')}",
                                        dataType: "json",
                                        type: "POST"

                                    }
                                },
                                schema: {
                                    data: "data" // records are returned in the "data" field of the response
                                }
                            },
                            change: function (e) {
                                if (this.value() && this.selectedIndex == -1) {
                                    var dt = this.dataSource._data[0];
                                    this.text(dt[this.options.dataTextField]);
                                    this._selectItem();
                                }
                            }
                        });
                    });
                </script>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.lossLimit">
                    <form:numericTextBox name="lossLimit" id="lossLimitInput" value="${tradeStrategy?.lossLimit ?: 0}"
                                         style="width: 450px;"
                                         format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.profitLimit">
                    <form:numericTextBox name="profitLimit" id="profitLimitInput"
                                         value="${tradeStrategy?.profitLimit ?: 0}" style="width: 450px;"
                                         format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.timeLimit">
                    <form:numericTextBox name="timeLimit" id="timeLimitInput" value="${tradeStrategy?.timeLimit ?: 0}"
                                         style="width: 450px;"
                                         format="#,## ${message(code: 'day')}" step="1" min="0" decimals="0"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="backTest.period">
                    <form:datePicker name="startDateFa" style="width:216px;"
                                     value="${format.jalaliDate(date: startDate)}"/>
                    <g:message code="to"/>
                    <form:datePicker name="endDateFa" style="width:216px;" value="${format.jalaliDate(date: endDate)}"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="backTest.outlay">
                    <form:numericTextBox name="outlay" style="width:450px;" value="10000000"
                                         format="#,## ${message(code: 'rial')}" step="100000"/>
                </form:field>
            </div>
        </div>

        <div class="row" style="display: none">
            <div class="col-xs-12">
                <form:field fieldName="backTest.buyWage">
                    <form:numericTextBox name="buyWage" style="width:450px;" value="0.00486" format="p3" decimals="5"
                                         step="0.001" min="0" readonly="true"/>
                </form:field>
            </div>
        </div>

        <div class="row" style="display: none">
            <div class="col-xs-12">
                <form:field fieldName="backTest.sellWage">
                    <form:numericTextBox name="sellWage" style="width:450px;" value="0.00526" format="p3" decimals="5"
                                         step="0.001" min="0" readonly="true"/>
                </form:field>
            </div>
        </div>

        <div class="row" style="display: none">
            <div class="col-xs-12">
                <form:field fieldName="backTest.buyTax">
                    <form:numericTextBox name="buyTax" style="width:450px;" value="0" format="p1" decimals="3"
                                         step="0.001" min="0" readonly="true"/>
                </form:field>
            </div>
        </div>

        <div class="row" style="display: none">
            <div class="col-xs-12">
                <form:field fieldName="backTest.sellTax">
                    <form:numericTextBox name="sellTax" style="width:450px;" value="0.005" format="p1" decimals="3"
                                         step="0.001" min="0" readonly="true"/>
                </form:field>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form:field fieldName="backTest.adjustmentType">
                    <form:select name="adjustmentType" style="width:450px;" value="${AdjustmentHelper.defaultType}"
                                 items="${[[text: '', value: '']] + AdjustmentHelper.TYPES.collect {
                                     [text: message(code: "priceAdjustment.types.${it}"), value: it]
                                 }}" disabledItems="${AdjustmentHelper.TYPES - AdjustmentHelper.ENABLED_TYPES}"/>
                </form:field>
            </div>
        </div>
        <g:hiddenField name="tradeStrategy.id" value="${tradeStrategy?.id}"/>
        <div class="toolbar" style="margin0top:10px;padding-top:10px;">
            <form:submitButton name="submit" text="${message(code: 'backTest.submit')}"/>
        </div>
    </form:form>
</div>
<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#adjustmentType-list').find('li:first-of-type').hide();
    });
</script>

</body>
</html>