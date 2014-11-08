<%@ page import="stocks.RoleHelper" %>

<g:javascript library="jquery" plugin="jquery"/>
%{--kendo.ui start--}%
%{--<asset:stylesheet src="kendo.ui/kendo.common.min.css" rel="stylesheet"/>--}%
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.common-bootstrap.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.metro.min.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.rtl.min.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.menu.min.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.all.min.css')}"/>
%{--kendo.ui end--}%
<asset:javascript src="form-validator/form-validator.js"/>
<asset:javascript src="form-validator/national-code.js"/>
<asset:javascript src="dotdotdot.js"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/bootstrap', file: 'bootstrap-responsive.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/bootstrap', file: 'bootstrap.css')}"/>
<asset:stylesheet src="kendo.corrections.less"/>
<asset:stylesheet src="awesome/css/font-awesome.min.css"/>
<asset:stylesheet src="common.less"/>

<sec:ifLoggedIn>
    <sec:ifAnyGranted roles="${RoleHelper.ROLE_ADMIN},${RoleHelper.ROLE_BROKER_ADMIN}">
        <g:render template="/layouts/admin/includesTop"/>
    </sec:ifAnyGranted>
    <sec:ifNotGranted roles="${RoleHelper.ROLE_ADMIN},${RoleHelper.ROLE_BROKER_ADMIN}">
        <sec:ifAnyGranted roles="${RoleHelper.ROLE_USER},${RoleHelper.ROLE_BROKER_USER}">
            <g:render template="/layouts/site/includesTop"/>
        </sec:ifAnyGranted>
    </sec:ifNotGranted>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
    <asset:stylesheet src="site.less"/>
    <asset:stylesheet src="default.less"/>
</sec:ifNotLoggedIn>