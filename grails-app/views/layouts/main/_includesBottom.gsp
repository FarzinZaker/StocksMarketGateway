<%@ page import="stocks.RoleHelper" %>

<script language="javascript" type="text/javascript" src="${resource(dir:'js/bootstrap', file: 'bootstrap.js')}"></script>
<asset:javascript src="common.js"/>

<sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
    <g:render template="/layouts/admin/includesBottom"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
    <g:render template="/layouts/site/includesBottom"/>
</sec:ifAnyGranted>