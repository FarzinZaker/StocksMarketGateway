<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/30/13
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="user.profile.invite"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'user.profile.payment'), url: createLink(controller: 'profile', action: 'payment')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">
            <div>
                <g:message code="profile.invite.social.description"/>
            </div>

            <div class="socialNetworks">
                <iv:invitationLink provider="google">
                    <i class="fa fa-google" style="background-color:#da4a38;"></i>
                    <span>Google</span>
                </iv:invitationLink>
                <iv:invitationLink provider="yahoo">
                    <i class="fa fa-yahoo" style="background-color:#400191;"></i>
                    <span>Yahoo</span>
                </iv:invitationLink>
                %{--<iv:invitationLink provider="linkedin">--}%
                    %{--<i class="fa fa-linkedin" style="background-color:#0097bd;"></i>--}%
                    %{--<span>LinkedIn</span>--}%
                %{--</iv:invitationLink>--}%
                %{--<iv:invitationLink provider="twitter">--}%
                    %{--<i class="fa fa-twitter" style="background-color:#43b3e5;"></i>--}%
                    %{--<span>Twitter</span>--}%
                %{--</iv:invitationLink>--}%
                %{--<iv:invitationLink provider="facebook">--}%
                    %{--<i class="fa fa-facebook" style="background-color:#1f69b3;"></i>--}%
                    %{--<span>Facebook</span>--}%
                %{--</iv:invitationLink>--}%

            </div>

            <div>
                <g:message code="profile.invite.emailList.description"/>
            </div>

            <div>
                <form:form name="mailListForm" controller="invitation" action="emailList">
                    <form:textArea name="mailList" style="width: 500px;height:150px;margin-top:10px;direction: ltr;"/>
                    <div class="toolbar">
                        <form:submitButton text="${message(code: 'send')}"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>