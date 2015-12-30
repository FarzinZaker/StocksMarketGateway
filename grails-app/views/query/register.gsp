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
    <script language="javascript" type="text/javascript" src="${resource(dir: 'js', file: 'angular.min.js')}"></script>
    <asset:stylesheet src="jquery-clockpicker.css"/>
    <asset:javascript src="jquery-clockpicker.js"/>
    <asset:javascript src="jquery.color.js"/>
    <script language="JavaScript">
        var timeList =
        <format:html value="${ScheduleTime.findAllBySchedule(queryInstance?.schedule)?.collect {
                [value: String.format('%02d:%02d', (it.timeInMinute / 60).toInteger(), (it.timeInMinute % 60).toInteger())]
    } as grails.converters.JSON}"/>
    </script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js', file: 'alerting.register.controller.js')}"></script>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingRegisterController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.newsletter.register'), url: createLink(controller: 'query')],
                    [text: message(code: 'menu.newsletter.register.list'), url: createLink(controller: 'query', action: 'select')],
                    [text: '<i class="fa fa-paper-plane-o"></i> ' + queryInstance.query?.title, url: createLink(controller: 'query', action: 'register', params: params)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="crimson">--}%
                %{--<i class="fa fa-paper-plane-o"></i>--}%
                %{--<g:message code="query.register.title" args="${[queryInstance.query?.title]}"/>--}%
            %{--</h1>--}%

            <div class="query-register-description"><format:html value="${queryInstance.query?.description}"/></div>
            <form:form name="registerForm" action="saveRegistration">
                <form:hidden name="id" value="${queryInstance?.id}"/>
                <form:hidden name="queryId" value="${params.query ?: queryInstance?.query?.id}"/>
                <div class="k-rtl">
                    <alerting:parameterFieldList queryInstanceId="${queryInstance?.id}" queryId="${params.query}"/>
                </div>
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
                    <form:submitButton text="${message(code: 'query.register.submit')}" name="save"/>
                    <form:submitButton text="${message(code: 'query.register.submitAndNew')}" name="saveAndNew"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script language="javascript">

    function toggle_periodicSchedule(e) {
        if ($('#scheduleType').length > 0 && $('#scheduleType').data('kendoComboBox')) {
            if ($('#scheduleType').data('kendoComboBox').value() == 'periodic')
                $('#periodicScheduleForm').stop().slideDown();
            else
                $('#periodicScheduleForm').stop().slideUp();

            if ($('#scheduleType').data('kendoComboBox').value() == 'specificTime')
                $('#specificTimeScheduleForm').stop().slideDown();
            else
                $('#specificTimeScheduleForm').stop().slideUp();
        }
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

        //this would do the same as button click as both submit the form
        $(document).on("submit", "#registerForm", function (e) {

            var result = true;
            <g:each in="${stocks.alerting.Parameter.findAllByQuery(queryInstance?.query)}" var="parameter">
            if ($('input[name=parameterValueValue_${parameter.id}]').length == 0) {
                $('#parameterValuesContainer_${parameter.id}').parent().parent().find('.help').append('<span class="help-block form-error">${message(code:'query.register.validation.required')}</span>');
                result = false;
            }
            </g:each>

            if (!result) {
                return false;
            }
        });
    });
</script>
</body>
</html>