<div class="chart-container">
    <div id="chart"></div>
</div>

<script language="javascript" type="text/javascript">

    Highcharts.theme = {
        colors: ['#10c4b2', '#ff7663', '#ffb74f', '#a2df53', '#1c9ec4', '#ff63a5'] // flat
    };
    Highcharts.setOptions(Highcharts.theme);

    var chartConfig = {
        chart: {
            backgroundColor: 'transparent'
        },
        plotOptions: {
            series: {
                stacking: ''
            },
            line: {
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: false
                        }
                    }
                },
                dataLabels: {
                    enabled: true,
                    useHTML: true,
                    formatter: function () {
                        if (this.x < 2)
                            return '';
                        return this.series.name + ': ' + (this.y * 100) + '%'
                    }
                }
            }
        },
        title: {
            text: ' '
        },
        tooltip: {
            useHTML: true,
            formatter: function () {
                if (this.series.name == '${message(code:'tools.calculator.borrowingRate')}')
                    return false;
                if (this.series.name == '${message(code:'tools.calculator.lendingRate')}')
                    return false;
                if (this.series.name == '${message(code:'tools.calculator.expectedReturn')}')
                    return false;
                return this.series.name + ': <b>%' + (Math.round(this.point.y * 1000) / 10) + '</b>'
            }
        },
        legend: {
            borderWidth: 0,
            useHTML: true,
//            title: {
//                style: {
//                    fontWeight: 'normal'
//                }
//            }
        },
        credits: {
            enabled: false
        },
        loading: false,
        size: {},
        yAxis: {
            title: {
                text: ''
            },
            labels: {
                formatter: function () {
                    return (this.value * 100) + '%'
                }
            }
        },
        xAxis: {
            title: {
                text: ''
            },
            tickColor: '#fffffff',
            labels: {
                formatter: function () {
                    return ''
                }
            },
            startOnTick: false,
            endOnTick: false,
            minPadding: 0,
            maxPadding: 0
        }
    };

    var chart;
    function onScopeChange(newValues, oldValues, scope) {
        chartConfig.series = $.map(scope.contracts, function (contract, index) {
            return {"name": contract.name, "data": [[1, scope.netArbitrage(contract)]], type: "column"};
        }).concat(
                [{
                    "name": '${message(code:'tools.calculator.borrowingRate')}',
                    "data": [[0, scope.borrowingRate], [1, scope.borrowingRate], [2, scope.borrowingRate]],
                    type: "line",
                    dashStyle: 'dash',
                    showInLegend: false
                }],
                [{
                    "name": '${message(code:'tools.calculator.lendingRate')}',
                    "data": [[0, scope.lendingRate], [1, scope.lendingRate], [2, scope.lendingRate]],
                    type: "line",
                    dashStyle: 'dash',
                    showInLegend: false
                }],
                [{
                    "name": '${message(code:'tools.calculator.expectedReturn')}',
                    "data": [[0, scope.expectedReturn], [1, scope.expectedReturn], [2, scope.expectedReturn]],
                    type: "line",
                    dashStyle: 'dash',
                    showInLegend: false
                }]
        );
        chart = $('#chart').highcharts(chartConfig);
//        chart = new Highcharts.Chart(chartConfig);
    }

    $(function () {
        onScopeChange(null, null, angular.element(document.getElementById('ngController')).scope());
    });
</script>
