<div class="k-rtl">
    <ul id="menu">
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