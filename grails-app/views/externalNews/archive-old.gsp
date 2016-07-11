<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 9/14/2015
  Time: 3:21 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="newsFeed.archive"/></title>
    <style>
    #archiveByTimeContainer .mix {
        display: none;
    }

    #archiveByTimeItems .loading {
        display: block;

    }

    #archiveByClickContainer .mix {
        display: none;
    }

    #archiveByClickItems .loading {
        display: block;

    }
    </style>
    <asset:javascript src="jquery.mixitup.js"/>
    <asset:javascript src="jquery.mixitup-pagination.js"/>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
</head>

<body>
<div id="newsFeedTimer"></div>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsFeed.archive'), url: createLink(action: 'archive')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-2">
            <g:render template="filter"/>
        </div>

        <div class="col-xs-5 ">
            <div class="dashLet cyan ">
                <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="newsFeed.byTime.title"/></h2>
                <div id="archiveByTimeContainer" class="newsFeedContainer">

                    <div class="clear-fix"></div>

                    <div id="archiveByTimeItems" class="newsFeedItems">
                        <form:loading id="loadArchiveByTime"/>
                    </div>
                </div>

                <div class="pagerByTime dashletFooter">
                </div>
            </div>
        </div>

        <div class="col-xs-5">
            <div class="dashLet green">
                <h2 style="float:right"><i class="fa fa-eye"></i> <g:message code="newsFeed.byClick.title"/></h2>
                <div id="archiveByClickContainer" class="newsFeedContainer">

                    <div class="clear-fix"></div>

                    <div id="archiveByClickItems" class="newsFeedItems">
                        <form:loading id="loadArchiveByClick"/>
                    </div>
                </div>

                <div class="pagerByClick dashletFooter">
                </div>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    var resetNews = false;

    function newsLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalNews', action: 'view')}/' + $(item).parent().parent().attr('data-id') + '?t=' + Math.round(new Date().getTime()/30000),
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
            url: '${createLink(action: 'jsonList')}?t=' + Math.round(new Date().getTime()/30000),
            type: 'POST',
            data: $('#filterForm').serialize(),
            success: function (response) {
                var feedByTimeContainer = $('#archiveByTimeItems');
                var feedByClickContainer = $('#archiveByClickItems');
                if (setupMixItUp || resetNews) {
                    feedByTimeContainer.html('');
                    feedByClickContainer.html('');
                    resetNews = false;
                }

                $.each(response.data, function () {
                    var oldItem = $('[data-id=' + this.identifier + ']');
                    if (oldItem.length > 0) {
                        oldItem.find('.newsFeedItemDate').html(this.dateString);
                        var clickCount = this.clickCount;
                        $.each(oldItem, function () {
                            $(this).find('.newsFeedItemClick').html(clickCount + ' ${message(code:'visit')}');
                            $(this).attr('data-click', clickCount);
                        })
                    }
                    else {
                        var itemByTimeContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageByTimeContainer = $('<div/>').addClass('newsFeedImageContainer');
                        imageByTimeContainer.append($('<img/>').attr('src', '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('height', '48px'));
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

                        var itemByClickContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var imageByClickContainer = $('<div/>').addClass('newsFeedImageContainer');
                        imageByClickContainer.append($('<img/>').attr('src', '/images/' + this.source + '.jpg').attr('alt', this.sourceString).attr('height', '48px'));
                        itemByClickContainer.append(imageByClickContainer);
                        var titleByClickContainer = $('<div/>').addClass('newsFeedTitleContainer');
                        title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        titleByClickContainer.append(title);
                        source = $('<div/>').addClass('newsFeedItemSource').html(this.sourceString);
                        titleByClickContainer.append(source);
                        itemByClickContainer.append(titleByClickContainer);
                        date = $('<div/>').addClass('newsFeedItemClick').html(this.clickCount + ' ${message(code:'visit')}');
                        itemByClickContainer.append(date);
                        itemByClickContainer.append($('<div/>').addClass('clear-fix'));

                        if (setupMixItUp) {
                            feedByTimeContainer.append(itemByTimeContainer);
                            feedByClickContainer.append(itemByClickContainer);
                        }
                        else {
                            var pageByTime = parseInt($('.pagerByTime .pager.active span').text());
                            var pageByClick = parseInt($('.pagerByClick .pager.active span').text());
                            $('#archiveByTimeContainer').mixItUp('prepend', itemByTimeContainer, {});//.mixItUp('sort', 'time:desc', true).mixItUp('paginate', pageByTime);
                            $('#archiveByClickContainer').mixItUp('prepend', itemByClickContainer, {});//.mixItUp('sort', 'click:desc', true).mixItUp('paginate', pageByClick);
                        }
                    }

                });

                if (setupMixItUp) {

                    $('#archiveByTimeContainer').mixItUp({
                        pagination: {
                            limit: 10,
                            prevButtonHTML: '<i class="fa fa-chevron-right"></i>',
                            nextButtonHTML: '<i class="fa fa-chevron-left"></i>'
                        },
                        layout: {
                            display: 'block'
                        },
                        load: {
                            sort: 'time:desc'
                        },
                        controls: {
                            toggleFilterButtons: false
                        },
                        selectors: {
                            pagersWrapper: '.pagerByTime'
                        }
                    });

                    $('#archiveByClickContainer').mixItUp({
                        pagination: {
                            limit: 10,
                            prevButtonHTML: '<i class="fa fa-chevron-right"></i>',
                            nextButtonHTML: '<i class="fa fa-chevron-left"></i>'
                        },
                        layout: {
                            display: 'block'
                        },
                        load: {
                            sort: 'click:desc'
                        },
                        controls: {
                            live: true,
                            toggleFilterButtons: false
                        },
                        selectors: {
                            pagersWrapper: '.pagerByClick'
                        }
                    });

                }
                else {
                    var pageByTime = parseInt($('.pagerByTime .pager.active span').text());
                    var pageByClick = parseInt($('.pagerByClick .pager.active span').text());
                    $('#archiveByTimeContainer').mixItUp('sort', 'time:desc', true).mixItUp('paginate', pageByTime);
                    $('#archiveByClickContainer').mixItUp('sort', 'click:desc', true).mixItUp('paginate', pageByClick);
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
</body>
</html>