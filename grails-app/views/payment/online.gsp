<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 4/11/13
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="grails.util.Environment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>

    <g:if test="${bankName == 'mellat'}">
        <title><g:message code="payment.mellat.title"/></title>
    </g:if>

    <g:if test="${bankName == 'saman'}">
        <title><g:message code="payment.saman.title"/></title>
    </g:if>
    <g:if test="${bankName == 'mellat'}">
        <g:if test="${!flash.message}">
            <script language="javascript" type="text/javascript">
                function postRefId(refIdValue) {
                    var form = document.createElement("form");
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "https://bpm.shaparak.ir/pgwchannel/startpay.mellat");
                    form.setAttribute("target", "_self");
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("name", "RefId");
                    hiddenField.setAttribute("value", refIdValue);
                    form.appendChild(hiddenField);
                    document.body.appendChild(form);
                    form.submit();
                    document.body.removeChild(form);
                }

                $(document).ready(function () {
                    postRefId('${refId}');
                });
            </script>
        </g:if>
    </g:if>
    <g:elseif test="${bankName == 'saman'}">
        <g:if test="${!flash.message}">
            <script language="javascript" type="text/javascript">
                function postRefId() {
                    var form = document.createElement("form");
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "https://sep.shaparak.ir/Payment.aspx");
                    form.setAttribute("target", "_self");

                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("name", "Amount");
                    hiddenField.setAttribute("value", ${amount});
                    form.appendChild(hiddenField);

                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("name", "MID");
                    hiddenField.setAttribute("value", ${merchantId});
                    form.appendChild(hiddenField);

                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("name", "ResNum");
                    hiddenField.setAttribute("value", ${reservationNumber});
                    form.appendChild(hiddenField);

                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("name", "RedirectURL");
                    <g:if test="${grails.util.Environment.current == Environment.DEVELOPMENT}">
                    hiddenField.setAttribute("value", "http://localhost:8080/Stocks/payment/onlineResultSaman");
                    </g:if>
                    <g:else>
                    hiddenField.setAttribute("value", "${createLink(uri: '/payment/onlineResultSaman', absolute: true)}");
                    </g:else>
                    form.appendChild(hiddenField);

                    document.body.appendChild(form);
                    form.submit();
                    document.body.removeChild(form);
                }

                $(document).ready(function () {
                    postRefId();
                });
            </script>
        </g:if>
    </g:elseif>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: "payment.${bankName}.title"), url: createLink(action: 'online', params: [accountId: params.accountId, amount: params.amount])]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:if test="${bankName == 'mellat'}">
                <g:if test="${!flash.message}">
                    <div class="info">
                        <div><g:message code="payment.mellat.waitingMessage"/></div>
                    </div>
                </g:if>
                <g:else>
                    <div class="error">
                        <div><g:message code="payment.error"/>: <b>${flash.message}</b></div>
                    </div>
                </g:else>
            </g:if>
            <g:if test="${bankName == 'saman'}">
                <g:if test="${!flash.message}">
                    <div class="info">
                        <div><g:message code="payment.saman.waitingMessage"/></div>
                    </div>
                </g:if>
                <g:else>
                    <div class="error">
                        <div><g:message code="payment.error"/>: <b>${flash.message}</b></div>
                    </div>
                </g:else>
            </g:if>
        </div>
    </div>
</body>
</html>