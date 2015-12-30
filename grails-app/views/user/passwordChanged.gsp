<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 3:15 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="password.change.success.title"/></title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-key"></i> ' + message(code: 'changePassword.title'), url: createLink(controller: 'user', action: 'changePassword')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form:info message="${message(code:'password.change.success.title')}"/>
        </div>
    </div>
</div>
</body>
</html>