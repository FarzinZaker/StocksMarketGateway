<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 11/03/2015
  Time: 16:21
--%>

<%@ page import="stocks.analysis.BackTestHelper; grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
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
            <div class="row-fluid">
                <div class="col-xs-12">
                    <layout:breadcrumb items="${[
                            [text: '', url: createLink(uri: '/')],
                            [text: message(code: 'menu.strategy'), url: createLink(controller: 'tradeStrategy')],
                            [text: backTest?.tradeStrategy?.name, url: createLink(controller: 'tradeStrategy', action: 'build', id: backTest?.tradeStrategy?.id)],
                            [text: message(code:'backTest.view.title',args:[backTest?.tradeStrategy?.name, "${backTest.symbol?.persianName} - ${backTest.symbol?.persianCode}"]), url: createLink(controller: 'backTest', action: 'view', id: backTest?.id)]
                    ]}"/>
                </div>
            </div>
            <div class="col-xs-12" id="pageHeader">
                <h1 style="float:right" class="magenta">
                    <i class="fa fa-magic"></i>
                    <g:message code="${'backTest.view.title'}"
                               args="${[backTest?.tradeStrategy?.name, "${backTest.symbol?.persianName} - ${backTest.symbol?.persianCode}"]}"/>
                </h1>

                <div style="float:left;margin-top:40px;font-size:12px;display: ${backTest.status == BackTestHelper.STATUS_FINISHED ? 'none': 'block'}">
                    <g:message code="backTest.currentDate"/>: <span id="currentDate"><format:jalaliDate
                        date="${backTest.currentDate}"/></span>
                </div>

                <div class="clear-fix"></div>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12" id="pnlSummary" style="margin-bottom:20px;display:${summery ? 'block' : 'none'}">
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