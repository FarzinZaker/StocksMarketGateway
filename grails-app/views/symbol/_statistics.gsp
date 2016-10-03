<table class="table table-striped">
    <tr></tr>
    <tr>
        <th>
        </th>
        <th>
            <g:message code="symbol.statistics.count"/>
        </th>
        <th>
            <g:message code="symbol.statistics.rank"/>
        </th>
    </tr>
    <tr>
        <td>
            <g:message code="symbol.statistics.positiveDays"/>
        </td>
        <td>
            ${positiveDays ?: '-'}
        </td>
        <td>
            ${positiveDaysRank ?: '-'}
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="symbol.statistics.negativeDays"/>
        </td>
        <td>
            ${negativeDays ?: '-'}
        </td>
        <td>
            ${negativeDaysRank ?: '-'}
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="symbol.statistics.buy"/>
        </td>
        <td>
            <g:if test="${buy}">
                <g:formatNumber number="${buy}" format="#.##"/>
            </g:if>
            <g:else>
                -
            </g:else>
        </td>
        <td>
            ${buyRank ?: '-'}
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="symbol.statistics.sell"/>
        </td>
        <td>
            <g:if test="${sell}">
                <g:formatNumber number="${sell}" format="#.##"/>
            </g:if>
            <g:else>
                -
            </g:else>
        </td>
        <td>
            ${sellRank ?: '-'}
        </td>
    </tr>
</table>