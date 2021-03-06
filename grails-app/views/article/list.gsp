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
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.twitter.articles'), url:createLink(controller: 'article')],
                    [text: '<i class="fa fa-file"></i> ' + message(code:'article.list.title'), url:createLink(controller: 'article', action: 'list')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="orange"><g:message code="article.list.title"/></h1>--}%
            <g:render template="../document/list"
                      model="[entityName: 'article', dataServiceUrl: createLink(controller: 'article', action: 'jsonList')]"/>
        </div>
    </div>
</div>
</body>
</html>