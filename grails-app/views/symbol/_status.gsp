<table style="width: 100%;line-height:30px;">
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>تاریخ:</span></td>
        <td><span dir="ltr" class="date"><format:jalaliDate date="${lastDailyTrade.date}"/></span></td>
        <td><span>ساعت:</span></td>
        <td><span dir="ltr" class="time"><g:formatDate date="${lastDailyTrade.dailyTrade.date}" format="hh:mm:ss"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>بیشترین:</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${lastDailyTrade.maxPrice}" type="number"/></span></td>
        <td><span>کمترین:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${lastDailyTrade.minPrice}" type="number"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>دفعات:</span></td>
        <td><span dir="ltr" class="tradecount"><g:formatNumber number="${lastDailyTrade.totalTradeCount}" type="number"/></span></td>
        <td><span>باز:</span></td>
        <td><span dir="ltr" class="open"><g:formatNumber number="${lastDailyTrade.firstTradePrice}" type="number"/></span></td>
    </tr>
    <tr>
        <td><span>حجم:</span></td>
        <g:set var="totalTradeVolume" value="${lastDailyTrade.totalTradeVolume}"/>
        <g:set var="totalTradeVolumeFlag" value=""/>
        <g:if test="${totalTradeVolume > 1000}">
            <g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>
            <g:set var="totalTradeVolumeFlag" value="K"/>
        </g:if>
        <g:if test="${totalTradeVolume > 1000}">
            <g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>
            <g:set var="totalTradeVolumeFlag" value="M"/>
        </g:if>
        <g:if test="${totalTradeVolume > 1000}">
            <g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>
            <g:set var="totalTradeVolumeFlag" value="B"/>
        </g:if>
        <td><span dir="ltr" class="sharecount"><g:formatNumber number="${Math.round(totalTradeVolume * 100)/100}" type="number"/>${totalTradeVolumeFlag}</span></td>
        <td><span>ارزش:</span></td>
        <g:set var="totalTradeValue" value="${lastDailyTrade.totalTradeValue}"/>
        <g:set var="totalTradeValueFlag" value=""/>
        <g:if test="${totalTradeValue > 1000}">
            <g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>
            <g:set var="totalTradeValueFlag" value="K"/>
        </g:if>
        <g:if test="${totalTradeValue > 1000}">
            <g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>
            <g:set var="totalTradeValueFlag" value="M"/>
        </g:if>
        <g:if test="${totalTradeValue > 1000}">
            <g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>
            <g:set var="totalTradeValueFlag" value="B"/>
        </g:if>
        <td><span dir="ltr" class="volume"><g:formatNumber number="${Math.round(totalTradeValue * 100)/100}" type="number"/>${totalTradeValueFlag}</span></td>
    </tr>
</table>