<ul id="menu" class="hideBeforeLoad">
    <li>
        <a href="${createLink(uri: '/')}">
            <i class="fa fa-home"></i> <g:message code="menu.home"/>
        </a>
    </li>
    <li>
        <g:message code="menu.twitter"/>
        <ul>
            <li>
                <a href="${createLink(controller: 'user', action: 'home')}"><g:message
                        code="menu.twitter.home"/></a>
            </li>
            <li class="k-separator">
            </li>
            <li><a href="${createLink(controller: 'article', action: 'create')}"><g:message
                    code="menu.twitter.articles.add"/></a></li>
            <li><a href="${createLink(controller: 'article', action: 'list')}"><g:message
                    code="menu.twitter.articles.list"/></a></li>
            <li class="k-separator">
            </li>
            <li>
                <a href="${createLink(controller: 'group', action: 'list')}"><g:message
                        code="menu.twitter.groups"/></a>
            </li>
            <li>
                <a href="${createLink(controller: 'group', action: 'select')}"><g:message
                        code="menu.twitter.groupSelect"/></a>
            </li>

        </ul>
    </li>
    <li>
        <g:message code="menu.newsletter.register"/>
        <ul>
            <li><a href="${createLink(controller: 'query', action: 'select')}"><g:message
                    code="menu.newsletter.register.list"/></a></li>
            <li><a href="${createLink(controller: 'query', action: 'instanceList')}"><g:message
                    code="menu.newsletter.register.my"/></a>
            </li>
        </ul>
    </li>
    <li>
        <g:message code="menu.portfolios"/>
        <ul>
            <li><a href="${createLink(controller: 'portfolio', action: 'list')}"><g:message
                    code="menu.portfolios.list"/></a></li>
            <portfolio:menu/>
        </ul>
    </li>
    <li>
        <g:message code="menu.tools"/>
        <ul>
            <li><a href="${createLink(controller: 'tools', action: 'calculator')}"><g:message
                    code="menu.tools.calculator"/></a></li>
            <li><a href="${createLink(controller: 'tools', action: 'correlation')}"><g:message
                    code="menu.tools.correlation"/></a></li>
        </ul>
    </li>
    <li>
        <g:message code="menu.reports"/>
        <ul>
            <li><a href="${createLink(controller: 'report', action: 'heatMap')}"><g:message
                    code="menu.reports.heatMap"/></a></li>
        </ul>
    </li>
    <li>
        <g:message code="menu.screener"/>
        <ul>
            <li><a href="${createLink(controller: 'screener', action: 'list')}"><g:message
                    code="menu.screener.list"/></a></li>
            <li><a href="${createLink(controller: 'screener', action: 'build')}"><g:message
                    code="menu.screener.new"/></a></li>
        </ul>
    </li>
    <li>
        <g:message code="menu.strategy"/>
        <ul>
            <li><a href="${createLink(controller: 'tradeStrategy', action: 'list')}"><g:message
                    code="menu.strategy.list"/></a></li>
            <li><a href="${createLink(controller: 'tradeStrategy', action: 'build')}"><g:message
                    code="menu.strategy.new"/></a></li>
        </ul>
    </li>
    <g:render template="/layouts/common/menu"/>
</ul>

<script>
    $(document).ready(function () {
        $("#menu").kendoMenu();
    });
</script>