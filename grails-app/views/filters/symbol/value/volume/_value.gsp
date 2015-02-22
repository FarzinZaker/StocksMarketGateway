<div class="filter-value-panel k-rtl">

    <input id="value" name="value" type="number" value="${value}"/>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $('#value').kendoNumericTextBox({
                format: "n0",
                step: 10000
            });
        });
    </script>
</div>