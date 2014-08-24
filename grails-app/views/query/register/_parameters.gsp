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