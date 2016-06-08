<form:datePickerResources/>
<form:field fieldName="customBonds.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:450px;" value="${item?.name}"
                  readonly="${item ? 'true' : 'false'}"/>
</form:field>
<form:field fieldName="customBonds.bank" showHelp="0">
    <form:textBox name="bank" style="width:450px;" value="${item?.bank}"/>
</form:field>
%{--<form:field fieldName="customBonds.value" showHelp="0">--}%
    %{--<form:numericTextBox name="value" style="width:450px;" value="${item?.value}" format="n0" min="0"--}%
                         %{--step="100000"/>--}%
%{--</form:field>--}%
<form:field fieldName="customBonds.benefit" showHelp="0">
    <form:numericTextBox name="benefit" style="width:450px;" value="${item?.benefit}" format="p0" min="0"
                         step="0.01"/>
</form:field>
<form:field fieldName="customBonds.benefitPeriod" showHelp="0">
    <form:numericTextBox name="benefitPeriod" style="width:250px;" value="${item?.benefitPeriod}" format="n0"
                         min="0" step="1"/>
    <form:select items="${stocks.portfolio.basic.CustomBonds.constraints.benefitPeriodType.inList.collect {
        [text: message(code: "customBonds.benefitPeriodType.${it}"), value: it]
    }}" name="benefitPeriodType" value="${item?.benefitPeriodType}" style="width:190px;"
                 placeholder="${message(code: 'please-select')}" preSelect="false"/>
</form:field>
<form:field fieldName="customBonds.firstPayDate" showHelp="0">
    <form:datePicker name="firstPayDateFa" id="firstPayDate_${UUID.randomUUID().toString().replace('-', '_')}"
                     style="width:450px;" value="${item?.firstPayDate ? format.jalaliDate(date: item?.firstPayDate) : ''}"/>
</form:field>
<form:field fieldName="customBonds.dueDate" showHelp="0">
    <form:datePicker name="dueDateFa" id="dueDate_${UUID.randomUUID().toString().replace('-', '_')}"
                     style="width:450px;" value="${item?.dueDate ? format.jalaliDate(date: item?.dueDate) : ''}"/>
</form:field>