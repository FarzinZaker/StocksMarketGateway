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

function toUnicode(value) {
    return value;
    var result = "";
    for (var i = 0; i < value.length; i++) {
        result += "\\u" + ("000" + value[i].charCodeAt(0).toString(16)).substr(-4);
    }
    return result;
}

var d3 = require('d3')
    , jsdom = require('jsdom')
    , htmlStub = '<html><head></head><body><div id="body"><?xml version="1.0" encoding="utf-8" standalone="yes"?><!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd"></div><script src="js/d3.v3.min.js"></script></body></html>'; // html file skull with a container div for the d3 dataviz

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
var treemap;
var chart;


function zoom(d) {

    treemap
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


    var zoomTransition = chart.selectAll("g.cell")
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
            return toUnicode(d.name);
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

// pass the html stub to jsDom
jsdom.env({ features: { QuerySelector: true }, html: htmlStub, done: function (errors, window) {
    // process the html document, like if we were at client side
    // code to generate the dataviz and process the resulting html file to be added here


    var el = window.document.querySelector('#body')
        , body = window.document.querySelector('body');


    treemap = d3.layout.treemap()
        .round(false)
        .size([chartWidth, chartHeight])
        .sticky(true)
        .value(size);

    // append the svg to the container selector
    chart = d3.select(el)
        .append("svg:svg")
        .attr('xmlns', 'http://www.w3.org/2000/svg')
        .attr('version', '1.1')
        .attr("width", chartWidth)
        .attr("height", chartHeight)
        .append("svg:g");


    var fs = require('fs');

    var http = require('http');

    http.get('http://www.4tablo.ir/report/heatMapJson', function (res) {

            var result = '';
            res.on('data', function (chunk) {
                result += chunk;
            });
            res.on('end', function () {
                fs.writeFile('data.txt', result, function (err) {
                    if (err) {
                        console.log('error saving document', err)
                    } else {
                        console.log('The file was saved, open data.txt to see the result')
                    }
                });

                node = root = JSON.parse(result);
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
                parentEnterTransition.append('text')
                    .style('fill', '#ffffff')
                    .style('font-family', 'tahoma')
                    .style('font-size', '11px')
                    .style('text-anchor', 'end')
                    .style('writing-mode', 'rl')
                    .attr('x', function (d) {
                        return d.dx - 10
                    })
                    .attr('y', 12)
//                    .append('textPath')
                    .text(function (d) {
                        if (d.dx > 100 && d.dy > 30)
                            return toUnicode(d.name);
                        else
                            return '';
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


                childEnterTransition.append("rect")
                    .style('stroke', '#ffffff')
                    .classed("background", true)
                    .style("fill", colorizeSymbols);
                childEnterTransition.append('text')
                    .style('fill', '#ffffff')
                    .style('font-family', 'tahoma')
                    .style('font-size', '11px')
                    .style('text-anchor', 'end')
                    .style('writing-mode', 'rl')
                    .attr('x', function (d) {
                        return d.dx - 10
                    })
                    .attr('y', 12)
                    .text(function (d) {
                        if (d.dx > 40 && d.dy > 30)
                            return toUnicode(d.name);
                        else
                            return '';
                    });

                zoom(node);
                // save result in an html file
                var svgsrc = el.innerHTML;

                fs.writeFile('heatMap.svg', svgsrc, function (err) {
                    if (err) {
                        console.log('error saving document', err)
                    } else {
                        console.log('The file was saved, open heatMap.svg to see the result')
                    }
                });


            });
        }
    );

}
});

