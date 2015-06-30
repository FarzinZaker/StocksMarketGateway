<style>
#newsFeedContainer .mix {
    display: none;
}

.loading {
    display: block;

}
</style>
<asset:javascript src="jquery.mixitup.js"/>
<asset:javascript src="jquery.mixitup-pagination.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>

<h2 class="orange" style="float:right"><i class="fa fa-newspaper-o"></i> <g:message code="newsFeed.title"/></h2>

<div id="newsFeedTimer"></div>

<div id="newsFeedContainer">

    <div id="newsFeedSort">

        <span><g:message code="newsFeed.sortText"/></span>

        <div class="sort active" data-sort="time:desc"><g:message code="newsFeed.newest"/></div>

        <div class="sort" data-sort="random"><g:message code="newsFeed.random"/></div>
    </div>
    <div class="clear-fix"></div>

    <div id="newsFeedFilters"></div>

    <div id="newsFeedItems">
        <form:loading/>
    </div>
</div>
<script language="javascript" type="text/javascript">

    function parseRSS(setupMixItUp) {
        $.ajax({
            url: '${createLink(action: 'news')}',
            success: function (response) {

                var filtersContainer = $('#newsFeedFilters');
                if (filtersContainer.text() == '') {
                    filtersContainer.append($('<div/>').addClass('filter').attr('data-filter', 'all').html('${message(code:'newsFeed.all')}'));
                    $.each(response.categories, function () {
                        var filterItem = $('<div/>').addClass('filter').attr('data-filter', '.' + this.value).html(this.text);
                        filtersContainer.append(filterItem);
                    });
                    $('#newsFeedSort').fadeIn(500);
                    filtersContainer.fadeIn(500);
                }
                var feedContainer = $('#newsFeedItems');
                if (setupMixItUp) {
                    feedContainer.html('');
                }
                $.each(response.data, function () {
                    var oldItem = $('[data-id=' + this.id + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.newsFeedItemDate').html(this.dateString);
                    }
                    else {
                        var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.id).attr('data-time', this.time);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.source + ':');
                        itemContainer.append(source);
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        itemContainer.append(title);
                        var date = $('<div/>').addClass('newsFeedItemDate').html(this.dateString);
                        itemContainer.append(date);

                        if (setupMixItUp) {
                            feedContainer.append(itemContainer);
                        }
                        else {
                            $('#newsFeedContainer').mixItUp('prepend', itemContainer, {}).mixItUp('sort', $('.sort.active').attr('data-sort'), true);
                        }
                    }

                });

                if (setupMixItUp) {

                    $('#newsFeedContainer').mixItUp({
                        animation: {
//                            animateChangeLayout: true
                        },
                        pagination: {
                            limit: 10
                        },
                        layout: {
                            display: 'block'
                        },
                        load: {
                            filter: '.economic',
                            sort: 'time:desc'
                        },
                        controls: {
//                            live: true,
                            toggleFilterButtons: false
                        }
                    });

                }

                $('#newsFeedTimer').timer('start');
            }
        });
    }
    parseRSS(true);
    $(document).ready(function () {
        $('#newsFeedTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#newsFeedTimer').timer('stop');
                parseRSS(false);
            }
        });
    });
</script>