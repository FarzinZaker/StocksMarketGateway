<div class="dashLet orange">
    <h2 style="float:right"><i class="fa fa-headphones"></i> <g:message code="twitter.home.followList.title"/></h2>

    <ul class="twitter-user-list">
        <g:each in="${followList}" var="follow" status="indexer">
            <li class="${indexer % 2 ? 'even' : 'odd'}">
                <div class="image">
                    <img width="60px"
                         src="${createLink(controller: 'image', action: follow.label == 'Person' ? 'profile' : 'property', params: [id: follow.identifier, size: 100, type: [follow.label]])}"/>
                </div>

                <div class="description">
                    <h4>${follow.title}</h4>

                    <a class="k-button"
                       href="${createLink(controller: follow.label == 'Person' ? 'user' : 'twitter', action: follow.label == 'Person' ? 'wall' : 'property', id: follow.identifier)}"><g:message
                            code="twitter.${follow.label == 'Person' ? 'author' : 'property'}.wall.list"/></a>
                </div>

                <div class="clear-fix"></div>
            </li>
        </g:each>
    </ul>
</div>