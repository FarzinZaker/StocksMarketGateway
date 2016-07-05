<g:if test="${authorList?.size()}">
    <div class="dashLet blue white">
        <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.group.authorList.title"/></h2>

        <ul class="twitter-user-list">
            <g:each in="${authorList}" var="author" status="indexer">
                <li class="${indexer % 2 ? 'even' : 'odd'}">
                    <div class="image"><img width="40px"
                                            src="${createLink(controller: 'image', action: 'profile', id: author.identifier)}"/>
                    </div>

                    <div class="description">
                        <a href="${createLink(controller: 'user', action: 'wall', id: author.identifier)}">${author.title}</a>
                    </div>

                    <div class="clear-fix"></div>
                </li>
            </g:each>
        </ul>
    </div>
</g:if>