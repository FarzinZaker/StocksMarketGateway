<layout:panel title="${message(code: 'user.followList')}" class="followingList" header="h2">
    <g:if test="${user.followList?.size()}">
        <g:each in="${user.followList}" var="followedUser">
            <div class="followingItem">
                <div class="meta">
                    <a href="${createLink(controller: 'user', action: 'wall', id: followedUser.id)}">
                        <img src="${createLink(controller: 'image', id: followedUser.image?.id, params: [size: 80, default: "user-${followedUser.sex ?: 'male'}"])}"/>
                    </a>
                </div>

                <div class="data">
                    <h3><a href="${createLink(controller: 'user', action: 'wall', id: followedUser.id)}">${followedUser}</a>
                    </h3>

                    <g:render template="/user/badgeList" model="${[user: user]}"/>
                </div>

                <div class="clear-fix"></div>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <div class="emptyMessage"><g:message code="user.followList.empty"/></div>
    </g:else>
</layout:panel>