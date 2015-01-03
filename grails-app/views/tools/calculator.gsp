<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 12/30/14
  Time: 2:41 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="tools.calculator.title"/></title>
    <script language="javascript" type="text/javascript" src="${resource(dir: 'js', file: 'angular.min.js')}"></script>
    <asset:javascript src="moment.js"/>
    <asset:javascript src="XIRR.js"/>
    <script language="JavaScript">
        var dollarPrice = ${dollarPrice};
        var onsPrice = ${onsPrice};
        var coinPrice = ${coinPrice};
        var contracts = <format:html value="${contracts as JSON}"/>
    </script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js', file: 'tools.calculator.controller.js')}"></script>
</head>

<body>
<div class="container-fluid k-rtl calculator" id="ngController" ng-controller="toolsCalculatorController">
<div class="row-fluid">
    <div class="col-xs-12">
        <h1><g:message code="tools.calculator.title"/></h1>

        <p><g:message code="tools.calculator.description"/></p>
    </div>

<g:render template="calculator/parameters"/>
<g:render template="calculator/baseInfo"/>

<div class="row-fluid">
    <div class="col-xs-12">
        <h2><g:message code="tools.calculator.result.title"/></h2>
    </div>
</div>

<div class="row-fluid">
<div class="col-sm-8">
    <g:render template="calculator/grid"/>
</div>

<div class="col-sm-4">
    <g:render template="calculator/chart"/>
</div>
</div>
</div>
</body>
</html>