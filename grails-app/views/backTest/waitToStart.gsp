<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 6/29/2016
  Time: 2:16 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${'backTest.view.title'}"
                      args="${[backTest?.tradeStrategy?.name, "${backTest.symbol?.persianName} - ${backTest.symbol?.persianCode}"]}"/></title>
</head>

<body>
<div class="container">

    <div class="row">
        <div class="col-sm-12 text-center">
            <asset:image src="big-loading.gif"/>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 text-center">
            <g:message code="backTest.waitToStart.description"/>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 text-center">
            <g:message code="backTest.waitToStart.wait"/>
        </div>
    </div>
</div>
    <script language="javascript">
        $(document).ready(function(){
            checkForStart();
        });
        function checkForStart(){
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'waitToStartJson')}',
                data: { id: ${params.id} }
            }).done(function (response) {
                if (response == "1") {
                    window.location.href = '${createLink(action: 'view', id: params.id)}';
                }
                else {
                    setTimeout(checkForStart, 500);
                }
            });
        }
    </script>
</body>
</html>