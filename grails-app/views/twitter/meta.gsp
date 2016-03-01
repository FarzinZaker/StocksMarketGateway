<span class="author" data-id="${author.identifier}">
    <g:if test="${canEdit}">
        <span class="k-button" onclick="edit${params.type}('${params.identifier ?: params.id}', this);" style="padding:1px 7px 2px;"><g:message code="edit"/></span>
    </g:if>
    <g:else>
        <a href="${createLink(controller: 'user', action: 'wall', id: author.identifier)}"><i
                class="fa fa-user"></i> ${author.title}</a>
    </g:else>
</span>
<span class="groups">
    <g:each in="${groups}" var="group">
        <a href="${createLink(controller: 'group', action: 'home', id: group.id)}">
            <i class="fa fa-users"></i> ${group.title}</a>
    </g:each>
</span>
<g:if test="${!hasAccess}">
    <i class="fa fa-lock"></i>
</g:if>