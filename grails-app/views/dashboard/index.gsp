<%@ page import="stocks.RoleHelper" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="site.title"/></title>

    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui', file: 'kendo.dataviz.min.js')}"></script>
    <style>


    .sparkLine {
        /*margin-top:7px;*/
        /*clear:both;*/
        float: left;
        font-size: 24px !important;
        width: 80px;
        text-align: left;
        margin-right: 10px;
        overflow: hidden;
        height: 30px !important;
        margin-top: 5px;
        margin-bottom: 5px;
        background: #fbfbfb;

    }

    .sparkLine path {
        fill: transparent;
    }

    .sparkLine span {
        height: 30px !important;
    }

    .sparkLine svg {
        height: 25px !important;
    }

    .sparkLine svg g g path {
        stroke-width: 1.5 !important;

    }
    </style>
</head>

<body>
<div class="container">
    <g:render template="dashLets/selectedSymbolsContainer"/>
    <g:render template="dashLets/groups"/>

    <div class="row">
        <div class="col-sm-4">
            <g:render template="dashLets/marketView"/>
            <g:render template="dashLets/announcements"/>
            <g:render template="dashLets/analysis"/>
        </div>

        <div class="col-sm-8">
            <div class="row">

                <div class="col-sm-5">
                    <g:render template="dashLets/rates"/>
                </div>

                <div class="col-sm-7">
                    <g:render template="dashLets/heatMap"/>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <g:render template="dashLets/twits"/>
                    <div class="row">
                        <div class="col-sm-12">
                            <g:render template="dashLets/newsFeed"/>
                        </div>
                    </div>
                </div>
            </div>
            <g:render template="dashLets/toolButtons"/>
        </div>

        %{--<div class="col-sm-3">--}%
        %{--<g:render template="dashLets/buttons/screener"/>--}%
        %{--<g:render template="dashLets/buttons/backTest"/>--}%
        %{--<g:render template="dashLets/buttons/alerting"/>--}%
        %{--<g:render template="dashLets/buttons/portfolio"/>--}%
        %{--<g:render template="dashLets/buttons/calculator"/>--}%
        %{--<g:render template="dashLets/buttons/correlation"/>--}%
        %{--</div>--}%
    </div>
</div>

</body>
</html>
