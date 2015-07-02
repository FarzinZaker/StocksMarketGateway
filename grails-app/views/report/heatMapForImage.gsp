<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 06/04/2015
  Time: 17:04
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="empty"/>
    <title><g:message code="report.heatmap.title"/></title>
    <style>
    body {
        height: auto !important;
    }

    #body {
        margin: 0 !important;
    }

    .step {
        display: none;
    }
    </style>
</head>

<body>
<div style="display: none">
    <g:render template="heatMapFilters"/>
</div>
<g:render template="heatMap"/>
</body>
</html>