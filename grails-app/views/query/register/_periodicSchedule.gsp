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