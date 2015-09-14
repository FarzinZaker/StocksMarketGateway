package stocks

class ChartTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "chart"

    def trendChart = { attrs, body ->

        def series = attrs.series
        def idCounter = 1
        series.each { serie ->
            serie.id = "serie_${idCounter++}"
            serie.data = serie.data.collect { [trend: false, value: it] }
            attrs.trendCategories.size().times {
                serie.data.add([trend: true, value: 0])
            }
        }

        out << "<div id='${attrs.id}Container'>${message(code: 'loading')}</div>"
        out << "<div class='toolbar' id='${attrs.id}Toolbar'><button class='export'>${message code:'chart.getImage'}</button></div>"

        out << """
            <script language='javascript' type='text/javascript'>
                jQuery('#${attrs.id}Container').ready(function () {
                    \$('#${attrs.id}Container').css('direction', 'ltr');
                    ${attrs.width? "\$('#${attrs.id}Toolbar').css('width', '${attrs.width}px');" : ""}
                    var ${attrs.id}Chart = new Highcharts.Chart({

                        chart: {
                            ${attrs.width ? "width: ${attrs.width}," : ""}
                            ${attrs.height ? "height: ${attrs.height}," : ""}
                            renderTo: '${attrs.id}Container',
                            animation: false
                        },

                        exporting: {
                            url: '${createLink(controller: 'chart', action: 'save')}'
                        },

                        ${attrs.title ? "title: {text: '${attrs.title}'}," : ""}
                        ${attrs.subtitle ? "subtitle: {text: '${attrs.subtitle}'}," : ""}

                        xAxis: {
                            categoryList: [${(attrs.categories + attrs.trendCategories)?.collect { "'${it}'" }?.join(',')}],
                            ${attrs.xAxisName ? "title: {text: '${attrs.xAxisName}'}," : ""}
                            plotBands: {
                                color: '#eeeeee', // Color value
                                from: '${attrs.categories?.size()}', // Start of the plot band
                                to: '${attrs.categories?.size() + attrs.trendCategories?.size() - 1}' // End of the plot band
                            }
                        },

                        yAxis: {
                            ${attrs.yAxisName ? "title: {text: '${attrs.yAxisName}'}," : ""}
                        },

                        plotOptions: {
                            series: {
                                lineWidth: 3,
                                point: {
                                    events: {

                                        mouseOver: function (e) {
                                            var ev = e;
                                        },

                                        drag: function (e) {
                                            this.series.chart.yAxis[0].setExtremes(this.series.chart.yAxis[0].tickPositions[0], this.series.chart.yAxis[0].tickPositions[this.series.chart.yAxis[0].tickPositions.length - 1], false, true);
                                            var input = [];
                                            input['x'] = [];
                                            input['y'] = [];
                                            var i = 0;
                                            for(i = 0; i < ${attrs.categories?.size()}; i++){
                                                input['x'][i] = i;
                                                input['y'][i] = this.series.data[i].y;
                                            }
                                            var estimationInput = [];
                                            estimationInput['x'] = [];
                                            for (var j = 0; j < ${attrs.trendCategories?.size()}; j++) {
                                                estimationInput['x'][j] = j + i;
                                            }
                                            var estimateData = applyModel(estimationInput, buildModel(input, ${attrs.regressionDegree}), ${attrs.regressionDegree});
                                            for(var k = 0; k < estimateData.length; k++){
                                                this.series.data[estimateData[k].x].update({
                                                    x: estimateData[k].x,
                                                    y: estimateData[k].y
                                                })
                                            }
                                        },
                                        drop: function(e){
                                            this.series.chart.yAxis[0].setExtremes(null, null, true, true);
                                        }
                                    }
                                },
                                stickyTracking: false
                            },
                            column: {
                                stacking: 'normal'
                            }
                        },

                        tooltip: {
                            yDecimals: 2
                        },

                        series: [
                            ${
            series.collect {
                "{id: '${it.id}', name: '${it.name}', data: [${it.data.collect { "{y: ${it.value}, draggableY: ${!it.trend}}" }.join(',')}]}"
            }.join(',\n')
        }
                        ]

                    });

                    //initial trend
                    for(var s = 0; s < ${attrs.id}Chart.series.length; s++){
                        var input = [];
                        input['x'] = [];
                        input['y'] = [];
                        var i = 0;
                        for(i = 0; i < ${attrs.categories?.size()}; i++){
                            input['x'][i] = i;
                            input['y'][i] = ${attrs.id}Chart.series[s].data[i].y;
                        }
                        var estimationInput = [];
                        estimationInput['x'] = [];
                        for (var j = 0; j < ${attrs.trendCategories?.size()}; j++) {
                            estimationInput['x'][j] = j + i;
                        }
                        var estimateData = applyModel(estimationInput, buildModel(input, ${attrs.regressionDegree}), ${attrs.regressionDegree});
                        for(var k = 0; k < estimateData.length; k++){
                            ${attrs.id}Chart.series[s].data[estimateData[k].x].update({
                                x: estimateData[k].x,
                                y: estimateData[k].y
                            })
                        }

                        //cursor on points
                        \$.each(${attrs.id}Chart.series[s].data,function(i,point){
                            if(point.options.draggableY){
                                point.graphic.attr({
                                    cursor:'ns-resize'
                                });
                            }
                        });
                    }

                    \$('#${attrs.id}Toolbar .export').click(function () {
                        var obj = {},
                        exportUrl = ${attrs.id}Chart.options.exporting.url;
                        obj.options = JSON.stringify(${attrs.id}Chart.options);
                        obj.type = 'image/png';
                        obj.async = true;

                        \$.ajax({
                            type: 'post',
                            url: exportUrl,
                            data: obj,
                            success: function (data) {
                                var imgContainer = \$("#${attrs.id}ImageContainer");
                                window.location.href = '${createLink(controller: 'chart', action: 'studio')}/' + data;

                            }
                        });


                    });
                });
            </script>
            """
    }
}
