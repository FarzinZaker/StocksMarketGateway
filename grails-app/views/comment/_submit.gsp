<layout:panel title="${message(code: "${params.controller}.comment.submit")}" class="submitComment" header="h3"
              loginRequiredAction="${message(code: 'article.comment.loginRequired')}">
    <form id="comment_form_${params.controller}_${document?.id ?: comment?.id}">
        <form:hidden name="type" value="${params.controller}"/>
        <form:hidden name="parentId" value="${document?.id ?: comment?.id}"/>
        <form:field fieldName="comment.body" showLabel="0" border="0">
            <form:editor name="body" id="comment_body_${params.controller}_${document?.id ?: comment?.id}" mode="medium"
                         width="500" height="200" validation="required" ajax="${params.controller == 'comment'}"/>
        </form:field>
    </form>

    <div class="toolbar">
        <form:button name="comment_submit" id="comment_submit_${params.controller}_${document?.id ?: comment?.id}"
                     text="${message(code: "${params.controller}.comment.submit")}"/>
        <form:loading id="comment_loading_${params.controller}_${document?.id ?: comment?.id}"/>
    </div>
</layout:panel>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $.validate({
            form: '#comment_form_${params.controller}_${document?.id ?: comment?.id}'
        });

        $('#comment_submit_${params.controller}_${document?.id ?: comment?.id}').click(function () {
            tinymce.triggerSave();
            if ($('#comment_form_${params.controller}_${document?.id ?: comment?.id}').isValid()) {
                $('#comment_submit_${params.controller}_${document?.id ?: comment?.id}').kendoButton({
                    enable: false
                });
                $('#comment_loading_${params.controller}_${document?.id ?: comment?.id}').show();
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'comment', action: 'save')}',
                    data: $('#comment_form_${params.controller}_${document?.id ?: comment?.id}').serialize()
                }).done(function (response) {
                    tinymce.get('comment_body_${params.controller}_${document?.id ?: comment?.id}').setContent('');
                    $('.commentEditorContainer').hide();
                    $('.btn-showCommentEditor').show();
                    $('#comment_loading_${params.controller}_${document?.id ?: comment?.id}').hide();
                    $('#e${document?'d':'c'}_${document?.id?:comment?.id}').hide();
                    $('#c${document?'d':'c'}_${document?.id?:comment?.id}').html(response + $('#c${document?'d':'c'}_${document?.id?:comment?.id}').html());
                    $('#comment_submit_${params.controller}_${document?.id ?: comment?.id}').data('kendoButton').enable(true);
                });
            }
        });
    });
</script>