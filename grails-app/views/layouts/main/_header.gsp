<%@ page import="stocks.RoleHelper" %>

<sec:ifAnyGranted roles="${RoleHelper.ROLE_BROKER_USER},${RoleHelper.ROLE_BROKER_ADMIN}">
    <g:render template="/layouts/broker/header"/>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="${RoleHelper.ROLE_USER},${RoleHelper.ROLE_ADMIN}">
    <g:render template="/layouts/site/header"/>
</sec:ifAnyGranted>

<sec:ifNotLoggedIn>
    <g:render template="/layouts/site/header"/>
</sec:ifNotLoggedIn>