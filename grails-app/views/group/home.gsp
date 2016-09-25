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
    <title>${group.title}</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: message(code: 'twitter.group.select'), url: createLink(controller: 'group', action: 'select')],
                    [text: '<i class="fa fa-users"></i> ' + group.title, url: createLink(controller: 'group', action: 'home', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3 k-rtl">
            <g:render template="home/header"/>
            <g:render template="home/authorList"/>
            <g:render template="home/editorList"/>
        </div>

        <div class="col-xs-6 k-rtl">
            <g:render template="/talk/write"
                      model="${[groups: [group]]}"/>
            <g:render template="home/materialList"/>
        </div>

        <div class="col-xs-3 k-rtl">
            <div class="dashLet cyan">
                <h2><i class="fa fa-star"></i> <g:message code="twitter.group.topMaterial.title"/></h2>
                <twitter:topGroupMaterials id="${group.idNumber}"/>
            </div>
            <g:render template="home/tagCloud"/>
        </div>
    </div>
</div>

</body>
</html>