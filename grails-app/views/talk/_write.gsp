<%@ page import="stocks.User" %>
<form class="talk" id="talkForm">
    <div class="infoBar">
        <img class="userImage"
             src="${createLink(controller: 'image', action: 'profile', params: [id: User.findByUsername(sec.username() as String)?.id, size: 100])}"/>

        <div class="toolbar">
            <form:button onclick="submitTalk();" text="${message(code: 'talk.button')}"/>
            <form:loading/>
        </div>
    </div>
    <twitter:tagSearch/>
    <twitter:authorSearch/>
    <form:editor name="body" mode="inline" height="136" hashTag="true" authorTag="true" tag="${tag}"/>
    <div class="clear-fix"></div>
</form>
<script language="javascript" type="text/javascript">
    function submitTalk() {
        var content = strip(tinymce.activeEditor.getContent()).trim();
        console.debug(content);
        if (content == '')
            window.alert('${message(code:'talk.error.empty')}');
        else {
            $('.talk .toolbar .k-button').hide();
            $('.talk .toolbar .loading').show();
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'talk', action: 'save')}',
                data: {body: tinymce.activeEditor.getContent()}
            }).done(function (response) {
                if (response == "1") {
                    tinymce.activeEditor.setContent('');
                    $('.talk .toolbar .k-button').show();
                    $('.talk .toolbar .loading').hide();
                    window.alert('${message(code:'talk.save.success')}');
                }
                else {
                    window.alert('${message(code:'backTest.delete.error')}');
                }
            });
        }
    }
</script>