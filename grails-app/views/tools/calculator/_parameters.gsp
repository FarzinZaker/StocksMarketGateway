<table>
    <tr>
        <td style="padding-left:10px;">
            <label>
                <g:message code="tools.calculator.interestRate"/>
            </label>

            <input id="interestRate" type="number" ng-model="interestRate" style="width: 140px;"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#interestRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        decimals: 3,
                        step: 0.005
                    });
                });
            </script>
        </td>
        <td>
            <label>
                <g:message code="tools.calculator.expectedReturn"/>
            </label>

            <input id="expectedReturn" type="number" ng-model="expectedReturn" style="width: 140px;"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#expectedReturn').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        decimals: 3,
                        step: 0.005
                    });
                });
            </script>
        </td>
    </tr>
    <tr>
        <td>

            <label>
                <g:message code="tools.calculator.borrowingRate"/></label>
            <input id="borrowingRate" type="number" ng-model="borrowingRate" style="width: 140px;"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#borrowingRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        decimals: 3,
                        step: 0.005
                    });
                });
            </script>
        </td>
        <td>
            <label>
                <g:message code="tools.calculator.lendingRate"/></label>
            <input id="lendingRate" type="number" ng-model="lendingRate" style="width: 140px;"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#lendingRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        decimals: 3,
                        step: 0.005
                    });
                });
            </script>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div style="margin-top:15px;">
                <label id="btnShowConditions" style="cursor: pointer;"><i class="fa fa-plus"></i> <g:message
                        code="calculator.showAdvancedParameters"/></label>
                <label id="btnHideConditions" style="display: none;cursor:pointer;"><i
                        class="fa fa-minus"></i> <g:message code="calculator.hideAdvancedParameters"/></label>
            </div>

            <table class="advancedParameters">
                <tr>
                    <td style="padding-left: 10px;">
                        <label>
                            <g:message code="tools.calculator.margin"/></label>
                        <input id="margin" type="number" ng-model="margin" style="width: 140px;"/>
                        <script language="javascript" type="text/javascript">
                            $(document).ready(function () {
                                $('#margin').kendoNumericTextBox({
                                    format: "##,# ریال",
                                    step: 1000000
                                });
                            });
                        </script>
                    </td>
                    <td>
                        <label>
                            <g:message code="tools.calculator.multiplicationCost"/>
                        </label>
                        <input id="multiplicationCost" type="number" ng-model="multiplicationCost"
                               style="width: 140px;"/>
                        <script language="javascript" type="text/javascript">
                            $(document).ready(function () {
                                $('#multiplicationCost').kendoNumericTextBox({
                                    format: "##,# ریال",
                                    step: 5000
                                });
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>
                            <g:message code="tools.calculator.addedValueTax"/>
                        </label>

                        <input id="addedValueTax" type="number" ng-model="addedValueTax" style="width: 140px;"/>
                        <script language="javascript" type="text/javascript">
                            $(document).ready(function () {
                                $('#addedValueTax').kendoNumericTextBox({
                                    format: "p0",
                                    min: 0,
                                    max: 1,
                                    step: 0.01
                                });
                            });
                        </script>
                    </td>
                    <td>
                        <label>
                            <g:message code="tools.calculator.marginChanges"/>
                        </label>
                        <input id="marginChanges" type="number" ng-model="marginChanges" style="width: 140px;"/>
                        <script language="javascript" type="text/javascript">
                            $(document).ready(function () {
                                $('#marginChanges').kendoNumericTextBox({
                                    format: "##,# ریال",
                                    step: 1000000
                                });
                            });
                        </script>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btnShowConditions').click(function () {
            $('#btnShowConditions').hide();
            $('#btnHideConditions').show();
            $('.advancedParameters').slideDown();
        });
        $('#btnHideConditions').click(function () {
            $('#btnHideConditions').hide();
            $('#btnShowConditions').show();
            $('.advancedParameters').slideUp();
        });
    });
</script>


