<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/28/14
  Time: 12:16 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="user.build.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.users'), url:createLink(controller: 'user')],
                    [text: '<i class="fa fa-user"></i> ' + message(code:'user.build.title'), url:createLink(controller: 'user', action: 'build', id:params.id)]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="cyan">--}%
                %{--<i class="fa fa-user"></i>--}%
                %{--<g:message code="user.build.title"/>--}%
            %{--</h1>--}%
            <form:error message="${flash.validationError}"/>
            <form:form action="save" name="userForm">

                <g:render template="/user/form"/>


                <form:field fieldName="userInfo.maxDept">
                    <form:numericTextBox name="maxDept" style="width:500px" value="${user?.maxDept ?: 0}"/>
                </form:field>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'user.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>