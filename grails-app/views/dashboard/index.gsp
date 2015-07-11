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
            <g:render template="dashLets/newsFeed"/>
        </div>

        <div class="col-sm-3">
            <g:render template="dashLets/heatMap"/>
            <g:render template="dashLets/screener"/>
            <g:render template="dashLets/backTest"/>
            <g:render template="dashLets/calculator"/>
        </div>
    </div>
</div>

</body>
</html>
