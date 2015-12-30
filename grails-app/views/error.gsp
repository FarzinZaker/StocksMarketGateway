<%@ page import="grails.util.Environment" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <g:message code="error.title"/>
    </title>
    <meta name="layout" content="main">
    <g:if env="development">
        <asset:stylesheet src="errors.css"/>
    </g:if>
    <script language="javascript" type="text/javascript">
        function showError(){
            $('#errorContainer').slideDown();
            $('#btn-show').slideUp();
        }
    </script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h1><g:message code="error.title"/></h1>

            <div class="info"><g:message code="error.description"/></div>

            <div class="toolbar" style="display: ${Environment.current != Environment.DEVELOPMENT ? 'block': 'none'}">
                <form:button id="btn-show" text="${message(code: 'error.show')}" onclick="showError()"/>
            </div>

            <div class="ltr" id="errorContainer" style="display: ${Environment.current != Environment.DEVELOPMENT ? 'none': 'block'}">
                <g:renderException exception="${exception}"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
