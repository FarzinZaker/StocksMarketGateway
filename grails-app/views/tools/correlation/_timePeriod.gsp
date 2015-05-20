
<form:datePickerResources/>
<form:field fieldName="correlation.timePeriod">
    <span style="width: 510px;display: inline-block">
        <form:datePicker name="startDate" value="${format.jalaliDate(date: new Date() - 30)}"/>
        <form:datePicker name="endDate" value="${format.jalaliDate(date: new Date())}"/>
        <select id="period" name="period" style="width: 150px;">
            %{--<option value="daily"><g:message code="correlation.timePeriod.day"/></option>--}%
            <option value="weekly" selected><g:message code="correlation.timePeriod.week"/></option>
            <option value="monthly"><g:message code="correlation.timePeriod.month"/></option>
        </select>
    </span>
</form:field>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $("#period").kendoComboBox();
    });
</script>