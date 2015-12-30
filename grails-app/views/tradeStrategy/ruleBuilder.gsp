<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="inline"/>
    <title><g:message code="${params.id ? 'tradeStrategy.title.edit' : 'tradeStrategy.title.new'}"
                      args="${[tradeStrategy?.name]}"/></title>
</head>

<body>
<div class="row" id="container">
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
    <div class="clear-fix"></div>
</div>

</body>
</html>