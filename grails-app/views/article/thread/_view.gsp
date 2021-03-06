<div class="article">
    <div class="image">
        <img src="${createLink(controller: 'image', id: article?.image?.id, params: [size: 350, type:'article'])}"/>
        <twitter:rateGage materialId="${vertexId}" showLabel="true"/>
        <g:if test="${canEdit}">
            <a style="display: block;margin-top:10px;" class="k-button"
               href="${createLink(controller: 'article', action: 'edit', id: params.id)}">
                <i class="fa fa-edit"
                   style="display: inline-block;margin-left: 5px;position: relative;top: 3px;"></i> <g:message
                    code="article.edit.label"/>
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

    <g:if test="${article?.files?.size()}">
        <h3><g:message code="article.attachments"/></h3>
        <ul class="attachments">
            <g:each in="${article?.files}" var="file" status="i">
                <li class="${i % 2 ? 'even' : ''}">
                    <a href="${createLink(controller: 'file', action: 'download', params: [id: file?.id, name: file?.name])}" target="_blank">
                        <img src="${assetPath(src: "file-extensions/32px/${file?.extension}.png")}"/>
                        ${file?.name}
                        <g:set var="fileSize" value="${file?.content?.size() as Double}"/>
                        <g:set var="fileSizeUnit" value="بایت"/>
                        <g:if test="${fileSize > 1024}">
                            <g:set var="fileSize" value="${fileSize / 1024}"/>
                            <g:set var="fileSizeUnit" value="کیلو بایت"/>
                        </g:if>
                        <g:if test="${fileSize > 1024}">
                            <g:set var="fileSize" value="${fileSize / 1024}"/>
                            <g:set var="fileSizeUnit" value="مگابایت"/>
                        </g:if>
                        <g:if test="${fileSize > 1024}">
                            <g:set var="fileSize" value="${fileSize / 1024}"/>
                            <g:set var="fileSizeUnit" value="گیگابایت"/>
                        </g:if>
                        <span>(<g:formatNumber number="${fileSize}" type="number" format="#.##"/> ${fileSizeUnit})</span>
                    </a>
                </li>
            </g:each>
        </ul>
    </g:if>
</div>