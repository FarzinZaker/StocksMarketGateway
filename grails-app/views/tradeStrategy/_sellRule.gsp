<iframe id="frmSellRule"
        src="${createLink(controller: 'tradeStrategy', action: 'ruleBuilder', params: [id: params.id, type: 'sell'])}"
        scrolling="no" style="width: 100%;height:0;" frameborder="0"></iframe>

<div id="loadingSellRule">
    <asset:image src="loading.gif" style="margin-left: 10px;"/>
    <g:message code="loading"/>
</div>
<script language="javascript" type="text/javascript">
    var frmSellRule = $('#frmSellRule');
    $(function () {
        frmSellRule.load(function () {
            var iframe_content = frmSellRule.contents().find('body').find('#container');
            iframe_content.resize(function () {
                var elem = $(this);
                var height = elem.outerHeight(true);
                if (height > 0)
                    frmSellRule.stop().show().animate(
                            {height: elem.outerHeight(true)}
                            , 500);
//                frmSellRule.css({height: elem.outerHeight(true)});
                $('#loadingSellRule').hide();
            });
            iframe_content.resize();
        });
    });

</script>