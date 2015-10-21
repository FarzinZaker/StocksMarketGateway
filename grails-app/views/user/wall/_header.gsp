
<div>
    <img width="100%" src="${createLink(controller: 'image', action: 'profile', params: [id: user?.id, size: 500])}"/>

    <h1>${user}</h1>

    <ul class="twitterAuthorInfo">
        <li>
            <label><g:message code="twitter.author.info.articlesCount"/></label>
            <span>${authorInfo.articlesCount}</span>
        </li>
        <li>
            <label><g:message code="twitter.author.info.averageArticlesRate"/></label>
            <span>${authorInfo.averageArticlesRate}</span>
        </li>
        <li>
            <label><g:message code="twitter.author.info.commentsCount"/></label>
            <span>${authorInfo.commentsCount}</span>
        </li>
        %{--<li>--}%
            %{--<label><g:message code="twitter.author.info.commentsWithLikeOrDislike"/></label>--}%
            %{--<span>${authorInfo.commentsWithLikeOrDislike}</span>--}%
        %{--</li>--}%
        <li>
            <label><g:message code="twitter.author.info.commentsLikes"/></label>
            <span>${authorInfo.commentsLikes}</span>
        </li>
        <li>
            <label><g:message code="twitter.author.info.commentsDislikes"/></label>
            <span>${authorInfo.commentsDislikes}</span>
        </li>
    </ul>
</div>