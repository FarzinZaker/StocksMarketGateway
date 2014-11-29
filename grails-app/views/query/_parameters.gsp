<%@ page import="grails.converters.JSON" %>
<script language="javascript">
    var parameterTypeData = <format:html value="${parameterTypeList as JSON}"/>;
    function addParameter() {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.parameterList.push({name: '', type: 'string', defaultValue: '', multiSelect: true});
        $scope.parameterCounter += 1;
        $scope.$apply();

        $("#type" + $scope.parameterCounter).removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: parameterTypeData,
            index: 0,
            change: function (e) {
                handleParameterTypeChange(e);
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });
    }
    function removeParameter(item) {
        var $scope = angular.element(document.getElementById('ngController')).scope();
        $scope.parameterList.splice(parseInt($(item).attr('data-row')), 1);
        $scope.parameterCounter -= 1;
        $scope.$apply();
    }

    $(document).ready(function () {

        $("input[name=parameterTypes]").removeClass('k-textbox').kendoComboBox({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: parameterTypeData,
            index: 0,
            change: function (e) {
                handleParameterTypeChange(e);
                if (this.value() && this.selectedIndex == -1) {
                    var dt = this.dataSource._data[0];
                    this.text(dt[this.options.dataTextField]);
                    this._selectItem();
                }
            }
        });

        $("input[name=parameterValues]").removeClass('k-textbox').each(function (index, item) {
            var combobox = $(this).parent().find('input[name=parameterTypes]').data('kendoComboBox');
            if (combobox.value() != 'string' &&
                    combobox.value() != 'integer') {
                //complex types
                $(this).removeClass('k-textbox').kendoComboBox({
                    dataTextField: "name",
                    dataValueField: "value",
                    filter: "contains",
                    template: '<div class="autocomplete-row"><h4>#: data.name #</h4><span>#: data.typeString #</span></div>',
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${createLink(action: 'autoComplete')}?domain=" + findParameterTypeSourceDomain(combobox.value()) + "&field=" + findParameterTypeSourceField(combobox.value()),
                                dataType: "json",
                                type: "POST"

                            }
                        },
                        schema: {
                            data: "data" // records are returned in the "data" field of the response
                        }
                    }
                });
            }
            else{
                $(this).addClass('k-textbox');
            }
        });
    });

    function handleParameterTypeChange(e) {
        var widget = $(e.sender.element).parent().parent().next('.k-combobox');
        var input = widget.find('input');
//        input.unwrap().removeAttr('style').addClass('k-textbox');
        if (input.length == 0)
            input = $(e.sender.element).parent().parent().next('input');
        var simpleInput = input.parent().next('input');
        var autocomplete = input.data("kendoComboBox");
        if (autocomplete)
            autocomplete.destroy();
        if (e.sender.value() != 'string' &&
                e.sender.value() != 'integer') {
            widget.remove();
            //complex types
            input.removeClass('k-textbox').width('350px').kendoComboBox({
                dataTextField: "name",
                dataValueField: "value",
                filter: "contains",
                template: '<div class="autocomplete-row"><h4>#: data.name #</h4><span>#: data.typeString #</span></div>',
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        type: 'odata',
                        read: {
                            url: "${createLink(action: 'autoComplete')}?domain=" + findParameterTypeSourceDomain(e.sender.value()) + "&field=" + findParameterTypeSourceField(e.sender.value()),
                            dataType: "json",
                            type: "POST"

                        }
                    },
                    schema: {
                        data: "data" // records are returned in the "data" field of the response
                    }
                }
            });
        }
        else{
            simpleInput.unwrap().show().addClass('k-textbox').prev('.k-dropdown-wrap').remove()
        }
    }

    function findParameterTypeSourceDomain(type) {
        var result = '';
        $.each(parameterTypeData, function (index, item) {
            if (item.value == type)
                result = item.domain;
        });
        return result;
    }

    function findParameterTypeSourceField(type) {
        var result = '';
        $.each(parameterTypeData, function (index, item) {
            if (item.value == type)
                result = item.field;
        });
        return result;
    }
</script>

<div class="grid-form">
    <div ng-repeat="parameter in parameterList" class="grid-form-row" ng-animate="'animate'">
        <input type="button" class="btn-delete-row" onclick="removeParameter(this)" data-row="{{$index}}"/>
        <form:textBox validation="required" ng-model="parameter.name" name="parameterNames" style="width:200px;"
                      placeholder="${message(code: 'query.build.parameters.name')}" ng-keyup="refillAutoCompleteList()"/>

        <span class="k-rtl">
            <input class="k-textbox" name="parameterTypes" id="type{{$index + 1}}" ng-model="parameter.type"/>
        </span>

        <input class="k-textbox" name="parameterValues" id="parameterValue{{$index + 1}}"
               ng-model="parameter.defaultValue" style="width:350px;"
               placeholder="${message(code: 'query.build.parameters.defaultValue')}"/>

        <input type="checkbox" class="css-checkbox" id="parameterMultiSelect{{$index + 1}}" name="parameterMultiSelects_{{$index + 1}}"
               ng-model="parameter.multiSelect" style="display: none" />
        <label class="css-label" for="parameterMultiSelect{{$index + 1}}" style="display: none">
            <g:message code='query.build.parameters.multiSelect'/>
        </label>
    </div>
</div>

<div class="info" ng-show="!parameterList.length"><g:message code="query.build.parameters.empty"/></div>

<div class="toolbar">
    <form:button text="${message(code: 'query.build.parameters')}" onclick="addParameter()"/>
</div>