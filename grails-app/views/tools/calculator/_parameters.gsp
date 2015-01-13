%{--<div class="row-fluid">--}%
%{--<div class="col-xs-12">--}%
%{--<h2><g:message code="tools.calculator.parameters.title"/></h2>--}%
%{--</div>--}%
%{--</div>--}%

<div class="row-fluid">
    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.multiplicationCost"/>
        </div>

        <div class="col-sm-12">
            <input id="multiplicationCost" type="number" ng-model="multiplicationCost"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#multiplicationCost').kendoNumericTextBox({
                        format: "n0"
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.addedValueTax"/>
        </div>

        <div class="col-sm-12">
            <input id="addedValueTax" type="number" ng-model="addedValueTax"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#addedValueTax').kendoNumericTextBox({
                        format: "n2",
                        step: 0.1
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.interestRate"/>
        </div>

        <div class="col-sm-12">
            <input id="interestRate" type="number" ng-model="interestRate"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#interestRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        step: 0.01
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.expectedReturn"/>
        </div>

        <div class="col-sm-12">
            <input id="expectedReturn" type="number" ng-model="expectedReturn"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#expectedReturn').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        step: 0.01
                    });
                });
            </script>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.borrowingRate"/>
        </div>

        <div class="col-sm-12">
            <input id="borrowingRate" type="number" ng-model="borrowingRate"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#borrowingRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        step: 0.01
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.lendingRate"/>
        </div>

        <div class="col-sm-12">
            <input id="lendingRate" type="number" ng-model="lendingRate"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#lendingRate').kendoNumericTextBox({
                        format: "p1",
                        min: 0,
                        max: 1,
                        step: 0.01
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.margin"/>
        </div>

        <div class="col-sm-12">
            <input id="margin" type="number" ng-model="margin"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#margin').kendoNumericTextBox({
                        format: "n0"
                    });
                });
            </script>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="col-sm-12">
            <g:message code="tools.calculator.marginChanges"/>
        </div>

        <div class="col-sm-12">
            <input id="marginChanges" type="number" ng-model="marginChanges"/>
            <script language="javascript" type="text/javascript">
                $(document).ready(function () {
                    $('#marginChanges').kendoNumericTextBox({
                        format: "n0"
                    });
                });
            </script>
        </div>
    </div>
</div>