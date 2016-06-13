<script language="javascript" type="text/javascript">
    function createPieChart(shareChartData) {
        // Internationalization
        Highcharts.setOptions({
            lang: {
                drillUpText: '◁ بازگشت'
            }
        });

        var options = {

            chart: {
//                height: 300
//                type: 'pie',
//                options3d: {
//                    enabled: true,
//                    alpha: 45,
//                    beta: 0
//                }
            },

            title: {
                text: ''
            },

            xAxis: {
                categories: true
            },

            drilldown: {
                series: shareChartData.drilldown
            },
            tooltip: {
                pointFormat: '<div style="direction:rtl;"><b>{point.value:,.0f}</b></div><div style="direction:rtl;"><b>{point.percentage:.1f}%</b></div>',
                useHTML: true
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        enabled: false
                    },
                    shadow: false
                },
                pie: {
                    showInLegend: true
                }
            },
            legend: {
                borderWidth: 0,
                useHTML: true,
                labelFormat: '<div style="direction:rtl;">{name}</div>'
            },

            series: [{
                name: 'Overview',
                colorByPoint: true,
                data: shareChartData.categories
            }]
        };

        options.chart.renderTo = 'shareChart';
        options.chart.type = 'pie';
        new Highcharts.Chart(options);
    }
</script>