<g:render template="/layouts/common/topBar"/>
<div id="header" class="k-rtl" role="banner">
    <a href="${createLink(uri: '/')}" id="logo">
        <asset:image src="logo.png" alt="stocks"/>
    </a>
    <g:render template="/layouts/main/menu"/>
    %{--<asset:image src="watermark.png" class="watermark"/>--}%
    <g:render template="/layouts/common/symbolSearch"/>
    <div class="clear-fix"></div>
</div>