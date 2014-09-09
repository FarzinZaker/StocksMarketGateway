<%@ page import="stocks.User" %>
<div id="header" class="brokerHeader" role="banner">
    <a href="${createLink(uri: '/')}">
        <broker:logo/>
        <h1>
            <broker:title/>
            <span><g:message code="subtitle"/></span>
        </h1>
    </a>
    <g:render template="/layouts/common/userPanel"/>
    <g:render template="/layouts/main/menu"/>
    <asset:image src="watermark.png" class="watermark"/>
</div>