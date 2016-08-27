
<div id="alertDialog" class="k-rtl">
    <asset:image src="error.png"/>
    <div class="message">message</div>
    <div class="toolbar">
        <input type="button" class="k-button btn-ok" value="${message(code:'alert.dialog.ok')}"/>
    </div>
</div>

<div id="infoDialog" class="k-rtl">
    <asset:image src="info.png"/>
    <div class="message">message</div>
    <div class="toolbar">
        <input type="button" class="k-button btn-ok" value="${message(code:'alert.dialog.ok')}"/>
    </div>
</div>

<script>
    var alertDialog = $("#alertDialog");
    var infoDialog = $("#infoDialog");
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

        window.info = function(message){
            infoDialog.find(".message").html(message);
            infoDialog.find(".btn-ok").click(function(){
                infoDialog.data("kendoWindow").close();
            });
            infoDialog.data("kendoWindow").center().open();
        }
    });
</script>