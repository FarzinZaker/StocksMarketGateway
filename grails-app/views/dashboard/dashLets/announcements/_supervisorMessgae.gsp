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

    <div id="supervisorMessagesItems" class="announcementFeedItems">
        <form:loading/>
    </div>
</div>

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
                var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.id).attr('data-time', this.time);
                var title = $('<span/>').addClass('announcementFeedItemTitle').attr('target', '_blank').attr('href', this.link).css('color','black').html(this.title);
                itemContainer.append(title);
                var date = $('<div/>').addClass('announcementFeedItemDate').html(this.dateString);
                itemContainer.append(date);

                container.append(itemContainer);
            }

        });

        container.animatedSort();
    }
</script>