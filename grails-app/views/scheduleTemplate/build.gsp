<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/10/14
  Time: 5:56 PM
--%>

<%@ page import="grails.converters.JSON; stocks.alerting.ScheduleDayTemplate" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="scheduleTemplate.build.title"/></title>
    <asset:javascript src="angular.min.js"/>
    <script language="JavaScript">
        var intervalSteps =
        <format:html value="${scheduleTemplateInstance.intervalSteps.collect {[value: it]} as JSON}"/>
    </script>
    <asset:javascript src="alerting.scheduleTemplate.controller.js"/>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingScheduleTemplateController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="scheduleTemplate.build.title"/></h1>

            <form:form name="scheduleForm" action="save">
                <form:hidden name="id" entity="${scheduleTemplateInstance}"/>
                <form:field fieldName="scheduleTemplate.title">
                    <form:textBox name="title" entity="${scheduleTemplateInstance}" validation="required"
                                  style="width:500px;"/>
                </form:field>
                <form:field fieldName="scheduleTemplate.allowedTypes">
                    <div style="width: 500px;">
                        <form:checkbox checked="${scheduleTemplateInstance?.eventBasedNotificationEnabled}"
                                       name="eventBasedNotificationEnabled"
                                       text="${message(code: 'scheduleTemplateInstance.eventBasedNotificationEnabled')}"/>
                    </div>

                    <div style="width: 500px;">
                        <form:checkbox checked="${scheduleTemplateInstance?.periodicNotificationEnabled}"
                                       name="periodicNotificationEnabled"
                                       text="${message(code: 'scheduleTemplateInstance.periodicNotificationEnabled')}"
                                       onchange="toggle_periodicSchedule(this);"/>
                    </div>

                    <div style="width: 500px;">
                        <form:checkbox checked="${scheduleTemplateInstance?.specificTimeNotificationEnabled}"
                                       name="specificTimeNotificationEnabled"
                                       text="${message(code: 'scheduleTemplateInstance.specificTimeNotificationEnabled')}"/>
                    </div>
                </form:field>
                <div id="periodicScheduleForm" style="display: none;">
                    <form:field fieldName="scheduleTemplate.intervalSteps">
                        <div ng-repeat="intervalStep in intervalSteps" class="grid-form-row" ng-animate="'animate'">
                            <input type="button" class="btn-delete-row" ng-click="removeIntervalStep($index)"
                                   data-row="{{$index}}"/>
                            <form:textBox validation="required" ng-model="intervalStep.value" name="intervalSteps"
                                          style="width:475px;margin-top:5px;margin-bottom:5px;"
                                          placeholder="${message(code: 'scheduleTemplate.build.intervalStep')}"/>

                        </div>

                        <div class="info" ng-show="!intervalSteps.length"><g:message
                                code="scheduleTemplate.build.intervalSteps.empty"/></div>

                        <div class="toolbar">
                            <form:button text="${message(code: 'scheduleTemplate.build.intervalStep.add')}"
                                         ng-click="addIntervalStep()" onclick="return false"/>
                        </div>
                    </form:field>
                    <div class="k-rtl">
                        <div id="tabstrip">
                            <ul>
                                <g:each in="${ScheduleDayTemplate.constraints.day.inList}" var="day">
                                    <li>
                                        <g:message code="ScheduleDayTemplate.${day}.label"/>
                                    </li>
                                </g:each>

                                <script language="javascript" type="text/javascript">
                                    $($('#tabstrip ul li').get(0)).addClass('k-state-active');
                                </script>
                            </ul>

                            <g:each in="${ScheduleDayTemplate.constraints.day.inList}" var="day">
                                <div>
                                    <g:render template="day"
                                              model="${[scheduleDayTemplateInstance: scheduleTemplateInstance.dayTemplates.find {
                                                  it.day == day
                                              }, day : day]}"/>
                                </div>
                            </g:each>
                        </div>
                    </div>
                </div>

                <div class="toolbar">
                    <form:submitButton text="${message(code: 'scheduleTemplate.build.submit')}"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script language="javascript">

    function toggle_periodicSchedule(item){
        if ($(item).is(':checked'))
            $('#periodicScheduleForm').stop().slideDown();
        else
            $('#periodicScheduleForm').first().stop().slideUp();
    }

    toggle_periodicSchedule(document.getElementById('periodicNotificationEnabled'));

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