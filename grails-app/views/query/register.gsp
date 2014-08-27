<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/11/14
  Time: 4:21 PM
--%>

<%@ page import="stocks.alerting.ScheduleTime" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="query.register.title" args="${[queryInstance.query?.title]}"/></title>
    <asset:javascript src="angular.min.js"/>
    <asset:stylesheet src="jquery-clockpicker.css"/>
    <asset:javascript src="jquery-clockpicker.js"/>
    <script language="JavaScript">
        var timeList =
        <format:html value="${ScheduleTime.findAllBySchedule(queryInstance?.schedule)?.collect {
                [value: String.format('%02d:%02d', (it.timeInMinute / 60).toInteger(), (it.timeInMinute % 60).toInteger())]
    } as grails.converters.JSON}"/>
    </script>
    <asset:javascript src="alerting.register.controller.js"/>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingRegisterController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.register.title" args="${[queryInstance.query?.title]}"/></h1>

            <div class="query-register-description"><format:html value="${queryInstance.query?.description}"/></div>
            <form:form name="registerForm" action="saveRegistration">
                <form:hidden name="id" value="${queryInstance?.id}"/>
                <form:hidden name="queryId" value="${params.query ?: queryInstance?.query?.id}"/>
                <g:render template="register/parameters"/>
                <form:field fieldName="query.register.scheduleType" showHelp="${scheduleTypes?.size() > 1 ? '1' : '0'}"
                            showLabel="${scheduleTypes?.size() > 1 ? '1' : '0'}">
                    <g:if test="${scheduleTypes?.size() > 1}">
                        <form:select name="scheduleType" items="${scheduleTypes.collect {
                            [text: message(code: "schedule.type.${it}"), value: it]
                        }}" style="width:500px" onchange="toggle_periodicSchedule"
                                     value="${queryInstance.schedule?.type}"/>
                    </g:if>
                    <g:else>
                        <form:hidden name="scheduleType" value="${scheduleTypes.find()}"/>
                        <div class="info"><g:message code="schedule.type.${scheduleTypes.find()}"/></div>
                    </g:else>
                </form:field>
                <div id="periodicScheduleForm" style="display: none;">
                    <g:render template="register/periodicSchedule"/>
                </div>

                <div id="specificTimeScheduleForm" style="display: none">
                    <g:render template="register/specificTimeSchedule"/>
                </div>

                <div class="toolbar">
                    <form:submitButton text="${message(code: 'query.register.submit')}"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script language="javascript">

    function toggle_periodicSchedule(e) {
        if ($('#scheduleType').data('kendoComboBox').value() == 'periodic')
            $('#periodicScheduleForm').stop().slideDown();
        else
            $('#periodicScheduleForm').stop().slideUp();

        if ($('#scheduleType').data('kendoComboBox').value() == 'specificTime')
            $('#specificTimeScheduleForm').stop().slideDown();
        else
            $('#specificTimeScheduleForm').stop().slideUp();
    }

    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });

        toggle_periodicSchedule();
    });
</script>
</body>
</html>