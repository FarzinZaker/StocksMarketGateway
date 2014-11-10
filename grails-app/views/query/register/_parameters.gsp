<div class="k-rtl">
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
            <g:else>
                <g:set var="domainClass" value="${grailsApplication.getDomainClass(queryInstance.query.domainClazz)}"/>
                <g:set var="domain" value="${domainClass.fullName}"/>
                <g:set var="field" value="${parameter.type?.toString()}"/>
                <form:textBox name="parameter_${parameter.id}" validation="required" style="width:500px"
                              value="${queryInstance.parameterValues?.find {
                                  it.parameter?.id == parameter?.id
                              }?.value ?: parameter.defaultValue}"/>
                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {

                        $("#parameter_${parameter.id}").removeClass('k-textbox').kendoComboBox({
                            dataTextField: "name",
                            dataValueField: "value",
                            filter: "contains",
                            dataSource: {
                                serverFiltering: true,
                                transport: {
                                    type: 'odata',
                                    read: {
                                        url: "${createLink(action: 'autoComplete')}?domain=${domain}&field=${field}",
                                        dataType: "json",
                                        type: "POST"

                                    }
                                },
                                schema: {
                                    data: "data" // records are returned in the "data" field of the response
                                }
                            }
                        });
                    });
                </script>
            </g:else>
        </form:field>
    </g:each>
</div>