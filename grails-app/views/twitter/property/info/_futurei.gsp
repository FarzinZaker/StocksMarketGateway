<div class="propertyInfoPrice ${propertyInfo.lastTradedPriceChanges > 0 ? 'positive' : propertyInfo.lastTradedPriceChanges > 0 ? 'negative' : ''}">
    <h4><g:message code="symbol.info.lastPrice"/></h4>
    <span>
        <b><g:formatNumber number="${propertyInfo.lastTradedPrice}" type="number"/></b>
    </span>
    <span>
        <g:formatNumber number="${propertyInfo.lastTradedPriceChanges}" type="number"/>
    </span>
    <span>
        <g:formatNumber number="${propertyInfo.lastTradedPriceChangesPercent}" type="number"/>%
    </span>
    <div class="clear-fix"></div>
</div>

<div class="propertyInfoPrice">
    <h4><g:message code="symbol.info.closingPrice"/></h4>
    <span>
        <g:formatNumber number="${propertyInfo.closingPrice}" type="number"/>
    </span>
    <div class="clear-fix"></div>
</div>

<div class="dashLet magenta propertyInfo">
    <table>
        <tr>
            <td><span>تاریخ:</span></td>
            <td><span class="date"><format:jalaliDate date="${propertyInfo.lastTradingDate}"/></span></td>
        </tr>
        <tr>
            <td><span>ساعت:</span></td>
            <td><span class="time"><g:formatDate date="${propertyInfo.lastTradingDate}"
                                                 format="hh:mm:ss"/></span></td>
        </tr>
        <tr>
            <td><span>بیشترین:</span></td>
            <td><span class="high"><g:formatNumber number="${propertyInfo.highTradedPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>کمترین:</span></td>
            <td><span class="low"><g:formatNumber number="${propertyInfo.lowTradedPrice}" type="number"/></span>
            </td>
        </tr>
        <tr>
            <td><span>دفعات:</span></td>
            <td><span class="tradecount"><g:formatNumber number="${propertyInfo.tradesCount}"
                                                         type="number"/></span></td>
        </tr>
        <tr>
            <td><span>باز:</span></td>
            <td><span class="open"><g:formatNumber number="${propertyInfo.firstTradedPrice}"
                                                   type="number"/></span></td>
        </tr>
        <tr>
            <td><span>حجم:</span></td>
            <g:set var="totalTradeVolume" value="${propertyInfo.tradesVolume}"/>
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
            <g:set var="totalTradeValue" value="${propertyInfo.tradesValue}"/>
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