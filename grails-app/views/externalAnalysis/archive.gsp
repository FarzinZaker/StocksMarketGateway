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
<div id="analysisFeedTimer"></div>

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
            <div class="dashLet cyan k-rtl">
                <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="analysisFeed.byTime.title"/></h2>

                <div class="analysisFeedContainer">
                    <div id="analysisByTimeListView" class="analysisFeedItems"></div>
                </div>

                <div id="analysisByTimePager" class="k-pager-wrap"></div>
            </div>
        </div>

        <div class="col-xs-5">
            <div class="dashLet green k-rtl">
                <h2 style="float:right"><i class="fa fa-eye"></i> <g:message code="analysisFeed.byClick.title"/></h2>


                <div class="analysisFeedContainer">
                    <div id="analysisByClickListView" class="analysisFeedItems"></div>
                </div>

                <div id="analysisByClickPager" class="k-pager-wrap"></div>
            </div>
        </div>
    </div>
</div>


<script type="text/x-kendo-tmpl" id="analysisByTimeTemplate">
<div class="mix">
    <div class="analysisFeedImageContainer">
        <img width="48px" src="/images/#:source#.jpg" alt="#:sourceString#"/>
    </div>
    <div class="analysisFeedTitleContainer">
        <a class="analysisFeedItemTitle" target="_blank" href="#:link#">#:title#</a>
        <div class="analysisFeedItemSource">#:sourceString#</div>
    </div>
    <div class="analysisFeedItemDate">#:dateString#</div>
    <div class="clear-fix"></div>
</div>
</script>

<script type="text/x-kendo-tmpl" id="analysisByClickTemplate">
<div class="mix">
    <div class="analysisFeedImageContainer">
        <img width="48px" src="/images/#:source#.jpg" alt="#:sourceString#"/>
    </div>
    <div class="analysisFeedTitleContainer">
        <a class="analysisFeedItemTitle" target="_blank" data-id="#:identifier#" href="#:link#">#:title#</a>
        <div class="analysisFeedItemSource">#:sourceString#</div>
    </div>
    <div class="analysisFeedItemClick">#:clickCount# ${message(code: 'visit')}</div>
    <div class="clear-fix"></div>
</div>
</script>


<script>

    function analysisLinkClick(item) {
        $.ajax({
            url: '${createLink(controller: 'externalAnalysis', action: 'view')}/' + $(item).attr('data-id') + '?t=' + new Date().getTime(),
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
        var analysisByTimeListView = $('#analysisByTimeListView').data('kendoListView');
        analysisByTimeListView.dataSource.read();   // added line
        analysisByTimeListView.refresh();
        var analysisByClickListView = $('#analysisByClickListView').data('kendoListView');
        analysisByClickListView.dataSource.read();   // added line
        analysisByClickListView.refresh();
        replaceClickEvents();
        $('#analysisFeedTimer').timer('start');
    }

    function replaceClickEvents() {
        $('.mix a').unbind('click').click(function (event) {
            event.preventDefault();
            analysisLinkClick(this);
        });
    }

    $(document).ready(function () {
        var analysisByTimeDataSource = new kendo.data.DataSource({
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
        var analysisByClickDataSource = new kendo.data.DataSource({
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

        $("#analysisByTimePager").kendoPager({
            dataSource: analysisByTimeDataSource
        });

        $("#analysisByClickPager").kendoPager({
            dataSource: analysisByClickDataSource
        });

        $("#analysisByTimeListView").kendoListView({
            dataSource: analysisByTimeDataSource,
            template: kendo.template($("#analysisByTimeTemplate").html()),
            dataBound: function () {
                replaceClickEvents();
            }
        });

        $("#analysisByClickListView").kendoListView({
            dataSource: analysisByClickDataSource,
            template: kendo.template($("#analysisByClickTemplate").html()),
            dataBound: function () {
                replaceClickEvents();
            }
        });

        $('#analysisFeedTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                $('#analysisFeedTimer').timer('stop');
                refreshData();
            }
        });

    });
</script>
</body>
</html>