<div class="report">
    <span class="user-badge">
        <userInfo:rateAverage user="${user}"/>
        <span><g:message code="badge.rateAverage.label"/></span>
    </span>
    <span class="user-badge">
        <userInfo:articleCount user="${user}"/>
        <span><g:message code="badge.articleCount.label"/></span>
    </span>
    <span class="user-badge">
        <userInfo:newsCount user="${user}"/>
        <span><g:message code="badge.newsCount.label"/></span>
    </span>
    %{--<span class="user-badge">--}%
        %{--<userInfo:likeCount user="${user}"/>--}%
        %{--<span><g:message code="badge.likeCount.label"/></span>--}%
    %{--</span>--}%
    %{--<span class="user-badge">--}%
        %{--<userInfo:dislikeCount user="${user}"/>--}%
        %{--<span><g:message code="badge.dislikeCount.label"/></span>--}%
    %{--</span>--}%
    <span class="user-badge">
        <userInfo:commentCount user="${user}"/>
        <span><g:message code="badge.commentCount.label"/></span>
    </span>
</div>