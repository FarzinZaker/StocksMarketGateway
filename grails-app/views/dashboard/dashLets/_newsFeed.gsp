<style>
#newsFeedContainer .mix {
    display: none;
}

#newsFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="jquery.mixitup.js"/>
<asset:javascript src="jquery.mixitup-pagination.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="newsFeedTimer" style="display: none"></div>

<div id="newsFeedContainer" class="dashLet darkBlue newsFeedContainer">
    <h2 style="float:right"><i class="fa fa-newspaper-o"></i> <g:message code="newsFeed.title"/></h2>

    <div id="newsFeedSort">

        <span style="font-size:12px;color: dimgrey"><g:message code="newsFeed.sortText"/></span>

        <div class="sort active" data-sort="time:desc"><g:message code="newsFeed.newest"/></div>

        <div class="sort" data-sort="click:desc"><g:message code="newsFeed.mostClicked"/></div>

        %{--<div class="sort" data-sort="random"><g:message code="newsFeed.random"/></div>--}%
    </div>

    <div class="clear-fix"></div>

    <div id="newsFeedFilters"></div>

    <div id="newsFeedItems" class="newsFeedItems">
        <form:loading/>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'news', action: 'archive')}"><g:message code="newsFeed.archive"/></a>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function newsLinkClick(item) {
        var win = window.open('${createLink(controller: 'news', action: 'view')}/' + $(item).parent().attr('data-id') + '?t=' + new Date().getTime(), '_blank');
        if (win) {
            win.focus();
        } else {
            window.location.href = '${createLink(controller: 'news', action: 'view')}/' + $(item).parent().attr('data-id') + '?t=' + new Date().getTime();
        }
        %{--$.ajax({url: '${createLink(controller: 'news', action: 'click')}/' + $(item).parent().attr('data-id') + '?t=' + new Date().getTime()});--}%
        $(item).parent().attr('data-click', parseInt($(item).parent().attr('data-click')));
    }

    function parseRSS(setupMixItUp) {
        $.ajax({
            url: '${createLink(action: 'news')}?t=' + new Date().getTime(),
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
                    var oldItem = $('[data-id=' + this.identifier + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.newsFeedItemDate').html(this.dateString);
                        oldItem.attr('data-click', this.clickCount);
                    }
                    else {
                        var itemContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        itemContainer.append(title);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.source);
                        itemContainer.append(source);
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
//                            filter: '.economic',
                            sort: 'time:desc'
                        },
                        controls: {
//                            live: true,
                            toggleFilterButtons: false
                        }
                    });

                    $('#newsFeedSort').find('.sort').click(function () {
                        $('#newsFeedSort').find('.sort').removeClass('active');
                        $(this).addClass('active');
                    });

                }
                else {
                    $('#newsFeedContainer').mixItUp('sort', $('.sort.active').attr('data-sort'), true);
                }

                $('.newsFeedItems a').unbind('click').click(function (event) {
                    event.preventDefault();
                    newsLinkClick(this);
                });

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