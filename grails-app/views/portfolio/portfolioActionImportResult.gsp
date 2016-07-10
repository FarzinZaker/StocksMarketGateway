<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 7/10/2016
  Time: 3:00 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.action.import.result"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: portfolio.name, url: createLink(controller: 'portfolio', action: 'build', id: portfolio.id)],
                    [text: message(code: 'portfolio.manage'), url: createLink(controller: 'portfolio', action: 'portfolioManage', id: portfolio?.id)],
                    [text: '<i class="fa fa-download"></i> ' + message(code: 'portfolio.action.import.result'), url: '#']
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">

            <div class="k-rtl k-header">
                <div id="toolbar"></div>
            </div>

            <table class="portfolioActionImportResult">
                <tr>
                    <th><g:message code="portfolio.action.import.result.itemType"/></th>
                    <th><g:message code="portfolio.action.import.result.property"/></th>
                    <th><g:message code="portfolio.action.import.result.actionType"/></th>
                    <th><g:message code="portfolio.action.import.result.actionDate"/></th>
                    <th><g:message code="portfolio.action.import.result.sharePrice"/></th>
                    <th><g:message code="portfolio.action.import.result.shareCount"/></th>
                    <g:if test="${portfolio.useBroker}">
                        <th><g:message code="portfolio.action.import.result.broker"/></th>
                        <th><g:message code="portfolio.action.import.result.source"/></th>
                    </g:if>
                    <th><g:message code="portfolio.action.import.result.errors"/></th>
                </tr>
                <g:each in="${result}" var="action">
                    <tr class="${action.errors?.size() ? 'hasError' : ''}">
                        <td><g:message code="${action.itemType?.clazz}.label"/></td>
                        <td>${action.property?.propertyName}</td>
                        <td><g:message code="portfolioAction.actionType.${action.actionType?.actionTypeId}"/></td>
                        <td>${action.actionDate}</td>
                        <td><g:formatNumber number="${action.sharePrice}" type="number"/></td>
                        <td><g:formatNumber number="${action.shareCount}" type="number"/></td>
                        <g:if test="${portfolio.useBroker}">
                            <td>
                                <g:if test="${action.transactionSourceType}">
                                    ${action.broker?.brokerName} (<g:message
                                        code="${action.transactionSourceType}.label"/>)
                                </g:if>
                            </td>
                            <td>${action.broker?.brokerName}</td>
                        </g:if>
                        <td>${action.errors?.join('<br/>')}</td>
                    </tr>
                </g:each>
            </table>
        </div>
    </div>
</div>

<script language="javascript">


    $(document).ready(function () {

        resetConfirm();

        $("#toolbar").kendoToolBar({
            items: [
                {type: "button", text: "<g:message code="portfolio.list.title"/>", click: btnPortfolioListClick},
                {type: "button", text: "<g:message code="portfolio.view.title"/>", click: btnViewPortfolioClick},
                {type: "button", text: "<g:message code="portfolio.reports.benefitLoss"/>", click: btnBenefitLossClick},
                {type: "button", text: "<g:message code="portfolio.property.manage"/>", click: btnManagePortfolioClick}
            ]
        });
    });

    function btnPortfolioListClick(e) {
        window.location.href = "${createLink(controller: 'portfolio', action: 'list')}/";
    }

    function btnViewPortfolioClick(e) {
        window.location.href = "${createLink(controller: 'portfolio',action: 'portfolioView')}/${params.id}";
    }

    function btnBenefitLossClick(e) {
        window.location.href = "${createLink(controller: 'portfolio',action: 'benefitLoss')}/${params.id}";
    }

    function btnManagePortfolioClick(e) {
        window.location.href = "${createLink(controller: 'portfolio',action: 'portfolioManage')}/${params.id}";
    }
</script>
</body>
</html>