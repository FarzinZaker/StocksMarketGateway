<div class="dashLet green">
    <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.author.groupList.title"/></h2>

    <ul class="twitter-user-list">
        <g:each in="${groupList}" var="group" status="indexer">
            <li class="${indexer % 2 ? 'even' : 'odd'}">
                <div class="image"><img width="60px"
                                        src="${createLink(controller: 'image', id: group.imageId)}"/>
                </div>

                <div class="description">
                    <h4>${group.title}</h4>

                    <a class="k-button" href="${createLink(controller: 'group', action: 'home', id: group.idNumber)}"><g:message
                            code="twitter.group.home"/></a>
                </div>

                <div class="clear-fix"></div>
            </li>
        </g:each>
    </ul>
</div>