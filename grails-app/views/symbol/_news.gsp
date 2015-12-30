<div>
    <div id="newsList" style="background-color: transparent"></div>

    <div id="newsPager" class="k-pager-wrap" style="margin-top:15px;background-color: transparent;"></div>
</div>

<script type="text/x-kendo-template" id="newsTemplate">
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
            pageSize: 5
        });

        $("#newsPager").kendoPager({
            dataSource: newsDataSource,
            buttonCount: 1
        });

        $("#newsList").kendoListView({
            dataSource: newsDataSource,
            template: kendo.template($("#newsTemplate").html())
        });
    });
</script>