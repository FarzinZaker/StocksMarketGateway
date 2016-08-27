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

        <div class="col-xs-9" style="padding:0;">
            <div class="row">
                <div class="col-xs-12 k-rtl">
                    <g:render template="property/chart"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-8 k-rtl">
                    <g:if test="${['Symbol', 'Index'].contains(property.label)}">
                        <g:render template="/technical/write"
                                  model="${[tag: [type: 'tag', clazz: property.label, title: property.title?.split('-')?.find()?.trim()?.replace(' ', '_'), id: property.identifier]]}"/>
                    </g:if>
                    <g:else>
                        <g:render template="/talk/write"
                                  model="${[tag: [type: 'tag', clazz: property.label, title: property.title?.split('-')?.find()?.trim()?.replace(' ', '_'), id: property.identifier]]}"/>
                    </g:else>
                    <g:render template="property/materialList"/>
                </div>

                <div class="col-xs-4 k-rtl">
                    <div class="dashLet cyan">
                        <h2><i class="fa fa-star"></i> <g:message code="twitter.property.topMaterial.title"/></h2>
                        <twitter:topPropertyMaterials id="${property.idNumber}"/>
                    </div>
                    <g:render template="property/authorList"/>
                    <g:render template="property/tagCloud"/>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>