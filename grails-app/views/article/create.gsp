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
    <title><g:message code="article.create.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.twitter.articles'), url:createLink(controller: 'article')],
                    [text: '<i class="fa fa-file"></i> ' + message(code:'menu.twitter.articles.add'), url:createLink(controller: 'article', action: 'create')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="orange">--}%
                %{--<i class="fa fa-file"></i>--}%
                %{--<g:message code="article.create.title"/>--}%
            %{--</h1>--}%
            <form:form action="save" name="articleForm">
                <g:render template="../document/form" model="${[document: article]}"/>
                <div class="toolbar">
                    <input type="submit" value="${message(code: 'article.create.submit.label')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>