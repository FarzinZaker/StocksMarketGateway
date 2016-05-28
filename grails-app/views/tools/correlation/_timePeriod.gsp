
<form:datePickerResources/>
<form:field fieldName="correlation.timePeriod">
    <span style="width: 510px;display: inline-block">
        <form:datePicker name="startDate" value=""/>
        <form:datePicker name="endDate" value=""/>
        <select id="period" name="period" style="width: 130px;" >
            <option value="" selected hidden></option>
            %{--<option value="daily"><g:message code="correlation.timePeriod.day"/></option>--}%
            <option value="weekly"><g:message code="correlation.timePeriod.week"/></option>
            <option value="monthly"><g:message code="correlation.timePeriod.month"/></option>
        </select>
    </span>
</form:field>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $("#period").kendoComboBox();
        $('#period_listbox').find('li:first-of-type').hide();
    });
</script>