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
%{--<g:render template="/article/menu" model="${[document: article]}"/>--}%
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-9">
            <div class="dashLet darkBlue">
                <h2 style="float:right"><i class="fa fa-file-text"></i> ${article.title}</h2>

                <div class="clear-fix" style="padding:10px;">
                    <g:render template="thread/view"/>
                    <ul class="propertyList">
                        <g:each in="${propertyList}" var="property">
                            <li>
                                <a href="${createLink(controller: 'twitter', action: 'property', id: property.identifier)}">
                                    <i class="fa fa-tag"></i>
                                    <span>${property.title}</span>

                                    <div class="clear-fix"></div>
                                </a>
                            </li>
                        </g:each>
                    </ul>
                    <g:if test="${hasAccess}">
                        <twitter:rating material="${vertex}"/>
                    </g:if>
                </div>

                <div class="clear-fix"></div>
            </div>

            <g:if test="${hasAccess}">
                <div class="dashLet crimson">
                    <h2><i class="fa fa-comment"></i> <g:message code="twitter.material.comment.title"/></h2>

                    <div style="padding:10px;">
                        <g:render template="/comment/submit" model="${[materialId: vertexId]}"/>
                    </div>
                </div>
            </g:if>

            <div class="dashLet steel">
                <h2><i class="fa fa-comments"></i> <g:message code="comment.list.title"/></h2>

                <div style="padding: 10px;">
                    <twitter:commentList materialId="${vertexId}"
                                         emptyMessage="${message(code: 'twitter.notComment')}"/>
                </div>
            </div>
        </div>

        <div class="col-xs-3">
            <g:render template="thread/author"/>
            <div class="dashLet orange">
                <h2><i class="fa fa-link"></i> <g:message code="twitter.article.relatedMaterials.title"/></h2>
                <twitter:relatedMaterials id="${vertexId}"/>
            </div>

            <div class="dashLet cyan">
                <h2><i class="fa fa-clock-o"></i> <g:message code="twitter.article.newMaterials.title"/></h2>
                <twitter:newMaterials id="${vertexId}"/>
            </div>

            <div class="dashLet magenta">
                <h2 style="float:right"><i class="fa fa-tags"></i> <g:message code="twitter.tags.title"/></h2>

                <div class="clear-fix center">
                    <twitter:tagCloud/>
                </div>
            </div>
        </div>
    </div>
</div>
<g:render template="/group/registerWindow"/>
</body>
</html>