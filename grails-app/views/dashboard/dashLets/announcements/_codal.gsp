<%@ page import="stocks.feed.ExternalNewsService" %>
<style>

.announcementFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="animatedSort.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="codalTimer" style="display: none"></div>

<div id="codalContainer" class="announcementFeedContainer">

    <div id="codalItems" class="announcementFeedItems">
        <form:loading/>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function fillCodal(data) {
        var container = $('#codalItems');
        container.find('.loading').remove();
        $.each(data, function () {
            var oldItem = container.find('[data-id=' + this.id + ']');
            if (oldItem.length > 0) {
                oldItem.find('.announcementFeedItemDate').html(this.dateString);
            }
            else {
                var itemContainer = $('<div/>').addClass('mix').attr('data-id', this.id).attr('data-time', this.time);
                var title = $('<a/>').addClass('announcementFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                itemContainer.append(title);
                var source = $('<div/>').addClass('announcementFeedItemSource').html(this.source);
                itemContainer.append(source);
                var date = $('<div/>').addClass('announcementFeedItemDate').html(this.dateString);
                itemContainer.append(date);

                container.append(itemContainer);
            }

        });

        container.animatedSort();
    }
</script>