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
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/kendo.ui', file: 'kendo.dataviz.min.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${resource(dir: 'css/kendo.ui', file: 'kendo.dataviz.metro.min.css')}"/>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="report.heatmap.title"/></h1>

            <p><g:message code="report.heatmap.description"/></p>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12 k-rtl">

            <div id="treeMap" style="height: 600px; font-size: 12px;"></div>

            <div class="box">
                <div class="box-col">
                    <h4>TreeMap rendering types</h4>
                    <ul class="options">
                        <li>
                            <input id="typeSquarified" name="type"
                                   type="radio" value="squarified" checked="checked" autocomplete="off"/>
                            <label for="typeSquarified">Squarified</label>
                        </li>
                        <li>
                            <input id="typeVertical" name="type"
                                   type="radio" value="vertical" autocomplete="off"/>
                            <label for="typeVertical">Vertical(Slice and Dice)</label>
                        </li>
                        <li>
                            <input id="typeHorizontal" name="type"
                                   type="radio" value="horizontal" autocomplete="off"/>
                            <label for="typeHorizontal">Horizontal(Slice and Dice)</label>
                        </li>
                    </ul>
                </div>
            </div>
            <script language="javascript" type="text/javascript">
                function createTreeMap() {
                    $("#treeMap").kendoTreeMap({
                        dataSource: {
                            transport: {
                                read: {
                                    url: "${createLink(action: 'heatMapJson')}",
                                    dataType: "json"
                                }
                            },
                            schema: {
                                model: {
                                    children: "items"
                                }
                            }
                        },
                        valueField: "value",
                        textField: "name"
                    });
                }

                $(document).ready(function () {
                    createTreeMap();
                    $(document).bind("kendo:skinChange", createTreeMap);
                    $(".options").bind("change", refresh);
                });

                function refresh() {
                    $("#treeMap").getKendoTreeMap().setOptions({
                        type: $("input[name=type]:checked").val()
                    });
                }
            </script>
        </div>
    </div>
</div>

</body>
</html>