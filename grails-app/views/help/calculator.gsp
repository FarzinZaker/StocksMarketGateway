<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="calculator.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"calculator.help.title"), url:createLink(controller: 'help', action: 'calculator')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/underConstruction"/>
        </div>
    </div>
</div>
</body>
</html>