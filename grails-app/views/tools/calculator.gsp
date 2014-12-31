<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 12/30/14
  Time: 2:41 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="tools.calculator.title"/></title>
</head>

<body>
<div class="container-fluid k-rtl calculator">
<div class="row-fluid">
    <div class="col-xs-12">
        <h1><g:message code="tools.calculator.title"/></h1>

        <p><g:message code="tools.calculator.description"/></p>
    </div>
</div>

<div class="row-fluid">
    <div class="col-xs-12">
        <h2><g:message code="tools.calculator.parameters.title"/></h2>
    </div>
</div>

<div class="row-fluid">
    <div class="col-sm-3">
        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.multiplicationCost"/>
            </div>

            <div class="col-sm-12">
                <input id="multiplicationCost" type="number" value="20000"/>
                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {
                        $('#multiplicationCost').kendoNumericTextBox({
                            format: "n0"
                        });
                    });
                </script>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.addedValueTax"/>
            </div>

            <div class="col-sm-12">
                <input id="addedValueTax" type="number" value="1"/>
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
    </div>

    <div class="col-sm-3">
        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.interestRate"/>
            </div>

            <div class="col-sm-12">
                <input id="interestRate" type="number" value="0.25"/>
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

        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.expectedReturn"/>
            </div>

            <div class="col-sm-12">
                <input id="expectedReturn" type="number" value="0.30"/>
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

    <div class="col-sm-3">
        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.borrowingRate"/>
            </div>

            <div class="col-sm-12">
                <input id="borrowingRate" type="number" value="0.23"/>
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

        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.lendingRate"/>
            </div>

            <div class="col-sm-12">
                <input id="lendingRate" type="number" value="0.20"/>
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
    </div>

    <div class="col-sm-3">
        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.margin"/>
            </div>

            <div class="col-sm-12">
                <input id="margin" type="number" value="20000000"/>
                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {
                        $('#margin').kendoNumericTextBox({
                            format: "n0"
                        });
                    });
                </script>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="col-sm-12">
                <g:message code="tools.calculator.marginChanges"/>
            </div>

            <div class="col-sm-12">
                <input id="marginChanges" type="number" value="0"/>
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
</div>

<div class="row-fluid">
    <div class="col-xs-12">
        <h2><g:message code="tools.calculator.baseInfo.title"/></h2>
    </div>
</div>

<div class="row-fluid">
    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.dollar"/>
        </div>

        <div class="col-sm-12 center">
            <b><g:formatNumber number="34700" type="number"/></b>
            <input type="hidden" value="34700"/>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.ons"/>
        </div>

        <div class="col-sm-12 center">
            <b><g:formatNumber number="1222" type="number"/></b>
            <input type="hidden" value="1222"/>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.coin"/>
        </div>

        <div class="col-sm-12 center">
            <b><g:formatNumber number="10000000" type="number"/></b>
            <input type="hidden" value="10000000"/>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.coinTheoric"/>
        </div>

        <div class="col-sm-12 center">
            <b><g:formatNumber number="9999619" type="number"/></b>
            <input type="hidden" value="9999619"/>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="col-xs-12">
        <h2><g:message code="tools.calculator.result.title"/></h2>
    </div>
</div>

<div class="row-fluid">
<div class="col-sm-8">
<div class="row-fluid">
    <div class="col-sm-4">
    </div>

    <div class="col-sm-8 center resultTitle">
        <g:message code="tools.calculator.grid.title"/>
    </div>
</div>

<div class="row-fluid">
    <div class="col-sm-4">
    </div>

    <div class="col-sm-2 center resultSubtitle">
        قرارداد آتی دی ۹۳
    </div>

    <div class="col-sm-2 center resultSubtitle">
        قرارداد آتی اسفند ۹۳
    </div>

    <div class="col-sm-2 center resultSubtitle">
        قرارداد آتی اردیبهشت ۹۴
    </div>

    <div class="col-sm-2 center resultSubtitle">
        قرارداد آتی تیر ۹۴
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4">
        <g:message code="tools.calculator.lastTradeDate"/>
    </div>

    <div class="col-sm-2 center">
        15-Jan-15
    </div>

    <div class="col-sm-2 center">
        15-Mar-15
    </div>

    <div class="col-sm-2 center">
        16-May-15
    </div>

    <div class="col-sm-2 center">
        16-Jul-15
    </div>
</div>

<div class="row-fluid even">
    <div class="col-sm-4">
        <g:message code="tools.calculator.remainingDay"/>
    </div>

    <div class="col-sm-2 center">
        32
    </div>

    <div class="col-sm-2 center">
        91
    </div>

    <div class="col-sm-2 center">
        153
    </div>

    <div class="col-sm-2 center">
        215
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4">
        <g:message code="tools.calculator.price"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="10300000" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="10760000" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="11315000" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="11760000" type="number"/>
    </div>
</div>

<div class="row-fluid even">
    <div class="col-sm-4">
        <g:message code="tools.calculator.onsWitchFixedDollar"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="10259" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="1315" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="1383" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="1438" type="number"/>
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4">
        <g:message code="tools.calculator.dollarWithFixedOns"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="35744" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="37344" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="39274" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="40821" type="number"/>
    </div>
</div>

<div class="row-fluid even">
    <div class="col-sm-4">
        <g:message code="tools.calculator.theoricBasedOnDollarAndOns"/>
    </div>

    <div class="col-sm-2 center">
        28.9%
    </div>

    <div class="col-sm-2 center">
        26.3%
    </div>

    <div class="col-sm-2 center">
        27.2%
    </div>

    <div class="col-sm-2 center">
        26.0%
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4">
        <g:message code="tools.calculator.theoricBasedNetPrice"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="1097559" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="10572097" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="10980511" type="number"/>
    </div>

    <div class="col-sm-2 center">
        <g:formatNumber number="11404703" type="number"/>
    </div>
</div>

<div class="row-fluid even">
    <div class="col-sm-4 double">
        <g:message code="tools.calculator.netArbitrage"/>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            <g:formatNumber number="1097559" type="number"/>
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            <g:formatNumber number="10572097" type="number"/>
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            <g:formatNumber number="10980511" type="number"/>
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            <g:formatNumber number="11404703" type="number"/>
        </div>
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4 double">
        فرصت آربیتراژ با قرارداد آتی دی
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            24.2%
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            26.9%
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            25.4%
        </div>
    </div>
</div>

<div class="row-fluid even">
    <div class="col-sm-4 double">
        فرصت آربیتراژ با قرارداد آتی اسفند
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            24.3%
        </div>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            23.5%
        </div>
    </div>
</div>

<div class="row-fluid odd">
    <div class="col-sm-4 double">
        فرصت آربیتراژ با قرارداد آتی اردیبهشت
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <br/><br/>
    </div>

    <div class="col-sm-2">
        <div class="col-sm-12 center">
            no
        </div>

        <div class="col-sm-12 center">
            17.4%
        </div>
    </div>
</div>

</div>

<div class="col-sm-4">
    chart
</div>
</div>
</div>
</body>
</html>