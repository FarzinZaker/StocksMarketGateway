<div class="k-rtl">
    <br/>
    <br/>
    <form:field fieldName="scheduleDay.allowedTimeRange" showHelp="0" border="0">
        <form:timeRangeSlider name="${day}_allowedTimeRange" style="width:600px"
                              rangeStart="${daySchedule?.startTimeInMinute ?: [scheduleDayTemplate.suggestedStartTimeInMinute, scheduleDayTemplate.minStartTimeInMinute].max()}"
                              rangeEnd="${daySchedule?.endTimeInMinute ?: [scheduleDayTemplate.suggestedEndTimeInMinute, scheduleDayTemplate.maxEndTimeInMinute].min()}"
                              min="${scheduleDayTemplate.minStartTimeInMinute}"
                              max="${scheduleDayTemplate.maxEndTimeInMinute}"/>
    </form:field>
</div>