
<script>
    var cashDataSource = new kendo.data.DataSource({
        transport: {
            type: 'odata',
            read: function (options) {
                $.ajax({
                    url: "${createLink(action: 'jsonPortfolioCashView', params: [id: params.id])}",
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
//                                $('#chartData').html(data.shareChartData);
//                        createPieChart(data.shareChartData);
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
                    currentValue: {type: "number"},
                    profitLoss: {type: "number"},
                    profitLossPercent: {type: "number"},
                    totalProfitLossPercent: {type: "number"}
                }
            },
            data: "data", // records are returned in the "data" field of the response
            total: "total"
        },
        aggregate: [
            {
                field: "cost",
                aggregate: "sum"
            },
            {
                field: "currentValue",
                aggregate: "sum"
            },
            {
                field: "profitLoss",
                aggregate: "sum"
            },
            {
                field: "totalProfitLossPercent",
                aggregate: "average"
            }
        ],
//        pageSize: 20,
        serverPaging: false,
        serverFiltering: true,
        serverSorting: true,
        group: {
            field: "clazzTitle",
            dir: "asc"
        }
    });
    $(document).ready(function () {

        $("#cashGrid").kendoGrid({
            dataSource: cashDataSource,
            dataBound: function (e) {

                var groups = cashDataSource.group()
                for (var i = 0; i < this.columns.length; i++) {
                    this.showColumn(this.columns[i].field);
                }
                for (var i = 0; i < groups.length; i++) {
                    this.hideColumn(groups[i].field);
                }
            },
//            height: 550,
            filterable: false,
            sortable: true,
            pageable: false,
            groupable: true,
            columns: [
                {
                    field: "clazzTitle",
//                    hidden:true,
                    title: "${message(code: 'portfolioItem.cashType.label')}"
                },
                {
                    field: "symbol",
                    title: "${message(code: 'portfolioItem.cash.label')}"
                },
                {
                    field: "currentValue",
                    title: "${message(code:'portfolioItem.remaining.label')}",
                    template: "#=formatNumber(currentValue)#",
                    footerTemplate: "<div>#=formatNumber(sum)#</div>"
                },
                {
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"},
                    template: '<a class="k-button" href="${createLink(controller: 'portfolio', action: 'portfolioItemView')}/#=id#" target="_blank">${message(code:'view')}</a>'
                }
            ]
        });

        resizeCashGridFont();
    });

    $(window).resize(function () {
        resizeCashGridFont();
    });

    function resizeCashGridFont() {
        var resolution = $(window).width();
        if (resolution <= 2000)
            $('#cashGrid').css('font-size', '14px');
        if (resolution <= 1600)
            $('#cashGrid').css('font-size', '13px');
        if (resolution <= 1280)
            $('#cashGrid').css('font-size', '12px');
        if (resolution <= 1024)
            $('#cashGrid').css('font-size', '11px');
        if (resolution <= 900)
            $('#cashGrid').css('font-size', '9px');
    }
</script>