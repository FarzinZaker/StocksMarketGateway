<table cellpadding="5">
    <tr>
        <td></td>
        <td colspan="{{contracts.length}}" class="center resultTitle"><g:message
                code="tools.calculator.grid.title"/></td>
    </tr>
    <tr>
        <td></td>
        <td ng-repeat="contract in contracts" class="center resultSubtitle">{{contract.name}}</td>
    </tr>
    <tr>
        <td class="left resultSubtitle">
            <g:message code="tools.calculator.lastTradeDate"/>
        </td>
        <td ng-repeat="contract in contracts" class="center resultSubtitle">{{contract.lastTradingDate}}</td>
    </tr>
    <tr>
        <td class="left resultSubtitle">
            <g:message code="tools.calculator.remainingDay"/>
        </td>
        <td ng-repeat="contract in contracts" class="center resultSubtitle">{{contract.remainingDays}}</td>
    </tr>
    <tr>
        <td class="left resultSubtitle">
            <g:message code="tools.calculator.price"/>
        </td>
        <td ng-repeat="contract in contracts" class="center resultSubtitle">{{contract.lastTradedPrice | number:0}}</td>
    </tr>
    <tr class="odd">
        <td class="left">
            <g:message code="tools.calculator.onsWitchFixedDollar"/>
        </td>
        <td ng-repeat="contract in contracts" class="center">{{onsWitchFixedDollar(contract) | number:0}}</td>
    </tr>
    <tr class="even">
        <td class="left">
            <g:message code="tools.calculator.dollarWithFixedOns"/>
        </td>
        <td ng-repeat="contract in contracts" class="center">{{dollarWithFixedOns(contract) | number:0}}</td>
    </tr>
    <tr class="odd">
        <td class="left">
            <g:message code="tools.calculator.theoricBasedOnDollarAndOns"/>
        </td>
        <td ng-repeat="contract in contracts" class="center">{{theoricBasedOnDollarAndOns(contract) | number:0}}</td>
    </tr>
    <tr class="even">
        <td class="left">
            <g:message code="tools.calculator.theoricBasedNetPrice"/>
        </td>
        <td ng-repeat="contract in contracts" class="center">{{theoricBasedNetPrice(contract) | number:0}}</td>
    </tr>
    <tr class="odd">
        <td class="left">
            <g:message code="tools.calculator.netArbitrage"/>
        </td>
        <td ng-repeat="contract in contracts" class="center">{{netArbitrage(contract) * 100 | number:1}}% <img
                width="16px" ng-src="${createLink(uri: '/')}/images/{{netArbitrageFlag(contract)}}.png"/></td>
    </tr>
    <tr ng-repeat="contract in contracts" ng-class="{'odd': $index % 2 == 1}">
        <td ng-show="$index < contracts.length - 1" class="left">
            <g:message code="tools.calculator.relativeArbitrage"/> {{contract.name}}
        </td>
        <td ng-show="$parent.$index < contracts.length - 1" ng-repeat="relatedContract in contracts"
            class="center">{{relativeArbitrage(contract, relatedContract, $index, $parent.$index) * 100 | number:1}}<span
                ng-show="$index > $parent.$index">% <img width="16px"
                                                         ng-src="${createLink(uri: '/')}/images/{{relativeArbitrageFlag(contract, relatedContract, $index, $parent.$index)}}.png"/>
        </span><span ng-show="$index <= $parent.$index">-</span></td>
    </tr>
</table>