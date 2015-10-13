<div class="hidden k-rtl">
    <div id="registerWindow"></div>
</div>

<div class="hidden" id="registerWindowLoading">
    <div style='height:327px'>
        <span class="loading" style="display: block">
            <asset:image src="loading.gif"/>
            <span><g:message code="wait"/></span>
        </span>
    </div>
</div>

<script language="javascript" type="text/javascript">
    function registerInGroup(groupId, groupTitle) {
        var win = $('#registerWindow').html($('#registerWindowLoading').html())
                .kendoWindow({
                    width: '600px',
                    content: '${createLink(controller: 'group', action: 'register')}/' + groupId,
                    modal: true,
                    close: function (e) {
                    }
                }).data('kendoWindow').title('${message(code:'twitter.group.register.title')} ' + groupTitle).center().open();
        win.bind("refresh", function () {
            win.center();
            win.open();
        });
    }
</script>