<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/22/14
  Time: 2:53 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="article.edit.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="orange"><g:message code="article.edit.title"/></h1>
            <g:form action="save">
                <g:render template="../document/form" model="${[document: article]}"/>
                <div class="toolbar">
                    <input type="submit" value="${message(code: 'article.edit.submit.label')}" class="k-button"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>