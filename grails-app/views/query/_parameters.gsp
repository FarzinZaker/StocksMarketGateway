<script language="javascript">
    function addParameter () {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.parameterList.push({name:'', type:'string', defaultValue:''});
        $scope.parameterCounter += 1;
        $scope.$apply();

        var data = [
            {"text":"${message(code:'query.build.parameters.type.string')}","value":"string"},
            {"text":"${message(code:'query.build.parameters.type.integer')}","value":"integer"},
            {"text":"${message(code:'query.build.parameters.type.date')}","value":"date"}];

        $("#type" + $scope.parameterCounter).removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: data,
            index: 0,
            change : function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });
    }
    function removeParameter (item) {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.parameterList.splice(parseInt($(item).attr('data-row')),1);
        $scope.parameterCounter -= 1;
        $scope.$apply();
    }

    $(document).ready(function(){

        var data = [
            {"text":"${message(code:'query.build.parameters.type.string')}","value":"string"},
            {"text":"${message(code:'query.build.parameters.type.integer')}","value":"integer"},
            {"text":"${message(code:'query.build.parameters.type.date')}","value":"date"}];

        $("input[name=parameterTypes]").removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: data,
            index: 0,
            change : function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });
    });
</script>

<div class="grid-form">
<div ng-repeat="parameter in parameterList" class="grid-form-row" ng-animate="'animate'">
    <input type="button" class="btn-delete-row" onclick="removeParameter(this)" data-row="{{$index}}" />
    <form:textBox validation="required" ng-model="parameter.name" name="parameterNames" style="width:200px;" placeholder="${message(code:'query.build.parameters.name')}"/>

    <span class="k-rtl">
        <input class="k-textbox" name="parameterTypes" id="type{{$index + 1}}" ng-model="parameter.type"  />
    </span>

    <form:textBox ng-model="parameter.defaultValue" name="parameterValues" style="width:200px;" placeholder="${message(code:'query.build.parameters.defaultValue')}"/>

</div>
</div>
<div class="info" ng-show="!parameterList.length"><g:message code="query.build.parameters.empty"/></div>
<div class="toolbar">
    <form:button text="${message(code:'query.build.parameters')}" onclick="addParameter()"/>
</div>