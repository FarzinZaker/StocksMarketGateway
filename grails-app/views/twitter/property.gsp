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
    <title>${property.title}</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: '<i class="fa fa-tag"></i> ' + property.title, url: createLink(controller: 'twitter', action: 'property', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3 k-rtl">
            <g:render template="property/header"/>
        </div>

        <div class="col-xs-6 k-rtl">
            <g:render template="/talk/write"
                      model="${[tag: [type: 'tag', clazz: property.label, title: property.title?.split('-')?.find()?.trim()?.replace(' ', '_'), id: property.identifier]]}"/>
            <g:render template="property/materialList"/>
        </div>

        <div class="col-xs-3 k-rtl">
            <div class="dashLet cyan">
                <h2><i class="fa fa-star"></i> <g:message code="twitter.property.topMaterial.title"/></h2>
                <twitter:topPropertyMaterials id="${property.idNumber}"/>
            </div>
            <g:render template="property/authorList"/>
            <g:render template="property/tagCloud"/>
        </div>
    </div>
</div>

</body>
</html>