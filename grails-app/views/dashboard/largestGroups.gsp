<ul class="twitter-user-list">
    <g:each in="${list}" var="item" status="indexer">
        <li class="${indexer % 2 ? 'even' : 'odd'}">
            <div class="image"><img width="40px"
                                    src="${createLink(controller: 'image', params: [id: item.imageId, size: 80])}"/>
            </div>

            <div class="description">
                <a href="${createLink(controller: 'group', action: 'home', id: item.idNumber)}">${item.title} (${item.size})</a>
            </div>

            <div class="clear-fix"></div>
        </li>
    </g:each>
</ul>