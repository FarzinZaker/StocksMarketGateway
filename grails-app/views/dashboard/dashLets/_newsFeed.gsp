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

        <div class="sort newsSort active" data-sort="time:desc"><g:message code="newsFeed.newest"/></div>

        <div class="sort newsSort" data-sort="click:desc"><g:message code="newsFeed.mostClicked"/></div>

        %{--<div class="sort" data-sort="random"><g:message code="newsFeed.random"/></div>--}%
    </div>

    <div class="clear-fix"></div>

    <div id="newsFeedFilters"></div>

    <div id="newsFeedItems" class="newsFeedItems">
        <form:loading/>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'externalNews', action: 'archive')}"><g:message code="newsFeed.archive"/></a>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function newsLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalNews', action: 'view')}/' + $(item).parent().parent().attr('data-id') + '?t=' + new Date().getTime(),
            success: function (response) {
                var win = window.open($(item).attr('href'), '_blank');
                if (win) {
                    win.focus();
                } else {
                    window.location.href = $(item).attr('href');
                }
                $(item).parent().attr('data-click', parseInt($(item).parent().attr('data-click')));
            }
        });
    }

    function parseRSS(setupMixItUp) {
        $.ajax({
            url: '${createLink(action: 'news')}?t=' + new Date().getTime(),
            success: function (response) {

                var filtersContainer = $('#newsFeedFilters');
                if (filtersContainer.text() == '') {
                    filtersContainer.append($('<div/>').addClass('newsFilter').addClass('filter').attr('data-filter', 'all').html('${message(code:'newsFeed.all')}'));
                    $.each(response.categories, function () {
                        var filterItem = $('<div/>').addClass('newsFilter').addClass('filter').attr('data-filter', '.' + this.value).html(this.text);
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

                        var itemByTimeContainer = $('<div/>').addClass('newsMix').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageByTimeContainer = $('<div/>').addClass('newsFeedImageContainer');
                        imageByTimeContainer.append($('<img/>').attr('src', '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('height', '40px'));
                        itemByTimeContainer.append(imageByTimeContainer);
                        var titleByTimeContainer = $('<div/>').addClass('newsFeedTitleContainer');
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        titleByTimeContainer.append(title);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.sourceString);
                        titleByTimeContainer.append(source);
                        itemByTimeContainer.append(titleByTimeContainer);
                        var date = $('<div/>').addClass('newsFeedItemDate').html(this.dateString);
                        itemByTimeContainer.append(date);
                        itemByTimeContainer.append($('<div/>').addClass('clear-fix'));

                        if (setupMixItUp) {
                            feedContainer.append(itemByTimeContainer);
                        }
                        else {
                            $('#newsFeedContainer').mixItUp('prepend', itemByTimeContainer, {}).mixItUp('sort', $('#newsFeedContainer').find('.sort.active').attr('data-sort'), true);
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
                        },
                        selectors: {
                            filter: '.newsFilter',
                            sort: '.newsSort',
                            target: '.newsMix'
                        }
                    });

                    $('#newsFeedSort').find('.sort').click(function () {
                        $('#newsFeedSort').find('.sort').removeClass('active');
                        $(this).addClass('active');
                    });

                }
                else {
                    $('#newsFeedContainer').mixItUp('sort', $('.newsSort.active').attr('data-sort'), true);
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