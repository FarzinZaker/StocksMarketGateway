<div class="col-xs-6">
    <div class="propertyBox Person">
        <div class="image">
            <img width="50px"
                 src="${createLink(controller: 'image', action: 'index', params: [id: imageId, size: 50])}"/>
        </div>

        <div class="description">
            <h1><a href="${createLink(controller: 'group', action: 'home', id: idNumber)}">${title}</a></h1>
        </div>
        <div class="row user-info">
            <div class="col-xs-6">
                <g:message code="groupCard.memberCount"/>:<twitter:memberCount groupId="${idNumber}"/>
            </div>
            <div class="col-xs-6">
                <g:message code="groupCard.postCount"/>:<twitter:groupPostCount groupId="${idNumber}"/>
            </div>

        </div>

        <div class="clear-fix"></div>
    </div>
</div>