<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 06/04/2015
  Time: 17:04
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta name="layout" content="main"/>
<title><g:message code="report.heatmap.title"/></title>
<asset:javascript src="d3/modernizr.js"/>
<asset:javascript src="d3/d3.js"/>
<asset:javascript src="d3/d3.tip.js"/>
<asset:javascript src="FarsiNormalizer.js"/>
<script language="javascript" type="text/javascript">


    function Interpolate(start, end, steps, count) {
        var s = start,
                e = end,
                final = s + (((e - s) / steps) * count);
        return Math.floor(final);
    }

    function Color(_r, _g, _b) {
        var r, g, b;
        var setColors = function (_r, _g, _b) {
            r = _r;
            g = _g;
            b = _b;
        };

        setColors(_r, _g, _b);
        this.getColors = function () {
            var colors = {
                r: r,
                g: g,
                b: b
            };
            return colors;
        };
    }

    function pickColorArray(value) {
        if (value > 5)
            value = 5;
        else if (value < -5)
            value = -5;
        var self = this,
                span = $(self).parent("span"),
                val = (-value + 5) * 100 / 10,
                red = new Color(246, 53, 56),
                white = new Color(65, 69, 84),
                green = new Color(48, 204, 90),
                start = green,
                end = white;

        if (val > 50) {
            start = white,
                    end = red;
            val = val % 51;
        }
        var startColors = start.getColors(),
                endColors = end.getColors();
        var r = Interpolate(startColors.r, endColors.r, 50, val);
        var g = Interpolate(startColors.g, endColors.g, 50, val);
        var b = Interpolate(startColors.b, endColors.b, 50, val);
        return [r, g, b]
    }

    function pickColor(value) {
        var colorArray = pickColorArray(value);
        return "rgb(" + colorArray[0] + "," + colorArray[1] + "," + colorArray[2] + ")";
    }
</script>
<style>
svg {
    overflow: hidden;
}

svg * {
    font-family: tahoma !important;
    font-weight: normal !important;
    font-size: 11px !important;
}

rect {
    pointer-events: all;
    cursor: pointer;
}

.cell.child rect {
    stroke: #262931;
}

.chart {
    display: block;
    margin: auto;
}

.parent .label {
    color: #FFFFFF;
}

.labelbody {
    background: transparent;
}

.label {
    margin: 2px;
    white-space: pre;
    overflow: hidden;
    text-overflow: ellipsis;
}

.child .label {
    white-space: pre-wrap;
    text-align: center;
    text-overflow: ellipsis;
}

.cell {
    font-size: 10px;
    cursor: pointer
}

.hm-tip {
    background-color: white;
    border: 3px solid black;
    min-width: 300px;
}

.hm-tip-header {
    padding: 3px;
}

.hm-tip-selected {
    padding: 3px 10px;
    color: white;
}

.hm-tip-selected-name {
    float: right;
    font-size: 24px !important;
    /*margin-left: 30px;*/
}

.hm-tip-selected-price {
    float: left;
    /*margin-left: 10px;*/
    font-size: 24px !important;
    width: 100px;
    text-align: left;
}

.hm-tip-selected-priceChange {
    float: left;
    font-size: 24px !important;
    direction: ltr;
    width: 100px;
    text-align: left;
}

.hm-tip-selected-fullName {
    clear: both;
}

.hm-tip-other {
    padding: 2px 10px;
    clear: both;
    border-bottom: 1px solid #EEEEEE;
}

.hm-tip-other-name {
    float: right;
    font-size: 14px !important;
}

.hm-tip-other-price {
    float: left;
    margin-left: 10px;
    font-size: 14px !important;
    width: 100px;
    text-align: left;
}

.hm-tip-other-priceChange {
    float: left;
    font-size: 14px !important;
    direction: ltr;
    width: 100px;
    text-align: left;
}

.clear {
    clear: both;
}

.step {
    float: left;
    direction: ltr;
    text-align: center;
    color: white;
    width: 50px;
    padding-left: 6px;
    padding-right: 6px;
}
</style>

</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.reports'), url: createLink(controller: 'report')],
                    [text: message(code: 'menu.reports.heatMap'), url: createLink(controller: 'report', action: 'heatMap')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="crimson">
                <i class="fa fa-desktop"></i>
                <g:message code="report.heatmap.title"/>
            </h1>

            <p><g:message code="report.heatmap.description"/></p>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <div class="whitePanel">
                <div>
                    <g:message code="report.heatmap.display.desc"/>
                    <input type="radio" id="rbtn_size" name="mode" class="css-checkbox" value="size" checked> <label
                        for="rbtn_size" class="css-label" style="margin-right:20px;margin-left:20px;"><g:message
                            code="report.heatmap.display.value"/></label>
                    <input type="radio" id="rbtn_count" name="mode" class="css-checkbox" value="count"> <label
                        for="rbtn_count" class="css-label"><g:message code="report.heatmap.display.volume"/></label>

                    <span style="margin-right:40px;margin-left:20px;display:inline-block;"><g:message
                            code="report.heatmap.symbol.count"/></span>
                    <form:numericTextBox name="symbolCount" value="10" min="1" max="100"/>
                </div>

                <div style="margin-top:10px;margin-bottom:10px;">
                    <span style="margin-left:20px;display:inline-block;"><g:message
                            code="report.heatmap.industryGroup.desc"/></span>
                    <form:select name="industry" onchange="industryChanged"
                                 items="${[[text: message(code: 'report.heatmap.industryGroup.all'), value: 0]] + industryGroups}"
                                 allowUserInput="false"
                                 style="width:300px;"/>
                    <span style="margin-right:40px;margin-left:20px;display:inline-block;"><g:message
                            code="report.heatmap.symbol.desc"/></span>
                    <input type="text" class="k-input k-textbox" id="txtSymbolFilter"/>
                </div>
            </div>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12 k-rtl">
            <div id="body" style="margin-top:20px;"></div>

            <script language="javascript" type="text/javascript">
                for (var i = 5; i >= -5; i--) {
                    document.write('<div class="step" style="background:' + pickColor(i) + ';">' + i + '%</div>')
                }
            </script>

            <div id="positions"></div>
        </div>
    </div>
</div>
<script type="text/javascript">

    //    $('#txtSymbolFilter').val('');
    $(document).ready(function () {
        $('#txtSymbolFilter').keyup(function () {
            searchSymbol();
        });
    });

    function industryChanged(e, value) {
        var intValue = parseInt(value);
        if (intValue == 0)
            node = root;
        else
            for (var i = 0; i < root.children.length; i++)
                if (intValue == root.children[i].id)
                    node = root.children[i];
        zoom(node);
    }

    function colorizeSymbols(d) {
        if (normalizeFarsi(d.name).toString().indexOf(normalizeFarsi($('#txtSymbolFilter').val().trim())) > -1)
            return pickColor(d.priceChange);
        else
            return shadeRGBColor(pickColorArray(d.priceChange), 0.5);
    }

    function colorizeIndustryGroups(d) {
        if (!d.parent)
            return headerColor;
        var matched = false;
        if (d.children.length > 0) {
            for (var i = 0; i < d.children.length; i++)
                if (normalizeFarsi(d.children[i].name).toString().indexOf(normalizeFarsi($('#txtSymbolFilter').val().trim())) > -1) {
                    matched = true;
                }
        }
        else
            return headerColor;
        var value = d.priceChangeOnSize;
        if ($('input[name=mode]:checked').val() == "count")
            value = d.priceChangeOnCount;
        if (matched)
            return pickColor(value);
        else
            return shadeRGBColor(pickColorArray(value), 0.5);
    }

    function searchSymbol() {
        chart.selectAll("g.cell.child rect").style("fill", colorizeSymbols);
        chart.selectAll("g.cell.parent rect").style("fill", colorizeIndustryGroups);
    }

    function shadeRGBColor(colorArray, percent) {
        var t = percent < 0 ? 0 : 255, p = percent < 0 ? percent * -1 : percent, R = colorArray[0], G = colorArray[1], B = colorArray[2];
        return "rgb(" + (Math.round((t - R) * p) + R) + "," + (Math.round((t - G) * p) + G) + "," + (Math.round((t - B) * p) + B) + ")";
    }

    var supportsForeignObject = Modernizr.svgforeignobject;
    var chartWidth = $('#body').width();
    var chartHeight = 500;
    var xscale = d3.scale.linear().range([0, chartWidth]);
    var yscale = d3.scale.linear().range([0, chartHeight]);
    var color = d3.scale.category10();
    var headerHeight = 20;
    var headerColor = "#414554";
    var transitionDuration = 500;
    var root;
    var node;

    var currentTipSymbolsCount = 0;
    var tip = d3.tip()
            .attr('class', 'd3-tip')
            .html(function (d) {
                var result = '<div class="hm-tip">' +
                        '<div class="hm-tip-header">' + d.parent.name + '</div>' +
                        '<div class="hm-tip-selected" style="background-color:' + pickColor(d.priceChange) + ';">' +
                        '<div>' +
                        '<div class="hm-tip-selected-name">' + d.name + '</div>' +
                        '<div class="hm-tip-selected-sparkLine" data-id="' + d.id + '"></div>' +
                        '<div class="hm-tip-selected-priceChange">' + d.priceChange + '%</div>' +
                        '<div class="hm-tip-selected-price">' + d.price + '</div>' +
                        '<div class="clear"></div>' +
                        '</div>' +
                        '<div class="hm-tip-selected-fullName">' + d.fullName + '</div>' +
                        '</div>';
                var count = d.parent.children.length;
                var selectedCount = parseInt($('#symbolCount').val());
                if (count > selectedCount)
                    count = selectedCount;
                for (var i = 0; i < count; i++) {
                    var c = d.parent.children[i];
                    result += '<div class="hm-tip-other">';
                    result += '<div class="hm-tip-other-name">' + c.name + '</div>';
                    result += '<div class="hm-tip-other-priceChange">' + c.priceChange + '%</div>';
                    result += '<div class="hm-tip-other-price">' + c.price + '</div>';
                    result += '<div class="clear"></div>';
                    result += '</div>'
                }
                result += '</div>';
                currentTipSymbolsCount = count;

                return result;

            });

    var treemap = d3.layout.treemap()
            .round(false)
            .size([chartWidth, chartHeight])
            .sticky(true)
            .value(
                    $('input[name=mode]:checked').val() == "size" ? size : count
//    function (d) {
//                return d.size;
//            }
    );

    var chart = d3.select("#body")
            .append("svg:svg")
            .attr("width", chartWidth)
            .attr("height", chartHeight)
            .append("svg:g");

    chart.on('mousemove', function () {
        if (currentTipSymbolsCount > 0)
            var coordinates = [0, 0];
        coordinates = d3.mouse(this);
        var x = coordinates[0];
        var y = coordinates[1];
        if (y > 250)
            y -= $('.d3-tip').height() - 370;
        else
            y += 440;
        if (x < 200)
            x = 200;
        if (x > chartWidth - 200)
            x = chartWidth - 200;
        $('.d3-tip').css('top', y).css('left', x - 125);

    });

    var vis = chart.append('g')
            .attr('transform', 'translate(20, 20)')
            .call(tip);

    d3.json("${createLink(action: 'heatMapJson')}", function (data) {
                node = root = data;
                var nodes = treemap.nodes(root);

                var children = nodes.filter(function (d) {
                    return !d.children;
                });
                var parents = nodes.filter(function (d) {
                    return d.children;
                });

                // create parent cells
                var parentCells = chart.selectAll("g.cell.parent")
                        .data(parents, function (d) {
                            return "p-" + d.id;
                        });
                var parentEnterTransition = parentCells.enter()
                        .append("g")
                        .attr("class", "cell parent")
                        .on("click", function (d) {
                            zoom(d);
                        });

                parentEnterTransition.append("rect")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx - 2);
                        })
                        .attr("height", headerHeight)
                        .style("fill", colorizeIndustryGroups);
//                        .style('display', function (d) {
//                            d.parent ? '' : 'none';
//                        });
                parentEnterTransition.append('foreignObject')
                        .attr("class", "foreignObject")
                        .append("xhtml:body")
                        .attr("class", "labelbody")
                        .append("div")
                        .attr("class", "label");
                // update transition
                var parentUpdateTransition = parentCells.transition().duration(transitionDuration);
                parentUpdateTransition.select(".cell")
                        .attr("transform", function (d) {
                            return "translate(" + d.dx + "," + d.y + ")";
                        });
                parentUpdateTransition.select("rect")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", headerHeight)
                        .style("fill", colorizeIndustryGroups);
//                        .style('display', function (d) {
//                            return d.parent ? '' : 'none';
//                        });
                parentUpdateTransition.select(".foreignObject")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", headerHeight)
                        .select(".labelbody .label")
                        .text(function (d) {
                            return d.priceChangeOnSize;
                        });
                // remove transition
                parentCells.exit()
                        .remove();

                // create children cells
                var childrenCells = chart.selectAll("g.cell.child")
                        .data(children, function (d) {
                            return "c-" + d.id;
                        });
                // enter transition
                var childEnterTransition = childrenCells.enter()
                        .append("g")
                        .attr("class", "cell child")
                        .on("click", function (d) {
                            zoom(node === d.parent ? root : d.parent);
                        });
                childEnterTransition
                        .on('mouseover', tip.show)
                        .on('mouseout', tip.hide);

                childEnterTransition.append("rect")
                        .classed("background", true)
                        .style("fill", colorizeSymbols);
                childEnterTransition.append('foreignObject')
                        .attr("class", "foreignObject")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", function (d) {
                            return Math.max(0.01, d.dy);
                        })
                        .append("xhtml:body")
                        .attr("class", "labelbody")
                        .append("div")
                        .attr("class", "label")
                        .text(function (d) {
                            return d.name;
                        });

                if (supportsForeignObject) {

                    childEnterTransition.selectAll(".foreignObject")
                            .filter(function (d) {
                                return d.dx < 40 || d.dy < 20;
                            }).style('display', 'none');
                } else {

                    childEnterTransition.selectAll(".foreignObject .labelbody .label")
                            .filter(function (d) {
                                return d.dx < 40 || d.dy < 20;
                            }).style('display', 'none');
                }


                // update transition
                var childUpdateTransition = childrenCells.transition().duration(transitionDuration);
                childUpdateTransition.select(".cell")
                        .attr("transform", function (d) {
                            return "translate(" + d.x + "," + d.y + ")";
                        });
                childUpdateTransition.select("rect")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", function (d) {
                            return d.dy;
                        })
                        .style("fill", colorizeSymbols);
                childUpdateTransition.select(".foreignObject")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", function (d) {
                            return Math.max(0.01, d.dy);
                        })
                        .select(".labelbody .label")
                        .text(function (d) {
                            return d.name;
                        });
                // exit transition
                childrenCells.exit()
                        .remove();

                $("input[type=radio]").change(function () {
                    if ($(this).is(':checked')) {
                        treemap.value(this.value == "size" ? size : count)
                                .nodes(root);
                        zoom(node);
                    }
                });


//                d3.select("input[type=radio]").on("change", function () {
//                    console.log("select zoom(node)");
//                    treemap.value(this.value == "size" ? size : count)
//                            .nodes(root);
//                    zoom(node);
//                });

                zoom(node);

//                // create the zoom listener
//                var zoomListener = d3.behavior.zoom()
//                        .scaleExtent([0.1, 3])
//                        .on("zoom", zoomHandler);
//
//// function for handling zoom event
//                function zoomHandler() {
//                    vis.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
//                }
//
//// create the svg
////                rootSvg = d3.select("#tree-body").append("svg:svg");
//                /*
//                 creating your svg image here
//                 */
//
//// apply the zoom behavior to the svg image
//                zoomListener(chart);
            }
    );


    function size(d) {
        return d.size;
    }


    function count(d) {
        return d.count;
    }


    //and another one
    function textHeight(d) {
        var ky = chartHeight / d.dy;
        yscale.domain([d.y, d.y + d.dy]);
        return (ky * d.dy) / headerHeight;
    }


    function getRGBComponents(color) {
        var r = color.substring(1, 3);
        var g = color.substring(3, 5);
        var b = color.substring(5, 7);
        return {
            R: parseInt(r, 16),
            G: parseInt(g, 16),
            B: parseInt(b, 16)
        };
    }


    function idealTextColor(bgColor) {
        var nThreshold = 105;
        var components = getRGBComponents(bgColor);
        var bgDelta = (components.R * 0.299) + (components.G * 0.587) + (components.B * 0.114);
        return ((255 - bgDelta) < nThreshold) ? "#000000" : "#ffffff";
    }


    function zoom(d) {

        $('#industry').data('kendoComboBox').select(function (dataItem) {
            return parseInt(dataItem.value) == parseInt(d.id);
        });

        this.treemap
                .padding([headerHeight / (chartHeight / d.dy) + 0, 1.75, 2.5, 1.75])
                .nodes(d);

        // moving the next two lines above treemap layout messes up padding of zoom result
        var kx = chartWidth / d.dx;
        var ky = chartHeight / d.dy;
        var level = d;

        xscale.domain([d.x, d.x + d.dx]);
        yscale.domain([d.y, d.y + d.dy]);

        if (node != level) {

            if (supportsForeignObject) {

                chart.selectAll(".foreignObject")
                        .filter(function (d) {
                            return d.dx < 40 || d.dy < 20;
                        }).style('display', 'none');
            } else {

                chart.selectAll(".foreignObject .labelbody .label")
                        .filter(function (d) {
                            return d.dx < 40 || d.dy < 20;
                        }).style('display', 'none');
            }
        }
        chart.selectAll(".cell.child")
                .select(".foreignObject .labelbody .label")
                .style("color", function (d) {
                    return idealTextColor(pickColor(d.priceChange));
                });

        var zoomTransition = chart.selectAll("g.cell").transition().duration(transitionDuration)
                .attr("transform", function (d) {
                    return "translate(" + xscale(d.x) + "," + yscale(d.y) + ")";
                })
                .each("end", function (d, i) {
                    if (!i && (level !== self.root)) {
                        chart.selectAll(".cell.child")
                                .filter(function (d) {
                                    return d.parent === self.node; // only get the children for selected group
                                })
                                .select(".foreignObject .labelbody .label")
                                .style("color", function (d) {
                                    return idealTextColor(pickColor(d.priceChange));
                                });

                        if (supportsForeignObject) {
                            chart.selectAll(".cell.child")
                                    .filter(function (d) {
                                        return d.parent === self.node; // only get the children for selected group
                                    })
                                    .select(".foreignObject")
                                    .style("display", "");
                        } else {
                            chart.selectAll(".cell.child")
                                    .filter(function (d) {
                                        return d.parent === self.node; // only get the children for selected group
                                    })
                                    .select(".foreignObject .labelbody .label")
                                    .style("display", "");
                        }
                    }
                });

        zoomTransition.select(".foreignObject")
                .attr("width", function (d) {
                    return Math.max(0.01, kx * d.dx);
                })
                .attr("height", function (d) {
                    return d.children ? headerHeight : Math.max(0.01, ky * d.dy);
                })
                .select(".labelbody .label")
                .text(function (d) {
                    return d.name;
                });

        // update the width/height of the rects
        zoomTransition.select("rect")
                .attr("width", function (d) {
                    return d.children ? Math.max(0.01, kx * d.dx - 2) : Math.max(0.01, kx * d.dx);
                })
                .attr("height", function (d) {
                    return d.children ? headerHeight : Math.max(0.01, ky * d.dy);
                })
                .attr('x', function (d) {
                    return d.children ? 1 : '';
                })
                .style("fill", function (d) {
                    if (!d.children)
                        return colorizeSymbols(d);
                    else if (d.children.length > 0) {
                        return colorizeIndustryGroups(d);
                    }
                    else
                        return headerColor
                });
//                .style('display', function (d) {
//                    return d.parent ? '' : 'none';
//                });

        node = d;

        if (d3.event) {
            d3.event.stopPropagation();
        }
    }

</script>

</body>
</html>