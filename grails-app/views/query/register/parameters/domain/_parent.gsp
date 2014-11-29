<g:set var="parentRelation" value="${null}"/>
<g:each in="${deliveryMethod.relations}" var="relation">
    <input id="parameter_${parameter.id}_${deliveryMethod.name}_${relation.domain.name.replace('.', '_')}_selector"
           style="width: 248px;"/>
%{--<input id="products" disabled="disabled"/>--}%
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            var parameter_${parameter.id}_${deliveryMethod.name}_${relation.domain.name.replace('.', '_')}_selector = $("#parameter_${parameter.id}_${deliveryMethod.name}_${relation.domain.name.replace('.', '_')}_selector").kendoComboBox({
                <g:if test="${parentRelation}">
                autoBind: false,
                cascadeFrom: "parameter_${parameter.id}_${deliveryMethod.name}_${parentRelation.domain.name.replace('.', '_')}_selector",
                </g:if>
                filter: "contains",
                placeholder: "${message(code:"${domainClass.fullName}.${property.name}.deliveryMethods.${deliveryMethod.name}.${relation.domain.name.replace('.','_')}.placeHolder")}",
                dataTextField: "name",
                dataValueField: "value",
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        type: "odata",
                        read: {
                            url: '<format:html value="${createLink(controller: 'query', action: 'cascadingData', params:[domain:relation.domain.name,parentDomain:parentRelation?.domain?.name, primaryKey:relation?.primaryKey, foreignKey:parentRelation?.foreignKey, display:relation.display])}"/>',
                            dataType: "json",
                            type: "POST"
                        }
                    },
                    schema: {
                        data: "data" // records are returned in the "data" field of the response
                    }
                },
                change: function (e) {
                    if (this.value() && this.selectedIndex == -1) {
                        var dt = this.dataSource._data[0];
                        this.text(dt[this.options.dataTextField]);
                        this._selectItem();
                    }
                }
            }).data("kendoComboBox");
        });
    </script>
    <g:set var="parentRelation" value="${relation}"/>
</g:each>
<script language="javascript" type="text/javascript">
    function getParameterValueText_${parameter.id}_${deliveryMethod.name}() {
        var combobox;
        var value = [];

        <g:each in="${deliveryMethod.relations}" var="relation">
        combobox = $("#parameter_${parameter.id}_${deliveryMethod.name}_${relation.domain.name.replace('.', '_')}_selector").data("kendoComboBox");
        if (combobox.dataItem())
            value[value.length] = combobox.dataItem().name;
        else
            value[value.length] = combobox.text();
        </g:each>

        return value.join(' - ');
    }
    function getParameterValueValue_${parameter.id}_${deliveryMethod.name}() {
        var combobox;
        var value = [];

        <g:each in="${deliveryMethod.relations}" var="relation">
        combobox = $("#parameter_${parameter.id}_${deliveryMethod.name}_${relation.domain.name.replace('.', '_')}_selector").data("kendoComboBox");

        if (combobox.dataItem())
            value[value.length] = combobox.dataItem().value;
        else
            value[value.length] = combobox.text();
        </g:each>

        return value.join(',');
    }
    function getParameterValueType_${parameter.id}_${deliveryMethod.name}() {
        return '${property.name}_${deliveryMethod.name}';
    }
</script>