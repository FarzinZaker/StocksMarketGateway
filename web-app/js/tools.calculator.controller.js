//var alertingQuery = angular.module('stocks', ["highcharts-ng"]);
(function (app, ng) {

    app.controller('toolsCalculatorController', function ($scope, $http) {
        //assumptions
        $scope.multiplicationCost = 20000.0;
        $scope.addedValueTax = 0;
        $scope.interestRate = 0.25;
        $scope.expectedReturn = 0.30;
        $scope.borrowingRate = 0.25;
        $scope.lendingRate = 0.20;
        $scope.margin = margin;
        $scope.marginChanges = 0.0;

        $scope.$watch('multiplicationCost', onScopeChange);
        $scope.$watch('addedValueTax', onScopeChange);
        $scope.$watch('interestRate', onScopeChange);
        $scope.$watch('expectedReturn', onScopeChange);
        $scope.$watch('borrowingRate', onScopeChange);
        $scope.$watch('lendingRate', onScopeChange);
        $scope.$watch('margin', onScopeChange);
        $scope.$watch('marginChanges', onScopeChange);

        $scope.dollarPrice = 0;
        $scope.onsPrice = 0;
        $scope.coinPrice = 0;
        $scope.contracts = 0;

        $scope.coinTheoricPrice = function () {
            return (($scope.dollarPrice * $scope.onsPrice / 4.249) + $scope.multiplicationCost) * (1 + $scope.addedValueTax);
        };

        $scope.onsWitchFixedDollar = function (contract) {
            return (((contract.lastTradedPrice / (1 + $scope.addedValueTax)) - $scope.multiplicationCost) * 4.249) / $scope.dollarPrice;
        };

        $scope.dollarWithFixedOns = function (contract) {
            return (((contract.lastTradedPrice / (1 + $scope.addedValueTax)) - $scope.multiplicationCost) * 4.249) / $scope.onsPrice;
        };

        $scope.theoricBasedOnDollarAndOns = function (contract) {
            return $scope.coinTheoricPrice() * Math.pow(Math.E, $scope.interestRate * contract.remainingDays / 365);
        };

        $scope.theoricBasedNetPrice = function (contract) {
            return $scope.coinPrice * Math.pow(Math.E, $scope.interestRate * contract.remainingDays / 365.0);
        };

        $scope.netArbitrage = function (contract) {
            return (((contract.lastTradedPrice - $scope.coinPrice - 3000 - 5000 + (($scope.margin / 10) * (0.07 / 365) * contract.remainingDays)) / ($scope.coinPrice + ($scope.margin / 10) + ($scope.marginChanges / 10))) / contract.remainingDays) * 365;
        };

        $scope.netArbitrageFlag = function (contract) {
            return $scope.netArbitrage(contract) < $scope.expectedReturn ? 'no' : 'yes';
        };

        $scope.relativeArbitrage = function (contract, relatedContract, relatedIndex, index) {

            if (index >= relatedIndex)
                return '-';

            var values = [];
            values[0] = (-1) * $scope.margin / 10;
            values[1] = (-1) * 3000 / 10;
            values[2] = (-1) * 3000 / 10;
            values[3] = (-1) * 5000 / 10;
            values[4] = (-1) * 5000 / 10;
            values[5] = (-1) * contract.lastTradedPrice;
            values[6] = relatedContract.lastTradedPrice;
            values[7] = $scope.margin / 10;
            values[8] = ($scope.margin / 10) * (0.07 / 365) * relatedContract.remainingDays;

            var now = new Date();
            var firstDate = new Date();
            firstDate.setDate(now.getDate() + contract.remainingDays);
            var secondDate = new Date();
            secondDate.setDate(now.getDate() + relatedContract.remainingDays);
            var dates = [];
            dates[0] = now;
            dates[1] = now;
            dates[2] = now;
            dates[3] = firstDate;
            dates[4] = secondDate;
            dates[5] = firstDate;
            dates[6] = secondDate;
            dates[7] = secondDate;
            dates[8] = secondDate;

            return XIRR(values, dates);
        };

        $scope.relativeArbitrageFlag = function (contract, relatedContract, relatedIndex, index) {

            if (index >= relatedIndex)
                return '';

            return $scope.relativeArbitrage(contract, relatedContract, relatedIndex, index) < $scope.expectedReturn ? 'no' : 'yes';
        };

        $scope.chartSeries = $.map($scope.contracts, function (contract, index) {
            return {"name": contract.name, "data": [$scope.netArbitrage(contract)], type: "column"};
        });


    });

    app.directive('highlighter', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            scope: {
                model: '=highlighter'
            },
            link: function (scope, element) {
                scope.$watch('model', function (nv, ov) {
                    if (nv !== ov) {
                        // apply class
                        element.addClass('highlight');

                        // auto remove after some delay
                        $timeout(function () {
                            element.removeClass('highlight');
                        }, 1000);
                    }
                });
            }
        };
    }]);
})(angular.module('stocks', ["highcharts-ng"]), angular);