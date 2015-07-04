<%@ page import="stocks.RoleHelper" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="site.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-3">
        </div>

        <div class="col-xs-6">
            <g:render template="newsFeed"/>
        </div>

        <div class="col-xs-3">
            <g:render template="heatMap" />
        </div>
    </div>
</div>

</body>
</html>
