<div class="row">
    <div class="col-sm-4">
        <a href="${createLink(controller: 'screener', action: 'list')}" class="toolButton">
            <i class="fa fa-th-list"></i>

            <h3><g:message code="dashboard.screener"/></h3>

            <p><g:message code="dashboard.screener.description"/></p>
        </a>
    </div>

    <div class="col-sm-4">
        <a href="${createLink(controller: 'tradeStrategy', action: 'list')}" class="toolButton">
            <i class="fa fa-magic"></i>

            <h3><g:message code="dashboard.backTest"/></h3>

            <p><g:message code="dashboard.backTest.description"/></p>
        </a>
    </div>

    <div class="col-sm-4">
        <a href="${createLink(controller: 'query', action: 'instanceList')}" class="toolButton">
            <i class="fa fa-paper-plane-o"></i>

            <h3><g:message code="dashboard.alerting"/></h3>

            <p><g:message code="dashboard.alerting.description"/></p>
        </a>
    </div>
</div>

<div class="row">

    <div class="col-sm-4">
        <a href="${createLink(controller: 'portfolio', action: 'list')}" class="toolButton">
            <i class="fa fa-shopping-cart"></i>

            <h3><g:message code="dashboard.portfolio"/></h3>

            <p><g:message code="dashboard.portfolio.description"/></p>
        </a>
    </div>

    <div class="col-sm-4">
        <a href="${createLink(controller: 'tools', action: 'calculator')}" class="toolButton">
            <i class="fa fa-calculator"></i>

            <h3><g:message code="dashboard.calculator"/></h3>

            <p><g:message code="dashboard.calculator.description"/></p>
        </a>
    </div>

    <div class="col-sm-4">
        <a href="${createLink(controller: 'tools', action: 'correlation')}" class="toolButton">
            <i class="fa fa-link"></i>

            <h3><g:message code="dashboard.correlation"/></h3>

            <p><g:message code="dashboard.correlation.description"/></p>
        </a>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $('.toolButton').mouseenter(function () {
        $(this).find('i').stop().css('font-size', '1px').animate({
            fontSize: '62px'
        }, 500, function () {
            $(this).stop().animate({
                fontSize: '54px'
            }, 200, function () {
                $(this).stop().animate({
                    fontSize: '58px'
                }, 100);
            })
        });
    }).mouseleave(function () {
        $(this).find('i').stop().css('font-size', '58px');
    });
</script>