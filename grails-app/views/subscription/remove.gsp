<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 9/28/2015
  Time: 2:19 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="subscription.remove.title"/></title>
</head>

<body>
<div class="dashLet crimson" style="width: 500px;margin:auto;margin-top:100px;">
    <h2><i class="fa fa-envelope"></i> <g:message code="subscription.remove.title"/></h2>

    <div style="padding: 10px;line-height:32px;">
        <div><g:message code="subscription.remove.description"/></div>

        <div><g:message code="subscription.email"/>: <span style="font-size:11px;">${email}</span></div>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'subscription', action: 'add', params: [email: email, source: source, token: addToken])}">
            <g:message code="subscription.add.title"/>
        </a>
    </div>
</div>
</body>
</html>