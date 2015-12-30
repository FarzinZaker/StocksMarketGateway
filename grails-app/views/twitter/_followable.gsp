<div class="followable followable_${item.idNumber?.replace(':', '_')} propertyBox ${item.label}">
    <div class="image">
        <img width="150px"
             src="${createLink(controller: 'image', action: item.label == 'Person' ? 'profile' : 'property', params: [id: item.identifier, size: 150, type: [item.label]])}"/>
    </div>

    <div class="description">
        <h4><a href="${createLink(controller: item.label == 'Person' ? 'user' : 'twitter', action: item.label == 'Person' ? 'wall' : 'property', id: item.identifier)}">${item.title}</a>
        </h4>

        <div class="depth">
            %{--<g:if test="${item.depth != 9999}">--}%
            <span>
                <g:message code="twitter.followable.depth"/>
            </span>
            <span>
                <g:if test="${item.depth == 9999}">
                    ∞
                </g:if>
                <g:else>
                    <g:formatNumber number="${item.depth}" type="number"/>
                </g:else>
            </span>
            %{--</g:if>--}%
        </div>

        <div class="followersCount">
            <span>
                <g:message code="twitter.followable.followersCount"/>
            </span>
            <span>
                <g:formatNumber number="${item.followersCount}" type="number"/>
            </span>
        </div>
        <twitter:followButton itemId="${item.idNumber}" callback="removeFollowableFromList"/>
    </div>

    <div class="clear-fix"></div>
</div>