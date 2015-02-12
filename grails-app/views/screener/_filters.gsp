<ul id="filtersList">
    <g:each in="${filterMap.keySet()}" var="group">
        <li class="k-state-active">
            <g:message code="${group}"/>
            <ul>
                <g:each in="${filterMap[group]}" var="filter">
                    <li data-filter="${filter.name}"><g:message code="${filter.name}"/>
                    <g:if test="${filter.hasParameter}">
                        <g:render template="${filter.parameterTemplate}"
                                  model="${filter.parameterModel + [id: filter.name.replace('.', '_')]}"/>
                    </g:if>
                    </li>
                </g:each>
            </ul>
        </li>
    </g:each>
</ul>

<script>

    function parameterChange(e, value) {
        $('#parameter').val(value);
    }

    $(document).ready(function () {
        $("#filtersList").kendoPanelBar({
            select: function (e) {
                var filter = $(e.item).attr("data-filter");
                if (filter) {
                    enableButtons(false);
                    $('#filter').val(filter);
                    var paramField = $(e.item).find('[name=parameter]');
                    if (paramField.length > 0)
                        $('#parameter').val(paramField.data('kendoComboBox').value());
                    else
                        $('#parameter').val('');
                    $('#values').stop().fadeOut(500, function () {
                    });
                    $('#values-loading').stop().fadeOut(500);
                    $('#operators').stop().fadeOut(500, function () {
                        $('#operators-loading').stop().fadeIn(500);
                    });
                    $.ajax({
                        type: "POST",
                        url: '${createLink(action: 'operators')}',
                        data: {filter: filter}
                    }).done(function (response) {
                        $('#operators-loading').stop().fadeOut(500, function () {
                            $('#operators').html(response).stop().fadeIn(500);
                            $('#operators-loading').hide();
                        });
                    });
                }
            }
        });
    });
</script>