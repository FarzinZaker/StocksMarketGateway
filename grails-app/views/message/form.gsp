<div style="padding: 10px;" class="k-rtl">
    <g:if test="${originalMessage && !forward}">
        <p class="text-justify">${originalMessage.body}</p>
    </g:if>
    <form:form name="messageForm">
        <g:if test="${forward}">
            <form:field fieldName="message.receiver" showHelp="0" border="0">
                <input name="receiver.id" id="receiver" class="k-textbox" data-validation="required"/>
            </form:field>
            <script language="javascript">

                $("#receiver").removeClass('k-textbox').width('462px').kendoComboBox({
                    dataTextField: "name",
                    dataValueField: "value",
                    filter: "contains",
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${createLink(controller: 'message', action: 'userAutoComplete')}",
                                dataType: "json",
                                type: "POST"

                            }
                        },
                        schema: {
                            data: "data" // records are returned in the "data" field of the response
                        }
                    },
                    change: function (e) {
                        if (this.value() && this.selectedIndex == -1) {
                            var dt = this.dataSource._data[0];
                            this.text(dt[this.options.dataTextField]);
                            this._selectItem();
                        }
                    }
                });
            </script>
        </g:if>
        <g:else>
            <form:hidden name="receiver.id" value="${receiver?.id}"/>
        </g:else>
        <form:hidden name="inReplyTo.id" value="${originalMessage?.id}"/>
        <form:hidden name="forward" value="${forward}"/>
        <form:field fieldName="message.body" showHelp="0">
            <form:textArea style="width:442px;height:120px;padding:10px !important;" name="body" validation="required"
                           value="${forward ? originalMessage?.body : ''}"/>
        </form:field>
        <form:button text="${message(code: 'message.send.btn')}" onclick="submitMessage()"/>
    </form:form>
</div>
<script language="javascript">
    function submitMessage() {
        var form = $('#messageForm');
        if (form.isValid()) {
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'message', action: 'save')}',
                data: form.serialize()
            }).done(function (response) {
                if (response == "1") {
                    messageFormWindow.close();
                }
                else {
                    window.alert('${message(code:'message.send.error')}');
                }
            });
        }
    }
</script>