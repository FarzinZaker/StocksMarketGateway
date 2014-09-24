<layout:panel class="user-card" title="${user}">
    <div class="meta">
        <img class="avatar"
             src="${createLink(controller: 'image', id: user?.image?.id, params: [size: 100, default: "user-${user?.sex ?: 'male'}"])}"/>

    </div>

    <div class="data">

        <g:render template="/user/badgeList" model="${[user: user]}"/>

        <sec:ifLoggedIn>
            <div class="buttons">
                <userInfo:wall user="${user}"/>
                <userInfo:follow user="${user}"/>
            </div>
        </sec:ifLoggedIn>
    </div>

    <div class="clear-fix"></div>
</layout:panel>