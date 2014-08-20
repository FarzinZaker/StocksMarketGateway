<%@ page import="stocks.TemplateHelper" %>
<form:field fieldName="query.smsHeaderTemplate" border="0">
    <form:textArea name="smsHeaderTemplate" entity="${queryInstance}"
                   style="width:488px;height:70px;margin-top:10px;"/>
</form:field>
<div class="token-bar">
    <g:each in="${TemplateHelper.SYSTEM_TOKENS}" var="token">
        <span class="k-button"
              onclick="addTokenToHeaderTemplate('[${message(code:"systemTokens.${token}.label")}]');"><g:message
                code="systemTokens.${token}.label"/></span>
    </g:each>
</div>
<form:field fieldName="query.smsBodyTemplate" border="0">
    <form:textArea name="smsTemplate" entity="${queryInstance}" validation="required"
                   style="width:488px;height:150px;margin-top:10px;"/>
</form:field>
<div class="token-bar">
    <g:each in="${tokens}" var="token">
        <span class="k-button"
              onclick="addTokenToSMSTemplate('[${message(code:"${domainClass}.${token}.label")}]');"><g:message
                code="${domainClass}.${token}.label"/></span>
    </g:each>
</div> <form:field fieldName="query.smsFooterTemplate" border="0">
    <form:textArea name="smsFooterTemplate" entity="${queryInstance}"
                   style="width:488px;height:70px;margin-top:10px;"/>
</form:field>
<div class="token-bar">
    <g:each in="${TemplateHelper.SYSTEM_TOKENS}" var="token">
        <span class="k-button"
              onclick="addTokenToFooterTemplate('[${message(code:"systemTokens.${token}.label")}]');"><g:message
                code="systemTokens.${token}.label"/></span>
    </g:each>
</div>

<script language="javascript" type="text/javascript">
    $.fn.insertAtCaret = function (text) {
        return this.each(function () {
            if (document.selection && this.tagName == 'TEXTAREA') {
                //IE textarea support
                this.focus();
                sel = document.selection.createRange();
                sel.text = text;
                this.focus();
            } else if (this.selectionStart || this.selectionStart == '0') {
                //MOZILLA/NETSCAPE support
                startPos = this.selectionStart;
                endPos = this.selectionEnd;
                scrollTop = this.scrollTop;
                this.value = this.value.substring(0, startPos) + text + this.value.substring(endPos, this.value.length);
                this.focus();
                this.selectionStart = startPos + text.length;
                this.selectionEnd = startPos + text.length;
                this.scrollTop = scrollTop;
            } else {
                // IE input[type=text] and other browsers
                this.value += text;
                this.focus();
                this.value = this.value;    // forces cursor to end
            }
        });
    };

    function addTokenToSMSTemplate(token) {
        $('#smsTemplate').insertAtCaret(token);
    }

    function addTokenToHeaderTemplate(token) {
        $('#smsHeaderTemplate').insertAtCaret(token);
    }

    function addTokenToFooterTemplate(token) {
        $('#smsFooterTemplate').insertAtCaret(token);
    }

</script>