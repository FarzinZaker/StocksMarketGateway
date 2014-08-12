<form:checkbox name="${day}_enabled" text="${message(code: 'scheduleDayTemplate.enabled.label')}"
               onchange="toggle_${day}(this);" checked="${scheduleDayTemplateInstance}"/>


<div style="display: none" class="k-rtl">
    <br/>
    <br/>
    <form:field fieldName="scheduleDayTemplate.allowedTimeRange" showHelp="0">
        <form:timeRangeSlider name="${day}_allowedTimeRange" style="width:600px"
                              rangeStart="${scheduleDayTemplateInstance?.minStartTimeInMinute}"
                              rangeEnd="${scheduleDayTemplateInstance?.maxEndTimeInMinute}"/>
    </form:field>
    <form:field fieldName="scheduleDayTemplate.suggestedTimeRange" showHelp="0" border="0">
        <form:timeRangeSlider name="${day}_suggestedTimeRange" style="width:600px"
                              rangeStart="${scheduleDayTemplateInstance?.suggestedStartTimeInMinute}"
                              rangeEnd="${scheduleDayTemplateInstance?.suggestedEndTimeInMinute}"/>
    </form:field>
</div>

<script language="javascript" type="text/javascript">

    function toggle_${day}(item) {
        if ($(item).is(':checked'))
            $(item).parent().find('div').first().stop().slideDown();
        else
            $(item).parent().find('div').first().stop().slideUp();
    }

    toggle_${day}(document.getElementById('${day}_enabled'));
</script>