<div class="article">
    <div class="image">
        <img src="${createLink(controller: 'image', id: article?.image?.id, params: [size: 350])}"/>
        <twitter:rateGage materialId="${vertexId}" showLabel="true"/>
        <g:if test="${canEdit}">
            <a style="display: block;margin-top:10px;" class="k-button"
               href="${createLink(controller: 'article', action: 'edit', id: params.id)}">
                <i class="fa fa-edit" style="display: inline-block;margin-left: 5px;position: relative;top: 3px;"></i> <g:message code="article.edit.label"/>
            </a>
        </g:if>
    </div>

    <h1>${article.title}</h1>

    <div class="summary"><format:html value="${article.summary}"/></div>

    <div class="body">
        <g:if test="${hasAccess}">
            <format:twit value="${article.body}"/>
        </g:if>
        <g:else>
            <div class="info-small" style="margin-right: 360px;">
                <g:message code="twitter.material.accessDenied"/>
            </div>

            <div class="groupListContainer">
                <div id="groupListView">
                    <g:render template="thread/groupList"/>
                </div>
            </div>
        </g:else>
    </div>
</div>