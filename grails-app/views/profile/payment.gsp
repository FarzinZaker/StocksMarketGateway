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
    <title><g:message code="user.profile.payment"/></title>
    <asset:javascript src="form-validator/security.js"/>
    <asset:javascript src="jquery.cropit.min.js"/>
</head>

<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'user.profile.payment'), url: createLink(controller: 'profile', action: 'payment')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">
            <form:form name="paymentForm" controller="payment" action="online">
                <form:field fieldName="payment.amount">
                    <form:numericTextBox name="amount" validation="required" style="width:500px;" value="100000"/>
                </form:field>
                <form:field fieldName="payment.bank">
                    <form:select name="accountId" validation="required"
                                 items="${grailsApplication.config.accounts.collect {
                                     [text: message(code: "bank.${it.bankName}"), value: it.id]
                                 }}"
                                 style="width:500px;"/>
                </form:field>
                <form:submitButton text="${message(code:'payment.sumbit.button')}"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>