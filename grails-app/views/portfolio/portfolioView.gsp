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
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: portfolio.name, url: createLink(controller: 'portfolio', action: 'build', id: portfolio.id)],
                    [text: message(code: 'portfolio.view.title'), url: createLink(controller: 'portfolio', action: 'portfolioView', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="darkBlue">
                <i class="fa fa-shopping-cart"></i>
                <g:message code="portfolio.view.title"/>
            </h1>

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
            <div style="direction:ltr">
                <div id="shareChart" class="chart"/>
            </div>
        </div>
        <pre id="chartData"></pre>
    </div>
</div>

<g:render template="view/chart"/>
<script>
    //    Highcharts.theme = {
    //        colors: ['#10c4b2', '#ff7663', '#ffb74f', '#a2df53', '#1c9ec4', '#ff63a5'] // flat
    //        colors: ['#8ebc00', '#309b46', '#25a0da', '#ff6900', '#e61e26', '#d8e404'] // metro
    //    };
    //    Highcharts.setOptions(Highcharts.theme);

    $(document).ready(function () {
        $("#toolbar").kendoToolBar({
            items: [
                {type: "button", text: "<g:message code="portfolio.list.title"/>", click: btnPortfolioListClick},
                {type: "button", text: "<g:message code="portfolio.property.manage"/>", click: btnPortfolioManageClick}
            ]
        });

        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    type: 'odata',
                    read: function (options) {
                        $.ajax({
                            url: "${createLink(action: 'jsonPortfolioView', params: [id: params.id])}",
                            dataType: "json",
                            type: "POST",
                            success: function (data) {
//                                $('#chartData').html(data.shareChartData);
                                createPieChart(data.shareChartData);
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
                            id: {type: "number"},
                            clazz: {type: "string"},
                            clazzTitle: {type: "string"},
                            symbol: {type: "string"},
                            shareCount: {type: "number"},
                            cost: {type: "number"},
                            avgPrice: {type: "number"},
                            shareValue: {type: "number"},
                            currentValue: {type: "number"}
                        }
                    },
                    data: "data", // records are returned in the "data" field of the response
                    total: "total"
                },
                pageSize: 20,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true,
                group: {
                    field: "clazzTitle",
                    dir: "asc"
                }
            },
//            height: 550,
            filterable: false,
            sortable: true,
            pageable: true,
            groupable: true,
            columns: [
                {
                    field: "clazzTitle",
                    title: "${message(code: 'portfolioItem.type.label')}"
                },
                {
                    field: "symbol",
                    title: "${message(code: 'portfolioItem.property.label')}"
                },
                {
                    field: "shareCount",
                    title: "${message(code:'portfolioItem.shareCount.label')}",
                    template: "#=formatNumber(shareCount)#"
                },
                {
                    field: "cost",
                    title: "${message(code:'portfolioItem.cost.label')}",
                    template: "#=formatNumber(cost)#"
                },
                {
                    field: "avgPrice",
                    title: "${message(code:'portfolioItem.avgPrice.label')}",
                    template: "#=formatNumber(avgPrice)#"
                },
                {
                    field: "shareValue",
                    title: "${message(code:'portfolioItem.shareValue.label')}",
                    template: "#=formatNumber(shareValue)#"
                },
                {
                    field: "currentValue",
                    title: "${message(code:'portfolioItem.currentValue.label')}",
                    template: "#=formatNumber(currentValue)#"
                },
                {
                    command: {text: "${message(code:'view')}", click: viewGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
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

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }
</script>

</body>
</html>