<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 11/7/2016
  Time: 3:55 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6 k-rtl">
            <br/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-6 k-rtl">
        </div>

        <div class="col-xs-6 k-rtl">
            <g:render template="/user/wall/topMaterials" model="${[groupId: '12:776']}"/>
        </div>
    </div>
</div>
</body>
</html>