<div class="demo-section k-header wide">
    <div id="newsList" style="margin-top:10px;"></div>

    <div id="newsPager" class="k-pager-wrap" style="margin-top:20px;"></div>
</div>

<script type="text/x-kendo-template" id="template">
<div class="symbolNewsItem">
    <div><span class="#:color#">#:type#</span> #=linkStart(link)##:title##=linkEnd(link)#</div>
    <span class="itemDate">#:date#</span>

    <div class="clear-fix"></div>
</div>
</script>

<script>

    function linkStart(link) {
        if (link)
            return  '<a href="' + link + '" target="_blank">';
        else return '';
    }

    function linkEnd(link) {
        if (link)
            return  '</a>';
        else return '';
    }

    $(function () {
        var newsDataSource = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: "${createLink(action: 'news', id:params.id)}",
                    dataType: "json"
                }
            },
            pageSize: 8
        });

        $("#newsPager").kendoPager({
            dataSource: newsDataSource
        });

        $("#newsList").kendoListView({
            dataSource: newsDataSource,
            template: kendo.template($("#template").html())
        });
    });
</script>