<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/27/14
  Time: 8:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${article.title}</title>
</head>

<body>
<g:render template="/article/menu" model="${[document: article]}"/>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-9">
            <g:render template="/document/view" model="${[document: article]}"/>
            <g:render template="/document/tags" model="${[document: article]}"/>
            <stocks:rating document="${article}"/>
            <g:render template="/comment/submit" model="${[document: article]}"/>
            <layout:panel title="${message(code: 'comment.list.title')}" header="h3">
                <stocks:commentList document="${article}" emptyMessage="${message(code: 'article.comments.emptyMessage')}"/>
            </layout:panel>
        </div>

        <div class="col-xs-3">
            <g:render template="/user/card" model="${[user: article.author]}"/>
            <layout:panel title="${message(code: 'relatedArticles.title')}">
                <stocks:relatedArticles id="${article.id}"/>
            </layout:panel>
            <layout:panel title="${message(code: 'newErticles.title')}">
                <stocks:newArticles/>
            </layout:panel>
            <layout:panel title="${message(code: 'tagCloud.title')}">
                <stocks:tagCloud type="${params.controller}"/>
            </layout:panel>
        </div>
    </div>
</div>
</body>
</html>