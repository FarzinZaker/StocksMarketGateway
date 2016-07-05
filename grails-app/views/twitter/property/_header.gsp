<%@ page import="grails.converters.JSON" %>
<twitter:followScript/>
<div class="followable followable_${property.idNumber?.replace(':', '_')} propertyBox ${property.label}">
    <div class="image">
        <img width="150px"
             src="${createLink(controller: 'image', action: property.label == 'Person' ? 'profile' : 'property', params: [id: property.identifier, size: 150, type: [property.label]])}"/>
    </div>

    <div class="description">
        <h1><a href="${createLink(controller: property.label == 'Person' ? 'user' : 'twitter', action: property.label == 'Person' ? 'wall' : 'property', id: property.identifier)}">${property.title}</a>
        </h1>
        <twitter:followButton itemId="${property.idNumber}"/>
    </div>

    <div class="clear-fix"></div>
</div>

<g:render template="property/info/${property.label?.toString()?.toLowerCase()}" model="${propertyInfo}"/>
