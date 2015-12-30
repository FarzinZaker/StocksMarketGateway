<div class="marketViewItem odd" id="marketView_tradeValue">
    <span class="marketViewItem_label">تاریخ</span>
    <span class="marketViewItem_value"><format:jalaliDate date="${lastDailyTrade.date}"/></span>
</div>

<div class="marketViewItem even">
    <span class="marketViewItem_label">ساعت</span>
    <span class="marketViewItem_value"><g:formatDate date="${lastDailyTrade.dailyTrade.date}" format="hh:mm:ss"/></span>
</div>

<div class="marketViewItem odd">
    <span class="marketViewItem_label">بیشترین</span>
    <span class="marketViewItem_value"><g:formatNumber number="${lastDailyTrade.maxPrice}" type="number"/></span>
</div>

<div class="marketViewItem even">
    <span class="marketViewItem_label">کمترین</span>
    <span class="marketViewItem_value"><g:formatNumber number="${lastDailyTrade.minPrice}" type="number"/></span>
</div>

<div class="marketViewItem odd">
    <span class="marketViewItem_label">دفعات</span>
    <span class="marketViewItem_value"><g:formatNumber number="${lastDailyTrade.totalTradeCount}" type="number"/></span>
</div>

<div class="marketViewItem even">
    <span class="marketViewItem_label">باز</span>
    <span class="marketViewItem_value"><g:formatNumber number="${lastDailyTrade.firstTradePrice}" type="number"/></span>
</div>
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
<div class="marketViewItem odd">
    <span class="marketViewItem_label">حجم</span>
    <span class="marketViewItem_value"><g:formatNumber number="${Math.round(totalTradeVolume * 100) / 100}"
                                                       type="number"/>${totalTradeVolumeFlag}</span>
</div>
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
<div class="marketViewItem even">
    <span class="marketViewItem_label">ارزش</span>
    <span class="marketViewItem_value"><g:formatNumber number="${Math.round(totalTradeValue * 100) / 100}"
                                                       type="number"/>${totalTradeValueFlag}</span>
</div>
