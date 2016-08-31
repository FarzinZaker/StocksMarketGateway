<div>
    <twitter:followScript/>
    <img width="100%" style="background-color: #d70060"
         src="${createLink(controller: 'image', action: 'profile', params: [id: user?.id, size: 500])}"/>

    <h1>${user}</h1>

    <sec:ifLoggedIn>
        <g:if test="${!ownPage}">
            <div style="text-align: left;margin-bottom:20px;">
                <msg:sendButton userId="${vertex.identifier}"/>
                <msg:sendWindow/>
                <twitter:followButton itemId="${vertex.idNumber}"/>
            </div>
        </g:if>
    </sec:ifLoggedIn>

    <ul class="twitterAuthorInfo">
        <li style="border-bottom-color:#fa6800;">
            <label><g:message code="twitter.author.info.articlesCount"/></label>
            <span style="background-color: #fa6800;">${authorInfo.articlesCount ?: 0}</span>
        </li>
        <li style="border-bottom-color:#8ebc00;margin-right:20px;">
            <label><g:message code="twitter.author.info.averageArticlesRate"/></label>
            <span style="background-color: #8ebc00;">${authorInfo.averageArticlesRate ?: 0}</span>
        </li>
        <li style="border-bottom-color:#1ba1e2;">
            <label><g:message code="twitter.author.info.commentsCount"/></label>
            <span style="background-color: #1ba1e2;">${authorInfo.commentsCount ?: 0}</span>
        </li>
        %{--<li>--}%
        %{--<label><g:message code="twitter.author.info.commentsWithLikeOrDislike"/></label>--}%
        %{--<span>${authorInfo.commentsWithLikeOrDislike ?: 0}</span>--}%
        %{--</li>--}%
        <li style="border-bottom-color:#60a917;margin-right:20px;">
            <label><g:message code="twitter.author.info.commentsLikes"/></label>
            <span style="background-color: #60a917;">${authorInfo.commentsLikes ?: 0}</span>
        </li>
        <li style="border-bottom-color:#d80073;margin-right:20px;">
            <label><g:message code="twitter.author.info.commentsDislikes"/></label>
            <span style="background-color: #d80073;">${authorInfo.commentsDislikes ?: 0}</span>
        </li>
    </ul>

</div>