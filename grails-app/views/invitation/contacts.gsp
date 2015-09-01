<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="grails.plugin.invitation.pickForm.submit"/></title>
    <link href="${resource(dir: 'css', file: 'inviter.css')}" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'inviter.js')}"></script>
</head>


<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: message(code: 'user.profile.invite'), url: createLink(controller: 'profile', action: 'invite')],
                    [text: '<i class="fa fa-user"></i> ' + message(code: 'select'), url: createLink(controller: 'invitation', action: 'emailList', params: [mailList: params.mailList])]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">
            <g:render template="/profile/menu"/>
        </div>

        <div class="col-xs-9">

            <h3><g:message code="grails.plugin.invitation.pickForm.submit"/></h3>

            <iv:contacts contacts="${contacts}"/>

            <h3><g:message code="grails.plugin.invitation.pickForm.title"/></h3>

            <iv:messageForm provider="${provider}"
                            link="${createLink(uri: '/', absolute: true)}"
                            subject="${message(code: 'grails.plugin.invitation.subject')}"
                            description="grails invitation"
                            caption="picture caption"
                            picture="http://www.grails.org/static/cXmUZIAv28XIiNgkRiz4RRl21TsGZ5HoGpZw1UITNyV.png"
                            redirectUrl="${createLink(uri: '/success', absolute: true)}"
                            message="${render(template: 'invitation')}"
                            canEditMessage="true"/>
        </div>
    </div>
</div>

</body>
</html>
