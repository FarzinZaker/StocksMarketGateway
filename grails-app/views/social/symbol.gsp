<g:set var="lastTradePriceChangePercent" value="${Math.round(lastDailyTrade.priceChange * 10000 / (lastDailyTrade.lastTradePrice - lastDailyTrade.priceChange)) / 100}"/>
<g:set var="closingPriceChangePercent"
       value="${Math.round((lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice) * 10000 / (lastDailyTrade.closingPrice - (lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice))) / 100}"/>
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

 ⚡️ ${symbol?.toString()?.replace('\u200C', '')}

<g:message code="symbol.info.lastPrice"/>: <g:if test="${lastDailyTrade.priceChange != 0}">
</g:if><g:formatNumber number="${lastDailyTrade.lastTradePrice}" type="number"/> <g:if test="${lastDailyTrade.priceChange != 0}"><g:if test="${lastDailyTrade.priceChange > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${lastDailyTrade.priceChange}" type="number"/> (<g:formatNumber number="${lastTradePriceChangePercent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

<g:message code="symbol.info.closingPrice"/>: <g:if test="${closingPriceChangePercent != 0}">
</g:if><g:formatNumber number="${lastDailyTrade.closingPrice}" type="number"/> <g:if test="${closingPriceChangePercent != 0}"><g:if test="${closingPriceChangePercent > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${lastDailyTrade.priceChange + lastDailyTrade.closingPrice - lastDailyTrade.lastTradePrice}" type="number"/> (<g:formatNumber number="${closingPriceChangePercent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

بیشترین: <g:formatNumber number="${lastDailyTrade.maxPrice}" type="number"/>
کمترین: <g:formatNumber number="${lastDailyTrade.minPrice}" type="number"/>
باز شده: <g:formatNumber number="${lastDailyTrade.firstTradePrice}" type="number"/>
تعداد معاملات: <g:formatNumber number="${lastDailyTrade.totalTradeCount}" type="number"/>
حجم کل معاملات: <g:formatNumber number="${Math.round(totalTradeVolume * 100)/100}" type="number"/>${totalTradeVolumeFlag}
ارزش کل معاملات: <g:formatNumber number="${Math.round(totalTradeValue * 100)/100}" type="number"/>${totalTradeValueFlag}

🕒<format:jalaliDate date="${date}"/> <g:formatDate date="${date}" format="hh:mm"/>

🌍 http://www.4tablo.ir
📱 @ChaharTabloBot