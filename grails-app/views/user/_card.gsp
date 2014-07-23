<layout:panel class="user-card" title="${user}">
    <div class="meta">
        <img class="avatar"
             src="${createLink(controller: 'image', id: user?.image?.id, params: [size: 100, default: "user-${user?.sex ?: 'male'}"])}"/>

    </div>

    <div class="data">

        <div class="report">
            <g:message code="rate.summary"
                       args="${[userInfo.rateAverage(user: user), userInfo.articleCount(user: user), userInfo.newsCount(user: user)]}"/>
        </div>

        <div class="report">
            <g:message code="like.summary"
                       args="${[userInfo.likeCount(user: user), userInfo.dislikeCount(user: user), userInfo.commentCount(user: user)]}"/>
        </div>

        <sec:ifLoggedIn>
            <div class="buttons">
                <userInfo:wall user="${user}"/>
                <userInfo:follow user="${user}"/>
            </div>
        </sec:ifLoggedIn>
    </div>

    <div class="clear-fix"></div>
</layout:panel>