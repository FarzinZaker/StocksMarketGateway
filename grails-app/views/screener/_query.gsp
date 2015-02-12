<div>
    <button class="addQueryButton"><g:message code="screener.addQuery"/></button>
</div>

<div style="display: none" id="filter-query-empty">
    <div class="info">
        <g:message code="screener.query.empty"/>
    </div>
</div>

<div style="display: none" id="queryItemLoading">
    <asset:image src="loading.gif" style="margin-left: 10px;"/>
    <g:message code="loading"/>
</div>

<div id="filter-query-panel">
    <g:set var="index" value="${0}"/>
    <g:each in="${rules}" var="rule">
        <div class="queryItem" id="queryItem_${index++}">
            <g:render template="queryItem"
                      model="${[filter: rule.filter, operator: rule.operator, value: rule.value, text: rule.text, parameter: rule.parameter]}"/>
        </div>
    </g:each>
</div>

<div>
    <button class="addQueryButton"><g:message code="screener.addQuery"/></button>
</div>

<script language="javascript" type="text/javascript">
    function enableButtons(value) {
        $.each($('.addQueryButton'), function () {
            $(this).data('kendoButton').enable(value);
        });
    }

    function addQuery() {
        var itemIndex = createEmptyQueryItem();
        setEmptyMessage();
        $.ajax({
            type: "POST",
            url: '${createLink(action: 'queryItem')}',
            data: $('#valueForm').serialize()
        }).done(function (response) {
            $('#queryItem_' + itemIndex).html(response);
        });
    }

    var queryItemCounter = ${index};
    function createEmptyQueryItem() {
        var index = ++queryItemCounter;
        $('#filter-query-panel').append('<div class="queryItem" id="queryItem_' + index + '"></div>');
        $('#queryItem_' + index).html($('#queryItemLoading').html());
        return index;
    }

    function setEmptyMessage() {
        if ($('#filter-query-panel').find('.queryItem').length == 0) {
            $('#filter-query-panel').html($('#filter-query-empty').html());
            $('#filter-query-panel').find($('.info').slideDown());
        }
        else
            $('#filter-query-panel').find($('.info').slideUp());

    }

    function removeQueryItem(item) {
        $(item).parent().slideUp(500, function () {
            $(this).remove();
            setEmptyMessage();
        });
    }

    $(document).ready(function () {
        $(".addQueryButton").kendoButton({
            enable: false
        }).click(addQuery);
        setEmptyMessage();
    });
</script>