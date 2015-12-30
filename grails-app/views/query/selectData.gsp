<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/5/14
  Time: 2:43 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="query.selectClass.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.newsletter.register'), url: createLink(controller: 'query')],
                    [text: '<i class="fa fa-paper-plane-o"></i> ' + message(code: 'query.selectClass.title'), url: createLink(controller: 'query', action: 'selectData')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="crimson">--}%
                %{--<i class="fa fa-paper-plane-o"></i>--}%
                %{--<g:message code="query.selectClass.title"/>--}%
            %{--</h1>--}%
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