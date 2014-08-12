<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/11/14
  Time: 4:21 PM
--%>

<%@ page import="stocks.alerting.ScheduleDay" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="site"/>
    <title><g:message code="query.register.title" args="${[queryInstance.query?.title]}"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.register.title" args="${[queryInstance.query?.title]}"/></h1>

            <div class="query-register-description"><format:html value="${queryInstance.query?.description}"/></div>
            <form:form name="registerForm" action="saveRegistration">
                <form:hidden name="id" value="${queryInstance?.id}"/>
                <form:hidden name="queryId" value="${params.query ?: queryInstance?.query?.id}"/>
                <g:each in="${stocks.alerting.Parameter.findAllByQuery(queryInstance.query)}" var="parameter">
                    <form:field label="${parameter.name}">
                        <g:if test="${parameter.type == 'date'}">
                            <form:datePicker name="parameter_${parameter.id}" validation="required" style="width:500px"
                                             value="${queryInstance.parameterValues?.find {
                                                 it.parameter?.id == parameter?.id
                                             }?.value ?: parameter.defaultValue}"/>
                        </g:if>
                        <g:elseif test="${parameter.type == 'integer'}">
                            <form:numericTextBox name="parameter_${parameter.id}" validation="required"
                                                 style="width:500px"
                                                 value="${queryInstance.parameterValues?.find {
                                                     it.parameter?.id == parameter?.id
                                                 }?.value ?: parameter.defaultValue}"/>
                        </g:elseif>
                        <g:elseif test="${parameter.type == 'string'}">
                            <form:textBox name="parameter_${parameter.id}" validation="required" style="width:500px"
                                          value="${queryInstance.parameterValues?.find {
                                              it.parameter?.id == parameter?.id
                                          }?.value ?: parameter.defaultValue}"/>
                        </g:elseif>
                    </form:field>
                </g:each>
                <form:field fieldName="query.register.scheduleType" showHelp="${scheduleTypes?.size() > 1 ? '1' : '0'}">
                    <g:if test="${scheduleTypes?.size() > 1}">
                        <form:select name="scheduleType" items="${scheduleTypes.collect {
                            [text: message(code: "schedule.type.${it}"), value: it]
                        }}" style="width:500px" onchange="toggle_periodicSchedule"
                                     value="${queryInstance.schedule?.type}"/>
                    </g:if>
                    <g:else>
                        <form:hidden name="scheduleType" value="${scheduleTypes.find()}"/>
                        <g:message code="schedule.type.${scheduleTypes.find()}"/>
                    </g:else>
                </form:field>
                <div id="periodicScheduleForm" style="display: none;">
                    <form:field fieldName="query.register.interval">
                        <form:select items="${queryInstance.query.scheduleTemplate.intervalSteps.collect {
                            [text: "${it >= 60 ? Math.floor(it / 60).toInteger() + ' ' + message(code: 'hour') : ''}" + " ${it >= 60 && it % 60 > 0 ? message(code: 'and') : ''} " + "${it % 60 > 0 ? it % 60 + ' ' + message(code: 'minute') : ''}", value: it]
                        }}"
                                     style="width:500px;" name="intervalStep"
                                     value="${queryInstance?.schedule?.intervalStep}"/>
                    </form:field>
                    <div class="k-rtl">
                        <div id="tabstrip">
                            <ul>
                                <g:each in="${queryInstance.query?.scheduleTemplate?.dayTemplates?.sort {
                                    it.id
                                }?.collect {
                                    it.day
                                }}" var="day">
                                    <li>
                                        <g:message code="ScheduleDayTemplate.${day}.label"/>
                                    </li>
                                </g:each>

                                <script language="javascript" type="text/javascript">
                                    $($('#tabstrip ul li').get(0)).addClass('k-state-active');
                                </script>
                            </ul>

                            <g:each in="${queryInstance.query?.scheduleTemplate?.dayTemplates?.sort { it.id }?.collect {
                                it.day
                            }}" var="day">
                                <div>
                                    <g:render template="daySchedule"
                                              model="${[
                                                      day                : day,
                                                      scheduleDayTemplate: queryInstance.query?.scheduleTemplate?.dayTemplates?.find {
                                                          it.day == day
                                                      },
                                                      daySchedule        : queryInstance.schedule?.days?.find {
                                                          it.day == day
                                                      }
                                              ]}"/>
                                </div>
                            </g:each>
                        </div>
                    </div>
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