<div class="filter-value-panel k-rtl">

    <input id="value" name="value" type="number" value="${value}"/>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $('#value').kendoNumericTextBox({
                format: "p0",
                min: 0,
                max: 1,
                step: 0.01
            });
        });
    </script>
</div>