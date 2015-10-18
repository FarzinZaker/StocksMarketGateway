<div class="dashLet green">
    <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.article.author.title"/></h2>

    <ul class="twitter-user-list">
        <li>
            <div class="image"><img width="60px"
                                    src="${createLink(controller: 'image', action: 'profile', id: article.author.id)}"/>
            </div>

            <div class="description">
                <h4>${article.author.toString()}</h4>

                <a class="k-button" href="${createLink(controller: 'user', action: 'wall', id: article.author.id)}"><g:message
                        code="twitter.author.wall.list"/></a>
            </div>

            <div class="clear-fix"></div>
        </li>
    </ul>
</div>