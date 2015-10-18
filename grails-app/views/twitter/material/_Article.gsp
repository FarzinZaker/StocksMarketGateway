<div class="image">
    <img src="${createLink(controller: 'image', params: [id: material.imageId, size: imageSize ?: 100])}"/>
</div>

<div class="description">
    <a href="${createLink(controller: 'article', action: 'thread', id: material.identifier)}">${material.title}</a>

    <p>${material.description}</p>

    <ul class="propertyList">
        <g:each in="${material.propertyList}" var="property">
            <li>
                <a href="${createLink(controller: 'twitter', action: property.label, id: property.identifier)}">
                    <i class="fa fa-tag"></i>
                    <span>${property.title}</span>

                    <div class="clear-fix"></div>
                </a>
            </li>
        </g:each>
    </ul>
</div>