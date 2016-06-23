<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.view.title"/></title>
    <asset:javascript src="highcharts/highcharts.js"/>
    <asset:javascript src="highcharts/modules/data.js"/>
    <asset:javascript src="highcharts/modules/drilldown.js"/>
    <asset:javascript src="highcharts/highcharts-3d.js"/>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: portfolio.name, url: createLink(controller: 'portfolio', action: 'build', id: portfolio.id)],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'portfolio.view.title'), url: createLink(controller: 'portfolio', action: 'portfolioView', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
            %{--<i class="fa fa-shopping-cart"></i>--}%
            %{--<g:message code="portfolio.view.title"/>--}%
            %{--</h1>--}%

            <div class="k-rtl k-header">
                <div id="toolbar"></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="k-rtl">
                <div id="tabstrip">
                    <ul>
                        <li class="k-state-active">
                            <g:message code="benfitLoss.grid"/>
                        </li>
                        <li>
                            <g:message code="benfitLoss.chart"/>
                        </li>
                    </ul>

                    <div>
                        <div id="grid"></div>
                        <div id="cashGrid" style="margin-top:20px;"></div>
                    </div>

                    <div>
                        <div id="shareChart" class="chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<g:render template="view/chart"/>
<g:render template="view/grid"/>
<g:render template="view/cashGrid"/>

<script language="javascript">
    $(document).ready(function(){

        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            }
        });

        $("#toolbar").kendoToolBar({
            items: [
                {type: "button", text: "<g:message code="portfolio.list.title"/>", click: btnPortfolioListClick},
                {type: "button", text: "<g:message code="portfolio.property.manage"/>", click: btnPortfolioManageClick},
                {type: "button", text: "<g:message code="portfolio.reports.benefitLoss"/>", click: btnBenefitLossClick}
            ]
        });
    });
</script>

</body>
</html>