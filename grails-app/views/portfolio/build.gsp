<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.build.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.build.title"/></h1>
            <g:if test="${flash.message}">
                <div class="errorMessage">
                    ${flash.message}
                </div>
            </g:if>
            <form:form action="save" name="portfolioForm">
                <form:hidden name="id" entity="${portfolio}"/>

                <form:field fieldName="portfolio.name">
                    <form:textBox name="name" style="width:500px" entity="${portfolio}" validation="required"
                                  value="${flash.data ?: portfolio?.name ?: ''}"/>
                </form:field>


                <div class="toolbar">
                    <input type="submit" value="${message(code: 'portfolio.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>