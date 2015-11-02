<%@ page import="grails.converters.JSON" %>
<twitter:followScript/>

<div>

    <h1>${property.title}</h1>

    <div style="text-align: left;margin-bottom:20px;">
        <twitter:followButton itemId="${property.idNumber}" />
    </div>

    <g:render template="property/info/${property.label?.toString()?.toLowerCase()}" model="${propertyInfo}"/>
</div>