<a class="k-button" style="padding: 8px 10px 1px;" onclick="addParameterValue_${parameter.id}()">
    <i class="fa fa-plus"></i>
</a>

<div class="multiSelectValues" id="parameterValuesContainer_${parameter.id}">
    <ul>
        <g:if test="${queryInstance.id}">
            <g:each in="${stocks.alerting.ParameterValue.findAllByQueryInstanceAndParameter(queryInstance, parameter)}" var="parameterValue">
                <li>
                    <i onclick="$(this).parent().remove()">x</i>
                    ${parameterValue.text}
                    <input type="hidden" name="parameterValueText_${parameter.id}" value="${parameterValue.text}"/>
                    <input type="hidden" name="parameterValueValue_${parameter.id}" value="${parameterValue.value}"/>
                    <input type="hidden" name="parameterValueType_${parameter.id}" value="${parameterValue.type}"/>
                </li>
            </g:each>
        </g:if>
        <g:elseif test="${parameter.defaultValue}">
            <li>
                <i onclick="$(this).parent().remove()">x</i>
                <alerting:parameterDefaultValueText parameter="${parameter}"/>
                <input type="hidden" name="parameterValueText_${parameter.id}" value="${alerting.parameterDefaultValueText(parameter: parameter)}"/>
                <input type="hidden" name="parameterValueValue_${parameter.id}" value="${alerting.parameterDefaultValueValue(parameter: parameter)}"/>
                <input type="hidden" name="parameterValueType_${parameter.id}" value="${alerting.parameterDefaultValueType(parameter: parameter)}"/>
            </li>
        </g:elseif>
    </ul>

    <div></div>
</div>

<script language="javascript" type="text/javascript">

    var parameterValueCounter_${parameter.id} = 1;
    function addParameterValue_${parameter.id}() {
        var text = getParameterValueText_${parameter.id}();
        var value = getParameterValueValue_${parameter.id}();
        var type = getParameterValueType_${parameter.id}();
        if (!value || value == '') {
            window.alert('${message(code:'alerting.query.register.notElementSelected.message')}');
            return;
        }
        var alreadyAddedItem;
        $("#parameterValuesContainer_${parameter.id}").find("ul input[name=parameterValueValue_${parameter.id}]").each(function () {
            if ($(this).val() == value)
                alreadyAddedItem = $(this).parent();
        });
        if (alreadyAddedItem) {
            alreadyAddedItem.animateHighlight("#8ebc00", 1000);
        }
        else {
            $('#parameterValuesContainer_${parameter.id}').find('ul')
                    .append('<li><i onclick="$(this).parent().remove()">x</i>'
                            + text
                            + '<input type="hidden" name="parameterValueText_${parameter.id}" value="' + text + '"/>'
                            + '<input type="hidden" name="parameterValueValue_${parameter.id}" value="' + value + '"/>'
                            + '<input type="hidden" name="parameterValueType_${parameter.id}" value="' + type + '"/>'
                            + '</li>');
        }
        $('#parameterValuesContainer_${parameter.id}').parent().parent().find('.form-error').fadeOut(200);
        return false;
    }
</script>