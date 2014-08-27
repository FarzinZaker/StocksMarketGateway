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
    <title><g:message code="register.title"/></title>
    <asset:javascript src="form-validator/security.js"/>
</head>

<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">

            <h1><g:message code="register.title"/></h1>
            <form:error message="${flash.validationError}"/>
            <form:form action="save" name="registerForm">
                <form:field fieldName="user.firstName">
                    <form:textBox name="firstName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.lastName">
                    <form:textBox name="lastName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.sex">
                    <form:select name="sex" entity="${user}" items="${[[text:message(code:'user.sex.male'),value:'male'],[text:message(code:'user.sex.female'),value:'female']]}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.mobile">
                    <form:textBox name="mobile" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.email">
                    <form:textBox name="email" entity="${user}" validation="email" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.password">
                    <form:password name="password_confirmation" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.confirmPassword">
                    <form:password name="password" validation="confirmation" style="width:500px;"/>
                </form:field>
                <form:field fieldName="captcha">
                    <form:captcha name="captcha" validation="required" width="500"/>
                </form:field>
                <form:submitButton name="submit" text="${message(code:'register.button.label')}"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>