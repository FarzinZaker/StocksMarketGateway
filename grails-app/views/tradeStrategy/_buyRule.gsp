<iframe id="frmBuyRule"
        src="${createLink(controller: 'tradeStrategy', action: 'ruleBuilder', params: [id: params.id, type: 'buy'])}"
        scrolling="no" style="width: 100%;height:0;" frameborder="0"></iframe>

<div id="loadingBuyRule">
    <asset:image src="loading.gif" style="margin-left: 10px;"/>
    <g:message code="loading"/>
</div>
<script language="javascript" type="text/javascript">
    var frmBuyRule = $('#frmBuyRule');
    $(function () {
        frmBuyRule.load(function () {
            var iframe_content = frmBuyRule.contents().find('body').find('#container');
            iframe_content.resize(function () {
                var elem = $(this);
                var height = elem.outerHeight(true);
                if (height > 0)
                    frmBuyRule.stop().animate(
                            {height: elem.outerHeight(true)}
                            , 500);
//                frmBuyRule.css('height', elem.outerHeight(true));
                $('#loadingBuyRule').hide();
            });
            iframe_content.resize();
        });
    });

</script>