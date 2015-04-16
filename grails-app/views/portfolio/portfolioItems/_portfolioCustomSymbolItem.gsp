
<form:datePickerResources/>
<form:field fieldName="customSymbol.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:450px;" value="${item?.name}"
                  readonly="${item ? 'true' : 'false'}"/>
</form:field>
<form:field fieldName="customSymbol.code" showHelp="0">
    <form:textBox name="code" style="width:450px;" value="${item?.code}"/>
</form:field>
<form:field fieldName="customSymbol.establishDate" showHelp="0">
    <form:datePicker name="establishDateFa" id="establishDate_${UUID.randomUUID().toString().replace('-', '_')}" style="width:450px;" value="${item?.establishDate ? format.jalaliDate(date: item?.establishDate) : ''}"/>
</form:field>