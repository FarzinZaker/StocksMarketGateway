<div style="line-height: 30px;">
    <span style="margin-left:30px;"><g:message code="symbol.info.lastPrice"/>:</span>
    <span style="white-space: nowrap">
        <span style="margin-left:30px;font-size:20px;font-weight: bold">
            <g:formatNumber number="${propertyInfo.finalIndexValue}" type="number"/>
        </span>
        <g:set var="todayIndexChangeValue"
               value="${Math.round(propertyInfo.todayIndexChangePercent / (propertyInfo.finalIndexValue / 100 + 1))}"/>
        <span style="margin-left:30px;color:${todayIndexChangeValue > 0 ? 'green' : 'red'}">
            <g:formatNumber number="${todayIndexChangeValue}" type="number"/>
        </span>
        <span style="color:${propertyInfo.todayIndexChangePercent > 0 ? 'green' : 'red'}">
            %<g:formatNumber number="${propertyInfo.todayIndexChangePercent}" type="number"/>
        </span>
    </span>
</div>

<div class="dashLet magenta propertyInfo">
    <table>
        <tr>
            <td><span>تاریخ:</span></td>
            <td><span class="date"><format:jalaliDate date="${propertyInfo.date}"/></span></td>
        </tr>
        <tr>
            <td><span>ساعت:</span></td>
            <td><span class="time"><g:formatDate date="${propertyInfo.date}"
                                                 format="hh:mm:ss"/></span></td>
        </tr>
        <tr>
            <td><span>بیشترین:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.highestIndexValue}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.lowestIndexValue}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای معامله شده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.tradedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای کاهش یافته:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.decreasedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای افزایش یافته:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.increasedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای بدون تغییر:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.unchangedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای معامله نشده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.notTradedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نمادهای رزرو شده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.reservedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>نماد های مسدود شده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.suspendedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کل نمادها:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.totalSymbolCount}" type="number"/></span>
            </td>
        </tr>
    </table>
</div>