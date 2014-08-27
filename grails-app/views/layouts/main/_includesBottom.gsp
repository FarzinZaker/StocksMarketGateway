<%@ page import="stocks.RoleHelper" %>

<asset:javascript src="bootstrap/bootstrap.js"/>
<asset:javascript src="common.js"/>

<sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
    <g:render template="/layouts/admin/includesBottom"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
    <g:render template="/layouts/site/includesBottom"/>
</sec:ifAnyGranted>