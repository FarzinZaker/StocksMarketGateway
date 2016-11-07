<div class="topsLet white hideBeforeLoad noPlaceReservation">
    <h2 style="float:right"><i class="fa fa-line-chart"></i> <g:message code="topArticles.title"/></h2>

    <div class="headerItem">
        <form:loading id="topArticlesLoading"/>
        <form:select name="topArticlesPeriod"
                     items="${[1, 7, 30, 0].collect { [text: message(code: "tops.period.${it}"), value: it] }}"
                     style="width:80px;" onchange="loadTopArticles"/>
    </div>

    <div id="topArticlesTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="topArticlesTabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="topArticles.recent.title"/>
                <li>
                    <g:message code="topArticles.mostVisited.title"/>
                </li>
                <li>
                    <g:message code="marketView.topRated.title"/>
                </li>
                <li>
                    <g:message code="marketView.mostCommented.title"/>
                </li>
            </ul>

            <div class="k-state-active">
                <ul id="topArticles_recent" class="materialList">

                </ul>
            </div>

            <div>
                <ul id="topArticles_mostVisited" class="materialList">
                </ul>

            </div>

            <div>
                <ul id="topArticles_topRated" class="materialList">
                </ul>

            </div>

            <div>
                <ul id="topArticles_mostCommented" class="materialList">
                </ul>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $("#topArticlesTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            }
        });
        $('#topArticlesTimer').timer({
            delay: 120000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#topArticlesTimer').timer('stop');
                loadTopArticles();
            }
        });
        loadTopArticles();
    });

    function loadTopArticles() {
        $('#topArticlesLoading').show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'twitter', action: 'topArticles')}',
            data: {period: $('#topArticlesPeriod').data('kendoComboBox').value()}
        }).done(function (response) {
            $('#topArticles_recent').html(response.recent);
            $('#topArticles_mostVisited').html(response.mostVisited);
            $('#topArticles_topRated').html(response.topRated);
            $('#topArticles_mostCommented').html(response.mostCommented);
            $('#topArticlesLoading').hide();
            $('#topArticlesTimer').timer('start');
        });

    }
</script>