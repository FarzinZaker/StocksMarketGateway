<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 02/03/2015
  Time: 13:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="${params.id ? 'tradeStrategy.title.edit' : 'tradeStrategy.title.new'}"
                      args="${[tradeStrategy?.name]}"/></title>
    <asset:javascript src="jquery.resize.min.js"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.strategy'), url: createLink(controller: 'tradeStrategy')],
                    [text: '<i class="fa fa-magic"></i> ' + message(code: "${params.id ? 'tradeStrategy.title.edit' : 'tradeStrategy.title.new'}", args: [tradeStrategy?.name]), url: createLink(controller: 'tradeStrategy', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    <g:if test="${flash.errors}">
        <div class="row">
            <div class="col-xs-12">
                <div class="errorBlock">
                    <g:message code="strategy.build.errors.title"/>
                    <ul>
                        <g:each in="${flash.errors}" var="error">
                            <li>${error}</li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </g:if>

    <div class="row">
        <div class="col-xs-12">
            <form:field fieldName="tradeStrategy.name">
                <form:textBox name="nameInput" style="width:450px;" value="${tradeStrategy?.name ?: ''}"/>
            </form:field>
        </div>
    </div>

    <g:if test="${params.id}">

        <div class="row">
            <div class="col-xs-12 k-rtl">
                <div id="tabstrip">
                    <ul>
                        <li class="k-state-active">
                            <g:message code="tradeStrategy.build.buyRule.title"/>
                        </li>
                        <li>
                            <g:message code="tradeStrategy.build.sellRule.title"/>
                        </li>
                        <li>
                            <g:message code="tradeStrategy.build.limits.title"/>
                        </li>
                    </ul>

                    <div>
                        <g:render template="buyRule"/>
                    </div>

                    <div>
                        <g:render template="sellRule"/>
                    </div>

                    <div>
                        <g:render template="limits"/>
                    </div>
                </div>
            </div>
        </div>

        <script language="javascript" type="text/javascript">
            $(document).ready(function () {
                $("#tabstrip").kendoTabStrip({
                    animation: {
                        open: {
                            effects: "fadeIn"
                        }
                    }
                });
            });
        </script>

    </g:if>

    <div class="row">
        <div class="col-xs-12">
            <g:render template="submit"/>
        </div>
    </div>

</div>
</body>
</html>