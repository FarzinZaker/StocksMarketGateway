<%@ page import="stocks.RoleHelper" %>
<sec:ifLoggedIn>
    <sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
        <g:render template="/layouts/admin/menu"/>
    </sec:ifAnyGranted>
    <sec:ifNotGranted roles="${RoleHelper.ROLE_ADMIN}">
        <sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
            <g:render template="/layouts/site/menu"/>
        </sec:ifAnyGranted>
        <sec:ifNotGranted roles="${RoleHelper.ROLE_USER}">
            <sec:ifAnyGranted roles="${RoleHelper.ROLE_BROKER_ADMIN}">
            </sec:ifAnyGranted>
            <sec:ifNotGranted roles="${RoleHelper.ROLE_BROKER_ADMIN}">
                <sec:ifAnyGranted roles="${RoleHelper.ROLE_BROKER_USER}">
                    <g:render template="/layouts/brokerUser/menu"/>
                </sec:ifAnyGranted>
            </sec:ifNotGranted>
        </sec:ifNotGranted>
    </sec:ifNotGranted>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
    <div class="k-rtl" id="menu" style="height: 51px;">
    </div>
</sec:ifNotLoggedIn>