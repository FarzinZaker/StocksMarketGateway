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
        <g:render template="/layouts/common/menu"/>
    </ul>
</div>

<script>
    $(document).ready(function () {
        $("#menu").kendoMenu();
    });
</script>