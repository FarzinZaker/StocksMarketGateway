<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.view.title"/></title>
    <asset:javascript src="highcharts/highcharts.js"/>
    <asset:javascript src="highcharts/highcharts-3d.js"/>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.view.title"/></h1>
            <div class="k-rtl k-header">
                <div id="toolbar"></div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-xs-8">
            <div class="k-rtl">
                <div id="grid"></div>
            </div>
        </div>
        <div class="col-xs-4">
            <div>
                <div id="shareChart" class="chart"/>
            </div>
        </div>
    </div>
</div>

<script>
    Highcharts.theme = {
        colors: ['#10c4b2', '#ff7663', '#ffb74f', '#a2df53', '#1c9ec4', '#ff63a5'] // flat
//        colors: ['#8ebc00', '#309b46', '#25a0da', '#ff6900', '#e61e26', '#d8e404'] // metro
    };
    Highcharts.setOptions(Highcharts.theme);

    $(document).ready(function () {
        $("#toolbar").kendoToolBar({
            items: [
                { type: "button", text: "<g:message code="portfolio.list.title"/>", click: btnPortfolioListClick },
                { type: "button", text: "<g:message code="portfolio.property.manage"/>", click: btnPortfolioManageClick }
            ]
        });

        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    type: 'odata',
                    read: function(options) {
                        $.ajax({
                            url: "${createLink(action: 'jsonPortfolioView', params: [id: params.id])}",
                            dataType: "json",
                            type: "POST",
                            success: function(data) {
                                createPieChart(data.shareChartData)
                                options.success(data.gridData);
                            }
                        })
                    },
                    parameterMap: function (data, action) {
                        if (action === "read") {
                            data.parent = $('#parent').val();
                            return data;
                        } else {
                            return data;
                        }
                    }
                },
                schema: {
                    model: {
                        id: "id",
                        fields: {
                            id: { type: "number" },
                            symbol: { type: "string" },
                            shareCount: { type: "number" },
                            cost: { type: "number" },
                            avgPrice: { type: "number" },
                            shareValue: {type: "number" },
                            currentValue: { type: "number" }
                        }
                    },
                    data: "data", // records are returned in the "data" field of the response
                    total: "total"
                },
                pageSize: 20,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true
            },
            height: 550,
            filterable: false,
            sortable: true,
            pageable: true,
            columns: [
                {
                    field: "symbol",
                    title: "${message(code:'symbol.label')}"
                } ,
                {
                    field: "shareCount",
                    title: "${message(code:'portfolioItem.shareCount.label')}"
                } ,
                {
                    field: "cost",
                    title: "${message(code:'portfolioItem.cost.label')}"
                } ,
                {
                    field: "avgPrice",
                    title: "${message(code:'portfolioItem.avgPrice.label')}"
                } ,
                {
                    field: "shareValue",
                    title: "${message(code:'portfolioItem.shareValue.label')}"
                } ,
                {
                    field: "currentValue",
                    title: "${message(code:'portfolioItem.currentValue.label')}"
                } ,
                { command: { text: "${message(code:'view')}", click: viewGridItem }, title: "", width: "85px", headerAttributes: { style: "text-align: center"} }
            ]
        });

        function createPieChart(shareChartData) {
            $('#shareChart').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: ''
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>',
                    useHTML: true
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: false,
                            format: '{point.name}',
                            useHTML: true
                        },
                        showInLegend: true
                    }
                },
                legend: {
                    borderWidth: 0,
                    useHTML: true
                },
                series: [{
                    type: 'pie',
                    name: '<g:message code="portfolio.view.shareChartSerie.label"/>',
                    data: shareChartData
                }]
            });
        };
    });

    function viewGridItem(e) {
        window.location.href = "${createLink(action: 'portfolioItemView')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function btnPortfolioListClick(e) {
        window.location.href = "${createLink(action: 'list')}/";
    }

    function btnPortfolioManageClick(e) {
        window.location.href = "${createLink(action: 'portfolioManage')}/${params.id}";
    }
</script>

</body>
</html>