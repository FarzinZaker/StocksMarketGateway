<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/1/14
  Time: 3:12 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="changePassword.title"/></title>
    <asset:javascript src="form-validator/security.js"/>
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
                <g:message code="changePassword.title"/>
            </h1>
            <form:error message="${flash.validationError}"/>
            <form:form action="saveNewPassword" name="changePasswordForm">
                <form:field fieldName="password.old">
                    <form:password name="oldPassword" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="password.new">
                    <form:password name="newPassword_confirmation" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="password.new.confirm">
                    <form:password name="newPassword" validation="confirmation" style="width:500px;"/>
                </form:field>
                <form:submitButton name="submit" text="${message(code:'changePassword.button.label')}"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>