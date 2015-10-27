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
        <div class="col-sm-3">
            <g:render template="dashLets/marketView"/>
            <g:render template="dashLets/rates"/>
            <g:render template="dashLets/announcements"/>
        </div>

        <div class="col-sm-6">
            %{--<g:render template="dashLets/twits"/>--}%
            <g:render template="dashLets/newsFeed"/>
            <g:render template="dashLets/analysis"/>
        </div>

        <div class="col-sm-3">
            <g:render template="dashLets/heatMap"/>
            <g:render template="dashLets/buttons/screener"/>
            <g:render template="dashLets/buttons/backTest"/>
            <g:render template="dashLets/buttons/alerting"/>
            <g:render template="dashLets/buttons/portfolio"/>
            <g:render template="dashLets/buttons/calculator"/>
            <g:render template="dashLets/buttons/correlation"/>
        </div>
    </div>
</div>

</body>
</html>
