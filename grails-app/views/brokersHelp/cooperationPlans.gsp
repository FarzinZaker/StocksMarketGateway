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
    <title><g:message code="brokers.collaboration.plans"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'brokers.title'), url:createLink(controller: 'brokersHelp')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"brokers.collaboration.plans"), url:createLink(controller: 'brokersHelp', action: 'cooperationPlans')]
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