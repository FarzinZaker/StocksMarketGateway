<div class="dashLet white">
    <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.article.author.title"/></h2>

    <ul class="twitter-user-list">
        <li>
            <div class="image"><img width="40px"
                                    src="${createLink(controller: 'image', action: 'profile', id: article.author.id)}"/>
            </div>

            <div class="description">
                <a href="${createLink(controller: 'user', action: 'wall', id: article.author.id)}">${article.author.toString()}</a>
            </div>

            <div class="clear-fix"></div>
        </li>
    </ul>
</div>