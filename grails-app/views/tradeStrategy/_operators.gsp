<ul id="operatorList">
<g:each in="${operators}" var="operator">
    <li data-operator="${operator}"><g:message code="stocks.filters.operators.${operator}"/></li>
</g:each>
</ul>

<script>
    $(document).ready(function () {
        $("#operatorList").kendoPanelBar({
            select: function (e) {
                var operator = $(e.item).attr("data-operator");
                if (operator) {
                    enableButtons(false);
                    $('#operator').val(operator);
                    $('#values').stop().fadeOut(500, function(){
                        $('#values-loading').stop().fadeIn(500);
                    });
                    $.ajax({
                        type: "POST",
                        url: '${createLink(action: 'values')}',
                        data: {operator: operator, filter: $('#filter').val()}
                    }).done(function (response) {
                        $('#values-loading').stop().fadeOut(500, function(){
                            $('#values').html(response).stop().fadeIn(500);
                            $('#values-loading').hide();
                            enableButtons(true);
                        });
                    });
                }
            }
        });
    });
</script>