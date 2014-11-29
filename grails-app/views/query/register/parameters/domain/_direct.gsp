<g:set var="domainClass" value="${grailsApplication.getDomainClass(queryInstance.query.domainClazz)}"/>
<g:set var="domain" value="${domainClass.fullName}"/>
<g:set var="field" value="${parameter.type?.toString()}"/>
<form:textBox name="parameter_${parameter.id}_${deliveryMethod.name}_selector" validation="${parameter.multiSelect ? '' : 'required'}"
              style="width:500px"/>
<script language="javascript" type="text/javascript">
    function getParameterValueText_${parameter.id}_${deliveryMethod.name}() {
        var combobox = $("#parameter_${parameter.id}_${deliveryMethod.name}_selector").data("kendoComboBox");

        if (combobox.dataItem())
            return combobox.dataItem().name;
        else
            return combobox.text();
    }
    function getParameterValueValue_${parameter.id}_${deliveryMethod.name}() {
        var combobox = $("#parameter_${parameter.id}_${deliveryMethod.name}_selector").data("kendoComboBox");

        if (combobox.dataItem())
            return combobox.dataItem().value;
        else
            return combobox.text();
    }
    function getParameterValueType_${parameter.id}_${deliveryMethod.name}() {
        return '${property.name}_${deliveryMethod.name}';
    }
    $(document).ready(function () {

        $("#parameter_${parameter.id}_${deliveryMethod.name}_selector").removeClass('k-textbox').width('490px').kendoComboBox({
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