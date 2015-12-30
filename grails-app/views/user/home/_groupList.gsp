<div class="dashLet white">
    <h2 style="float:right"><i class="fa fa-pencil"></i> <g:message code="twitter.home.groupList.title"/></h2>

    <ul class="twitter-user-list">
        <g:each in="${groupList}" var="group" status="indexer">
            <li class="${indexer % 2 ? 'even' : 'odd'}">
                <div class="image"><img width="40px"
                                        src="${createLink(controller: 'image', id: group.imageId)}"/>
                </div>

                <div class="description">
                    <a  href="${createLink(controller: 'group', action: 'home', id: group.idNumber)}">${group.title}</a>
                </div>

                <div class="clear-fix"></div>
            </li>
        </g:each>
    </ul>
    <div class="dashletFooter">
        <a href="${createLink(controller: 'group', action: 'select')}"><g:message code="twitter.group.select"/></a>
    </div>
</div>