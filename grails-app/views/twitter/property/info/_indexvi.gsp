<g:set var="todayIndexChangeValue"
       value="${Math.round(propertyInfo.todayIndexChangePercent / (propertyInfo.finalIndexValue / 100 + 1))}"/>


<div class="propertyInfoPrice ${todayIndexChangeValue > 0 ? 'positive' : todayIndexChangeValue < 0 ? 'negative' : ''}">
    <h4><g:message code="symbol.info.lastPrice"/></h4>
    <span>
        <b><g:formatNumber number="${propertyInfo.finalIndexValue}" type="number"/></b>
    </span>
    <span>
        <g:formatNumber number="${todayIndexChangeValue}" type="number"/>
    </span>
    <span>
        <g:formatNumber number="${propertyInfo.todayIndexChangePercent}" type="number"/>%
    </span>
    <div class="clear-fix"></div>
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
            <td><span>معامله شده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.tradedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کاهش یافته:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.decreasedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>افزایش یافته:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.increasedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>بدون تغییر:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.unchangedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>معامله نشده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.notTradedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>رزرو شده:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.reservedSymbolCount}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span> مسدود شده:</span></td>
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