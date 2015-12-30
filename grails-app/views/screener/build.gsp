<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 19/01/2015
  Time: 13:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${params.id ? 'screener.title.edit' : 'screener.title.new'}"
                      args="${[screener?.title]}"/></title>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingRegisterController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.screener'), url: createLink(controller: 'screener')],
                    [text: '<i class="fa fa-filter"></i> ' + message(code: "${params.id ? 'screener.title.edit' : 'screener.title.new'}", args: [screener?.title]), url: createLink(controller: 'screener', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    %{--<div class="row">--}%
        %{--<div class="col-xs-12">--}%
            %{--<h1 class="pink">--}%
                %{--<i class="fa fa-filter"></i>--}%
                %{--<g:message code="${params.id ? 'screener.title.edit' : 'screener.title.new'}"--}%
                           %{--args="${[screener?.title]}"/>--}%
            %{--</h1>--}%

            %{--<p><g:message code="screener.description"/></p>--}%
        %{--</div>--}%
    %{--</div>--}%

    <div class="row">
        <div class="col-xs-12">
            <form:field fieldName="screener.name">
                <form:textBox name="nameInput" style="width:450px;" value="${screener?.title ?: ''}"/>
            </form:field>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-2 k-rtl">
            <g:render template="filters"/>
        </div>

        <div class="col-xs-2">
            <div id="operators-loading" style="display: none;padding:10px;">
                <asset:image src="loading.gif" style="margin-left: 10px;"/>
                <g:message code="loading"/>
            </div>

            <div id="operators"></div>
        </div>

        <div class="col-xs-2">
            <div id="values-loading" style="display: none;padding:10px;">
                <asset:image src="loading.gif" style="margin-left: 10px;"/>
                <g:message code="loading"/>
            </div>

            <form:form name="valueForm">
                <g:hiddenField name="filter" id="filter"/>
                <g:hiddenField name="parameter" id="parameter"/>
                <g:hiddenField name="operator" id="operator"/>
                <div id="values"></div>
            </form:form>
        </div>

        <div class="col-xs-6">
            <g:render template="query" model="${[rules: rules]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <g:render template="submit"/>
        </div>
    </div>
</div>

</body>
</html>