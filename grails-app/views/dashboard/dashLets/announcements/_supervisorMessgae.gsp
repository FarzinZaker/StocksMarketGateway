<%@ page import="stocks.feed.ExternalNewsService" %>
<style>

.announcementFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="animatedSort.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="supervisorMessagesTimer"></div>

<div id="supervisorMessagesContainer" class="announcementFeedContainer">

    <div id="supervisorMessagesItems" class="announcementFeedItems linkList">
        <form:loading/>
    </div>
</div>

<script id="supervisorMessageTipTemplate" type="text/x-kendo-template">
<h3>#=target.data('title')#</h3>
<p>#=target.data('description')#</p>
</script>

<script language="javascript" type="text/javascript">

    function fillSupervisorMessages(data) {
        var container = $('#supervisorMessagesItems');
        container.find('.loading').remove();
        $.each(data, function () {
            var oldItem = container.find('[data-id=' + this.id + ']');
            if (oldItem.length > 0) {
                oldItem.find('.announcementFeedItemDate').html(this.dateString);
            }
            else {
                var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.id).attr('data-time', this.time)
                        .attr('data-description', this.description)
                        .attr('data-title', this.title);
                var title = $('<span/>').addClass('announcementFeedItemTitle').html(this.title);
                itemContainer.append(title);
                var footer = $('<div/>').addClass('announcementFeedItemFooter');
                var date = $('<div/>').addClass('announcementFeedItemDate').html(this.dateString);
                footer.append(date);
                itemContainer.append(footer);

                container.append(itemContainer);

                $("#supervisorMessagesItems").kendoTooltip({
                    filter: ".mix",
                    content: kendo.template($("#supervisorMessageTipTemplate").html()),
                    width: 400,
//                    height: 200,
                    position: "left"
                });

//                $("#products").find("a").click(false);
            }

        });
        container.animatedSort({
            margin: 5,
            padding: 0
        });
    }
</script>