<g:if test="${suggestedValues && suggestedValues.size()}">
    <form:select name="parameter_${parameter.id}_selector" validation="${parameter.multiSelect ? '' : 'required'}"
                 style="width:500px;" preSelect="false"
                 items="${suggestedValues.collect { [text: it.title, value: it.id] }}"
                 suggest="true" allowUserInput="true"/>
    <script language="javascript" type="text/javascript">
        function getParameterValueText_${parameter.id}() {
            var combobox = $("#parameter_${parameter.id}_selector").data("kendoComboBox");

            if (combobox.dataItem())
                return combobox.dataItem().text;
            else
                return combobox.text();
        }
        function getParameterValueValue_${parameter.id}() {
            var combobox = $("#parameter_${parameter.id}_selector").data("kendoComboBox");

            if (combobox.dataItem())
                return combobox.dataItem().value;
            else
                return combobox.text();
        }
        function getParameterValueType_${parameter.id}() {
            var combobox = $("#parameter_${parameter.id}_selector").data("kendoComboBox");

            if (combobox.dataItem())
                return 'predefined';
            else
                return 'const';
        }
    </script>
</g:if>
<g:else>
    <form:textBox id="parameter_${parameter.id}_selector" name="parameter_${parameter.id}_selector"
                  validation="${parameter.multiSelect ? '' : 'required'}"
                  style="width:500px"
                  value="${queryInstance?.parameterValues?.find {
                      it.parameter?.id == parameter?.id
                  }?.value ?: parameter.defaultValue}"/>
    <script language="javascript" type="text/javascript">
        function getParameterValueText_${parameter.id}() {
            return $('#parameter_${parameter.id}_selector').val();
        }
        function getParameterValueValue_${parameter.id}() {
            return $('#parameter_${parameter.id}_selector').val();
        }
        function getParameterValueType_${parameter.id}() {
            return 'const';
        }
    </script>
</g:else>