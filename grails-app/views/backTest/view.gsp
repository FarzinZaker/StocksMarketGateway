<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 11/03/2015
  Time: 16:21
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${'backTest.view.title'}"
                      args="${[backTest?.tradeStrategy?.name, "${backTest.symbol?.persianName} - ${backTest.symbol?.persianCode}"]}"/></title>
    <asset:javascript src="highstocks/highstock.js"/>
    <asset:javascript src="highstocks/modules/exporting.js"/>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui/jalali', file: 'JalaliDate.js')}"></script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js', file: 'analysis.backTest.js')}"></script>
    <script language="javascript" type="text/javascript">
        backTestStatus = '${backTest.status}';
        backTestStartTime = ${backTest.startDate.time};
        signals = <format:html value="${signals as JSON}"/>;
        portfolioLogs = <format:html value="${logs as JSON}"/>;
        dataUrl = '<g:createLink action="viewJson" id="${backTest.id}"/>';
        summaryUrl = '<g:createLink action="summary" id="${backTest.id}"/>';
    </script>

</head>

<body>

<div class="container-fluid">
    <div id="dataLoadTimer"></div>

    <div id="dataShowTimer"></div>
    <form:form name="backTestForm" controller="backTest" action="save">
        <div class="row-fluid">
            <div class="col-xs-12" id="pageHeader">
                <h1><g:message code="${'backTest.view.title'}"
                               args="${[backTest?.tradeStrategy?.name, "${backTest.symbol?.persianName} - ${backTest.symbol?.persianCode}"]}"/></h1>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12" id="pnlSummary">
                <g:render template="summary" model="[summary: summery]"/>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <g:render template="chart" model="[backTest: backTest]"/>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <g:render template="signals"/>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>