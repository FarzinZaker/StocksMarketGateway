<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/21/14
  Time: 4:28 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>HTML5 Diagram Sample Application by Telerik Kendo UI</title>

    <g:javascript library="jquery" plugin="jquery"/>

    <asset:stylesheet src="kendo.ui/kendo.common.min.css"/>
    <asset:stylesheet src="kendo.ui/kendo.rtl.min.css"/>
    <asset:stylesheet src="kendo.ui/kendo.metro.min.css"/>
    <asset:stylesheet src="kendo.ui/kendo.dataviz.min.css"/>
    <asset:stylesheet src="kendo.ui/kendo.dataviz.metro.min.css"/>

    <asset:javascript src="kendo.ui/kendo.all.min.js"/>
    <asset:javascript src="kendo.ui/kendo.timezones.min.js"/>

    <link rel="stylesheet" href="http://demos.telerik.com/kendo-ui/content/integration/diagram/css/styles.css" />
</head>
<body>
<div id="menu">
    <ul></ul>
</div>
<div id="splitter">
    <div id="left-pane">
        <div class="pane-content">
            <ul id="shapesPanelBar">
                <li>
                    Basic Shapes
                    <ul>
                        <li>
                            <span class="shapeItem" data-shape='{"width":120,"height":120,"type":"rectangle"}' style="background-position: 0 0"></span>
                            <span>Square</span>
                        </li><li>
                        <span class="shapeItem" data-shape='{"type":"circle","width":120,"height":120}' style="background-position: -60px 0"></span>
                        <span>Circle</span>
                    </li><li>
                        <span class="shapeItem" data-shape='{"width":120,"height":80,"type":"rectangle"}' style="background-position: -120px 0"></span>
                        <span>Rectangle</span>
                    </li><li>
                        <span class="shapeItem" data-shape='{"type":"circle","width":120,"height":80}' style="background-position: -180px 0"></span>
                        <span>Ellipse</span>
                    </li>
                    </ul>
                </li>
                <li>
                    Polygons
                    <ul>
                        <li>
                            <span class="shapeItem" data-shape='{"path":"M 60,0 L120,44 L97,114 L23,114 L0,44 z"}' style="background-position: -240px 0"></span>
                            <span>Pentagon</span>
                        </li><li>
                        <span class="shapeItem" data-shape='{"path":"m30,0 L90,0 L120,52 L90,104 L30,104 L0,52 z"}' style="background-position: -300px 0"></span>
                        <span>Hexagon</span>
                    </li><li>
                        <span class="shapeItem" data-shape='{"path":"m60,0 L108.12,23.17 L120,75.24 L86.7,116.99 L33.3,116.99 L0,75.24 L11.88,23.17 z"}' style="background-position: -360px 0"></span>
                        <span>Heptagon</span>
                    </li><li>
                        <span class="shapeItem" data-shape='{"path":"m35.15,0 L84.85,0 L120,35.15 L120,84.85 L84.85,120 L35.15,120 L0,84.85 L0,35.15 z"}' style="background-position: -420px 0"></span>
                        <span>Octagon</span>
                    </li>
                    </ul>
                </li>
                <li>
                    Arrows
                    <ul>
                        <li>
                            <span class="shapeItem" data-shape='{"path":"m0,20 L20,0 L20,10 L120,10 L120,30 L20,30 L20,40 z"}' style="background-position: -480px 0"></span>
                            <span>45 degree</span>
                        </li><li>
                        <span class="shapeItem" data-shape='{"path":"m0,20 L11.5,0 L11.5,10 L120,10 L120,31 L11.5,31 L11.5,40 z"}' style="background-position: -540px 0"></span>
                        <span>60 degree</span>
                    </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div id="center-pane">
        <div class="pane-content">
            <div id="diagram"></div>
        </div>
    </div>
    <div id="right-pane">
        <div class="pane-content">
            <ul id="configurationPanelBar">
                <li>
                    Canvas Properties
                    <div id="canvasProperties">
                        <ul>
                            <li>
                                <span>Background Color:</span>
                                <input id="canvasBackgroundColorPicker" class="" />
                            </li>
                            <li>
                                <span>Layout:</span>
                                <input id="canvasLayout" />
                            </li>
                        </ul>
                    </div>
                </li>
                <li>
                    Shape Properties
                    <div id="shapeProperties">
                        <ul>
                            <li>
                                <span>Background Color:</span>
                                <input id="shapeBackgroundColorPicker" />
                            </li>
                            <li>
                                <span>Stroke Color:</span>
                                <input id="shapeStrokeColorPicker" />
                            </li>
                            <li>
                                <span>Stroke Width:</span>
                                <input type="text" id="shapeStrokeWidth" class="numeric" />
                            </li>
                            <li>
                                <span>Width:</span>
                                <input type="text" id="shapeWidth" class="numeric" />
                            </li>
                            <li>
                                <span>Height:</span>
                                <input type="text" id="shapeHeight" class="numeric" />
                            </li>
                            <li>
                                <span>Position X:</span>
                                <input type="text" id="shapePositionX" class="numeric" />
                            </li>
                            <li>
                                <span>Position Y:</span>
                                <input type="text" id="shapePositionY" class="numeric" />
                            </li>
                        </ul>
                    </div>
                </li>
                <li>
                    Connection Properties
                    <div id="connectionProperties">
                        <ul>
                            <li>
                                <span>Start Cap:</span>
                                <input id="connectionStartCap" />
                            </li>
                            <li>
                                <span>End Cap:</span>
                                <input id="connectionEndCap" />
                            </li>
                        </ul>
                    </div>
                </li>
                <li>
                    Align
                    <div id="alignConfiguration" style="width: 100%; padding: 10px; box-sizing: border-box; text-align: left;">
                        <button class="configurationButtons" data-position="top">
                            <span class="alignTop"></span>
                        </button><button class="configurationButtons" data-position="bottom">
                        <span class="alignBottom"></span>
                    </button><button class="configurationButtons" data-position="left">
                        <span class="alignLeft"></span>
                    </button><button class="configurationButtons" data-position="right">
                        <span class="alignRight"></span>
                    </button>
                    </div>
                </li>
                <li>
                    Arrange
                    <div id="arrangeConfiguration">
                        <div style="width: 100%; padding: 10px; box-sizing: border-box; text-align: left;">
                            <button class="configurationButtons">
                                <span class="toFront"></span>
                            </button><button class="configurationButtons">
                            <span class="toBack"></span>
                        </button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="bottom-box">
    <input id="diagramZoom" />
    <input type="text" id="diagramZoomIndicator" class="k-textbox" value="100" style="width: 40px; vertical-align: middle;" />
</div>

<div class="about">
    <h2>HTML5 Diagram Sample Application by Telerik</h2>

    <p>The Diagramming Tool app was built with Telerik HTML5 Kendo UI widgets. Telerik Kendo UI framework consists of <a href="http://www.telerik.com/kendo-ui#Widgets">70+ UI widgets</a> that take care of the common functionality of your application, while leaving you with more time to work on its business logic.</p>
    <p>The app demonstrates the Diagram widget functionality and its integration with numerous other Telerik components. The source code is available so you can use it as a base for implementing your custom scenario.</p>
    <p>The sample app enables users to modify, add, remove and connect shapes. Once they are happy with the diagram they have created, they can save it to a file and load it back later.</p>
    <p>Make sure you try the various built-in layouts of the Diagram control showcased in the sample.</p>
    <h3>Featured Kendo UI Widgets</h3>
    <ul>
        <li><a href="http://www.telerik.com/kendo-ui/web-button">Button</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/colorpicker">Color Picker</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/diagram">Diagram</a></li>
        <li><a href="http://demos.telerik.com/kendo-ui/web/dragdrop/index.html">Drag & Drop</a></li>
    </ul>
    <ul>
        <li><a href="http://www.telerik.com/kendo-ui/dropdownlist">DropDownList</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/menu">Menu</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/numerictextbox">Numeric TextBox</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/panelbar">PanelBar</a></li>
    </ul>
    <ul>
        <li><a href="http://www.telerik.com/kendo-ui/splitter">Splitter</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/slider">Slider</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/upload">Upload</a></li>
        <li><a href="http://www.telerik.com/kendo-ui/window">Window</a></li>
    </ul>
    <br/><br/>
</div>

<script>
    $(function() {
        var Shape = kendo.dataviz.diagram.Shape,
                Connection = kendo.dataviz.diagram.Connection,
                Rect = kendo.dataviz.diagram.Rect,
                Point = kendo.dataviz.diagram.Point,
                selected;

        $("#canvasProperties").on("change", canvasPropertiesChange);

        var layoutMapping = {
            "TreeDown": {
                type: "tree",
                subtype: "down"
            },
            "TreeUp": {
                type: "tree",
                subtype: "up"
            },
            "TreeLeft": {
                type: "tree",
                subtype: "left"
            },
            "TreeRight": {
                type: "tree",
                subtype: "right"
            },
            "RadialTree": {
                type: "tree",
                subtype: "radial"
            },
            "TipOverTree": {
                type: "tree",
                subtype: "typeover"
            },
            "LayeredHorizontal": {
                type: "layered",
                subtype: "horizontal"
            },
            "LayeredVertical": {
                type: "layered",
                subtype: "vertial"
            },
            "ForceDirected": {
                type: "force",
                subtype: "directed"
            },
            "MindmapVertical": {
                type: "tree",
                subtype: "mindmapvertical"
            },
            "MindmapHorizontal": {
                type: "tree",
                subtype: "mindmaphorizontal"
            }
        };

        function canvasPropertiesChange() {
            diagram.element.css(
                    "background-color",
                    $("#canvasBackgroundColorPicker").getKendoColorPicker().value());
            var layout = layoutMapping[$("#canvasLayout").getKendoDropDownList().value()];

            diagram.layout({
                type: layout.type,
                subtype: layout.subtype,
                animation: true
            });
        }

        $("#shapeProperties").on("change", shapePropertiesChange);

        function shapePropertiesChange() {
            var elements = selected || [],
                    options = {
                        background: $("#shapeBackgroundColorPicker").getKendoColorPicker().value(),
                        stroke: {
                            color: $("#shapeStrokeColorPicker").getKendoColorPicker().value(),
                            width: $("#shapeStrokeWidth").getKendoNumericTextBox().value()
                        }
                    },
                    bounds = new Rect(
                            $("#shapePositionX").getKendoNumericTextBox().value(),
                            $("#shapePositionY").getKendoNumericTextBox().value(),
                            $("#shapeWidth").getKendoNumericTextBox().value(),
                            $("#shapeHeight").getKendoNumericTextBox().value()
                    ),
                    element, i;

            for (i = 0; i < elements.length; i++) {
                element = elements[i];
                if (element instanceof Shape) {
                    element.redraw(options);

                    element.bounds(bounds);
                }
            }
        }

        $("#connectionProperties").on("change", connectionPropertiesChange);

        function connectionPropertiesChange() {
            var elements = selected || [],
                    options = {
                        startCap: $("#connectionStartCap").getKendoDropDownList().value(),
                        endCap: $("#connectionEndCap").getKendoDropDownList().value()
                    },
                    element;

            for (i = 0; i < elements.length; i++) {
                element = elements[i];
                if (element instanceof Connection) {
                    element.redraw(options);
                }
            }
        }

        $("#alignConfiguration .configurationButtons").kendoButton({
            click: function(e) {
                var value = this.element.data("position");
                diagram.alignShapes(value);
            }
        });

        $("#arrangeConfiguration .configurationButtons").kendoButton({
            click: function (e) {
                var methodName = this.element.find("span").attr("class");
                diagram[methodName]();
            }
        });

        $("#diagramZoomIndicator").change(function() {
            var value = $(this).val();
            $("#diagramZoom").getKendoSlider().value(value);
            diagram.zoom(value);
        });

        function reset() {
            diagram.clear();
        }

        function undo() {
            diagram.undo();
        }

        function redo() {
            diagram.redo();
        }

        function copyItem() {
            diagram.copy();
        }

        function pasteItem() {
            diagram.paste();
        }

        var actions = {
            blank: reset,
            undo: undo,
            redo: redo,
            copy: copyItem,
            paste: pasteItem
        };

        function onMenuSelect(e) {
            var item = $(e.item),
                    itemText = item.children(".k-link").text();

            if (!item.hasClass("active")) {
                return;
            }

            actions[itemText.charAt(0).toLowerCase() + itemText.slice(1)]();
        }

        $("#menu ul").kendoMenu({
            dataSource: [
                { text: "New", spriteCssClass: "new-item", items: [
                    { text: "Blank", spriteCssClass: "blank-item", cssClass: "active" }
                ]
                },
                { text: "Open<input id='upload' type='file' name='files' />", encoded: false, spriteCssClass: "open-item", cssClass: "upload-item" },
                { text: "Save<a id='export' download='diagram.json'></a>", encoded: false, spriteCssClass: "save-item" },
                { text: "Undo", spriteCssClass: "undo-item", cssClass: "active" },
                { text: "Redo", spriteCssClass: "redo-item", cssClass: "active" },
                { text: "Copy", spriteCssClass: "copy-item", cssClass: "active" },
                { text: "Paste", spriteCssClass: "paste-item", cssClass: "active" }
            ],
            select: onMenuSelect
        });

        $("#export").on("click", function() {
            var json = JSON.stringify(diagram.save()),
                    blob = new Blob([json], {type: "application\/json"});;

            if (navigator.msSaveBlob) {
                navigator.msSaveBlob(blob, this.getAttribute("download"));
            } else {
                this.href = window.URL.createObjectURL(blob);
            }
        });

        function onSelect(e) {
            if (typeof(FileReader) !== "undefined") {
                var f = e.files[0].rawFile,
                        reader = new FileReader;

                reader.onload = (function(file) {
                    return function(e) {
                        diagram.load(JSON.parse(e.target.result));
                    };
                })(f);

                reader.readAsBinaryString(f);
            }
        }

        $("#upload").kendoUpload({
            async: {
                saveUrl: "save",
                removeUrl: "remove",
                autoUpload: true
            },
            showFileList: false,
            localization: {
                select: ""
            },
            select: onSelect
        });

        $("#splitter").kendoSplitter({
            panes: [
                { collapsible: true, size: "200px" },
                { collapsible: false, scrollable: false },
                { collapsible: true, size: "300px" }
            ]
        });

        function diagramSelect(e) {
            if (e.selected.length) {
                selected = e.selected;
                var element = e.selected[0];
                if (element instanceof Shape) {
                    updateShapeProperties(element.options);
                } else {
                    updateConnectionProperties(element.options);
                }
            }
        }

        var diagram = $("#diagram").kendoDiagram({
            theme: "default",
            dataSource: {
                data: [{
                    name: "0",
                    items: [{
                        name: "0"
                    }]
                }],
                schema: {
                    model: {
                        children: "items"
                    }
                }
            },
            shapeDefaults: {
                width: 120,
                height: 120,
                background: "#8ebc00"
            },
            layout: {
                type: "tree",
                subtype: "right"
            },
            select: diagramSelect
        }).getKendoDiagram();

        $("#shapesPanelBar").kendoPanelBar({
            expandMode: "multiple"
        }).getKendoPanelBar().expand(">li", false);

        $("#configurationPanelBar").kendoPanelBar({
            expandMode: "multiple"
        }).getKendoPanelBar().expand(">li", false);

        $("#canvasBackgroundColorPicker").kendoColorPicker({
            value: "#ffffff",
            buttons: false
        });

        $("#shapeBackgroundColorPicker").kendoColorPicker({
            value: "#ffffff",
            buttons: false
        });

        $("#shapeStrokeColorPicker").kendoColorPicker({
            value: "#ffffff",
            buttons: false
        });

        $("#canvasLayout").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: [
                { value: "TreeDown", text: "Tree Down" },
                { value: "TreeUp", text: "Tree Up" },
                { value: "TreeLeft", text: "Tree Left" },
                { value: "TreeRight", text: "Tree Right" },
                { value: "RadialTree", text: "Radial Tree" },
                { value: "TipOverTree", text: "Tip-Over Tree" },
                { value: "LayeredHorizontal", text: "Layered Horizontal" },
                { value: "LayeredVertical", text: "Layered Vertical" },
                { value: "ForceDirected", text: "Force directed" },
                { value: "MindmapVertical", text: "Mindmap Vertical" },
                { value: "MindmapHorizontal", text: "Mindmap Horizontal" }
            ]
        });

        $("#connectionStartCap").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: [
                { value: "None", text: "None" },
                { value: "ArrowStart", text: "Arrow Start" },
                { value: "ArrowEnd", text: "Arrow End" },
                { value: "FilledCircle", text: "Filed Circle" }
            ]
        });

        $("#connectionEndCap").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: [
                { value: "None", text: "None" },
                { value: "ArrowStart", text: "Arrow Start" },
                { value: "ArrowEnd", text: "Arrow End" },
                { value: "FilledCircle", text: "Filed Circle" }
            ]
        });

        function updateSliderIndicator(e) {
            $("#diagramZoomIndicator").attr("value", e.value);

            diagram.zoom(e.value / 100);
        }

        $("#diagramZoom").kendoSlider({
            min: 10,
            max: 200,
            value: 100,
            smallStep: 10,
            largeStep: 50,
            tickPlacement: "none",
            showButtons: false,
            change: updateSliderIndicator,
            slide: updateSliderIndicator
        });

        $(".numeric").kendoNumericTextBox();

        window.about = $(".about").kendoWindow({
            visible: false,
            width: 800,
            resizable: false,
            title: "About"
        }).getKendoWindow();

        function updateShapeProperties(shape) {
            $("#shapeBackgroundColorPicker").getKendoColorPicker().value(kendo.parseColor(shape.background));
            $("#shapeStrokeColorPicker").getKendoColorPicker().value(kendo.parseColor(shape.stroke.color));
            $("#shapeStrokeWidth").getKendoNumericTextBox().value(shape.stroke.width);
            $("#shapeWidth").getKendoNumericTextBox().value(shape.width);
            $("#shapeHeight").getKendoNumericTextBox().value(shape.height);
            $("#shapePositionX").getKendoNumericTextBox().value(shape.x);
            $("#shapePositionY").getKendoNumericTextBox().value(shape.y);
        }

        function updateConnectionProperties(shape) {
            $("#connectionStartCap").getKendoDropDownList().value(shape.startCap);
            $("#connectionEndCap").getKendoDropDownList().value(shape.endCap);
        }

        $("#shapesPanelBar .shapeItem").kendoDraggable({
            hint: function() {
                return this.element.clone();
            }
        });

        $("#diagram").kendoDropTarget({
            drop: function(e) {
                var item, pos, transformed;
                if (e.draggable.hint) {
                    item = e.draggable.hint.data("shape");
                    pos = e.draggable.hintOffset;
                    pos = new Point(pos.left, pos.top);
                    var transformed = diagram.documentToModel(pos);
                    item.x = transformed.x;
                    item.y = transformed.y;

                    diagram.addShape(item);
                }
            }
        });
    });
</script>
</body>
</html>