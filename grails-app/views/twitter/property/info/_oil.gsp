<div style="line-height: 30px;">
    <span style="margin-left:30px;"><g:message code="symbol.info.lastPrice"/>:</span>
    <span style="white-space: nowrap">
        <span style="margin-left:30px;font-size:20px;font-weight: bold">
            <g:formatNumber number="${propertyInfo.price}" type="number"/>
        </span>
        <span style="margin-left:30px;color:${propertyInfo.change > 0 ? 'green' : 'red'}">
            <g:formatNumber number="${propertyInfo.change}" type="number"/>
        </span>
        <span style="color:${propertyInfo.percent > 0 ? 'green' : 'red'}">
            %<g:formatNumber number="${propertyInfo.percent}" type="number"/>
        </span>
    </span>
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
            <td><span>باز:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.open}" type="number"/> ${propertyInfo.unit}</span>
            </td>
        </tr>
        <tr>
            <td><span>بیشترین:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.high}" type="number"/> ${propertyInfo.unit}</span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.low}" type="number"/> ${propertyInfo.unit}</span>
            </td>
        </tr>
    </table>
</div>