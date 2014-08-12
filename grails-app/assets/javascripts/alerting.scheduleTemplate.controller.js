var alertingQuery = angular.module('stocks', []);

alertingQuery.controller('alertingScheduleTemplateController', function ($scope, $http) {
    $scope.intervalSteps = intervalSteps;
    $scope.intervalStepCounter = intervalSteps.length;


    $scope.addIntervalStep = function () {
        $scope.intervalSteps[$scope.intervalSteps.length] = {value: Math.max.apply(Math, $.map($scope.intervalSteps, function (element){
            return element.value;
        })) * 2};
        $scope.intervalStepCounter += 1;
        return false;
    }

    $scope.removeIntervalStep = function (index) {
        if ($scope.intervalSteps.length == 1)
            return;
        $scope.intervalSteps.splice(parseInt(index), 1);
        $scope.intervalStepCounter -= 1;
        return false;
    }
});