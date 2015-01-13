%{--<div class="row-fluid">--}%
    %{--<div class="col-xs-12">--}%
        %{--<h2><g:message code="tools.calculator.baseInfo.title"/></h2>--}%
    %{--</div>--}%
%{--</div>--}%

<div class="row-fluid">
    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.dollar"/>
        </div>

        <div class="col-sm-12 center">
            <b>{{dollarPrice | number:0}}</b>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.ons"/>
        </div>

        <div class="col-sm-12 center">
            <b>{{onsPrice | number:2}}</b>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.coin"/>
        </div>

        <div class="col-sm-12 center">
            <b>{{coinPrice | number:0}}</b>
        </div>
    </div>

    <div class="col-xs-3 baseInfo">
        <div class="col-sm-12 center">
            <g:message code="tools.calculator.price.coinTheoric"/>
        </div>

        <div class="col-sm-12 center">
            <b>{{coinTheoricPrice() | number:0}}</b>
        </div>
    </div>
</div>
