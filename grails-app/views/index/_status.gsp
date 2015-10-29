<table style="width: 100%;line-height:30px;">
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>تاریخ:</span></td>
        <td><span dir="ltr" class="date"><format:jalaliDate date="${index.date}"/></span></td>
        <td><span>ساعت:</span></td>
        <td><span dir="ltr" class="time"><g:formatDate date="${index.date}" format="hh:mm:ss"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>بیشترین:</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${index.highestIndexValue}" type="number"/></span></td>
        <td><span>کمترین:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${index.lowestIndexValue}" type="number"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>معامله شده</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${index.tradedSymbolCount}" type="number"/></span></td>
        <td><span>کاهش یافته:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${index.decreasedSymbolCount}" type="number"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>افزایش یافته:</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${index.increasedSymbolCount}" type="number"/></span></td>
        <td><span>بدون تغییر:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${index.unchangedSymbolCount}" type="number"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>معامله نشده:</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${index.notTradedSymbolCount}" type="number"/></span></td>
        <td><span>رزور شده:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${index.reservedSymbolCount}" type="number"/></span></td>
    </tr>
    <tr style="border-bottom:1px dashed #d9dadb;">
        <td><span>مسدود شده:</span></td>
        <td><span dir="ltr" class="high"><g:formatNumber number="${index.suspendedSymbolCount}" type="number"/></span></td>
        <td><span>کل نمادها:</span></td>
        <td><span dir="ltr" class="low"><g:formatNumber number="${index.totalSymbolCount}" type="number"/></span></td>
    </tr>
</table>