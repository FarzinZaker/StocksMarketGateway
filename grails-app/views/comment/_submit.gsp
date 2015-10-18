<layout:panel class="submitComment" showHeader="no"
              loginRequiredAction="${message(code: 'twitter.material.comment.loginRequired')}">
    <form id="comment_form_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}">
        <form:hidden name="type" value="${params.controller}"/>
        <form:hidden name="parentId" value="${materialId ?: comment?.idNumber}"/>
        <form:field fieldName="comment.body" showLabel="0" border="0">
            <form:editor name="body"
                         id="comment_body_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}"
                         mode="medium"
                         width="500" height="200" validation="required" ajax="${params.controller == 'comment'}"/>
        </form:field>
    </form>

    <div class="toolbar">
        <form:button name="comment_submit"
                     id="comment_submit_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}"
                     text="${message(code: "${params.controller}.comment.submit")}"/>
        <form:loading
                id="comment_loading_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}"/>
    </div>
</layout:panel>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $.validate({
            form: '#comment_form_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}'
        });

        $('#comment_submit_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').click(function () {
            tinymce.triggerSave();
            if ($('#comment_form_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').isValid()) {
                $('#comment_submit_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').kendoButton({
                    enable: false
                });
                $('#comment_loading_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').show();
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'comment', action: 'save')}',
                    data: $('#comment_form_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').serialize()
                }).done(function (response) {
                    tinymce.get('comment_body_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').setContent('');
                    $('.commentEditorContainer').hide();
                    $('.btn-showCommentEditor').show();
                    $('#comment_loading_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').hide();
                    $('#e${material?'d':'c'}_${materialId?.replace(':', '_')?:comment?.idNumber?.replace(':', '_')}').hide();
                    $('#c${material?'d':'c'}_${materialId?.replace(':', '_')?:comment?.idNumber?.replace(':', '_')}').html(response + $('#c${material?'d':'c'}_${materialId?.replace(':', '_')?:comment?.idNumber?.replace(':', '_')}').html());
                    $('#comment_submit_${params.controller}_${materialId?.replace(':', '_') ?: comment?.idNumber?.replace(':', '_')}').data('kendoButton').enable(true);
                });
            }
        });
    });
</script>