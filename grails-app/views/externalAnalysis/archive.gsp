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
    <title><g:message code="analysisFeed.archive"/></title>
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
                    [text: message(code: 'analysisFeed.archive'), url: createLink(action: 'archive')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-2">
            <g:render template="filter"/>
        </div>

        <div class="col-xs-5 ">
            <div class="dashLet white k-rtl">
                <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="newsFeed.byTime.title"/></h2>

                <div class="newsFeedContainer">
                    <div class="newsFeedItemsContainer">
                        <div id="newsByTimeListView" class="newsFeedItems"></div>
                    </div>
                </div>

                <div id="newsByTimePager" class="k-pager-wrap"></div>
            </div>
        </div>

        <div class="col-xs-5">
            <div class="dashLet white k-rtl">
                <h2 style="float:right"><i class="fa fa-eye"></i> <g:message code="newsFeed.byClick.title"/></h2>


                <div class="newsFeedContainer">
                    <div class="newsFeedItemsContainer">
                        <div id="newsByClickListView" class="newsFeedItems"></div>
                    </div>
                </div>

                <div id="newsByClickPager" class="k-pager-wrap"></div>
            </div>
        </div>
    </div>
</div>


<script type="text/x-kendo-tmpl" id="newsByTimeTemplate">
<div class="mix">
    %{--<div class="newsFeedImageContainer">--}%
%{--<img width="48px" src="/images/#:source#.jpg" alt="#:sourceString#"/>--}%
%{--</div>--}%
    <a class="newsFeedTitleContainer" target="_blank" href="#:link#">
        <div class="newsFeedItemTitle">#:title#</div>
        <div class="newsFeedItemSource">#:sourceString#</div>
        <div class="newsFeedItemDate">#:dateString#</div>
    </a>
    <div class="clear-fix"></div>
</div>
</script>

<script type="text/x-kendo-tmpl" id="newsByClickTemplate">
<div class="mix">
    %{--<div class="newsFeedImageContainer">--}%
%{--<img width="48px" src="/images/#:source#.jpg" alt="#:sourceString#"/>--}%
%{--</div>--}%
    <a class="newsFeedTitleContainer" target="_blank" href="#:link#">
        <div class="newsFeedItemTitle">#:title#</div>
        <div class="newsFeedItemSource">#:sourceString#</div>
        <div class="newsFeedItemDate">#:dateString#</div>
    </a>
    <div class="clear-fix"></div>
</div>
</script>


<script>

    function newsLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalAnalysis', action: 'view')}/' + $(item).attr('data-id') + '?t=' + Math.round(new Date().getTime()/30000),
            success: function (response) {
                var win = window.open($(item).attr('href'), '_blank');
                if (win) {
                    win.focus();
                } else {
                    window.location.href = $(item).attr('href');
                }
            }
        });
    }

    function refreshData() {
        var newsByTimeListView = $('#newsByTimeListView').data('kendoListView');
        newsByTimeListView.dataSource.read();   // added line
        newsByTimeListView.refresh();
        var newsByClickListView = $('#newsByClickListView').data('kendoListView');
        newsByClickListView.dataSource.read();   // added line
        newsByClickListView.refresh();
        replaceClickEvents();
        $('#newsFeedTimer').timer('start');
    }

    function replaceClickEvents() {
        $('.mix a').unbind('click').click(function (event) {
            event.preventDefault();
            newsLinkClick(this);
        });
    }

    $(document).ready(function () {
        var newsByTimeDataSource = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: '${createLink(action: 'jsonList')}?sort=date&order=desc',
                    dataType: "json",
                    type: "POST"

                },
                parameterMap: function (data, action) {
                    if (action === "read") {
                        data = $('#filterForm').serialize() + "&page=" + data.page + "&pageSize=" + data.pageSize + "&take=" + data.take + "&skip=" + data.skip;
                        return data;
                    } else {
                        return data;
                    }
                }
            },
            schema: {
                data: "data", // records are returned in the "data" field of the response
                total: "total" // total number of records is in the "total" field of the response
            },
            serverPaging: true,
            pageSize: 10
        });
        var newsByClickDataSource = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: '${createLink(action: 'jsonList')}?sort=clickCount&order=desc',
                    dataType: "json",
                    type: "POST"

                },
                parameterMap: function (data, action) {
                    if (action === "read") {
                        data = $('#filterForm').serialize() + "&page=" + data.page + "&pageSize=" + data.pageSize + "&take=" + data.take + "&skip=" + data.skip;
                        return data;
                    } else {
                        return data;
                    }
                }
            },
            schema: {
                data: "data", // records are returned in the "data" field of the response
                total: "total" // total number of records is in the "total" field of the response
            },
            serverPaging: true,
            pageSize: 10
        });

        $("#newsByTimePager").kendoPager({
            dataSource: newsByTimeDataSource
        });

        $("#newsByClickPager").kendoPager({
            dataSource: newsByClickDataSource
        });

        $("#newsByTimeListView").kendoListView({
            dataSource: newsByTimeDataSource,
            template: kendo.template($("#newsByTimeTemplate").html()),
            dataBound: function () {
                replaceClickEvents();
            }
        });

        $("#newsByClickListView").kendoListView({
            dataSource: newsByClickDataSource,
            template: kendo.template($("#newsByClickTemplate").html()),
            dataBound: function () {
                replaceClickEvents();
            }
        });

        $('#newsFeedTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                $('#newsFeedTimer').timer('stop');
                refreshData();
            }
        });

    });
</script>
</body>
</html>