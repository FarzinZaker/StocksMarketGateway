
<script>
    var dataSource = new kendo.data.DataSource({
        transport: {
            type: 'odata',
            read: function (options) {
                $.ajax({
                    url: "${createLink(action: 'jsonPortfolioPropertyView', params: [id: params.id])}",
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

        $("#grid").kendoGrid({
            dataSource: dataSource,
            dataBound: function (e) {

                var groups = dataSource.group()
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
                    template: "#=formatNumber(cost)#",
                    footerTemplate: "<div>#=formatNumber(sum)#</div>"
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
                    template: "#=formatNumber(currentValue)#",
                    footerTemplate: "<div>#=formatNumber(sum)#</div>"
                },
                {
                    field: "profitLoss",
                    title: "${message(code:'portfolioItem.profitLoss.label')}",
                    template: "<div class='#:profitLoss>0?\"positive\":\"negative\"#'>#=formatNumber(profitLoss)#</div>",
                    footerTemplate: "<div class='#:sum>0?\"positive\":\"negative\"#'>#=formatNumber(sum)#</div>"
                },
                {
                    field: "totalProfitLossPercent",
                    title: "${message(code:'portfolioItem.profitLossPercent.label')}",
                    template: "<div class='#:profitLossPercent>0?\"positive\":\"negative\"#'>#=formatNumber(profitLossPercent)#%</div>",
                    footerTemplate: "<div class='#:average>0?\"positive\":\"negative\"#'>#=formatNumber(average)#%</div>"
                },
                {
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"},
                    template: '<a class="k-button" href="${createLink(controller: 'portfolio', action: 'portfolioItemView')}/#=id#" target="_blank">${message(code:'view')}</a>'
                }
            ]
        });

        resizeGridFont();
    });

    $(window).resize(function () {
        resizeGridFont();
    });

    function resizeGridFont() {
        var resolution = $(window).width();
        if (resolution <= 2000)
            $('#grid').css('font-size', '14px');
        if (resolution <= 1600)
            $('#grid').css('font-size', '13px');
        if (resolution <= 1280)
            $('#grid').css('font-size', '12px');
        if (resolution <= 1024)
            $('#grid').css('font-size', '11px');
        if (resolution <= 900)
            $('#grid').css('font-size', '9px');
    }

    function viewGridItem(e) {
        window.location.href = "${createLink(action: 'portfolioItemView')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function btnPortfolioListClick(e) {
        window.location.href = "${createLink(action: 'list')}/";
    }

    function btnPortfolioManageClick(e) {
        window.location.href = "${createLink(action: 'portfolioManage')}/${params.id}";
    }

    function btnBenefitLossClick(e) {
        window.location.href = "${createLink(action: 'benefitLoss')}/${params.id}";
    }

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }
</script>