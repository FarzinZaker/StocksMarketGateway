<div class="clear-fix"></div>
<layout:panel class="submitComment" showHeader="no"
              loginRequiredAction="${message(code: 'twitter.material.comment.loginRequired')}">
    <form id="comment_form_${parentId?.replace(':', '_')}">
        <form:hidden name="parentId" value="${parentId}"/>
        <form:hidden name="template" value="/twitter/material/comment"/>
        <form:editor name="commentBody"
                     id="comment_body_${parentId?.replace(':', '_')}"
                     mode="inline"
                     height="80" validation="required" ajax="${true}"/>
    </form>

    <div class="toolbar">
        <form:button name="comment_submit"
                     id="comment_submit_${parentId?.replace(':', '_')}"
                     text="${message(code: "comment.comment.submit")}"/>
        <span class="k-button" onclick='cancelTwitterComment("comment_form_${parentId?.replace(':', '_')}")'>
            <g:message code="editor.dialogCancel"/>
        </span>
        <form:loading
                id="comment_loading_${parentId?.replace(':', '_')}"/>
    </div>
</layout:panel>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $.validate({
            form: '#comment_form_${parentId?.replace(':', '_')}'
        });

        $('#comment_submit_${parentId?.replace(':', '_')}').click(function () {
            tinymce.triggerSave();
            if ($('#comment_form_${parentId?.replace(':', '_')}').isValid()) {
                $('#comment_submit_${parentId?.replace(':', '_')}').kendoButton({
                    enable: false
                });
                $('#comment_loading_${parentId?.replace(':', '_')}').show();
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'comment', action: 'save')}',
                    data: $('#comment_form_${parentId?.replace(':', '_')}').serialize()
                }).done(function (response) {
                    tinymce.get('comment_body_${parentId?.replace(':', '_')}').setContent('');
                    $('.replyEditor').hide();
                    $('.replyButton').show();
                    $('#comment_loading_${parentId?.replace(':', '_')}').hide();
                    var item = $('<li/>');
                    item.html(response);
                    var mainContainer = $('#comment_form_${parentId?.replace(':', '_')}').parent().parent().parent().parent();
                    mainContainer.find('ul:first').prepend(item);
                    item.slideDown();
                    var counter = mainContainer.find('.showAllButtonContainer span');
                    var parts = counter.text().trim().split(' ');
                    var currentCounter = 0;
                    for (var i = 0; i < parts.length; i++)
                        if (parseInt(parts[i], 10)) {
                            currentCounter = parseInt(parts[i], 10) + 1;
                            parts[i] = currentCounter.toString();
                        }
                    counter.html(parts.join(' '));
                    mainContainer.find('.replyButtonContainer .replyCounter').html(currentCounter);
                    mainContainer.find('.replyButtonContainer').slideDown();
                    %{--$('#et_${parentId?.replace(':', '_')}').hide();--}%
                    %{--$('#ct_${parentId?.replace(':', '_')}').html(response + $('#ct}_${parentId?.replace(':', '_')}').html());--}%
                    $('#comment_submit_${parentId?.replace(':', '_')}').data('kendoButton').enable(true);
                });
            }
        });
    });
</script>