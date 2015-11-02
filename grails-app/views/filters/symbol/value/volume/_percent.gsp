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

    <form:select items="${[5, 10, 30, 90, 365].collect { [text: "میانگین ${it} روزه", value: it] }}" name="value"
                 id="valueDays" value="${days}"/>
    %{--<input id="valueDays" name="value" type="number" value="${days}"/>--}%
    %{--<script language="javascript" type="text/javascript">--}%
        %{--$(document).ready(function () {--}%
            %{--$('#valueDays').kendoNumericTextBox({--}%
                %{--format: "میانیگین ##,# روزه",--}%
                %{--step: 1,--}%
                %{--min: 1--}%
            %{--});--}%
        %{--});--}%
    %{--</script>--}%
</div>