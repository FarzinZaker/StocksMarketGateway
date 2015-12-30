<div class="boxList">
    <h2><g:message code="twitter.home.followList.title"/></h2>

    <ul class="twitter-user-list">
        <g:each in="${followList}" var="follow" status="indexer">
            <li class="${indexer % 2 ? 'even' : 'odd'} propertyBox ${follow.label}">
                <div class="image">
                    <img width="40px"
                         src="${createLink(controller: 'image', action: follow.label == 'Person' ? 'profile' : 'property', params: [id: follow.identifier, size: 100, type: [follow.label]])}"/>
                </div>

                <div class="description">
                    <a href="${createLink(controller: follow.label == 'Person' ? 'user' : 'twitter', action: follow.label == 'Person' ? 'wall' : 'property', id: follow.identifier)}">${follow.title}</a>
                </div>

                <div class="clear-fix"></div>
            </li>
        </g:each>
    </ul>
</div>