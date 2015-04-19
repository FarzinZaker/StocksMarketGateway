<%@ page import="grails.converters.JSON; java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.manage"/></title>
    <asset:stylesheet src="portfolioManagement.less"/>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingQueryController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="portfolio.manage"/></h1>

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
                            {type: "button", text: "<g:message code="portfolio.view.title"/>", click: btnViewPortfolioClick}
                        ]
                    });
                });

                function btnPortfolioListClick(e) {
                    window.location.href = "${createLink(action: 'list')}/";
                }

                function btnViewPortfolioClick(e) {
                    window.location.href = "${createLink(action: 'portfolioView')}/${params.id}";
                }

            </script>

            <g:render template="manage/editors"/>
            <g:render template="manage/detailsGrid"/>
            <g:render template="manage/grid"/>
        </div>
    </div>
</div>
</body>
</html>