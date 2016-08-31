<div class="hidden k-rtl">
    <div id="messageFormWindow"></div>
</div>

<div class="hidden" id="messageFormLoading">
    <div style='height:327px'>
        <span class="loading" style="display: block">
            <asset:image src="loading.gif"/>
            <span><g:message code="wait"/></span>
        </span>
    </div>
</div>

<script language="javascript" type="text/javascript">
    var messageFormWindow;
    function sendMessage(receiverId, receiverName, inReplyToId, forward) {
        var url = '${createLink(controller: 'message', action: 'form')}?receiver=' + receiverId;
        if (inReplyToId)
            url += '&inReplyTo=' + inReplyToId;
        if (forward)
            url += '&forward=' + 1;
        messageFormWindow = $('#messageFormWindow').html($('#messageFormLoading').html())
                .kendoWindow({
                    width: '500px',
                    content: url,
                    modal: true
                }).data('kendoWindow').center().open();
        if (inReplyToId && !forward)
            messageFormWindow.title('${message(code:'message.form.title.reply')} ' + receiverName);
        else if (!forward)
            messageFormWindow.title('${message(code:'message.form.title.new')} ' + receiverName);
        else
            messageFormWindow.title('${message(code:'message.form.title.forward.part1')} ' + receiverName + ' ${message(code:'message.form.title.forward.part2')}');
        messageFormWindow.bind("refresh", function () {
            messageFormWindow.center();
            messageFormWindow.open();
        });
    }
</script>