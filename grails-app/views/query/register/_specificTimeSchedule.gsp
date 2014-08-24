<%@ page import="stocks.alerting.ScheduleDay" %>
<script language="javascript">
    function addTime() {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.timeList.push({value: '18:00'});
        $scope.timeCounter += 1;
        $scope.$apply();

        $("#time" + $scope.timeCounter).clockpicker({
            placement: 'top',
            align: 'right',
            autoclose: true
        });
    }
    function removeTime(item) {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.timeList.splice(parseInt($(item).attr('data-row')), 1);
        $scope.timeCounter -= 1;
        $scope.$apply();
    }

    $(document).ready(function () {
        $('.clockpicker').clockpicker({
            placement: 'top',
            align: 'right',
            autoclose: true
        });
    });
</script>

<form:field fieldName="query.register.days">
    <div style="width:500px;">
        <g:each in="${ScheduleDay.constraints.day.inList}" var="day">
            <form:checkbox text="${message(code: "ScheduleDayTemplate.${day}.label")}"
                           checked="${queryInstance?.schedule?.days?.any { it.day == day }}"
                           name="scheduleTime_${day}" style="margin-left:10px;min-width: 80px;"/>
        </g:each>
    </div>
</form:field>
<form:field fieldName="query.register.times" showHelp="0">
    <div ng-repeat="time in timeList" class="grid-form-row" ng-animate="'animate'" style="margin-bottom:5px;">
        <input style="float:right;" type="button" class="btn-delete-row" onclick="removeTime(this)" data-row="{{$index}}"/>

        <div style="float:right">
            <div class="input-group clockpicker" style="200px">
                <input name="scheduleTimes" type="text" class="form-control" width="200px" id="time{{$index + 1}}"
                       ng-model="time.value">
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-time"></span>
                </span>
            </div>
        </div>

        <div class="clear-fix"></div>
    </div>

    <div class="info" ng-show="!timeList.length"><g:message code="query.register.times.empty"/></div>

    <div class="toolbar">
        <form:button text="${message(code: 'query.register.times.add')}" onclick="addTime()"/>
    </div>
</form:field>