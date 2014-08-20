<ul class="user-panel">
    <sec:ifLoggedIn>
        <li><a href="${createLink(controller: 'user', action: 'changePassword')}"><g:message code="menu.currentUser.changePassword"/> <i class="glyphicon glyphicon-cog"></i></a></li>
        <li><a href="${createLink(controller: 'logout')}"><g:message code="menu.currentUser.logout"/> <i class="glyphicon glyphicon-log-out"></i></a></li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li><a href="${createLink(controller: 'login')}"><g:message code="menu.currentUser.login"/> <i class="glyphicon glyphicon-log-in"></i></a></li>
        <li><a href="${createLink(controller: 'user', action: 'register')}"><g:message code="menu.currentUser.register"/> <i class="glyphicon glyphicon-plus"></i></a></li>
    </sec:ifNotLoggedIn>
</ul>