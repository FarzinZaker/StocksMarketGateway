<%@ page import="stocks.RoleHelper" %>

<g:javascript library="jquery" plugin="jquery"/>
%{--kendo.ui start--}%
%{--<asset:stylesheet src="kendo.ui/kendo.common.min.css" rel="stylesheet"/>--}%
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.common-bootstrap.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.metro.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.rtl.min.css')}"/>
%{--kendo.ui end--}%
<asset:javascript src="form-validator/form-validator.js"/>
<asset:javascript src="form-validator/national-code.js"/>
<asset:javascript src="dotdotdot.js"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/bootstrap', file: 'bootstrap.css')}"/>
<asset:stylesheet src="kendo.corrections.less"/>
<asset:stylesheet src="awesome/css/font-awesome.min.css"/>
<asset:stylesheet src="common.less"/>

<script language="javascript" type="text/javascript" src="${resource(dir:'js/kendo.ui/jalali', file:'JalaliDate.js')}"></script>
<script language="javascript" type="text/javascript" src="${resource(dir:'js/kendo.ui', file:'kendo.all.js')}"></script>
<script language="javascript" type="text/javascript" src="${resource(dir:'js/kendo.ui/jalali', file:'fa-IR.js')}"></script>

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