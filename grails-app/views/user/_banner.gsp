<div class="user-banner">
    <div class="meta">
        <img class="avatar"
             src="${createLink(controller: 'image', id: user?.image?.id, params: [size: 200, default: "user-${user?.sex ?: 'male'}"])}"/>

    </div>

    <div class="data">
        <h2>${user}</h2>

        <sec:ifLoggedIn>
            <div class="buttons">
                <userInfo:follow user="${user}"/>
            </div>
        </sec:ifLoggedIn>

        <g:render template="/user/badgeList" model="${[user: user]}"/>
    </div>

    <div class="clear-fix"></div>
</div>