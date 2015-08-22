<%@ page import="stocks.User" %>
%{--<g:render template="/layouts/common/topBar"/>--}%
<div id="header" class="brokerHeader k-rtl" role="banner">
    <a href="${createLink(uri: '/')}">
        <broker:logo/>
        <h1>
            <broker:title/>
            <span><g:message code="subtitle"/></span>
        </h1>
    </a>
    <g:render template="/layouts/main/menu"/>
    <g:render template="/layouts/common/userPanel"/>
    <g:render template="/layouts/common/symbolSearch"/>
    %{--<asset:image src="watermark.png" class="watermark"/>--}%
    <div class="clear-fix"></div>
</div>