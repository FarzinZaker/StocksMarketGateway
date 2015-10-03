<%@ page import="stocks.feed.ExternalAnalysisService" %>

<div id="analysisFeedTimer" style="display: none"></div>

<div id="analysisFeedContainer" class="dashLet green analysisFeedContainer k-rtl">
    <h2><i class="fa fa-bullseye"></i> <g:message code="analysisFeed.title"/></h2>


    <div id="analysisFeedTabStrip">
        <ul>
            <li class="k-state-active">
                <g:message code="all"/>
            </li>
            <g:each in="${ExternalAnalysisService.visibleCategoryList}">
                <li>
                    <g:message code="analysisCategory.${it}"/>
                </li>
            </g:each>
        </ul>

        <div>
            <div id="analysisFeedItems_all" class="analysisFeedItems">
                <form:loading/>
            </div>
        </div>
        <g:each in="${ExternalAnalysisService.visibleCategoryList}">
            <div>
                <div id="analysisFeedItems_${it}" class="analysisFeedItems">
                    <form:loading/>
                </div>
            </div>
        </g:each>
    </div>

    <div class="dashletFooter">
        <a href="${createLink(controller: 'externalAnalysis', action: 'archive')}"><g:message
                code="analysisFeed.archive"/></a>
    </div>
</div>
<script language="javascript" type="text/javascript">

    function analysisLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalAnalysis', action: 'view')}/' + $(item).parent().parent().attr('data-id') + '?t=' + new Date().getTime(),
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
    function showAnalysisFeed(type) {
        $.ajax({
            url: '${createLink(action: 'analysis')}/' + type + '?t=' + new Date().getTime(),
            success: function (response) {
                var container = $('#analysisFeedItems_' + type);
                container.find('.loading').remove();
                $.each(response.data, function () {
                    var oldItem = container.find('[data-id=' + this.identifier + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.analysisFeedItemDate').html(this.dateString);
                        oldItem.attr('data-click', this.clickCount);
                    }
                    else {
                        var itemContainer = $('<div/>').addClass('analysisMix').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageContainer = $('<div/>').addClass('analysisFeedImageContainer');
                        imageContainer.append($('<img/>').attr('src', '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('height', '40px'));
                        itemContainer.append(imageContainer);
                        var titleContainer = $('<div/>').addClass('analysisFeedTitleContainer');
                        var title = $('<a/>').addClass('analysisFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        titleContainer.append(title);
                        var source = $('<div/>').addClass('analysisFeedItemSource').html(this.sourceString);
                        titleContainer.append(source);
                        itemContainer.append(titleContainer);
                        var date = $('<div/>').addClass('analysisFeedItemDate').html(this.dateString);
                        itemContainer.append(date);
                        itemContainer.append($('<div/>').addClass('clear-fix'));

                        container.append(itemContainer);
                    }

                });

                $('.analysisFeedItems a').unbind('click').click(function (event) {
                    event.preventDefault();
                    analysisLinkClick(this);
                });

                container.animatedSort();
            }
        });
    }

    $(document).ready(function () {
        showAnalysisFeed('all');
        <g:each in="${ExternalAnalysisService.visibleCategoryList}">
        showAnalysisFeed('${it}');
        </g:each>
        $("#analysisFeedTabStrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            },
            show: function (e) {
                var container = $('#' + $(e.item).attr('aria-controls')).find('.analysisFeedItems');
                container.find('.mix').css('opacity', 0).animate({'opacity': 1}, 700);
                container.animatedSort();
                $('html, body').stop().animate({
                    scrollTop: container.parent().parent().parent().parent().offset().top - 70
                }, 1000);
            }
        });

        $('#analysisFeedTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                var timer = $('#analysisFeedTimer');
                timer.timer('stop');
                showAnalysisFeed('all');
                <g:each in="${ExternalAnalysisService.visibleCategoryList}">
                showAnalysisFeed('${it}');
                </g:each>
                timer.timer('start');
            }
        });
    });
</script>