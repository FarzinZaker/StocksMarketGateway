<script language="javascript">
    function addSortingRule() {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.sortingRuleList.push({fieldName: '', sortDirection: 'asc', sortOrder: $scope.sortingRuleList.length});
        $scope.sortingRuleCounter += 1;
        $scope.$apply();


        var sortDirectionData = [
            {"text": "${message(code:'query.build.sortingRules.sortDirection.asc')}", "value": "asc"},
            {"text": "${message(code:'query.build.sortingRules.sortDirection.desc')}", "value": "desc"}
        ];

        $("#sortDirection" + $scope.sortingRuleCounter).removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: sortDirectionData,
            index: 0,
            change: function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });


        //field names
        var fieldNameData = <format:html value="${fields.collect{[text: message(code:"${domainClass}.${it}.label"), value: it]} as grails.converters.JSON}"/>;

        $("#fieldName" + $scope.sortingRuleCounter).removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: fieldNameData,
            index: 0,
            change: function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });

        return false;
    }
    function removeSortingRule(item) {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.sortingRuleList.splice(parseInt($(item).attr('data-row')), 1);
        $scope.sortingRuleCounter -= 1;
        $scope.$apply();
        return false;
    }

    $(document).ready(function () {

        var sortDirectionData = [
            {"text": "${message(code:'query.build.sortingRules.sortDirection.asc')}", "value": "asc"},
            {"text": "${message(code:'query.build.sortingRules.sortDirection.desc')}", "value": "desc"}
        ];

        $("input[name=sortingRuleSortDirections]").removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: sortDirectionData,
            index: 0,
            change: function (e) {
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });


        //field names
        var fieldNameData = <format:html value="${fields.collect{[text: message(code:"${domainClass}.${it}.label"), value: it]} as grails.converters.JSON}"/>;

        $("input[name=sortingRuleFieldNames]").removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: fieldNameData,
            index: 0,
            change: function (e) {
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
    <div ng-repeat="sortingRule in sortingRuleList" class="grid-form-row" ng-animate="'animate'">
        <input type="button" class="btn-delete-row" onclick="removeSortingRule(this)" data-row="{{$index}}"/>

        <span class="k-rtl">
            <input class="k-textbox" name="sortingRuleFieldNames" id="fieldName{{$index + 1}}"
                   ng-model="sortingRule.fieldName"/>
        </span>
        <span class="k-rtl">
            <input class="k-textbox" name="sortingRuleSortDirections" id="sortDirection{{$index + 1}}"
                   ng-model="sortingRule.sortDirection"/>
        </span>

        <input type="hidden" name="sortingRuleSortOrders" value="{{$index + 1}}"/>

    </div>
</div>

<div class="info" ng-show="!sortingRuleList.length"><g:message code="query.build.sortingRules.empty"/></div>

<div class="toolbar">
    <form:button text="${message(code: 'query.build.sortingRules.add')}" onclick="addSortingRule()"/>
</div>