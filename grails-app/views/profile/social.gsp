<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 11/4/2015
  Time: 5:51 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="user.profile.social"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-share-square-o"></i> ' + message(code: 'user.profile.social'), url: createLink(controller: 'profile', action: 'social')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">
            <div class="social-connect">
                <div class="social-image">
                    <asset:image src="telegram.png"/>
                    <h3>Telegram</h3>
                </div>
                <g:if test="${telegramUser}">
                    <ul class="profile-info">
                        <li class="profile-field">
                            <div class="txt">${telegramUser.firstName} ${telegramUser.lastName}</div>

                            <div class="lbl"><g:message code="profile.fullName"/></div>
                        </li>
                        <li class="profile-field">
                            <div class="txt">${telegramUser.userName}</div>

                            <div class="lbl"><g:message code="profile.userName"/></div>
                        </li>
                    </ul>
                </g:if>
                <g:else>
                    <div class="connect-guide">
                        <p>
                            <g:message code="social.connect.telegram.notConnectedMessage"/>
                        </p>
                        <pre style="direction: ltr;margin-top:5px;display: inline-block;">connect ${key}</pre>

                        <div class="toolbar" style="margin-top:0;">
                            <form:button text="${message(code: 'social.connect.telegram.check')}"
                                         onclick="location.reload();"/>
                        </div>
                    </div>
                </g:else>
            </div>
        </div>
    </div>
</div>
</body>
</html>