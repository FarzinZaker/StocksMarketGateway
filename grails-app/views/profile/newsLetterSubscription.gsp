<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 9/24/14
  Time: 12:56 PM
--%>

<%@ page import="grails.converters.JSON;stocks.messaging.NewsLetterCategory;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="user.profile.newsLetterSubscription"/></title>
    <asset:javascript src="form-validator/security.js"/>
    <asset:javascript src="jquery.cropit.min.js"/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-bullhorn"></i> ' + message(code: 'user.profile.newsLetterSubscription'), url: createLink(controller: 'profile', action: 'newsLetterSubscription')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">
            <form:form name="subscriptionForm" controller="newsLetter" action="saveUserSubscription">

                <form:field fieldName="user.newsLetterCategories">
                    <form:hidden name="userId" value="${user?.id}"/>
                    <div style="width: 510px;">
                        <g:each in="${user?.newsLetterCategories?.sort{it?.name}}" var="category">
                            <form:checkbox name="category_${category?.id}" text="${category.name}" checked="${true}" style="width: 250px;"/>
                        </g:each>
                        <g:each in="${NewsLetterCategory.findAllByDeleted(false)?.sort{it?.name}}" var="category">
                            <g:if test="${!user?.newsLetterCategories?.collect { it.id }?.contains(category?.id)}">
                                <form:checkbox name="category_${category?.id}" text="${category.name}" checked="${false}"
                                               style="width: 250px"/>
                            </g:if>
                        </g:each>
                    </div>
                </form:field>
                <form:submitButton text="${message(code:'newsLetterSubscription.sumbit.button')}"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>