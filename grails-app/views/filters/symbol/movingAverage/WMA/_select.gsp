<div class="k-rtl">
    <select id="valueList" name="value">
        <option value="Price"><g:message code="Price"/></option>
        <g:each in="${list}" var="item">
            <option value="${item.value}">${item.text}</option>
        </g:each>
    </select>
    <form:select items="${[7, 3, 26, 50, 200].collect { [text: it.toString(), value: it] }}" name="value"
                 id="value_parameter_selector" style="width:60px;text-align: center;" value="50"/>
</div>

<script language="javascript" type="text/javascript">
    $('#valueList').kendoComboBox({
        change: function (e) {
            setValueParameterVisibility();
        }
    });

    function setValueParameterVisibility() {
        var paramInput = $('#value_parameter_selector').data('kendoComboBox');
        if ($('#valueList').data('kendoComboBox').value() == 'Price') {
            paramInput.value('');
            paramInput.wrapper.hide();
        }
        else {
            paramInput.value('50');
            paramInput.wrapper.show();
        }
    }

    setValueParameterVisibility();
</script>