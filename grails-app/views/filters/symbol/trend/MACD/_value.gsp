<div class="filter-value-panel">
    <div>
        <form:checkbox checked="true" name="value_signal_switch" id="macd_signal" onchange="macd_signal_change()"
                       text="${message(code: 'macd.signal')}"/>
    </div>

    <div>
        <form:checkbox name="value_constant_switch" id="macd_value" onchange="macd_value_change()"
                       text="${message(code: 'macd.value')}"/>
    </div>

    <div id="macd_value_value">
        <form:numericTextBox name="value" value="${value}" decimals="0"/>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function macd_signal_change() {
        if ($('#macd_signal').is(':checked'))
            $('#macd_value').attr('checked', false);
        else
            $('#macd_value').attr('checked', true);
        macd_format_value();
    }
    function macd_value_change() {
        if ($('#macd_value').is(':checked'))
            $('#macd_signal').attr('checked', false);
        else
            $('#macd_signal').attr('checked', true);
        macd_format_value();
    }

    function macd_format_value() {
        if ($('#macd_value').is(':checked'))
            $('#macd_value_value').stop().slideDown();
        else
            $('#macd_value_value').stop().slideUp();
    }

    macd_value_change();
    macd_signal_change();
</script>