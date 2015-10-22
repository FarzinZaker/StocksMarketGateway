<g:each in="${propertyList}" var="property">
    <li>
        <a href="${createLink(controller: 'twitter', action: 'property', id: property.identifier)}">
            <i class="fa fa-tag"></i>
            <span>${property.title}</span>

            <div class="clear-fix"></div>
        </a>
    </li>
</g:each>