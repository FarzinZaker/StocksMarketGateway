<div class="col-xs-6">
    <div class="propertyBox Person">
        <div class="image">
            <img width="50px"
                 src="${createLink(controller: 'image', action: 'profile', params: [id: identifier, size: 50, type: 'Person'])}"/>
        </div>

        <div class="description">
            <h1><a href="${createLink(controller: 'user', action: 'wall', id: identifier)}">${title}</a></h1>
        </div>
        <div class="row user-info">
            <div class="col-xs-6">
                <g:message code="userCard.followersCount"/>:<userInfo:followerCount userId="${identifier}"/>
            </div>
            <div class="col-xs-6">
                <g:message code="userCard.score"/>:<userInfo:userRate userId="${identifier}"/>
            </div>
            <div class="col-xs-6">
                <g:message code="userCard.post"/>:<userInfo:userArticleCount userId="${identifier}"/>
            </div>
            <div class="col-xs-6">
                <g:message code="userCard.4tabloScore"/>:<userInfo:userRate4tablo userId="${identifier}"/>
            </div>
        </div>

        <div class="clear-fix"></div>
    </div>
</div>