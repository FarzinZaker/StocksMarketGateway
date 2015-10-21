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
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: '<i class="fa fa-user"></i> ' + user, url: createLink(controller: 'user', action: 'wall', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-2 k-rtl">
            <g:render template="wall/header"/>
        </div>
        <div class="col-xs-7 k-rtl">
            <g:render template="wall/materialList"/>
        </div>
        <div class="col-xs-3 k-rtl">
            <g:render template="wall/groupList"/>
            <div class="dashLet cyan">
                <h2><i class="fa fa-clock-o"></i> <g:message code="twitter.author.topMaterial.title"/></h2>
                <twitter:topAuthorMaterials id="${vertex.idNumber}"/>
            </div>
            <g:render template="wall/tagCloud"/>
        </div>
    </div>
</div>

</body>
</html>