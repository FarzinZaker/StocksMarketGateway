<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 02/03/2015
  Time: 19:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${'backTest.build.title'}"
                      args="${[tradeStrategy?.name]}"/></title>
</head>

<body>

<div class="container-fluid">
    <form:form name="backTestForm" controller="backTest" action="save">
        <div class="row-fluid">
            <div class="col-xs-12">
                <h1><g:message code="${'backTest.build.title'}"
                               args="${[tradeStrategy?.name]}"/></h1>

                <p><g:message code="backTest.description"/></p>
            </div>
        </div>

        <div class="row-fluid">
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

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.lossLimit">
                    <form:numericTextBox name="lossLimit" id="lossLimitInput" value="${tradeStrategy?.lossLimit ?: 0}"
                                         style="width: 450px;"
                                         format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.profitLimit">
                    <form:numericTextBox name="profitLimit" id="profitLimitInput"
                                         value="${tradeStrategy?.profitLimit ?: 0}" style="width: 450px;"
                                         format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="tradeStrategy.timeLimit">
                    <form:numericTextBox name="timeLimit" id="timeLimitInput" value="${tradeStrategy?.timeLimit ?: 0}"
                                         style="width: 450px;"
                                         format="#,## ${message(code: 'day')}" step="1" min="0" decimals="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.period">
                    <form:datePicker name="startDateFa" style="width:216px;"
                                     value="${format.jalaliDate(date: startDate)}"/>
                    <g:message code="to"/>
                    <form:datePicker name="endDateFa" style="width:216px;" value="${format.jalaliDate(date: endDate)}"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.outlay">
                    <form:numericTextBox name="outlay" style="width:450px;" value="10000000"
                                         format="#,## ${message(code: 'rial')}"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.buyWage">
                    <form:numericTextBox name="buyWage" style="width:450px;" value="0.03" format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.sellWage">
                    <form:numericTextBox name="sellWage" style="width:450px;" value="0.05" format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.buyTax">
                    <form:numericTextBox name="buyTax" style="width:450px;" value="0.01" format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <form:field fieldName="backTest.sellTax">
                    <form:numericTextBox name="sellTax" style="width:450px;" value="0.02" format="p0" step="0.01" min="0"/>
                </form:field>
            </div>
        </div>
        <g:hiddenField name="tradeStrategy.id" value="${tradeStrategy?.id}"/>
        <div class="toolbar" style="margin0top:10px;padding-top:10px;" onclick="gatherData()">
            <form:submitButton name="submit" text="${message(code: 'backTest.submit')}"/>
        </div>
    </form:form>
</div>

</body>
</html>