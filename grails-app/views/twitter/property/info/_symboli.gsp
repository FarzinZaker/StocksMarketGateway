<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>
<div id="symbolInfoTimer"></div>
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
        <td class="buy-info buy-count-${it}">-</td>
        <td class="buy-info buy-volume-${it}">-</td>
        <td class="buy-info buy-price-${it}">-</td>
        <td class="sell-info sell-price-${it}">-</td>
        <td class="sell-info sell-volume-${it}">-</td>
        <td class="sell-info sell-count-${it}">-</td>
    </tr>
    </g:each>
</table>

<div class="symbolPriceInfo">
    <div class="line high"><div class="label label-bottom  high-label" ></div></div>
    <div class="line low"><div class="label label-bottom low-label" ></div><div class="label label-bottom  mid-label" ></div></div>
    <div class="line mid"><div class="mid-high-label label-top" ></div><div class="mid-low-label label-top" ></div></div>
    <div class="line cur"><div class="label-top  cur-label" ></div></div>
</div>

%{--<g:set var="lastTradePriceChangePercent"--}%
       %{--value="${Math.round(propertyInfo.priceChange * 10000 / (propertyInfo.lastTradePrice - propertyInfo.priceChange)) / 100}"/>--}%
%{--<div class="propertyInfoPrice ${lastTradePriceChangePercent > 0 ? 'positive' : lastTradePriceChangePercent < 0 ? 'negative' : ''}">--}%
    %{--<h4><g:message code="symbol.info.lastPrice"/></h4>--}%
    %{--<span>--}%
        %{--<b><g:formatNumber number="${propertyInfo.lastTradePrice}" type="number"/></b>--}%
    %{--</span>--}%
    %{--<span>--}%
        %{--<g:formatNumber number="${propertyInfo.priceChange}" type="number"/>--}%
    %{--</span>--}%
    %{--<span>--}%
        %{--<g:formatNumber number="${lastTradePriceChangePercent}" type="number"/>%--}%
    %{--</span>--}%
    %{--<div class="clear-fix"></div>--}%
%{--</div>--}%
%{--<g:set var="closingPriceChangePercent"--}%
       %{--value="${Math.round((propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice) * 10000 / (propertyInfo.closingPrice - (propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice))) / 100}"/>--}%
%{--<div class="propertyInfoPrice ${closingPriceChangePercent > 0 ? 'positive' : closingPriceChangePercent < 0 ? 'negative' : 0}">--}%
    %{--<h4><g:message code="symbol.info.closingPrice"/></h4>--}%
    %{--<span>--}%
        %{--<g:formatNumber number="${propertyInfo.closingPrice}" type="number"/>--}%
    %{--</span>--}%
    %{--<span>--}%
        %{--<g:formatNumber--}%
                %{--number="${propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice}"--}%
                %{--type="number"/>--}%
    %{--</span>--}%
    %{--<span>--}%
        %{--<g:formatNumber number="${closingPriceChangePercent}" type="number"/>%--}%
    %{--</span>--}%
    %{--<div class="clear-fix"></div>--}%
%{--</div>--}%

%{--<div class="dashLet magenta propertyInfo">--}%
    %{--<table>--}%
        %{--<tr>--}%
            %{--<td><span>تاریخ</span></td>--}%
            %{--<td><span class="date"><format:jalaliDate date="${propertyInfo.date}"/></span></td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>ساعت</span></td>--}%
            %{--<td><span class="time"><g:formatDate date="${propertyInfo.dailyTrade.date}"--}%
                                                 %{--format="hh:mm:ss"/></span></td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>بیشترین</span></td>--}%
            %{--<td><span class="high"><g:formatNumber number="${propertyInfo.maxPrice}" type="number"/></span>--}%
            %{--</td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>کمترین</span></td>--}%
            %{--<td><span class="low"><g:formatNumber number="${propertyInfo.minPrice}" type="number"/></span>--}%
            %{--</td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>دفعات</span></td>--}%
            %{--<td><span class="tradecount"><g:formatNumber number="${propertyInfo.totalTradeCount}"--}%
                                                         %{--type="number"/></span></td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>باز</span></td>--}%
            %{--<td><span class="open"><g:formatNumber number="${propertyInfo.firstTradePrice}"--}%
                                                   %{--type="number"/></span></td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>حجم</span></td>--}%
            %{--<g:set var="totalTradeVolume" value="${propertyInfo.totalTradeVolume}"/>--}%
            %{--<g:set var="totalTradeVolumeFlag" value=""/>--}%
            %{--<g:if test="${totalTradeVolume > 1000}">--}%
                %{--<g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>--}%
                %{--<g:set var="totalTradeVolumeFlag" value="K"/>--}%
            %{--</g:if>--}%
            %{--<g:if test="${totalTradeVolume > 1000}">--}%
                %{--<g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>--}%
                %{--<g:set var="totalTradeVolumeFlag" value="M"/>--}%
            %{--</g:if>--}%
            %{--<g:if test="${totalTradeVolume > 1000}">--}%
                %{--<g:set var="totalTradeVolume" value="${totalTradeVolume / 1000}"/>--}%
                %{--<g:set var="totalTradeVolumeFlag" value="B"/>--}%
            %{--</g:if>--}%
            %{--<td><span class="shareCount"><g:formatNumber number="${Math.round(totalTradeVolume * 100) / 100}"--}%
                                                         %{--type="number"/>${totalTradeVolumeFlag}</span></td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td><span>ارزش</span></td>--}%
            %{--<g:set var="totalTradeValue" value="${propertyInfo.totalTradeValue}"/>--}%
            %{--<g:set var="totalTradeValueFlag" value=""/>--}%
            %{--<g:if test="${totalTradeValue > 1000}">--}%
                %{--<g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>--}%
                %{--<g:set var="totalTradeValueFlag" value="K"/>--}%
            %{--</g:if>--}%
            %{--<g:if test="${totalTradeValue > 1000}">--}%
                %{--<g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>--}%
                %{--<g:set var="totalTradeValueFlag" value="M"/>--}%
            %{--</g:if>--}%
            %{--<g:if test="${totalTradeValue > 1000}">--}%
                %{--<g:set var="totalTradeValue" value="${totalTradeValue / 1000}"/>--}%
                %{--<g:set var="totalTradeValueFlag" value="B"/>--}%
            %{--</g:if>--}%
            %{--<td><span class="volume"><g:formatNumber number="${Math.round(totalTradeValue * 100) / 100}"--}%
                                                     %{--type="number"/>${totalTradeValueFlag}</span></td>--}%
        %{--</tr>--}%
    %{--</table>--}%
%{--</div>--}%

<script>
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    function updateInfo(){
        $.get('<g:createLink controller="twitter" action="symbolInfoAjax" id="${property?.identifier}" />').success(function(data){
            for(var i in data.bestOrders){
                var bestOrder=data.bestOrders[i];
                $('.buy-count-'+bestOrder.number).html(bestOrder.orderCount?numberWithCommas(bestOrder.orderCount):'-');
                $('.buy-volume-'+bestOrder.number).html(bestOrder.orderVolume?numberWithCommas(bestOrder.orderVolume):'-');
                $('.buy-price-'+bestOrder.number).html(bestOrder.orderValue?numberWithCommas(bestOrder.orderValue):'-');
                $('.sell-count-'+bestOrder.number).html(bestOrder.offerCount?numberWithCommas(bestOrder.offerCount):'-');
                $('.sell-volume-'+bestOrder.number).html(bestOrder.offerVolume?numberWithCommas(bestOrder.offerVolume):'-');
                $('.sell-price-'+bestOrder.number).html(bestOrder.offerValue?numberWithCommas(bestOrder.offerValue):'-');
            }
            var len=data.symbolStatus.maxAllowed-data.symbolStatus.minAllowed;
            $('.symbolPriceInfo .low').css('width',((data.symbolStatus.yesterday-data.symbolStatus.minAllowed)/len)*100+'%');
            $('.symbolPriceInfo .high').css('width',((data.symbolStatus.maxAllowed-data.symbolStatus.yesterday)/len)*100+'%');
            $('.symbolPriceInfo .cur').css('left',((data.symbolStatus.last-data.symbolStatus.minAllowed)/len)*100+'%');
            $('.symbolPriceInfo .mid').css('left',((data.symbolStatus.min-data.symbolStatus.minAllowed)/len)*100+'%')
                    .css('right',((data.symbolStatus.maxAllowed-data.symbolStatus.max)/len)*100+'%');
            $('.symbolPriceInfo .low-label').html(numberWithCommas(data.symbolStatus.minAllowed));
            $('.symbolPriceInfo .high-label').html(numberWithCommas(data.symbolStatus.maxAllowed));
            $('.symbolPriceInfo .mid-label').html(numberWithCommas(data.symbolStatus.yesterday));
            $('.symbolPriceInfo .mid-high-label').html(numberWithCommas(data.symbolStatus.max));
            $('.symbolPriceInfo .mid-low-label').html(numberWithCommas(data.symbolStatus.min));
            $('.symbolPriceInfo .cur-label').html(numberWithCommas(data.symbolStatus.last));
            $('#symbolInfoTimer').timer('start');
        })
    }
    $(function(){
        $('#symbolInfoTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#symbolInfoTimer').timer('stop');
                updateInfo();
            }
        });
        updateInfo();
    });
</script>