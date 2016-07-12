<%@ page import="stocks.graph.MaterialGraphService" %>
<style>

.twitsFeedItems .loading {
    display: block;

}
</style>
<asset:javascript src="animatedSort.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>


<div id="twitsFeedTimer" style="display: none"></div>

<div id="twitsFeedContainer" class="dashLet white twitsFeedContainer k-rtl">
    <h2><i class="fa fa-twitter"></i> <g:message code="twitter.title"/></h2>


    <div id="twitsFeedTabStrip">
        <ul>
        %{--<li class="k-state-active">--}%
        %{--<g:message code="all"/>--}%
        %{--</li>--}%
            <g:each in="${MaterialGraphService.ENABLED_TYPES}" var="type" status="indexer">
                <li class="${indexer == 0 ? 'k-state-active' : ''}">
                    <g:message code="materialType.${type}"/>
                </li>
            </g:each>
        </ul>

    %{--<div>--}%
    %{--<div id="twitsFeedItems_Material" class="twitsFeedItems">--}%
    %{--<form:loading/>--}%
    %{--</div>--}%
    %{--</div>--}%
        <g:each in="${MaterialGraphService.ENABLED_TYPES}">
            <div>
                <div id="twitsFeedItems_${it}" class="twitsFeedItems">
                    <form:loading/>
                </div>
            </div>
        </g:each>
    </div>

    %{--<div class="dashletFooter">--}%
    %{--<a href="${createLink(controller: 'user', action: 'home')}"><g:message--}%
    %{--code="twitter.title"/></a>--}%
    %{--</div>--}%
</div>
<script language="javascript" type="text/javascript">

    function showTwitsFeed(type) {
        $.ajax({
            url: '${createLink(action: 'twits')}/' + type + '?t=' +  Math.round(new Date().getTime()/30000),
            success: function (response) {
                var container = $('#twitsFeedItems_' + type);
//                container.find('.loading').remove();
                container.html('');
                var indexer = 0;
                $.each(response, function () {
//                    var oldItem = container.find('[data-id=' + this.identifier + ']');
//                    if (oldItem.length > 0) {
//                        oldItem.find('.twitsFeedItemDate').html(this.dateString);
//                        oldItem.attr('data-click', this.clickCount);
//                    }
//                    else {
                    var itemContainer = $('<div/>').addClass('twitsMix').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                    var imageContainer = $('<div/>').addClass('twitsFeedImageContainer');
                    imageContainer.append($('<img/>').attr('src', '/image/index/' + this.imageId).attr('alt', this.title));
                    itemContainer.append(imageContainer);
                    var titleContainer = $('<div/>').addClass('twitsFeedTitleContainer');
                    var title = $('<a/>').addClass('twitsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                    titleContainer.append(title);
                    var source = $('<div/>').addClass('twitsFeedItemSource').html(this.description);
                    titleContainer.append(source);
                    itemContainer.append(titleContainer);
                    var date = $('<div/>').addClass('twitsFeedItemDate').html('<i class="fa fa-calendar"></i> ' + this.dateString);
                    itemContainer.append(date);
                    var readMore = $('<a/>').addClass('twitsFeedReadMore').attr('target', '_blank').attr('href', this.link).html('<label>${message(code:'readMore')}</label> <span><i class="fa fa-arrow-left"></i><i class="fa fa-arrow-left"></i></span>');
                    itemContainer.append(readMore);
                    itemContainer.append($('<div/>').addClass('clear-fix'));

                    container.append(itemContainer);
//                    }

                });

                $('.twitsFeedReadMore').mouseover(function () {
                    $(this).find('span i').stop().animate({marginRight: 0});
                }).mouseout(function () {
                    $(this).find('span i').stop().animate({marginRight: -12});
                });

//                container.animatedSort();
            }
        });
    }

    $(document).ready(function () {
        showTwitsFeed('Material');
        <g:each in="${MaterialGraphService.ENABLED_TYPES}">
        showTwitsFeed('${it}');
        </g:each>
        $("#twitsFeedTabStrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            },
            show: function (e) {
//                var container = $('#' + $(e.item).attr('aria-controls')).find('.twitsFeedItems');
//                container.find('.mix').css('opacity', 0).animate({'opacity': 1}, 700);
//                container.animatedSort();
//                $('html, body').stop().animate({
//                    scrollTop: 0
//                }, 1000);
            }
        });

        $('#twitsFeedTimer').timer({
            delay: 120000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                var timer = $('#twitsFeedTimer');
                timer.timer('stop');
                showTwitsFeed('Material');
                <g:each in="${MaterialGraphService.ENABLED_TYPES}">
                showTwitsFeed('${it}');
                </g:each>
                timer.timer('start');
            }
        });
    });
</script>