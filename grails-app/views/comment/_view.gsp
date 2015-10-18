<div class="comment">
    <div class="meta">
        <img class="avatar"
             src="${createLink(controller: 'image', action: 'profile', id: comment.author?.identifier, params: [size: 100])}"/>
        <span class="author">${comment?.author?.title}</span>
        <span class="date"><format:jalaliDate date="${comment?.dateCreated}" hm="true"/></span>
    </div>

    <div class="data">
        <div class="body"><format:html value="${comment?.body}"/></div>

        <sec:ifLoggedIn>
            <twitter:like commentId="${comment.idNumber}"/>
            <form:button name="btn-respond_${comment?.idNumber?.replace(':','_')}" class="btn-showCommentEditor"
                         text="${message(code: 'comment.respond')}"/>
            <form:loading id="comment_editor_loading_${comment?.idNumber?.replace(':','_')}"/>
            <div class="commentEditorContainer" id="commentEditorContainer_${comment?.idNumber?.replace(':','_')}"></div>
        </sec:ifLoggedIn>
    </div>

    <div class="clear-fix"></div>

    <div class="responses">
        <stocks:commentList comment="${comment}"/>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btn-respond_${comment?.idNumber?.replace(':','_')}').click(function () {
            $('.commentEditorContainer').hide();
            $('#comment_editor_loading_${comment?.idNumber?.replace(':','_')}').show();
            $('.btn-showCommentEditor').show();
            $('#btn-respond_${comment?.idNumber?.replace(':','_')}').kendoButton({
                enable: false
            });
            if ($('#commentEditorContainer_${comment?.idNumber?.replace(':','_')}').html()) {
                $(this).hide();
                $('#comment_editor_loading_${comment?.idNumber?.replace(':','_')}').hide();
                $('#commentEditorContainer_${comment?.idNumber?.replace(':','_')}').slideDown();
                $('#btn-respond_${comment?.idNumber?.replace(':','_')}').hide().data('kendoButton').enable(true);
            }
            else {
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'comment', action: 'editor', id: comment?.idNumber)}'
                }).done(function (response) {
                    $(this).hide();
                    $('#comment_editor_loading_${comment?.idNumber?.replace(':','_')}').hide();
                    $('#commentEditorContainer_${comment?.idNumber?.replace(':','_')}').html(response).slideDown();
                    $('#btn-respond_${comment?.idNumber?.replace(':','_')}').hide().data('kendoButton').enable(true);
                });
            }
        });
    });
</script>