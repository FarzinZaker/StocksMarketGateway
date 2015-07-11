<style>
#supervisorMessagesContainer .mix {
    display: none;
}
</style>


<div id="supervisorMessagesTimer"></div>

<div id="supervisorMessagesContainer" class="announcementFeedContainer">

    <div id="supervisorMessagesItems" class="announcementFeedItems">
        <form:loading/>
    </div>
</div>
<script language="javascript" type="text/javascript">

    var setupSupervisorMessagesMixItUp = true;
    function fillSupervisorMessages(data) {
        var feedContainer = $('#supervisorMessagesItems');
        if (setupSupervisorMessagesMixItUp) {
            feedContainer.html('');
        }
        $.each(data, function () {
            var oldItem = $('[data-id=' + this.id + ']');
            if (oldItem.length > 0) {
                oldItem.find('.announcementFeedItemDate').html(this.dateString);
            }
            else {
                var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.id).attr('data-time', this.time);
                var title = $('<span/>').addClass('announcementFeedItemTitle').attr('target', '_blank').attr('href', this.link).css('color','black').html(this.title);
                itemContainer.append(title);
//                var source = $('<div/>').addClass('announcementFeedItemSource').html(this.source);
//                itemContainer.append(source);
                var date = $('<div/>').addClass('announcementFeedItemDate').html(this.dateString);
                itemContainer.append(date);

                if (setupSupervisorMessagesMixItUp) {
                    feedContainer.append(itemContainer);
                }
                else {
                    $('#supervisorMessagesContainer').mixItUp('prepend', itemContainer, {}).mixItUp('sort', $('.sort.active').attr('data-sort'), true);
                }
            }

        });

        if (setupSupervisorMessagesMixItUp) {

            $('#supervisorMessagesContainer').mixItUp({
                animation: {
                },
                pagination: {
                    limit: 10
                },
                layout: {
                    display: 'block'
                },
                load: {
                    sort: 'time:desc'
                },
                controls: {
                    toggleFilterButtons: false
                }
            });
            setupSupervisorMessagesMixItUp = false;

        }
    }
</script>