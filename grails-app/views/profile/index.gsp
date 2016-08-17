<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 9/24/14
  Time: 12:56 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="menu.currentUser.profile"/></title>
    <asset:javascript src="form-validator/security.js"/>
    <asset:javascript src="jquery.cropit.min.js"/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-user"></i> ' + message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">

            <ul class="profile-info">
                <li class="profile-field txt-align-center last">
                    <g:if test="${user?.image}">
                        <img src="${createLink(controller: 'image', id: user?.imageId)}"
                              class="profile-image"/>
                    </g:if>
                    <g:else>
                        <g:if test="${user?.externalImageUrl}">
                            <img src="${user?.externalImageUrl}"
                                  class="profile-image"/>
                        </g:if>
                        <g:else>
                            <asset:image src="user-noImage.png"
                                         class="profile-image"/>
                        </g:else>
                    </g:else>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt">${user?.nickname}</div>

                    <div class="lbl"><g:message code="profile.nickname"/></div>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt">${user?.username}</div>

                    <div class="lbl"><g:message code="profile.userName"/></div>
                </li>
                <li class="profile-field">
                    <div class="txt">${user?.firstName} ${user?.lastName}</div>

                    <div class="lbl"><g:message code="profile.fullName"/></div>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt"><g:message code="user.sex.${user.sex}"/></div>

                    <div class="lbl"><g:message code="profile.sex"/></div>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt">${user?.mobile}</div>

                    <div class="lbl"><g:message code="profile.mobile"/></div>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt">${user.nationalCode}</div>

                    <div class="lbl"><g:message code="profile.nationalCode"/></div>
                </li>
                <li class="profile-field" tabindex="0">
                    <div class="txt">${user?.city?.province?.name} - ${user?.city?.name}</div>

                    <div class="lbl"><g:message code="profile.city"/></div>
                </li>
                <li class="profile-field last" tabindex="0">
                    <div class="txt"><g:formatNumber number="${balance}" type="number"/> <g:message code="rial"/></div>

                    <div class="lbl"><g:message code="profile.balance"/></div>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>