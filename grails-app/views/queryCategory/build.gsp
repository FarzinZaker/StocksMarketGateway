<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="queryCategory.build.title"/></title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.newsletter.register'), url: createLink(controller: 'query')],
                    [text: message(code: 'queryCategory.build.title'), url: createLink(controller: 'queryCategory', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="green">
                <i class="fa fa-folder-open"></i>
                <g:message code="queryCategory.build.title"/>
            </h1>
            <form:form action="save" name="queryCategoryForm">
                <form:hidden name="id" entity="${category}"/>

                <form:field fieldName="queryCategory.name">
                    <form:textBox name="name" style="width:500px" entity="${category}" validation="required"/>
                </form:field>

                <form:field fieldName="queryCategory.parent">
                    <form:treeCombo name="parent" style="width:500px" value="${category?.parent?.id ?: 0}"
                                    domainClass="stocks.alerting.QueryCategory" relationProperty="parent"
                                    titleProperty="name"/>
                </form:field>

                <form:field fieldName="queryCategory.description">
                    <form:editor name="description" width="500" mode="simple" entity="${category}"/>
                </form:field>

                <form:field fieldName="queryCategory.image">
                    <form:imageUpload name="image" style="width:500px;" entity="${category}"
                                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
                </form:field>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'queryCategory.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>