<li>
    <g:message code="menu.currentUser"/>
    <ul>
        <sec:ifLoggedIn>
            <li><a href="${createLink(controller: 'user', action: 'changePassword')}"><g:message code="menu.currentUser.changePassword"/></a></li>
            <li><a href="${createLink(controller: 'logout')}"><g:message code="menu.currentUser.logout"/></a></li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li><a href="${createLink(controller: 'login')}"><g:message code="menu.currentUser.login"/></a></li>
            <li><a href="${createLink(controller: 'user', action: 'register')}"><g:message code="menu.currentUser.register"/></a></li>
        </sec:ifNotLoggedIn>
    </ul>
</li>