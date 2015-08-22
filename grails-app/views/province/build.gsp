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
    <title><g:message code="province.build.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.basicInfo'), url:createLink(controller: 'baseInfo')],
                    [text: '<i class="fa fa-flag"></i> ' + message(code:'province.build.title'), url:createLink(controller: 'province', action: 'build', id:params.id)]
            ]}"/>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-xs-12">
            %{--<h1 class="orange">--}%
                %{--<i class="fa fa-flag"></i>--}%
                %{--<g:message code="province.build.title"/>--}%
            %{--</h1>--}%
            <form:form action="save" name="provinceForm">
                <form:hidden name="id" entity="${province}"/>

                <form:field fieldName="province.name">
                    <form:textBox name="name" style="width:500px" entity="${province}" validation="required"/>
                </form:field>


                <div class="toolbar">
                    <input type="submit" value="${message(code: 'province.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>