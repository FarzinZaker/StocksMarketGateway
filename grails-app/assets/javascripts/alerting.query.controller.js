var alertingQuery = angular.module('stocks', []);

alertingQuery.controller('alertingQueryController', function ($scope, $http) {
    $scope.parameterList = new kendo.data.ObservableArray(parameterList);
    $scope.parameterCounter = parameterList.length;

    $scope.parameterList.bind('change', function(e){
        $scope.refillAutoCompleteList();
    });

    $scope.refillAutoCompleteList = function(){
        $scope.autoCompleteList = new kendo.data.ObservableArray(
            fieldList);
        $.each($scope.parameterList, function(index, value){
            $scope.autoCompleteList.push({name: value.name, value: value.name, type:'parameter', typeString: parameterTypeString})
        })
    }

    $scope.refillAutoCompleteList();
});