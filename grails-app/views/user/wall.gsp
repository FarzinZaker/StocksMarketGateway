<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 10/13/2015
  Time: 5:55 PM
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${user}</title>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: '<i class="fa fa-user"></i> ' + user, url: createLink(controller: 'user', action: 'wall', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3 k-rtl">
            <g:render template="wall/header"/>
        </div>
        <div class="col-xs-6 k-rtl">
            <g:render template="/talk/write"
                      model="${[tag: [type: 'author', clazz: 'Person', title: user.toString().replace(' ', '_'), id: params.id]]}"/>
            <g:render template="wall/topMaterials" model="${[groupId: vertex?.idNumber]}"/>
            <g:render template="wall/materialList"/>
        </div>
        <div class="col-xs-3 k-rtl">
            %{--<div class="dashLet cyan">--}%
                %{--<h2><i class="fa fa-star"></i> <g:message code="twitter.author.topMaterial.title"/></h2>--}%
                %{--<twitter:topAuthorMaterials id="${vertex.idNumber}"/>--}%
            %{--</div>--}%
            <g:render template="wall/groupList"/>
            %{--<g:render template="wall/tagCloud"/>--}%
        </div>
    </div>
</div>

</body>
</html>