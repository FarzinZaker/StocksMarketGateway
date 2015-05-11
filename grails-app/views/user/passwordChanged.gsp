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

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'changePassword.title'), url: createLink(controller: 'user', action: 'changePassword')]
            ]}"/>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="steel">
                <i class="fa fa-key"></i>
                <g:message code="password.change.success.title"/>
            </h1>
            <form:info message="${flash.message}"/>
        </div>
    </div>
</div>
</body>
</html>