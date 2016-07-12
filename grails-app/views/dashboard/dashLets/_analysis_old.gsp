<style>
#analysisFeedContainer .mix {
    display: none;
}

#analysisFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="jquery.mixitup.js"/>
<asset:javascript src="jquery.mixitup-pagination.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="analysisFeedTimer" style="display: none"></div>

<div id="analysisFeedContainer" class="dashLet green analysisFeedContainer">
    <h2 style="float:right"><i class="fa fa-bullseye"></i> <g:message code="analysisFeed.title"/></h2>

    <div id="analysisFeedSort">

        <span style="font-size:12px;color: dimgrey"><g:message code="analysisFeed.sortText"/></span>

        <div class="sort analysisSort active" data-sort="time:desc"><g:message code="analysisFeed.newest"/></div>

        <div class="sort analysisSort" data-sort="click:desc"><g:message code="analysisFeed.mostClicked"/></div>

        %{--<div class="sort" data-sort="random"><g:message code="analysisFeed.random"/></div>--}%
    </div>

    <div class="clear-fix"></div>

    <div id="analysisFeedFilters"></div>

    <div id="analysisFeedItems" class="analysisFeedItems">
        <form:loading/>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'externalAnalysis', action: 'archive')}"><g:message
                code="analysisFeed.archive"/></a>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function analysisLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalAnalysis', action: 'view')}/' + $(item).parent().parent().attr('data-id') + '?t=' + Math.round(new Date().getTime()/30000),
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

    function parseAnalysisFeed(setupMixItUp) {
        $.ajax({
            url: '${createLink(action: 'analysis')}?t=' + Math.round(new Date().getTime()/30000),
            success: function (response) {

                var filtersContainer = $('#analysisFeedFilters');
                if (filtersContainer.text() == '') {
                    filtersContainer.append($('<div/>').addClass('analysisFilter').addClass('filter').attr('data-filter', 'all').html('${message(code:'analysisFeed.all')}'));
                    $.each(response.categories, function () {
                        var filterItem = $('<div/>').addClass('analysisFilter').addClass('filter').attr('data-filter', '.' + this.value).html(this.text);
                        filtersContainer.append(filterItem);
                    });
                    $('#analysisFeedSort').fadeIn(500);
                    filtersContainer.fadeIn(500);
                }
                var feedContainer = $('#analysisFeedItems');
                if (setupMixItUp) {
                    feedContainer.html('');
                }
                $.each(response.data, function () {
                    var oldItem = $('[data-id=' + this.identifier + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.analysisFeedItemDate').html(this.dateString);
                        oldItem.attr('data-click', this.clickCount);
                    }
                    else {

                        var itemByTimeContainer = $('<div/>').addClass('analysisMix').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageByTimeContainer = $('<div/>').addClass('analysisFeedImageContainer');
                        imageByTimeContainer.append($('<img/>').attr('src', this.imageUrl ? this.imageUrl : '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('width', '40px'));
                        itemByTimeContainer.append(imageByTimeContainer);
                        var titleByTimeContainer = $('<div/>').addClass('analysisFeedTitleContainer');
                        var title = $('<a/>').addClass('analysisFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        titleByTimeContainer.append(title);
                        var source = $('<div/>').addClass('analysisFeedItemSource').html(this.sourceString);
                        titleByTimeContainer.append(source);
                        itemByTimeContainer.append(titleByTimeContainer);
                        var date = $('<div/>').addClass('analysisFeedItemDate').html(this.dateString);
                        itemByTimeContainer.append(date);
                        itemByTimeContainer.append($('<div/>').addClass('clear-fix'));

                        if (setupMixItUp) {
                            feedContainer.append(itemByTimeContainer);
                        }
                        else {
                            $('#analysisFeedContainer').mixItUp('prepend', itemByTimeContainer, {}).mixItUp('sort', $('.analysisSort.active').attr('data-sort'), true);
                        }
                    }

                });

                if (setupMixItUp) {

                    $('#analysisFeedContainer').mixItUp({
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
                            filter: '.analysisFilter',
                            sort: '.analysisSort',
                            target: '.analysisMix'
                        }
                    });

                    $('#analysisFeedSort').find('.sort').click(function () {
                        $('#analysisFeedSort').find('.sort').removeClass('active');
                        $(this).addClass('active');
                    });

                }
                else {
                    $('#analysisFeedContainer').mixItUp('sort', $('#analysisFeedContainer').find('.sort.active').attr('data-sort'), true);
                }

                $('.analysisFeedItems a').unbind('click').click(function (event) {
                    event.preventDefault();
                    analysisLinkClick(this);
                });

                $('#analysisFeedTimer').timer('start');

            }
        });
    }
    parseAnalysisFeed(true);
    $(document).ready(function () {
        $('#analysisFeedTimer').timer({
            delay: 120000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#analysisFeedTimer').timer('stop');
                parseAnalysisFeed(false);
            }
        });
    });
</script>