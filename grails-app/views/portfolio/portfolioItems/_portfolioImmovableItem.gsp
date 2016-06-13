<form:field fieldName="immovableProperty.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:440px;" value="${item?.name}"/>
</form:field>
<form:field fieldName="immovableProperty.area" showHelp="0">
    <form:numericTextBox name="area" style="width:450px;" value="${item?.area}" format="n0" step="1" min="0"/>
</form:field>
<form:field fieldName="immovableProperty.price" showHelp="0">
    <form:numericTextBox name="price" style="width:450px;" value="${item?.price}" format="n0" step="1" min="0" validation="required"/>
</form:field>
<form:field fieldName="immovableProperty.address" showHelp="0">
    <form:textArea name="address" style="width:450px;" value="${item?.address}"/>
</form:field>