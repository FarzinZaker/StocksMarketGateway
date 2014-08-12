
<div id="confirmDialog" class="k-rtl">
    <asset:image src="question.png"/>
    <div class="message">message</div>
    <div class="toolbar">
        <input type="button" class="k-button btn-cancel" value="${message(code:'confirm.dialog.cancel')}"/>
        <input type="button" class="k-button btn-ok" value="${message(code:'confirm.dialog.ok')}"/>
    </div>
</div>

<script>
    var confirmDialog = $("#confirmDialog");
    $(document).ready(function() {

        if (!confirmDialog.data("kendoWindow")) {
            confirmDialog.kendoWindow({
                width: "400px",
                %{--title: "${message(code:'confirm.dialog.title')}",--}%
                modal: true,
                actions: [
                    "Close"
                ]
            });
        }

        window.confirm = function(message, callback, cancelCallback){
            confirmDialog.find(".message").html(message);
            confirmDialog.find(".btn-cancel").click(function(){
                confirmDialog.data("kendoWindow").close();
                if(cancelCallback)
                    cancelCallback();
            });

            confirmDialog.parent().find('.k-window-action').click(function(){
                if(cancelCallback)
                    cancelCallback();
            });

            confirmDialog.find(".btn-ok").click(function(){
                confirmDialog.data("kendoWindow").close();
                callback();
            });
            confirmDialog.data("kendoWindow").center().open();
        }
    });
</script>