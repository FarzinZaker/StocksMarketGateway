<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 9/24/14
  Time: 12:56 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${params.id ? 'transaction.edit' : 'transaction.create'}"/></title>
    <asset:javascript src="form-validator/security.js"/>
    <asset:stylesheet src="jquery-clockpicker.css"/>
    <asset:javascript src="jquery-clockpicker.js"/>
    <form:datePickerResources/>
</head>

<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'transaction'), url: createLink(controller: 'transaction')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: params.id ? 'transaction.edit' : 'transaction.create'), url: createLink(controller: 'transaction', action: 'build', params: params)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">

        <div class="col-xs-12">
            <form:form name="transactionForm" action="save">
                <form:hidden name="sourceList" value="${sourceList}"/>
                <form:hidden name="id" entity="${transaction}"/>
                <form:field fieldName="transaction.value">
                    <form:numericTextBox name="value" entity="${transaction}" validation="required" style="width:500px;"
                                         value="100000"/>
                </form:field>
                <form:field fieldName="transaction.account">
                    <form:select name="accountId" validation="required" value="${transaction?.accountId}"
                                 items="${grailsApplication.config.accounts.collect {
                                     [text: message(code: "bank.${it.bankName}"), value: it.id]
                                 }}"
                                 style="width:500px;"/>
                </form:field>
                <form:field fieldName="transaction.type">
                    <form:select name="type" validation="required" value="${transaction?.type}"
                                 items="${stocks.AccountingHelper.TRANSACTION_TYPE_LIST.collect {
                                     [text: message(code: "transaction.type.${it}"), value: it]
                                 }}"
                                 style="width:500px;"/>
                </form:field>
                <form:field fieldName="transaction.date">
                    <div style="float:right;">
                        <form:datePicker style="width:250px;text-align: center" name="date"
                                         value="${format.jalaliDate(date: transaction?.date ?: new Date())}"/>
                    </div>

                    <div class="input-group clockpicker" style="float:right;margin-right:10px;width:200px;">
                        <input name="time" type="text" class="form-control"
                               style="padding-top:0;padding-bottom:0;height:30px;width:200px;"
                               value="${format.jalaliDate(date: transaction?.date ?: new Date(), timeOnly: true)}">
                        <span class="input-group-addon" style="padding-top:0;padding-bottom:0;">
                            <span class="glyphicon glyphicon-time"></span>
                        </span>
                    </div>
                    <script language="javascript" type="text/javascript">
                        $(document).ready(function () {
                            $('.clockpicker').clockpicker({
                                placement: 'top',
                                align: 'right',
                                autoclose: true
                            });
                        });
                    </script>
                </form:field>
                <form:field fieldName="transaction.customer">
                    <div class="k-rtl">
                        <form:textBox name="customer.id" id="customer" validation="required" style="width:500px"
                                      value="${transaction?.customerId}"/>
                        <script language="javascript" type="text/javascript">
                            $(document).ready(function () {
                                $("#customer").removeClass('k-textbox').width('490').kendoComboBox({
                                    dataTextField: "name",
                                    dataValueField: "value",
                                    filter: "contains",
                                    dataSource: {
                                        serverFiltering: true,
                                        transport: {
                                            type: 'odata',
                                            read: {
                                                url: "${createLink(controller: 'user', action: 'autoComplete')}",
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
                </form:field>
                <form:submitButton text="${message(code: 'transaction.sumbit.button')}"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>