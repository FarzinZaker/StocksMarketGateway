<%@ page import="grails.converters.JSON" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="grails.plugin.invitation.pickForm.submit"/></title>
    <link href="${resource(dir: 'css', file: 'inviter.css')}" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'inviter.js')}"></script>
</head>


<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-envelope"></i> ' + message(code: 'user.profile.invite'), url: createLink(controller: 'profile', action: 'invite')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <g:render template="/profile/menu"/>
        </div>

        <div class="col-xs-9 k-rtl">

            <div id="grid">
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">

    var list = <format:html  value="${list as JSON}"/>;
    $(document).ready(function () {


        $("#grid").html('').kendoGrid({

            dataSource: {
                data : list
            },
            filterable: false,
            sortable: true,
            scrollable: true,
            columns: [
                {
                    field: "address",
                    title: "${message(code:'invitation.result.address.label')}",
                    attributes: {style: "text-align: left"},
                    headerAttributes: {style: "text-align: left"}
                },
                {
                    field: "state",
                    title: "${message(code:'invitation.result.state.label')}",
                    template: "<img width='16px' src='${createLink(uri: '/images/')}#=data.state#.png' />",
                    width:"50px",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    field: "description",
                    title: "${message(code:'invitation.result.description.label')}"
                }
            ]
        });
    });
</script>
</body>
</html>
