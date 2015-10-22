<div class="dashLet green">
    <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.property.authorList.title"/></h2>

    <ul class="twitter-user-list">
        <g:each in="${authorList}" var="author" status="indexer">
            <li class="${indexer % 2 ? 'even' : 'odd'}">
                <div class="image"><img width="60px"
                                        src="${createLink(controller: 'image', action: 'profile', id: author.identifier)}"/>
                </div>

                <div class="description">
                    <h4>${author.title}</h4>

                    <a class="k-button" href="${createLink(controller: 'user', action: 'wall', id: author.identifier)}"><g:message
                            code="twitter.author.wall.list"/></a>
                </div>

                <div class="clear-fix"></div>
            </li>
        </g:each>
    </ul>
</div>