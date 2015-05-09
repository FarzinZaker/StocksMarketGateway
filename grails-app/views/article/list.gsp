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
    <title><g:message code="article.list.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="orange"><g:message code="article.list.title"/></h1>
            <g:render template="../document/list"
                      model="[entityName: 'article', dataServiceUrl: createLink(controller: 'article', action: 'jsonList')]"/>
        </div>
    </div>
</div>
</body>
</html>