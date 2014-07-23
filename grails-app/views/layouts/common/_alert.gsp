
<asset:javascript src="kendo.ui/kendo.core.min.js"/>
<asset:javascript src="kendo.ui/kendo.userevents.min.js"/>
<asset:javascript src="kendo.ui/kendo.draganddrop.min.js"/>
<asset:javascript src="kendo.ui/kendo.window.min.js"/>
<div id="alertDialog" class="k-rtl">
    <asset:image src="error.png"/>
    <div class="message">message</div>
    <div class="toolbar">
        <input type="button" class="k-button btn-ok" value="${message(code:'confirm.dialog.ok')}"/>
    </div>
</div>

<script>
    var alertDialog = $("#alertDialog");
    $(document).ready(function() {

        if (!alertDialog.data("kendoWindow")) {
            alertDialog.kendoWindow({
                width: "400px",
                modal: true,
                actions: [
                    "Close"
                ]
            });
        }

        window.alert = function(message){
            alertDialog.find(".message").html(message);
            alertDialog.find(".btn-ok").click(function(){
                alertDialog.data("kendoWindow").close();
            });
            alertDialog.data("kendoWindow").center().open();
        }
    });
</script>