<g:each in="${symbols}" var="symbol" status="indexer">
    <g:if test="${indexer % 2 == 0}">
        <div class="col-sm-3">
            <div class="row">
    </g:if>
    <div class="col-sm-6 ${symbol.priceChangePercent > 0 ? 'green' : 'red'}">
        <g:if test="${symbol.priceChangePercent > 0}">
            <i class="fa fa-caret-up"></i>
        </g:if>
        <g:else>
            <i class="fa fa-caret-down"></i>
        </g:else>
        <span>
            ${symbol.symbolPersianCode}
        </span>
        <span>
            ${Math.round(symbol.priceChangePercent * 10000) / 100}%
        </span>
    </div>
    <g:if test="${indexer % 2 == 1}">
        </div>
    </div>
    </g:if>
</g:each>