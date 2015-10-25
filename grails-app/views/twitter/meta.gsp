<span class="author">
    <a class="k-button" href="${createLink(controller: 'user', action: 'wall', id: author.identifier)}"><i class="fa fa-pencil"></i> ${author.title}</a>
</span>
<span class="groups">
    <g:each in="${groups}" var="group">
        <a class="k-button" href="${createLink(controller: 'group', action: 'home', id: group.id)}">
            <i class="fa fa-users"></i> ${group.title}</a>
    </g:each>
</span>
<g:if test="${!hasAccess}">
    <i class="fa fa-lock"></i>
</g:if>