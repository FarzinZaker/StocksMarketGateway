<form:field fieldName="movableProperty.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:450px;" value="${item?.name}"/>
</form:field>
<form:field fieldName="movableProperty.price" showHelp="0">
    <form:numericTextBox name="price" validation="required" style="width:440px;" value="${item?.price}" format="n0" step="1" min="0"/>
</form:field>