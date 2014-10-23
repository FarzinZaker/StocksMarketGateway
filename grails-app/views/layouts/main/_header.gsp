<%@ page import="stocks.RoleHelper" %>

<sec:ifLoggedIn>
    <sec:ifAnyGranted roles="${RoleHelper.ROLE_BROKER_USER},${RoleHelper.ROLE_BROKER_ADMIN}">
        <g:render template="/layouts/broker/header"/>
    </sec:ifAnyGranted>
    <sec:ifNotGranted roles="${RoleHelper.ROLE_BROKER_USER},${RoleHelper.ROLE_BROKER_ADMIN}">

        <sec:ifAnyGranted roles="${RoleHelper.ROLE_USER},${RoleHelper.ROLE_ADMIN}">
            <g:render template="/layouts/site/header"/>
        </sec:ifAnyGranted>
    </sec:ifNotGranted>
</sec:ifLoggedIn>

<sec:ifNotLoggedIn>
    <g:render template="/layouts/site/header"/>
</sec:ifNotLoggedIn>