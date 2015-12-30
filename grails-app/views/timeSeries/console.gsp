<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 31/05/2015
  Time: 16:45
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Time Series Console</title>
</head>

<body>

<div class="container ltr">

    <div class="row">
        <div class="col-xs-12">
            <h1>
                Time Series Console
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <g:form action="console">
                <form:textArea name="query" style="width:100%;height:300px;padding:15px;" value="${params.query}" />
                <div style="text-align: right;margin:10px;">
                    <form:submitButton text="Execute"/>
                </div>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <pre id="result">
            </pre>
        </div>
    </div>

    <script language="javascript" type="text/javascript">
        var data = <format:html value="${result as JSON}"/>;
        $('#result').html(JSON.stringify(data, null, 4));
    </script>
</div>
</body>
</html>