<style>
#codalContainer .mix {
    display: none;
}
</style>
<asset:javascript src="jquery.mixitup.js"/>
<asset:javascript src="jquery.mixitup-pagination.js"/>


<div id="codalTimer"></div>

<div id="codalContainer" class="announcementFeedContainer">

    <div id="codalItems" class="announcementFeedItems">
        <form:loading/>
    </div>
</div>

<script language="javascript" type="text/javascript">

    var setupCodalMixItUp = true;
    function fillCodal(data) {
        var feedContainer = $('#codalItems');
        if (setupCodalMixItUp) {
            feedContainer.html('');
        }
        $.each(data, function () {
            var oldItem = $('[data-id=' + this.id + ']');
            if (oldItem.length > 0) {
                oldItem.find('.announcementFeedItemDate').html(this.dateString);
            }
            else {
                var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.id).attr('data-time', this.time);
                var title = $('<a/>').addClass('announcementFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                itemContainer.append(title);
                var source = $('<div/>').addClass('announcementFeedItemSource').html(this.source);
                itemContainer.append(source);
                var date = $('<div/>').addClass('announcementFeedItemDate').html(this.dateString);
                itemContainer.append(date);

                if (setupCodalMixItUp) {
                    feedContainer.append(itemContainer);
                }
                else {
                    $('#codalContainer').mixItUp('prepend', itemContainer, {}).mixItUp('sort', $('.sort.active').attr('data-sort'), true);
                }
            }

        });

        if (setupCodalMixItUp) {

            $('#codalContainer').mixItUp({
                animation: {},
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
            setupCodalMixItUp = false;

        }
    }
</script>