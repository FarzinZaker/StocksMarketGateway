<%@ page import="stocks.RoleHelper" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="site.title"/></title>
    <!--[if lt IE 9]>
    <asset:javascript src="html5shiv.js"/>
    <![endif]-->
    <asset:stylesheet src="tilt.min.css"/>
</head>

<body>
<div class="container">
    <div class="row">
    <div class="col-xs-12">
        <sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
        <g:render template="/layouts/admin/flatMenu"/>
        </sec:ifAnyGranted>
        <sec:ifNotGranted roles="${RoleHelper.ROLE_ADMIN}">
            <sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
                <g:render template="/layouts/site/flatMenu"/>
            </sec:ifAnyGranted>
            <sec:ifNotGranted roles="${RoleHelper.ROLE_USER}">
                <sec:ifAnyGranted roles="${RoleHelper.ROLE_BROKER_USER}">
                    <g:render template="/layouts/brokerUser/flatMenu"/>
                </sec:ifAnyGranted>
                </div>
            </sec:ifNotGranted>
        </sec:ifNotGranted>
    </div>
</div>
</div>

<asset:javascript src="tilt.min.js"/>
%{--<asset:javascript src="lightbox-2.6.min.js"/>--}%
</body>
</html>
