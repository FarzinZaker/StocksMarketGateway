<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/2/13
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="payment.online.result"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: "payment.online.result"), url: createLink(action: 'online', params: params)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <g:if test="${verificationResult?.toString() == "0"}">
                <div class="info">
                    <div><g:message code="payment.online.result.success"/></div>
                </div>

                <div style="padding: 10px;line-height: 20px;">
                    <div>
                        <g:message code="payment.online.amount"/>:
                        <b>${onlinePayment?.amount} <g:message code="rial"/></b>
                    </div>

                    <div>
                        <g:message code="payment.online.transactionReferenceCode"/>:
                        <b>${onlinePayment?.transactionReferenceCode}</b>
                    </div>

                    %{--<g:if test="${onlinePayment?.order}">--}%
                    %{--<div>--}%
                    %{--<g:message code="payment.online.order"/>:--}%
                    %{--<b>${onlinePayment?.order?.trackingCode}</b>--}%
                    %{--</div>--}%

                    %{--<div>--}%
                    %{--<g:message code="payment.online.order.paymentResult"/>:--}%
                    %{--<b>--}%
                    %{--<g:if test="${orderPaid}">--}%
                    %{--<g:message code="payment.online.order.paymentResult.success"/>--}%
                    %{--</g:if>--}%
                    %{--<g:else>--}%
                    %{--<g:message code="payment.online.order.paymentResult.fail"/>--}%
                    %{--</g:else>--}%
                    %{--</b>--}%
                    %{--</div>--}%
                    %{--<g:if test="${grailsApplication.config.payOnCheckout}">--}%
                    %{--<div class='info'><div><g:message code="order.creation.success.message"/>--}%
                    %{--<h2 style="font-size: 16px !important;margin-bottom:0;">--}%
                    %{--<g:message code="order.trackingCode.label"/>: <b>${onlinePayment?.order?.trackingCode}</b>--}%
                    %{--</h2>--}%
                    %{--</div></div>--}%
                    %{--</g:if>--}%
                    %{--</g:if>--}%
                </div>
            </g:if>
            <g:else>
                <div class="errorMessage">
                    <div>
                        <g:message code="payment.online.result.error"/>
                        <g:if test="${onlinePayment?.resultCode}">
                            <br/><b><g:message code="onlinePayment.${onlinePayment.accountId}.error.${onlinePayment?.resultCode}"/></b>
                        </g:if>
                        <div style="display: none">${verificationResult}</div>
                    </div>
                </div>
            </g:else>
        </div>
    </div>
</div>
</body>
</html>