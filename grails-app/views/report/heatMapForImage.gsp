
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:javascript src="d3/d3.js"/>
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

</head>

<body>

<div id="body"></div>

<script type="text/javascript">

    function colorizeSymbols(d) {
        return pickColor(d.priceChange);
    }

    function colorizeIndustryGroups(d) {
        if (!d.parent)
            return headerColor;
        var sum = 0;
        if (d.children.length > 0) {
            for (var i = 0; i < d.children.length; i++)
                sum += d.children[i].priceChange;
        }
        else
            return headerColor;
        return pickColor(sum / d.children.length);
    }

    var supportsForeignObject = false;
    var chartWidth = 500;
    var chartHeight = 400;
    var xscale = d3.scale.linear().range([0, chartWidth]);
    var yscale = d3.scale.linear().range([0, chartHeight]);
    var color = d3.scale.category10();
    var headerHeight = 20;
    var headerColor = "#414554";
    var transitionDuration = 500;
    var root;
    var node;


    var treemap = d3.layout.treemap()
            .round(false)
            .size([chartWidth, chartHeight])
            .sticky(true)
            .value(size);

    var chart = d3.select("#body")
            .append("svg:svg")
            .attr("width", chartWidth)
            .attr("height", chartHeight)
            .append("svg:g");


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

                parentEnterTransition.append("rect")
                        .style('stroke', '#ffffff')
                        .attr("width", function (d) {
                            return Math.max(0.01, d.dx - 2);
                        })
                        .attr("height", headerHeight)
                        .style("fill", colorizeIndustryGroups);
                parentEnterTransition.append('foreignObject')
                        .attr("class", "foreignObject")
                        .append("xhtml:body")
                        .attr("class", "labelbody")
                        .style('background', 'transparent')
                        .style('direction', 'rtl')
                        .style('margin', '0')
                        .style('padding-right', '5px')
                        .style('text-align', 'right')
                        .append("div")
                        .attr("class", "label")
                        .style('font-family', 'tahoma')
                        .style('font-size', '11px')
                        .style('margin', '2px')
                        .style('white-space', 'pre')
                        .style('overflow', 'hidden')
                        .style('text-overflow', 'ellipsis')
                        .style('color', '#ffffff');


                if (supportsForeignObject) {

                    parentEnterTransition.selectAll(".foreignObject")
                            .filter(function (d) {
                                return d.dx < 40 || d.dy < 20;
                            }).style('display', 'none');
                } else {

                    parentEnterTransition.selectAll(".foreignObject .labelbody .label")
                            .filter(function (d) {
                                return d.dx < 40 || d.dy < 20;
                            }).style('display', 'none');
                }

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


                childEnterTransition.append("rect")
                        .style('stroke', '#ffffff')
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
                        .style('background', 'transparent')
                        .style('direction', 'rtl')
                        .style('margin', '0')
                        .style('padding-right', '5px')
                        .style('text-align', 'right')
                        .append("div")
                        .attr("class", "label")
                        .style('font-family', 'tahoma')
                        .style('font-size', '11px')
                        .style('margin', '2px')
                        .style('white-space', 'pre')
                        .style('overflow', 'hidden')
                        .style('text-overflow', 'ellipsis')
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

                zoom(node);
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

        this.treemap
                .padding([headerHeight / (chartHeight / d.dy) + 1, 0.75, 1.5, 0.75])
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

        var zoomTransition = chart.selectAll("g.cell")//.transition().duration(transitionDuration)
                .attr("transform", function (d) {
                    return "translate(" + xscale(d.x) + "," + yscale(d.y) + ")";
                })
                .each(function (d, i) {
                    var self = this;
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

        node = d;

        if (d3.event) {
            d3.event.stopPropagation();
        }
    }

</script>

</body>
</html>