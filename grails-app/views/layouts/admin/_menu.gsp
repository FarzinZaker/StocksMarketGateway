<div class="k-rtl">
    <ul id="menu">
        <li>
            <g:message code="menu.articles"/>
            <ul>
                <li><a href="${createLink(controller: 'article', action: 'create')}"><g:message
                        code="menu.articles.add"/></a></li>
                <li><a href="${createLink(controller: 'article', action: 'list')}"><g:message
                        code="menu.articles.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="menu.newsletters"/>
            <ul>
                <li><a href="${createLink(controller: 'queryCategory', action: 'build')}"><g:message
                        code="menu.category.add"/></a></li>
                <li><a href="${createLink(controller: 'scheduleTemplate', action: 'list')}"><g:message
                        code="menu.category.list"/></a></li>
            <li><a href="${createLink(controller: 'scheduleTemplate', action: 'build')}"><g:message
                    code="menu.scheduleTemplate.add"/></a></li>
                <li><a href="${createLink(controller: 'queryCategory', action: 'list')}"><g:message
                        code="menu.scheduleTemplate.list"/></a></li>
                <li><a href="${createLink(controller: 'query', action: 'selectData')}"><g:message
                        code="menu.newsletters.add"/></a></li>
                <li><a href="${createLink(controller: 'query', action: 'list')}"><g:message
                        code="menu.newsletters.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="menu.users"/>
            <ul>
                <li><g:message code="menu.users.add"/></li>
                <li><g:message code="menu.users.list"/></li>
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