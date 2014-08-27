<%@ page import="stocks.RoleHelper" %>
<sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
    <g:render template="/layouts/admin/menu"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
    <g:render template="/layouts/site/menu"/>
</sec:ifAnyGranted>
<sec:ifNotLoggedIn>
    <div class="k-rtl" id="menu" style="height: 51px;">
    </div>
</sec:ifNotLoggedIn>