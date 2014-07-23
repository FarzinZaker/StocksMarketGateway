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

        <div class="report">
            <g:message code="rate.summary"
                       args="${[userInfo.rateAverage(user: user), userInfo.articleCount(user: user), userInfo.newsCount(user: user)]}"/>
        </div>

        <div class="report">
            <g:message code="like.summary"
                       args="${[userInfo.likeCount(user: user), userInfo.dislikeCount(user: user), userInfo.commentCount(user: user)]}"/>
        </div>
    </div>

    <div class="clear-fix"></div>
</div>