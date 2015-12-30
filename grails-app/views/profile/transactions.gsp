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
    <title><g:message code='user.profile.transactions'/></title>
    <asset:javascript src="form-validator/security.js"/>

    <script language="javascript" type="text/javascript">
        var dataUrlParams = "user=${user?.id}";
        var editUrlParams = "user=${user?.id}";
    </script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.currentUser.profile'), url: createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'user.profile.transactions'), url: createLink(controller: 'profile', action: 'payment')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9" style="min-height:550px;">
            <g:render template="/transaction/list" model="${[readOnly: true]}"/>
        </div>
    </div>
</div>
</body>
</html>