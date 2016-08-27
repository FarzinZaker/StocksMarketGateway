<table class="table center table-bordered table-best-order">
    <tr>
        <td colspan="3" class="buy"><g:message code="symbol.info.bestOrder.buy" /></td>
        <td colspan="3" class="sell"><g:message code="symbol.info.bestOrder.sell" /></td>
    </tr>
    <tr>
        <td class="buy"><g:message code="symbol.info.bestOrder.count"/></td>
        <td class="buy"><g:message code="symbol.info.bestOrder.volume"/></td>
        <td class="buy"><g:message code="symbol.info.bestOrder.price"/></td>
        <td class="sell"><g:message code="symbol.info.bestOrder.price"/></td>
        <td class="sell"><g:message code="symbol.info.bestOrder.volume"/></td>
        <td class="sell"><g:message code="symbol.info.bestOrder.count"/></td>
    </tr>
    <g:each in="${(1..3)}">
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    </g:each>
</table>

<g:set var="lastTradePriceChangePercent"
       value="${Math.round(propertyInfo.priceChange * 10000 / (propertyInfo.lastTradePrice - propertyInfo.priceChange)) / 100}"/>
<div class="propertyInfoPrice ${lastTradePriceChangePercent > 0 ? 'positive' : lastTradePriceChangePercent < 0 ? 'negative' : ''}">
    <h4><g:message code="symbol.info.lastPrice"/></h4>
    <span>
        <b><g:formatNumber number="${propertyInfo.lastTradePrice}" type="number"/></b>
    </span>
    <span>
        <g:formatNumber number="${propertyInfo.priceChange}" type="number"/>
    </span>
    <span>
        <g:formatNumber number="${lastTradePriceChangePercent}" type="number"/>%
    </span>
    <div class="clear-fix"></div>
</div>
<g:set var="closingPriceChangePercent"
       value="${Math.round((propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice) * 10000 / (propertyInfo.closingPrice - (propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice))) / 100}"/>
<div class="propertyInfoPrice ${closingPriceChangePercent > 0 ? 'positive' : closingPriceChangePercent < 0 ? 'negative' : 0}">
    <h4><g:message code="symbol.info.closingPrice"/></h4>
    <span>
        <g:formatNumber number="${propertyInfo.closingPrice}" type="number"/>
    </span>
    <span>
        <g:formatNumber
                number="${propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice}"
                type="number"/>
    </span>
    <span>
        <g:formatNumber number="${closingPriceChangePercent}" type="number"/>%
    </span>
    <div class="clear-fix"></div>
</div>

<div class="dashLet magenta propertyInfo">
    <table>
        <tr>
            <td><span>تاریخ</span></td>
            <td><span class="date"><format:jalaliDate date="${propertyInfo.date}"/></span></td>
        </tr>
        <tr>
            <td><span>ساعت</span></td>
            <td><span class="time"><g:formatDate date="${propertyInfo.dailyTrade.date}"
                                                 format="hh:mm:ss"/></span></td>
        </tr>
        <tr>
            <td><span>بیشترین</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.maxPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.minPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>دفعات</span></td>
            <td><span class="tradecount"><g:formatNumber number="${propertyInfo.totalTradeCount}"
                                                         type="number"/></span></td>
        </tr>
        <tr>
            <td><span>باز</span></td>
            <td><span class="open"><g:formatNumber number="${propertyInfo.firstTradePrice}"
                                                   type="number"/></span></td>
        </tr>
        <tr>
            <td><span>حجم</span></td>
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
            <td><span>ارزش</span></td>
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