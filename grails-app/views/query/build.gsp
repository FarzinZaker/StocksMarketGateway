<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/5/14
  Time: 4:46 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="query.build.title" args="${[message(code: "${domainClass}.label")]}"/></title>
    <script language="javascript" type="text/javascript" src="${resource(dir:'js', file:'angular.min.js')}"></script>
    <script language="JavaScript">
        var parameterList = <format:html value="${parameters.collect {
                [
                        name: it.name,
                        type: it.type,
                        defaultValue: it.defaultValue
                ]
                } as JSON}"/>
        var sortingRuleList = <format:html value="${sortingRules.collect {
                [
                        fieldName: it.fieldName,
                        sortDirection: it.sortDirection,
                        sortOrder: it.sortOrder
                ]
                } as JSON}"/>

        var fieldList = <format:html value="${fields.collect{[name: message(code: "${domainClass}.${it}.label"), value: it, type:'field', typeString:message(code:"autocomplete.itemType.field")]} as JSON}"/>
        var parameterTypeString = "${message(code:'autocomplete.itemType.parameter')}"
    </script>
    <script language="javascript" type="text/javascript" src="${resource(dir:'js', file:'alerting.query.controller.js')}"></script>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.build.title" args="${[message(code: "${domainClass}.label")]}"/></h1>

            <form:form name="queryForm" action="save">
                <form:hidden name="id" entity="${queryInstance}"/>
                <g:if test="${queryInstance}">
                    <form:hidden name="domainClazz" entity="${queryInstance}"/>
                </g:if>
                <g:else>
                    <form:hidden name="domainClazz" value="${params.domainClass}"/>
                </g:else>
                <form:field fieldName="query.title">
                    <form:textBox name="title" entity="${queryInstance}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="query.description">
                    <form:editor name="description" entity="${queryInstance}" validation="required" width="500"/>
                </form:field>
                <form:field fieldName="query.category">
                    <form:treeCombo name="category" style="width:500px" value="${queryInstance?.category?.id ?: 0}"
                                    domainClass="stocks.alerting.QueryCategory" relationProperty="parent"
                                    titleProperty="name"/>
                </form:field>
                <form:field fieldName="query.image">
                    <form:imageUpload name="image" style="width:500px;" entity="${queryInstance}"
                                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
                </form:field>
                <form:field fieldName="query.scheduleTemplate">
                    <form:select name="scheduleTemplate" value="${queryInstance?.scheduleTemplate?.id}" items="${scheduleTemplates.collect{[text: it.title, value: it.id]}}" validation="required" style="width:500px;"/>
                </form:field>
                <div class="k-rtl" class="ng-cloak">
                    <div id="tabstrip">
                        <ul>
                            <li class="k-state-active">
                                <g:message code="query.build.parameters.title"/>
                            </li>
                            <li>
                                <g:message code="query.build.filter.title"/>
                            </li>
                            <li>
                                <g:message code="query.build.sorting.title"/>
                            </li>
                            <li>
                                <g:message code="query.build.smsTemplate.title"/>
                            </li>
                        </ul>

                        <div>
                            <g:render template="parameters"/>
                        </div>

                        <div>
                            <g:render template="query"/>
                        </div>

                        <div>
                            <g:render template="sorting"/>
                        </div>

                        <div>
                            <g:render template="smsTemplate"/>
                        </div>
                    </div>
                </div>

                <div class="toolbar">
                    <form:submitButton text="${message(code: 'query.build.submit')}"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script language="javascript">
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

</body>
</html>