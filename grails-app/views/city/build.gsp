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
    <title><g:message code="city.build.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.basicInfo'), url:createLink(controller: 'baseInfo')],
                    [text: '<i class="fa fa-map-marker"></i> ' + message(code:'city.build.title'), url:createLink(controller: 'city', action: 'build', id:params.id)]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="orange">--}%
                %{--<i class="fa fa-map-marker"></i>--}%
                %{--<g:message code="city.build.title"/>--}%
            %{--</h1>--}%
            <form:form action="save" name="cityForm">
                <form:hidden name="id" entity="${city}"/>

                <form:field fieldName="city.name">
                    <form:textBox name="name" style="width:500px" entity="${city}" validation="required"/>
                </form:field>

                <form:field fieldName="city.province">
                    <form:select name="provinceId" validation="required"
                                 items="${provinceList.collect { [text: it.name, value: it.id] }}" style="width:500px;"/>
                </form:field>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'city.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>