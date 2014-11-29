<script language="javascript" type="text/javascript">
    var currentDeliveryMethod_${parameter.id}
    function getParameterValueText_${parameter.id}(){
        return eval('getParameterValueText_${parameter.id}_' + currentDeliveryMethod_${parameter.id} + '()');
    }
    function getParameterValueValue_${parameter.id}(){
        return eval('getParameterValueValue_${parameter.id}_' + currentDeliveryMethod_${parameter.id} + '()');
    }
    function getParameterValueType_${parameter.id}(){
        return eval('getParameterValueType_${parameter.id}_' + currentDeliveryMethod_${parameter.id} + '()');
    }
</script>