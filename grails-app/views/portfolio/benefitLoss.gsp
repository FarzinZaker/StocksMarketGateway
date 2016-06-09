<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.reports.benefitLoss"/></title>
    %{--<asset:javascript src="highcharts/highcharts.js"/>--}%
    %{--<asset:javascript src="highcharts/modules/data.js"/>--}%
    %{--<asset:javascript src="highcharts/modules/drilldown.js"/>--}%
    %{--<asset:javascript src="highcharts/highcharts-3d.js"/>--}%
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: portfolio.name, url: createLink(controller: 'portfolio', action: 'build', id: portfolio.id)],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'portfolio.reports.benefitLoss'), url: createLink(controller: 'portfolio', action: 'benefitLoss', id: params.id)]
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
                        <li>
                            <g:message code="benfitLoss.grid"/>
                        </li>
                        <li class="k-state-active">
                            <g:message code="benfitLoss.chart"/>
                        </li>
                    </ul>

                    <div>
                        <div id="grid"></div>
                    </div>

                    <div>
                        <g:render template="benefitLossChart"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var dataSource = new kendo.data.DataSource({
        transport: {
            type: 'odata',
            read: function (options) {
                $.ajax({
                    url: "${createLink(action: 'benefitLossJson', params: [id: params.id])}",
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        options.success(data);
                        createChart(data.data);
//                        createPieChart(data.shareChartData);
                    }
                })
            }
        },
        schema: {
            model: {
                id: "date",
                fields: {
                    date: {type: "string"},
                    actualBenefitLoss: {type: "number"},
                    potentialBenefitLoss: {type: "number"},
                    totalBenefitLoss: {type: "number"}
                }
            },
            data: "data", // records are returned in the "data" field of the response
            total: "total"
        },
//        pageSize: 20,
        serverPaging: false,
        serverFiltering: true,
        serverSorting: true
    });

    $(document).ready(function () {

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
                {type: "button", text: "<g:message code="portfolio.view.title"/>", click: btnViewPortfolioClick},
                {type: "button", text: "<g:message code="portfolio.property.manage"/>", click: btnPortfolioManageClick}
            ]
        });

        $("#grid").kendoGrid({
            dataSource: dataSource,
            filterable: false,
            sortable: true,
            pageable: false,
            groupable: false,
            columns: [
                {
                    field: "date",
                    title: "${message(code: 'portfolio.benefitLoss.date')}"
                },
                {
                    field: "actualBenefitLoss",
                    title: "${message(code: 'portfolio.benefitLoss.actual')}",
                    template: "<div class='#:actualBenefitLoss>0?\"positive\":\"negative\"#'>#=formatNumber(actualBenefitLoss)#</div>"
                },
                {
                    field: "potentialBenefitLoss",
                    title: "${message(code: 'portfolio.benefitLoss.potential')}",
                    template: "<div class='#:potentialBenefitLoss>0?\"positive\":\"negative\"#'>#=formatNumber(potentialBenefitLoss)#</div>"
                },
                {
                    field: "totalBenefitLoss",
                    title: "${message(code:'portfolio.benefitLoss.total')}",
                    template: "<div class='#:totalBenefitLoss>0?\"positive\":\"negative\"#'>#=formatNumber(totalBenefitLoss)#</div>"
                }
            ]
        });

    });

    function btnPortfolioListClick(e) {
        window.location.href = "${createLink(action: 'list')}/";
    }

    function btnPortfolioManageClick(e) {
        window.location.href = "${createLink(action: 'portfolioManage')}/${params.id}";
    }

    function btnViewPortfolioClick(e) {
        window.location.href = "${createLink(action: 'portfolioView')}/${params.id}";
    }

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }
</script>

</body>
</html>