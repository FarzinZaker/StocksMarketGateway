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

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsFeed.archive'), url: createLink(action: 'archive')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-2">
            <g:render template="filter"/>
        </div>

        <div class="col-xs-5">
            <div id="archiveByTimeContainer" class="dashLet cyan newsFeedContainer">
                <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="newsFeed.byTime.title"/></h2>

                <div class="clear-fix"></div>

                <div id="archiveByTimeItems" class="newsFeedItems">
                    <form:loading id="loadArchiveByTime"/>
                </div>
            </div>
        </div>

        <div class="col-xs-5">
            <div id="archiveByClickContainer" class="dashLet green newsFeedContainer">
                <h2 style="float:right"><i class="fa fa-eye"></i> <g:message code="newsFeed.byClick.title"/></h2>

                <div class="clear-fix"></div>

                <div id="archiveByClickItems" class="newsFeedItems">
                    <form:loading id="loadArchiveByClick"/>
                </div>
            </div>
        </div>
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
        $(item).parent().attr('data-click', parseInt($(item).parent().attr('data-click')));
    }

    function parseRSS(setupMixItUp) {
        $.ajax({
            url: '${createLink(controller: 'dashboard', action: 'news')}?t=' + new Date().getTime(),
            success: function (response) {
                var feedByTimeContainer = $('#archiveByTimeItems');
                var feedByClickContainer = $('#archiveByClickItems');
                if (setupMixItUp) {
                    feedByTimeContainer.html('');
                    feedByClickContainer.html('');
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
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        itemByTimeContainer.append(title);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.source);
                        itemByTimeContainer.append(source);
                        var date = $('<div/>').addClass('newsFeedItemDate').html(this.dateString);
                        itemByTimeContainer.append(date);

                        var itemByClickContainer = $('<div/>').addClass('mix').addClass(this.category).attr('data-id', this.identifier).attr('data-time', this.time).attr('data-click', this.clickCount);
                        var title = $('<a/>').addClass('newsFeedItemTitle').attr('target', '_blank').attr('href', this.link).html(this.title);
                        itemByClickContainer.append(title);
                        var source = $('<div/>').addClass('newsFeedItemSource').html(this.source);
                        itemByClickContainer.append(source);
                        var date = $('<div/>').addClass('newsFeedItemClick').html(this.clickCount + ' ${message(code:'visit')}');
                        itemByClickContainer.append(date);

                        if (setupMixItUp) {
                            feedByTimeContainer.append(itemByTimeContainer);
                            feedByClickContainer.append(itemByClickContainer);
                        }
                        else{
                            $('#archiveByTimeContainer').mixItUp('prepend', itemByTimeContainer, {}).mixItUp('sort', 'time:desc', true);
                            $('#archiveByClickContainer').mixItUp('prepend', itemByClickContainer, {}).mixItUp('sort', 'click:desc', true);
                        }
                    }

                });

                if (setupMixItUp) {

                    $('#archiveByTimeContainer').mixItUp({
                        pagination: {
                            limit: 20
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

                    $('#archiveByClickContainer').mixItUp({
                        pagination: {
                            limit: 20
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
                        }
                    });

                }
                else{
                    $('#archiveByTimeContainer').mixItUp('sort', 'time:desc', true);
                    $('#archiveByClickContainer').mixItUp('sort', 'click:desc', true);
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