<form:field fieldName="businessPartner.name" showHelp="0">
    <div class="k-rtl">
        <form:textBox name="name" id="name" validation="required" style="width:450px;" value="${item?.name}"
                      class="${item ? 'k-input' : ''}"/>
    </div>
</form:field>