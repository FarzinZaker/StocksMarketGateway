<ul class="twitter-user-list">
    <g:each in="${list}" var="item" status="indexer">
        <li class="${indexer % 2 ? 'even' : 'odd'}">
            <div class="image"><img width="40px"
                                    src="${createLink(controller: 'image', action: 'property', params: [id: item.identifier, type: item.class, size: 80])}"/>
            </div>

            <div class="description">
                <a href="${createLink(controller: 'twitter', action: 'property', id: item.identifier)}">${item.title} (${item.count})</a>
            </div>

            <div class="clear-fix"></div>
        </li>
    </g:each>
</ul>