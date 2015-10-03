<%@ page import="stocks.feed.ExternalNewsService" %>
<style>

.newsFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="animatedSort.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="newsFeedTimer" style="display: none"></div>

<div id="newsFeedContainer" class="dashLet darkBlue newsFeedContainer k-rtl">
    <h2><i class="fa fa-newspaper-o"></i> <g:message code="newsFeed.title"/></h2>


    <div id="newsFeedTabStrip">
        <ul>
            <li class="k-state-active">
                <g:message code="all"/>
            </li>
            <g:each in="${ExternalNewsService.categoryList}">
                <li>
                    <g:message code="newsCategory.${it}"/>
                </li>
            </g:each>
        </ul>

        <div>
            <div id="newsFeedItems_all" class="newsFeedItems">
                <form:loading/>
            </div>
        </div>
        <g:each in="${ExternalNewsService.categoryList}">
            <div>
                <div id="newsFeedItems_${it}" class="newsFeedItems">
                    <form:loading/>
                </div>
            </div>
        </g:each>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'externalNews', action: 'archive')}"><g:message
                code="newsFeed.archive"/></a>
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
    function showNewsFeed(type) {
        $.ajax({
            url: '${createLink(action: 'news')}/' + type + '?t=' + new Date().getTime(),
            success: function (response) {
                var container = $('#newsFeedItems_' + type);
                container.find('.loading').remove();
                $.each(response.data, function () {
                    var oldItem = container.find('[data-id=' + this.identifier + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.newsFeedItemDate').html(this.dateString);
                        oldItem.attr('data-click', this.clickCount);
                    }
                    else {
                        var itemContainer = $('<div/>').addClass('newsMix').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageContainer = $('<div/>').addClass('newsFeedImageContainer');
                        imageContainer.append($('<img/>').attr('src', '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('height', '40px'));
                        itemContainer.append(imageContainer);
                        var titleContainer = $('<div/>').addClass('newsFeedTitleContainer');
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        titleContainer.append(title);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.sourceString);
                        titleContainer.append(source);
                        itemContainer.append(titleContainer);
                        var date = $('<div/>').addClass('newsFeedItemDate').html(this.dateString);
                        itemContainer.append(date);
                        itemContainer.append($('<div/>').addClass('clear-fix'));

                        container.append(itemContainer);
                    }

                });

                $('.newsFeedItems a').unbind('click').click(function (event) {
                    event.preventDefault();
                    newsLinkClick(this);
                });

                container.animatedSort();
            }
        });
    }

    $(document).ready(function () {
        showNewsFeed('all');
        <g:each in="${ExternalNewsService.categoryList}">
        showNewsFeed('${it}');
        </g:each>
        $("#newsFeedTabStrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            },
            show: function (e) {
                var container = $('#' + $(e.item).attr('aria-controls')).find('.newsFeedItems');
                container.find('.mix').css('opacity', 0).animate({'opacity': 1}, 700);
                container.animatedSort();
                $('html, body').stop().animate({
                    scrollTop: 0
                }, 1000);
            }
        });

        $('#newsFeedTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                var timer = $('#newsFeedTimer');
                timer.timer('stop');
                showNewsFeed('all');
                <g:each in="${ExternalNewsService.categoryList}">
                showNewsFeed('${it}');
                </g:each>
                timer.timer('start');
            }
        });
    });
</script>