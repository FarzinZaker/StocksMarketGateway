<%@ page import="stocks.RoleHelper" %>

<g:javascript library="jquery" plugin="jquery"/>
%{--kendo.ui start--}%
%{--<asset:stylesheet src="kendo.ui/kendo.common.min.css" rel="stylesheet"/>--}%
<asset:stylesheet src="kendo.ui/kendo.common-bootstrap.min.css" rel="stylesheet"/>
<asset:stylesheet src="kendo.ui/kendo.metro.min.css" rel="stylesheet"/>
<asset:stylesheet src="kendo.ui/kendo.rtl.min.css" rel="stylesheet"/>
<asset:stylesheet src="kendo.ui/kendo.menu.min.css" rel="stylesheet"/>
<asset:javascript src="kendo.ui/kendo.all.min.js"/>
%{--kendo.ui end--}%
<asset:javascript src="form-validator/form-validator.js"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<asset:stylesheet src="bootstrap/bootstrap-responsive.css"/>
<asset:stylesheet src="bootstrap/bootstrap.css"/>
<asset:stylesheet src="kendo.corrections.less"/>
<asset:stylesheet src="common.less"/>

<sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN}">
    <g:render template="/layouts/admin/includesTop"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="${RoleHelper.ROLE_USER}">
    <g:render template="/layouts/site/includesTop"/>
</sec:ifAnyGranted>
<sec:ifNotLoggedIn>
    <asset:stylesheet src="default.less"/>
</sec:ifNotLoggedIn>