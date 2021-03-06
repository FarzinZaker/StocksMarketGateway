<div class="propertyInfoPrice ${propertyInfo.change > 0 ? 'positive' : propertyInfo.change < 0 ? 'negative' : 0}">
    <h4><g:message code="symbol.info.lastPrice"/></h4>
        <span>
            <g:formatNumber number="${propertyInfo.price}" type="number"/>
        </span>
        <span>
            <g:formatNumber number="${propertyInfo.change}" type="number"/>
        </span>
        <span>
            ${propertyInfo.change > 0 ? '' : '-'}<g:formatNumber number="${propertyInfo.percent}" type="number"/>%
        </span>
    <div class="clear-fix"></div>
</div>

<div class="dashLet magenta propertyInfo">
    <table>
        <tr>
            <td><span>تاریخ:</span></td>
            <td><span class="date"><format:jalaliDate date="${propertyInfo.time}"/></span></td>
        </tr>
        <tr>
            <td><span>ساعت:</span></td>
            <td><span class="time"><g:formatDate date="${propertyInfo.time}"
                                                 format="hh:mm:ss"/></span></td>
        </tr>
        <tr>
            <td><span>بیشترین:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.high}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.low}" type="number"/></span>
            </td>
        </tr>
    </table>
</div>