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

        function pickColor(value) {
            if (value > 4)
                value = 4;
            else if (value < -4)
                value = -4;
            var self = this,
                    span = $(self).parent("span"),
                    val = (-value + 4) * 100 / 8,
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

            return "rgb(" + r + "," + g + "," + b + ")";
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
        stroke: #EEEEEE;
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
        <div class="col-xs-12 k-rtl">
            <div id="body"></div>

            <script language="javascript" type="text/javascript">
                for (var i = 4; i >= -4; i--) {
                    document.write('<div class="step" style="background:' + pickColor(i) + ';">' + i + '%</div>')
                }
            </script>

            <div id="positions"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
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


    var tip = d3.tip()
            .attr('class', 'd3-tip')
            .html(function (d) {
                var result = '<div class="hm-tip">' +
                        '<div class="hm-tip-header">' + d.parent.name + '</div>' +
                        '<div class="hm-tip-selected" style="background-color:' + pickColor(d.priceChange) + ';">' +
                        '<div>' +
                        '<div class="hm-tip-selected-name">' + d.name + '</div>' +
                        '<div class="hm-tip-selected-priceChange">' + d.priceChange + '%</div>' +
                        '<div class="hm-tip-selected-price">' + d.price + '</div>' +
                        '<div class="clear"></div>' +
                        '</div>' +
                        '<div class="hm-tip-selected-fullName">' + d.fullName + '</div>' +
                        '</div>';
                var count = d.parent.children.length;
                if (count > 10)
                    count = 10;
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
                return result;
            });

    var treemap = d3.layout.treemap()
            .round(false)
            .size([chartWidth, chartHeight])
            .sticky(true)
            .value(function (d) {
                return d.size;
            });

    var chart = d3.select("#body")
            .append("svg:svg")
            .attr("width", chartWidth)
            .attr("height", chartHeight)
            .append("svg:g");

    chart.on('mousemove', function () {
        var coordinates = [0, 0];
        coordinates = d3.mouse(this);
        var x = coordinates[0];
        var y = coordinates[1];
        if (y > 250)
            y -= 40;
        else
            y += 370;
        if (x < 200)
            x = 200;
        if (x > chartWidth - 200)
            x = chartWidth - 200;
        $('.d3-tip').css('top', y - 100).css('left', x - 125);
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
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", headerHeight)
                        .style("fill", function (d) {
                            if (d.children.length > 0) {
                                var sum = 0;
                                for (var i = 0; i < d.children.length; i++)
                                    sum += d.children[i].priceChange;
                                return pickColor(sum / d.children.length);
                            }
                            else
                                return headerColor
                        })
                        .style('display', function (d) {
                            d.parent ? '' : 'none';
                        });
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
                        .style("fill", function (d) {
                            if (d.children.length > 0) {
                                var sum = 0;
                                for (var i = 0; i < d.children.length; i++)
                                    sum += d.children[i].priceChange;
                                return pickColor(sum / d.children.length);
                            }
                            else
                                return headerColor
                        })
                        .style('display', function (d) {
                            return d.parent ? '' : 'none';
                        });
                parentUpdateTransition.select(".foreignObject")
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx);
                        })
                        .attr("height", headerHeight)
                        .select(".labelbody .label")
                        .text(function (d) {
                            return d.name;
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
                        .style("fill", function (d) {
                            return pickColor(d.priceChange);
                        });
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
                        .style("fill", function (d) {
                            return pickColor(d.priceChange);
                        });
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

//                d3.select("select").on("change", function () {
//                    console.log("select zoom(node)");
//                    treemap.value(this.value == "size" ? size : count)
//                            .nodes(root);
//                    zoom(node);
//                });

                zoom(node);

                // create the zoom listener
                var zoomListener = d3.behavior.zoom()
                        .scaleExtent([0.1, 3])
                        .on("zoom", zoomHandler);

// function for handling zoom event
                function zoomHandler() {
                    vis.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
                }

// create the svg
//                rootSvg = d3.select("#tree-body").append("svg:svg");
                /*
                 creating your svg image here
                 */

// apply the zoom behavior to the svg image
                zoomListener(chart);
            }
    );


    function size(d) {
        return d.size;
    }


    function count(d) {
        return 1;
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
        this.treemap
                .padding([headerHeight / (chartHeight / d.dy), 0, 0, 0])
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
                    return Math.max(0.01, kx * d.dx);
                })
                .attr("height", function (d) {
                    return d.children ? headerHeight : Math.max(0.01, ky * d.dy);
                })
                .style("fill", function (d) {
                    if (!d.children)
                        return pickColor(d.priceChange);
                    else if (d.children.length > 0) {
                        var sum = 0;
                        for (var i = 0; i < d.children.length; i++)
                            sum += d.children[i].priceChange;
                        return pickColor(sum / d.children.length);
                    }
                    else
                        return headerColor
                })
                .style('display', function (d) {
                    return d.parent ? '' : 'none';
                });

        node = d;

        if (d3.event) {
            d3.event.stopPropagation();
        }
    }

</script>

</body>
</html>