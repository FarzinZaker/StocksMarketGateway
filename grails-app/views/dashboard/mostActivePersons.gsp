<ul class="twitter-user-list">
    <g:each in="${list}" var="item" status="indexer">
        <li class="${indexer % 2 ? 'even' : 'odd'}">
            <div class="image"><img width="40px"
                                    src="${createLink(controller: 'image', action: 'profile', id: item.identifier)}"/>
            </div>

            <div class="description">
                <a href="${createLink(controller: 'user', action: 'wall', id: item.identifier)}">${item.title} (${item.count})</a>
            </div>

            <div class="clear-fix"></div>
        </li>
    </g:each>
</ul>