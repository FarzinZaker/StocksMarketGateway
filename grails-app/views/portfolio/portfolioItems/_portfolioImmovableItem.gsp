<form:field fieldName="immovableProperty.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:450px;" value="${item?.name}"
                  readonly="${item ? 'true' : 'false'}"/>
</form:field>
<form:field fieldName="immovableProperty.area" showHelp="0">
    <form:numericTextBox name="area" style="width:450px;" value="${item?.area}" format="n0" step="1" min="0"/>
</form:field>
<form:field fieldName="immovableProperty.price" validation="required" showHelp="0">
    <form:numericTextBox name="price" style="width:450px;" value="${item?.price}" format="n0" step="1" min="0"/>
</form:field>
<form:field fieldName="immovableProperty.address" showHelp="0">
    <form:textArea name="address" style="width:450px;" value="${item?.address}"/>
</form:field>