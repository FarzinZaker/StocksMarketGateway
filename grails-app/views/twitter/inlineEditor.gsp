<layout:panel class="submitItem" showHeader="no"
              loginRequiredAction="${message(code: 'twitter.material.item.loginRequired')}">
    <form id="item_editForm_${itemId?.replace(':', '_')}">
        <form:hidden name="id" value="${itemId}"/>
        <form:hidden name="type" value="${type}"/>
        <form:hidden name="template" value="/twitter/material/item"/>
        <form:editor name="itemBody"
                     id="item_body_${itemId?.replace(':', '_')}_${UUID.randomUUID()}"
                     mode="inline"
                     height="80" validation="required" ajax="${true}"
                     text="${body}" hashTag="${(type == 'Talk').toString()}" authorTag="${(type == 'Talk').toString()}"/>
    </form>

    <div class="toolbar" style="margin-top:5px;">
        <form:button name="item_submit"
                     id="item_submit_${itemId?.replace(':', '_')}"
                     text="${message(code: "item.item.submit")}"/>
        <span class="k-button" onclick='cancel${type}InlineEdit()'>
            <g:message code="editor.dialogCancel"/>
        </span>
        <form:loading
                id="item_loading_${itemId?.replace(':', '_')}"/>
    </div>
</layout:panel>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $.validate({
            form: '#item_editForm_${itemId?.replace(':', '_')}'
        });

        $('#item_submit_${itemId?.replace(':', '_')}').click(function () {
            tinymce.triggerSave();
            if ($('#item_editForm_${itemId?.replace(':', '_')}').isValid()) {
                $('#item_submit_${itemId?.replace(':', '_')}').kendoButton({
                    enable: false
                });
                $('#item_loading_${itemId?.replace(':', '_')}').show();
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'twitter', action: 'inlineEdit')}',
                    data: $('#item_editForm_${itemId?.replace(':', '_')}').serialize()
                }).done(function (response) {
                    var mainContainer = $('#item_editForm_${itemId?.replace(':', '_')}').parent().parent().parent().parent();
                    mainContainer.find('.description').first().html(response);
                    cancel${type}InlineEdit();
                });
            }
        });
    });
</script>