<div class="filter-value-panel k-rtl">

    <input id="valuePercent" name="value" type="number" value="${percent}"/>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $('#valuePercent').kendoNumericTextBox({
                format: "p0",
                min: 0,
                max: 1,
                step: 0.01
            });
        });
    </script>
    <div style="height: 10px;"></div>
    <input id="valueDays" name="value" type="number" value="${days}"/>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $('#valueDays').kendoNumericTextBox({
                format: "میانیگین ##,# روزه",
                step: 1,
                min: 1
            });
        });
    </script>
</div>