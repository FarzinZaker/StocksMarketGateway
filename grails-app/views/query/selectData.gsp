<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/5/14
  Time: 2:43 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title><g:message code="query.selectClass.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.selectClass.title"/></h1>
            <form:form name="selectClassForm" action="build">
                <form:field fieldName="query.class" showLabel="true">
                    <alerting:domainClassList/>
                </form:field>
                <div class="toolbar">
                    <form:submitButton text="${message(code: 'query.selectClass.submit')}"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>