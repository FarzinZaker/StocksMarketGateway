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
                <g:render template="heatMapFilters"/>
            </div>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12 k-rtl">
            <g:render template="heatMap"/>
        </div>
    </div>
</div>

</body>
</html>