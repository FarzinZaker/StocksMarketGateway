<div class="k-rtl">
    <ul id="menu">
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
        <g:render template="/layouts/common/menu"/>
    </ul>
</div>

<script>
    $(document).ready(function () {
        $("#menu").kendoMenu();
    });
</script>