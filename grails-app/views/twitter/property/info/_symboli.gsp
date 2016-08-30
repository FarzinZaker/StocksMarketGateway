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

<table class="table table-striped" id="symbolInfo">
    <tr>
        <td colspan="2"><g:message code="symbol.info.lastTrade" /></td>
        <td class="lastTrade text-left" colspan="2"></td>
    </tr>
    <tr>
        <td class="text-left"><g:message code="symbol.info.closingPrice" /></td>
        <td colspan="3">
            <span class="closingPrice"></span>
            <span class="closingPriceChange small"></span>
            <span class="closingPriceChangePercent small"></span>
        </td>
    </tr>
    <tr>
        <td class="text-left"><g:message code="symbol.info.max" /></td>
        <td class="max"></td>
        <td class="text-left"><g:message code="symbol.info.min" /></td>
        <td class="min"></td>
    </tr>
    <tr>
        <td class="text-left"><g:message code="symbol.info.count" /></td>
        <td class="count"></td>
        <td class="text-left"><g:message code="symbol.info.first" /></td>
        <td class="first"></td>
    </tr>
    <tr>
        <td class="text-left"><g:message code="symbol.info.volume" /></td>
        <td class="volume small"></td>
        <td class="text-left"><g:message code="symbol.info.value" /></td>
        <td class="value small"></td>
    </tr>
</table>
<div class="symbolPriceInfo">
    <div class="line high"><div class="label label-bottom  high-label" ></div></div>
    <div class="line low"><div class="label label-bottom low-label" ></div><div class="label label-bottom  mid-label" ></div></div>
    <div class="line mid"><div class="mid-high-label label-top" ></div><div class="mid-low-label label-top" ></div></div>
    <div class="line cur"><div class="label-top  cur-label" ></div></div>
</div>

<table class="table table-striped" id="clientTypeTable">
    <tr>
        <td></td>
        <td class="center bg-black"><g:message code="symbol.info.bestOrder.buy" /></td>
        <td class="center  bg-black"><g:message code="symbol.info.bestOrder.sell" /></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.individualTotalPrice" /></td>
        <td class="smaller individualTotalPriceBuy"></td>
        <td class="smaller individualTotalPriceSell"></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.legalTotalPrice" /></td>
        <td class="smaller legalTotalPriceBuy"></td>
        <td class="smaller legalTotalPriceSell"></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.individualTotalCount" /></td>
        <td class="smaller individualCountBuy"></td>
        <td class="smaller individualCountSell"></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.legalTotalCount" /></td>
        <td class="smaller legalCountBuy"></td>
        <td class="smaller legalCountSell"></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.individualAvgPrice" /></td>
        <td class="smaller individualAvgPriceBuy"></td>
        <td class="smaller individualAvgPriceSell"></td>
    </tr>
    <tr>
        <td><g:message code="symbol.info.legalAvgPrice" /></td>
        <td class="smaller legalAvgPriceBuy"></td>
        <td class="smaller legalAvgPriceSell"></td>
    </tr>
</table>
<div class="marketViewItem even clear-fix">
    <div class="marketViewItem" id="marketView_totalBuyVolume">
        <span class="marketViewItem_label"><g:message code="marketView.totalBuyVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div style="height:10px;background-color:#3b5998;margin-top:2px;">
        <div style="height:10px;background-color:#d80073;width:0" id="buyIndicator"></div>
    </div>

    <div class="marketViewItem" id="marketView_totalLegalBuyVolume" style="float:right;font-size:11px;">
        <span class="marketViewItem_label" style="margin-left:5px;"><g:message
                code="marketView.totalLegalBuyVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div class="marketViewItem" id="marketView_totalIndividualBuyVolume" style="float:left;font-size:11px;">
        <span class="marketViewItem_value" style="margin-left:5px">-</span>
        <span class="marketViewItem_label" style="margin-left:0;"><g:message
                code="marketView.totalIndividualBuyVolume"/></span>
    </div>

    <div class="clear-fix"></div>
</div>

<div class="marketViewItem even clear-fix">
    <div class="marketViewItem clear-fix" id="marketView_totalSellVolume">
        <span class="marketViewItem_label"><g:message code="marketView.totalSellVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div style="height:10px;background-color:#3b5998;margin-top:2px;">
        <div style="height:10px;background-color:#d80073;width:0" id="sellIndicator"></div>
    </div>

    <div class="marketViewItem" id="marketView_totalLegalSellVolume" style="float:right;font-size:11px;">
        <span class="marketViewItem_label" style="margin-left:5px"><g:message
                code="marketView.totalLegalSellVolume"/></span>
        <span class="marketViewItem_value">-</span>
    </div>

    <div class="marketViewItem" id="marketView_totalIndividualSellVolume" style="float:left;font-size:11px;">
        <span class="marketViewItem_value" style="margin-left:5px;">-</span>
        <span class="marketViewItem_label" style="margin-left:0"><g:message
                code="marketView.totalIndividualSellVolume"/></span>
    </div>

    <div class="clear-fix"></div>
</div>


<script>
    function strNum(x){
        if(x){
            if(x>1000000000)
                return [Math.round(x/10000000)/100,' <g:message code="milyard" />'];
            if(x>1000000)
                return [Math.round(x/10000)/100,' <g:message code="milyon" />'];
            if(x>1000)
                return [Math.round(x/10)/100,' <g:message code="hezar" />'];
            return [x,''];
        }
    }
    function numberWithCommas(x) {
        if(x)
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

            $('#symbolInfo .lastTrade').html(data.symbolStatus.lastTrade);
            $('#symbolInfo .closingPrice').html(numberWithCommas(data.symbolStatus.closingPrice));
            var change=data.symbolStatus.closingPrice+data.symbolStatus.priceChange-data.symbolStatus.last;
            $('#symbolInfo .closingPriceChange').html(numberWithCommas(change)).addClass(change>0?'green':'red');
            var changePercent=Math.round((data.symbolStatus.closingPrice+data.symbolStatus.priceChange-data.symbolStatus.last)*10000/(data.symbolStatus.last-data.symbolStatus.priceChange))/100;
            $('#symbolInfo .closingPriceChangePercent').html(numberWithCommas(changePercent)+'%').addClass(changePercent>0?'green':'red');

            $('#symbolInfo .max').html(numberWithCommas(data.symbolStatus.max));
            $('#symbolInfo .min').html(numberWithCommas(data.symbolStatus.min));
            $('#symbolInfo .count').html(numberWithCommas(data.symbolStatus.count));
            $('#symbolInfo .first').html(numberWithCommas(data.symbolStatus.first));
            var value=strNum(data.symbolStatus.value);
            $('#symbolInfo .value').html(numberWithCommas(value[0])+value[1]);
            var volume=strNum(data.symbolStatus.volume);
            $('#symbolInfo .volume').html(numberWithCommas(volume[0])+volume[1]);

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


            var totalVolume=data.symbolClientType.individualBuyVolume + data.symbolClientType.legalBuyVolume;
            $('#clientTypeTable .individualTotalPriceBuy').html(numberWithCommas(Math.round(data.symbolClientType.individualBuyVolume*data.symbolStatus.totalValue/totalVolume)));
            $('#clientTypeTable .individualTotalPriceSell').html(numberWithCommas(Math.round(data.symbolClientType.individualSellVolume*data.symbolStatus.totalValue/totalVolume)));
            $('#clientTypeTable .individualCountBuy').html(numberWithCommas(Math.round(data.symbolClientType.individualBuyCount)));
            $('#clientTypeTable .individualCountSell').html(numberWithCommas(Math.round(data.symbolClientType.individualSellCount)));
            $('#clientTypeTable .individualAvgPriceBuy').html(numberWithCommas(Math.round(data.symbolClientType.individualBuyVolume*data.symbolStatus.totalValue/(totalVolume*data.symbolClientType.individualBuyCount))));
            $('#clientTypeTable .individualAvgPriceSell').html(numberWithCommas(Math.round(data.symbolClientType.individualSellVolume*data.symbolStatus.totalValue/(totalVolume*data.symbolClientType.individualSellCount))));
            $('#clientTypeTable .legalTotalPriceBuy').html(numberWithCommas(Math.round(data.symbolClientType.legalBuyVolume*data.symbolStatus.totalValue/totalVolume)));
            $('#clientTypeTable .legalTotalPriceSell').html(numberWithCommas(Math.round(data.symbolClientType.legalSellVolume*data.symbolStatus.totalValue/totalVolume)));
            $('#clientTypeTable .legalCountBuy').html(numberWithCommas(Math.round(data.symbolClientType.legalBuyCount)));
            $('#clientTypeTable .legalCountSell').html(numberWithCommas(Math.round(data.symbolClientType.legalSellCount)));
            $('#clientTypeTable .legalAvgPriceBuy').html(numberWithCommas(Math.round(data.symbolClientType.legalBuyVolume*data.symbolStatus.totalValue/(totalVolume*data.symbolClientType.legalBuyCount))));
            $('#clientTypeTable .legalAvgPriceSell').html(numberWithCommas(Math.round(data.symbolClientType.legalSellVolume*data.symbolStatus.totalValue/(totalVolume*data.symbolClientType.legalSellCount))));

            $('#marketView_totalBuyVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.individualBuyVolume + data.symbolClientType.legalBuyVolume));
            $('#marketView_totalSellVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.individualSellVolume + data.symbolClientType.legalSellVolume));
            $('#marketView_totalIndividualBuyVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.individualBuyVolume));
            $('#marketView_totalIndividualSellVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.individualSellVolume));
            $('#marketView_totalLegalBuyVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.legalBuyVolume));
            $('#marketView_totalLegalSellVolume').find('.marketViewItem_value').html(numberWithCommas(data.symbolClientType.legalSellVolume));
            $('#buyIndicator').css('width', Math.round(data.symbolClientType.legalBuyVolume * 100 / (data.symbolClientType.legalBuyVolume + data.symbolClientType.individualBuyVolume)) + '%');
            $('#sellIndicator').css('width', Math.round(data.symbolClientType.legalSellVolume * 100 / (data.symbolClientType.legalSellVolume + data.symbolClientType.individualSellVolume)) + '%');


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