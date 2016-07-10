<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 7/10/2016
  Time: 3:00 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.action.import.result"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.users'), url:createLink(controller: 'user')],
                    [text: message(code:'user.list.title'), url:createLink(controller: 'user', action: 'list')],
                    [text: '<i class="fa fa-download"></i> ' + message(code: 'users.import'), url: '#']
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">

            <table class="portfolioActionImportResult">
                <tr>
                    <th><g:message code="user.firstName.label"/></th>
                    <th><g:message code="user.lastName.label"/></th>
                    <th><g:message code="user.mobile.label"/></th>
                    <th><g:message code="user.sex.label"/></th>
                    <th><g:message code="user.nationalCode.label"/></th>
                    <th><g:message code="user.city.label"/></th>
                    <th><g:message code="user.email.label"/></th>
                    <th><g:message code="user.broker.label"/></th>
                    <th><g:message code="portfolio.action.import.result.errors"/></th>
                </tr>
                <g:each in="${result}" var="user">
                    <tr class="${user.errors?.size() ? 'hasError' : ''}">
                        <td>${user?.firstName}</td>
                        <td>${user?.lastName}</td>
                        <td>${user?.mobile}</td>
                        <td><g:message code="user.sex.${user?.sex}"/></td>
                        <td>${user?.nationalCode}</td>
                        <td>${user?.city?.name}</td>
                        <td>${user?.email}</td>
                        <td>${user?.broker?.name}</td>
                        <td>${user?.errors?.join('<br/>')}</td>
                    </tr>
                </g:each>
            </table>
        </div>
    </div>
</div>
</body>
</html>