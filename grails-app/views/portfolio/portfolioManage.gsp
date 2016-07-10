<%@ page import="grails.converters.JSON; java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.manage"/></title>
    <asset:stylesheet src="portfolioManagement.less"/>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui/jalali', file: 'JalaliDate.js')}"></script>
    <style>
        #itemDialog{
            padding: 0;
        }
        #itemDialog .container{
            width: 630px;
            padding-top:20px;
        }
        #itemDialog .row{
            clear:both;
        }

        #itemDialog .col-xs-6{
            width:290px !important;
            padding-right:10px;
            padding-left:10px;
        }

        #itemDialog .col-xs-12{
            width:620px !important;
            padding-right:10px;
            padding-left:10px;
        }

        #itemDialog .toolbar{
            padding-top:0px;
            padding-bottom:10px;
        }
    </style>
</head>

<body>
<div class="container" id="ngController" ng-controller="alertingQueryController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: portfolio.name, url: createLink(controller: 'portfolio', action: 'build', id: portfolio.id)],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'portfolio.manage'), url: createLink(controller: 'portfolio', action: 'portfolioManage', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
                %{--<i class="fa fa-shopping-cart"></i>--}%
                %{--<g:message code="portfolio.manage"/>--}%
            %{--</h1>--}%

            <div class="k-rtl k-header">
                <div id="toolbar"></div>
            </div>

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <div class="hidden">
                <ul id="propertyContextMenu">
                    <li data-key="create"><i class="fa fa-icon fa-plus"></i> <g:message code="create"/></li>
                    <li data-key="separator" class="k-separator"></li>
                    <li data-key="edit"><i class="fa fa-icon fa-edit"></i> <g:message code="edit"/></li>
                    <li data-key="delete"><i class="fa fa-icon fa-remove"></i> <g:message code="delete"/></li>
                </ul>
            </div>

            <script>

                useOldConfirm = true;

                $(document).ready(function () {

                    resetConfirm();

                    $("#toolbar").kendoToolBar({
                        items: [
                            {type: "button", text: "<g:message code="portfolio.list.title"/>", click: btnPortfolioListClick},
                            {type: "button", text: "<g:message code="portfolio.view.title"/>", click: btnViewPortfolioClick},
                            {type: "button", text: "<g:message code="portfolio.reports.benefitLoss"/>", click: btnBenefitLossClick},
                            {type: "button", text: "<g:message code="portfolio.actions.import"/>", click: btnImportPortfolioActionsClick}
                        ]
                    });
                });

                function btnPortfolioListClick(e) {
                    window.location.href = "${createLink(action: 'list')}/";
                }

                function btnViewPortfolioClick(e) {
                    window.location.href = "${createLink(action: 'portfolioView')}/${params.id}";
                }

                function btnBenefitLossClick(e) {
                    window.location.href = "${createLink(action: 'benefitLoss')}/${params.id}";
                }

                function btnImportPortfolioActionsClick(e){
                    var win = $('#importWindow').html('')
                            .kendoWindow({
                                title: '${message(code:'portfolio.actions.import')}',
                                width: '500px',
                                content: '${createLink(controller: 'portfolioAction', action: 'portfolioActionImport')}/' + ${params.id},
                                modal: true,
                                close: function (e) {
                                }
                            }).data('kendoWindow').center().open();
                    win.bind("refresh", function () {
                        win.center();
                        win.open();
                    });
                }

            </script>

            <g:render template="manage/editors"/>
            <g:render template="manage/dialog"/>
            <g:render template="manage/detailsGrid"/>
            <g:render template="manage/grid"/>
        </div>
    </div>
</div>
<div class="hidden k-rtl">
    <div id="importWindow"></div>
</div>
</body>
</html>