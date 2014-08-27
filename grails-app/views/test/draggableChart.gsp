<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/19/14
  Time: 4:33 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>DraggableChart</title>
    <asset:javascript src="highcharts/highcharts.js"/>
    <asset:javascript src="highcharts/highcharts-more.js"/>
    <asset:javascript src="highcharts/highcharts-draggable-points.js"/>
    <asset:javascript src="sylvester.js"/>
    <asset:javascript src="lyric.js"/>
</head>

<body>
<chart:trendChart
        id="chart"
        width="1000"
        height="700"
        title="Estimate product sales for 2015 - 2017"
        subtitle="Drag the anchor points to adjust data"
        yAxisName="Units"
        xAxisName="Year"
        categories="${categories}"
        series="${series}"
        regressionDegree="3"
        trendCategories="${trendCategories}"/>
</body>
</html>