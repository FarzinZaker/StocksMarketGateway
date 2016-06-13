<form:field fieldName="portfolioBankItem.bank" showHelp="0">
    <div class="k-rtl">
        <form:textBox name="bank" id="bank" validation="required" style="width:450px;" value="${item?.bank}" class="${item ? 'k-input' : ''}"/>
    </div>
</form:field>
<form:field fieldName="portfolioBankItem.branch" showHelp="0">
    <div class="k-rtl">
        <form:textBox name="branch" id="branch" style="width:450px" value="${item?.branch}"/>
    </div>
</form:field>
<form:field fieldName="portfolioBankItem.accountType" showHelp="0">
    <div class="k-rtl">
        <form:textBox name="accountType" id="accountType" style="width:450px" value="${item?.accountType}"/>
    </div>
</form:field>
<form:field fieldName="portfolioBankItem.accountNumber" showHelp="0">
    <div class="k-rtl">
        <form:textBox name="accountNumber" id="accountNumber" style="width:450px" value="${item?.accountNumber}"/>
    </div>
</form:field>