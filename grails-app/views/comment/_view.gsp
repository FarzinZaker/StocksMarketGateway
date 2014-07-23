<div class="comment">
    <div class="meta">
        <img class="avatar"
             src="${createLink(controller: 'image', id: comment.author?.image?.id, params: [size: 100, default: "user-${comment.author?.sex ?: 'male'}"])}"/>
        <span class="author">${comment?.author}</span>
        <span class="date"><format:jalaliDate date="${comment?.dateCreated}" hm="true"/></span>
    </div>

    <div class="data">
        <div class="body"><format:html value="${comment?.body}"/></div>

        <sec:ifLoggedIn>
            <stocks:like comment="${comment}"/>
            <form:button name="btn-respond_${comment?.id}" class="btn-showCommentEditor"
                         text="${message(code: 'comment.respond')}"/>
            <form:loading id="comment_editor_loading_${comment?.id}"/>
            <div class="commentEditorContainer" id="commentEditorContainer_${comment?.id}"></div>
        </sec:ifLoggedIn>
    </div>

    <div class="clear-fix"></div>

    <div class="responses">
        <stocks:commentList comment="${comment}"/>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btn-respond_${comment?.id}').click(function () {
            $('.commentEditorContainer').hide();
            $('#comment_editor_loading_${comment?.id}').show();
            $('.btn-showCommentEditor').show();
            $('#btn-respond_${comment?.id}').kendoButton({
                enable: false
            });
            if ($('#commentEditorContainer_${comment?.id}').html()) {
                $(this).hide();
                $('#comment_editor_loading_${comment?.id}').hide();
                $('#commentEditorContainer_${comment?.id}').slideDown();
                $('#btn-respond_${comment?.id}').hide().data('kendoButton').enable(true);
            }
            else {
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'comment', action: 'editor', id: comment?.id)}'
                }).done(function (response) {
                    $(this).hide();
                    $('#comment_editor_loading_${comment?.id}').hide();
                    $('#commentEditorContainer_${comment?.id}').html(response).slideDown();
                    $('#btn-respond_${comment?.id}').hide().data('kendoButton').enable(true);
                });
            }
        });
    });
</script>