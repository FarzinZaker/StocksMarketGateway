<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 4/28/13
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title><g:message code="register.checkForActivationMail.title"/></title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'register.title'), url: createLink(controller: 'user', action: 'register')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="info">
                <g:message code="register.checkForActivationMail.body"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>