<div id="chartContainer" style="height: 500px; min-width: 310px;direction:ltr;"></div>

<script language="javascript" type="text/javascript">
    Highcharts.theme = {
        colors: ['#10c4b2', '#ff63a5', '#1c9ec4', '#ffb74f', '#a2df53', '#ff7663'] // flat
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

        $.ajax({
            dataType: "json",
            url: '<format:html value="${createLink(controller: 'data', action: 'ohlcv', params: [id: backTest.symbolId, start: backTest.startDate.time, end: backTest.endDate.time, adjustmentType: backTest.adjustmentType])}"/>',

            success: function (data) {
                // split the data set into ohlc and volume
                var ohlc = [],
                        volume = [],
                        portfolioStockValue = [],
                        portfolioTotalValue = [],
                        dataLength = data.length,
                // set the allowed units for data grouping
                        groupingUnits = [[
                            'week',                         // unit name
                            [1]                             // allowed multiples
                        ], [
                            'month',
                            [1, 2, 3, 4, 6]
                        ]],

                        i = 0;

                for (i = 0; i < dataLength; i += 1) {
                    ohlc.push([
                        data[i][0], // the date
                        data[i][1], // open
                        data[i][2], // high
                        data[i][3], // low
                        data[i][4] // close
                    ]);

                    volume.push([
                        data[i][0], // the date
                        data[i][5] // the volume
                    ]);
                }

                for (i = 0; i < visiblePortfolioLogs.length; i += 1) {
                    portfolioTotalValue.push([
                        visiblePortfolioLogs[i][0], // the date
                        visiblePortfolioLogs[i][1]
                    ]);

                    portfolioStockValue.push([
                        visiblePortfolioLogs[i][0], // the date
                        visiblePortfolioLogs[i][2]
                    ]);
                }


                // create the chart
                $('#chartContainer').highcharts('StockChart', {
                    chart: {
                        backgroundColor: '#f5f5f5'
                    },

                    rangeSelector: false,

                    title: {
                        text: ''
                    },

                    yAxis: [{
                        labels: {
                            align: 'right',
                            x: -3
                        },
                        title: {
                            text: '${message(code:'symbol.dailyTrade.price')}',
                            useHTML: true
                        },
                        height: '30%',
                        lineWidth: 2
                    }, {
                        labels: {
                            align: 'right',
                            x: -3
                        },
                        title: {
                            text: '${message(code:'symbol.dailyTrade.volume')}',
                            useHTML: true
                        },
                        top: '35%',
                        height: '25%',
                        offset: 0,
                        lineWidth: 2
                    }, {
                        labels: {
                            align: 'right',
                            x: -3
                        },
                        title: {
                            text: '${message(code:'portfolioLog')}',
                            useHTML: true
                        },
                        top: '65%',
                        height: '35%',
                        offset: 0,
                        lineWidth: 2
                    }],
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

                    tooltip: {
                        formatter: function () {

                            if (this.points.length < 2 && this.points[0].series.name == 'navigator_series')
                                return false;

                            var s = getLongJalaliDate(new Date(this.x)) + '<br/>';

                            $.each(this.points, function () {
                                if (this.series.name != 'navigator_series') {
                                    if (this.series.name == '${backTest.symbol.persianName}') {
                                        s += '<span style="color:' + this.series.color + ';direction:rtl">' + this.series.name + '</span><br/>${message(code:'symbol.dailyTrade.open')}: <b>' + formatNumber(this.point.open) + '</b><br/>${message(code:'symbol.dailyTrade.high')}: <b>' + formatNumber(this.point.high) + '</b><br/>${message(code:'symbol.dailyTrade.low')}: <b>' + formatNumber(this.point.low) + '</b><br/>${message(code:'symbol.dailyTrade.close')}: <b>' + formatNumber(this.point.close) + '</b><br/>';
                                    }
                                    else if (this.series.name == '${message(code:'portfolio.totalValue')}') {
                                        s += '<br/><span style="color:' + this.series.color + ';direction:rtl">' + this.series.name + '</span>: <b>' + formatNumber(this.y) + '</b><br/>';
                                    }
                                    else if (this.series.name == '${message(code:'portfolio.stockValue')}') {
                                        s += '<span style="color:' + this.series.color + ';direction:rtl">' + this.series.name + '</span>: <b>' + formatNumber(this.y) + '</b><br/>';
                                    }
                                    else {
                                        s += '<span style="direction:rtl">' + this.series.name + '</span>: <b>' + formatNumber(this.y) + '</b><br/>';
                                    }
                                }
                            });

                            return s;
                        },
//                            pointFormat: '<span style="color:{series.color};direction:rtl;text-align:right;">{series.name}</span>: <b>{point.y}</b><br/>',
                        valueDecimals: 2,
                        useHTML: true
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

                    series: [{
                        type: 'candlestick',
                        name: '${backTest.symbol.persianName}',
                        data: ohlc,
                        dataGrouping: {
                            units: groupingUnits
                        }
                    }, {
                        type: 'column',
                        name: '${message(code:'symbol.dailyTrade.volume')}',
                        data: volume,
                        yAxis: 1,
                        dataGrouping: {
                            units: groupingUnits
                        }
                    }, {
                        type: 'area',
                        name: '${message(code:'portfolio.totalValue')}',
                        data: portfolioTotalValue,
                        yAxis: 2,
                        dataGrouping: {
                            units: groupingUnits
                        }
                    }, {
                        type: 'area',
                        name: '${message(code:'portfolio.stockValue')}',
                        data: portfolioStockValue,
                        yAxis: 2,
                        dataGrouping: {
                            units: groupingUnits
                        }
                    }]
                });

                backTestChart = $('#chartContainer').highcharts();
            }
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

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }
</script>