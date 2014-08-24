var alertingQuery = angular.module('stocks', []);

alertingQuery.controller('alertingRegisterController', function ($scope, $http) {
    $scope.timeList = new kendo.data.ObservableArray(timeList);
    $scope.timeCounter = timeList.length;
});