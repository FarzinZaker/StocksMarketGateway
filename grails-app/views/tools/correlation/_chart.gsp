<asset:stylesheet src="common.less"/>
<asset:stylesheet src="site.less"/>
<asset:stylesheet src="default.less"/>
<g:javascript library="jquery" plugin="jquery"/>
<asset:javascript src="highstocks/highstock.js"/>
<script language="javascript" type="text/javascript"
        src="${resource(dir: 'js/kendo.ui/jalali', file: 'JalaliDate.js')}"></script>

<div id="container" style="height: 400px; min-width: 310px;direction: rtl;text-align:right;"></div>

<script language="javascript" type="text/javascript">
    Highcharts.theme = {
        colors: ['#10c4b2', '#ff7663', '#ffb74f', '#a2df53', '#1c9ec4', '#ff63a5'] // flat
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
    $(function () {
        var seriesOptions = [],
                seriesCounter = 0,
                names = [
                    {
                        group: '${params.sourceGroup}',
                        item: '${params.sourceItem}'
                    },
                    {
                        group: '${params.targetGroup}',
                        item: '${params.targetItem}'
                    }
                ],
        // create the chart when all data is loaded
                createChart = function () {

                    $('#container').highcharts('StockChart', {

                        rangeSelector: false,

                        yAxis: {
                            labels: {
//                                formatter: function () {
//                                    return (this.value > 0 ? ' + ' : '') + this.value + '%';
//                                }
                            },
                            plotLines: [{
                                value: 0,
                                width: 2,
                                color: 'silver'
                            }]
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
                                var s = getLongJalaliDate(new Date(this.x)) + '<br/>';

                                $.each(this.points, function () {
                                    s += '<span style="color:' + this.series.color + ';direction:rtl;text-align:right;">' + this.series.name + '</span>: <b>' + this.y + '</b><br/>';
                                });

                                return s;
                            },
//                            pointFormat: '<span style="color:{series.color};direction:rtl;text-align:right;">{series.name}</span>: <b>{point.y}</b><br/>',
                            valueDecimals: 2,
                            useHTML: true
                        },

                        series: seriesOptions
                    });
                };

        $.each(names, function (i, name) {
            $.ajax({
                type: "POST",
                url: '<format:html value="${createLink(action: 'correlationChartData', params: [startDate:params.startDate, endDate:params.endDate, period:params.period,])}"/>&group=' + name.group + '&item=' + name.item
            }).done(function (response) {
                seriesOptions[i] = {
                    name: response.name,
                    data: response.data
                };

                // As we're loading the data asynchronously, we don't know what order it will arrive. So
                // we keep a counter and create the chart when all the data is loaded.
                seriesCounter += 1;

                if (seriesCounter === names.length) {
                    createChart();
                }
            });
        });
    });

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