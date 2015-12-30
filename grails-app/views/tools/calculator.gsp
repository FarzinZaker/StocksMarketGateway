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
    <asset:javascript src="highcharts/highcharts.js"/>
    <asset:javascript src="highcharts/highcharts-ng.min.js"/>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
    <script language="JavaScript">
        var dollarPrice = ${dollarPrice};
        var onsPrice = ${onsPrice};
        var coinPrice = ${coinPrice};
        var contracts = <format:html value="${contracts as JSON}"/>;
        var margin = <gs:value name="tools.calculator.margin" />;
    </script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js', file: 'tools.calculator.controller.js')}"></script>
    <style>
    .highlight {
        background-color: #10c4b2 !important;
        transition: background 200ms !important;
    }
    </style>
</head>

<body>
<div class="container k-rtl calculator" id="ngController" ng-controller="toolsCalculatorController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.tools'), url: createLink(controller: 'tools')],
                    [text: '<i class="fa fa-calculator"></i> ' + message(code: 'menu.tools.calculator'), url: createLink(controller: 'tools', action: 'calculator')]
            ]}"/>
        </div>
    </div>

    %{--<div class="row">--}%
    %{--<div class="col-xs-12">--}%
    %{--<h1 class="cyan">--}%
    %{--<i class="fa fa-calculator"></i>--}%
    %{--<g:message code="tools.calculator.title"/>--}%
    %{--</h1>--}%

    %{--<p><g:message code="tools.calculator.description"/></p>--}%
    %{--</div>--}%
    %{--</div>--}%

    <div class="row">
        <div class="col-xs-12">
            <table class="calculator-container">
                <tr>
                    <td width="300px;">
                        <div class="parameters">
                            <g:render template="calculator/parameters"/>
                        </div>

                        <div>
                            <g:render template="calculator/chart"/>
                        </div>
                    </td>
                    <td rowspan="2">

                        <div class="whitePanel smallPadding calculator">
                            <g:render template="calculator/grid"/>
                        </div>

                        <div class="baseInfo">
                            <g:render template="calculator/baseInfo"/>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div id="timer"></div>
    <script language="javascript" type="text/javascript">

        $(document).ready(function () {
            loadData();
            $('#timer').timer({
                delay: 60000,
                repeat: true,
                autostart: true,
                callback: function (index) {
                    loadData();
                }
            });
        });

        function loadData() {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'calculatorJson')}'
            }).done(function (response) {
                var $scope = angular.element(document.getElementById('ngController')).scope();
                $scope.dollarPrice = response.dollarPrice;
                $scope.onsPrice = response.onsPrice;
                $scope.coinPrice = response.coinPrice;
                $scope.contracts = response.contracts;
                $scope.$apply();
                onScopeChange(null, null, angular.element(document.getElementById('ngController')).scope());
            });
        }
    </script>
</div>
</body>
</html>