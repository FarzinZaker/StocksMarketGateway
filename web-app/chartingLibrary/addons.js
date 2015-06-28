/**
 * Created by root on 6/28/15.
 */

function setupAdjustmentButton(widget){
    var t, i;
//    if (AssetOptions.type == 1 && n.adjustmentData != null) {
        t = "";
        $.each(adjustmentTypes, function () {
            t += "<option value='" + this.value + "'>" + this.text + "<\/option>"
        });
        widget.createButton().attr("class", "sleep").append($("<div style='direction:rtl; width:250px;height: 28px;overflow: hidden; background: url(../../chartingLibrary/static/images/caret.png) no-repeat 4% #fff; border: 1px solid #ccc;'><select id='justificationSelect' style='width:275px; text-align:right;  padding:0 5px; font-family:eYekan; font-size:13px; background: transparent; line-height: 1;border: 0;border-radius: 0;height: 28px;-webkit-appearance: none;'>" + t + "<\/select><\/div>"));
        i = $("#tv_chart_container iframe");
        $("#justificationSelect", i.contents()).val(defaultAdjustmentType);
        $("#justificationSelect", i.contents()).on("change", function () {
            var t = this;
//            n.adjustmentType = this.value != "" ? this.value : "";
            widget.setSymbol(symbolName, "1D", function () {
                widget.setSymbol(symbolName, "D")
            })
        });
//    }
}