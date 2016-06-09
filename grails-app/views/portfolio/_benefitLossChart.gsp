<asset:javascript src="highstocks/highstock.js"/>
<script language="javascript" type="text/javascript"
        src="${resource(dir: 'js/kendo.ui/jalali', file: 'JalaliDate.js')}"></script>

<div id="container" style="height: 400px; text-align:right;"></div>

<script language="javascript" type="text/javascript">
    Highcharts.theme = {
        colors: ['#60A917', '#FA6800', '#3B5998', '#10c4b2', '#ff7663', '#ffb74f', '#a2df53', '#1c9ec4', '#ff63a5'] // flat
    };
    Highcharts.dateFormats = {
        d: function (timestamp) {
            var months = ["", "فروردين", "ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دي", "بهمن", "اسفند"];
            var value = new Date(timestamp);
            var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
            return persianDate[2] + "" + months[persianDate[1]];

        },

        m: function (timestamp) {
            var months = ["", "فروردين", "ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دي", "بهمن", "اسفند"];
            var value = new Date(timestamp);
            var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
            return months[persianDate[1]] + " " + persianDate[0];

        },

        y: function (timestamp) {
            var value = new Date(timestamp);
            var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
            return persianDate[0];

        }
    };
    Highcharts.setOptions(Highcharts.theme);
    function createChart(data) {

        var actual = [], potential = [], total = [];
        $.each(data, function () {
            actual.push([this.time, this.actualBenefitLoss]);
            potential.push([this.time, this.potentialBenefitLoss]);
            total.push([this.time, this.totalBenefitLoss]);
        });
        actual.sort(function(a, b){return a[0] - b[0]});
        potential.sort(function(a, b){return a[0] - b[0]});
        total.sort(function(a, b){return a[0] - b[0]});
        var dataList = [actual, potential, total];

        $('#container').highcharts('StockChart', {

                    chart: {
//                                    width: $('#container').width() - 200
                    },

                    rangeSelector: false,

                    yAxis: { // Primary yAxis
                        title: {
                            text: '${message(code:'benefitLoss')}',
                            style: {
                                color: Highcharts.getOptions().colors[0]
                            }
                        },
                        labels: {
                            x: -10,
                            y: -5,
                            useHTML: true,
                            style: {
                                color: Highcharts.getOptions().colors[0]
                            }
                        }
                    },
                    xAxis: {
                        dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%d',
                            week: '%d',
                            month: '%m',
                            year: '%y'
                        },
                        labels: {
                            useHTML: true
                        }
                    },

                    navigator: {
                        xAxis: {
                            dateTimeLabelFormats: {
                                second: '%H:%M:%S',
                                minute: '%H:%M',
                                hour: '%H:%M',
                                day: '%d',
                                week: '%d',
                                month: '%m',
                                year: '%y'
                            },
                            labels: {
                                useHTML: true
                            }
                        }
                    },

                    plotOptions: {
                        series: {
//                                compare: 'percent'
                        }
                    },

                    tooltip: {
                        formatter: function () {

                            if (this.points.length < 2 && this.points[0].series.name == 'navigator_series')
                                return false;

                            var s = '<div style="text-align: right">' + getLongJalaliDate(new Date(this.x)) + '<br/>';

                            $.each(this.points, function () {
                                if (this.series.name != 'navigator_series')
                                    s += '<span style="direction:rtl;text-align:right;display:inline-block;color:' + this.series.color + ';">' + this.series.name + '</span> <b style="direction:ltr;text-align:left;display:inline-block;float:left;">' + (this.y < 0 ? '-' : '') + formatNumber(this.y) + '</b><div class="clear-fix">';
                            });

                            return s + '</div>';
                        },
//                            pointFormat: '<span style="color:{series.color};direction:rtl;text-align:right;">{series.name}</span>: <b>{point.y}</b><br/>',
                        valueDecimals: 2,
                        useHTML: true
                    },

                    series: [
                        {
                            name: '${message(code:'portfolio.benefitLoss.actual')}',
                            data: actual
                        },
                        {
                            name: '${message(code:'portfolio.benefitLoss.potential')}',
                            data: potential
                        },
                        {
                            name: '${message(code:'portfolio.benefitLoss.total')}',
                            data: total
                        }
                    ]
                }
                , function (chart) {
                    chart.addSeries({
                        name: 'navigator_series',
                        data: dataList[1],
                        isInternal: true,
                        showInLegend: false
                    });
                });
    }

    function getJalaliDate(value) {
        if (value == '')
            value = new Date();
        if (typeof(value) == 'string')
            value = new Date(value);
        var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
        return (persianDate[0] + "/" + (persianDate[1] < 10 ? '0' : '') + persianDate[1] + "/" + persianDate[2]);
    }

    function getLongJalaliDate(value) {

        var days = ["شنبه", "يکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه"];
        var months = ["", "فروردين", "ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دي", "بهمن", "اسفند"];
        if (value == '')
            value = new Date();
        if (typeof(value) == 'string')
            value = new Date(value);
        var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
        var jalaliDate = new JalaliDate(persianDate[0], persianDate[1], persianDate[2]);

        return (days[jalaliDate.getDay() - 1] + " " + persianDate[2] + " " + months[persianDate[1]] + " " + persianDate[0]);
    }
</script>