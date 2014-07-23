<div class="k-rtl">
    <ul id="menu">
        <li>
            <g:message code="admin.menu.articles"/>
            <ul>
                <li><a href="${createLink(controller: 'article', action: 'create')}"><g:message code="admin.menu.articles.add"/></a></li>
                <li><a href="${createLink(controller: 'article', action: 'list')}"><g:message code="admin.menu.articles.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="admin.menu.users"/>
            <ul>
            <li><g:message code="admin.menu.users.add"/></li>
                <li><g:message code="admin.menu.users.list"/></li>
            </ul>
        </li>
        <li>
            <g:message code="admin.menu.currentUser"/>
            <ul>
                <li><g:message code="admin.menu.currentUser.changePassword"/></li>
                <li><a href="${createLink(controller: 'logout')}"><g:message code="admin.menu.currentUser.logout"/></a></li>
            </ul>
        </li>
    </ul>
</div>

<script>
    $(document).ready(function() {
        $("#menu").kendoMenu();
    });
</script>