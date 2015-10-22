<div style="line-height: 30px;">
    <span style="margin-left:30px;"><g:message code="symbol.info.lastPrice"/>:</span>
    <span style="white-space: nowrap">
        <span style="margin-left:30px;font-size:20px;font-weight: bold">
            <g:formatNumber number="${propertyInfo.lastTradePrice}" type="number"/>
        </span>
        <g:set var="lastTradePriceChangePercent"
               value="${Math.round(propertyInfo.priceChange * 10000 / (propertyInfo.lastTradePrice - propertyInfo.priceChange)) / 100}"/>
        <span style="margin-left:30px;color:${lastTradePriceChangePercent > 0 ? 'green' : 'red'}">
            <g:formatNumber number="${propertyInfo.priceChange}" type="number"/>
        </span>
        <span style="color:${lastTradePriceChangePercent > 0 ? 'green' : 'red'}">
            %<g:formatNumber number="${lastTradePriceChangePercent}" type="number"/>
        </span>
    </span>
</div>

<div style="line-height: 30px;">
    <span style="margin-left:30px;"><g:message code="symbol.info.closingPrice"/>:</span>
    <span style="white-space: nowrap">
        <span style="margin-left:30px;">
            <g:formatNumber number="${propertyInfo.closingPrice}" type="number"/></span>
        <g:set var="closingPriceChangePercent"
               value="${Math.round((propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice) * 10000 / (propertyInfo.closingPrice - (propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice))) / 100}"/>
        <span style="margin-left:30px;color:${closingPriceChangePercent > 0 ? 'green' : 'red'}">
            <g:formatNumber
                    number="${propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice}"
                    type="number"/>
        </span>
        <span style="color:${closingPriceChangePercent > 0 ? 'green' : 'red'}">
            %<g:formatNumber number="${closingPriceChangePercent}" type="number"/>
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
            <td><span class="time"><g:formatDate date="${propertyInfo.dailyTrade.date}"
                                                 format="hh:mm:ss"/></span></td>
        </tr>
        <tr>
            <td><span>بیشترین:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.maxPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.minPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>دفعات:</span></td>
            <td><span class="tradecount"><g:formatNumber number="${propertyInfo.totalTradeCount}"
                                                         type="number"/></span></td>
        </tr>
        <tr>
            <td><span>باز:</span></td>
            <td><span class="open"><g:formatNumber number="${propertyInfo.firstTradePrice}"
                                                   type="number"/></span></td>
        </tr>
        <tr>
            <td><span>حجم:</span></td>
            <g:set var="totalTradeVolume" value="${propertyInfo.totalTradeVolume}"/>
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
            <td><span class="shareCount"><g:formatNumber number="${Math.round(totalTradeVolume * 100) / 100}"
                                                         type="number"/>${totalTradeVolumeFlag}</span></td>
        </tr>
        <tr>
            <td><span>ارزش:</span></td>
            <g:set var="totalTradeValue" value="${propertyInfo.totalTradeValue}"/>
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
            <td><span class="volume"><g:formatNumber number="${Math.round(totalTradeValue * 100) / 100}"
                                                     type="number"/>${totalTradeValueFlag}</span></td>
        </tr>
    </table>
</div>