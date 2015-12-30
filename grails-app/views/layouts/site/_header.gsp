%{--<g:render template="/layouts/common/topBar"/>--}%
%{--<div id="header" class="k-rtl" role="banner" style="display: none">--}%
    %{--<a href="${createLink(uri: '/')}" id="logo">--}%
        %{--<asset:image src="logo.png" alt="stocks"/>--}%
    %{--</a>--}%
    %{--<asset:image src="watermark.png" class="watermark"/>--}%
    %{--<g:render template="/layouts/common/userPanel"/>--}%
    %{--<g:render template="/layouts/common/symbolSearch"/>--}%
    %{--<div class="clear-fix"></div>--}%
%{--</div>--}%

<div class="header" id="header">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 k-rtl">
                <a href="${createLink(uri: '/')}" id="logo">
                    <asset:image src="logo.png" alt="stocks"/>
                </a>
                <g:render template="/layouts/common/userPanel"/>
                <g:render template="/layouts/common/symbolSearch"/>
            </div>
        </div>
    </div>
</div>

<div class="menuContainer">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 k-rtl">
                <g:render template="/layouts/main/menu"/>
            </div>
        </div>
    </div>
</div>