<g:each in="${propertyList}" var="property">
    <li>
        <a href="${createLink(controller: 'twitter', action: property.label, id: property.identifier)}">
            <i class="fa fa-tag"></i>
            <span>${property.title}</span>

            <div class="clear-fix"></div>
        </a>
    </li>
</g:each>