<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.build.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: message(code: 'menu.portfolios.list'), url: createLink(controller: 'portfolio', action: 'list', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <h1 class="darkBlue">
                <i class="fa fa-shopping-cart"></i>
                <g:message code="portfolio.build.title"/></h1>
            <g:if test="${flash.message}">
                <div class="errorMessage">
                    ${flash.message}
                </div>
            </g:if>
            <form:form action="save" name="portfolioForm">
                <form:hidden name="id" entity="${portfolio}"/>

                <form:field fieldName="portfolio.name">
                    <form:textBox name="name" style="width:500px" entity="${portfolio}" validation="required"
                                  value="${flash.data ?: portfolio?.name ?: ''}"/>
                </form:field>
                <form:field fieldName="portfolio.advanced">
                    <form:checkbox name="defaultItems" text="${message(code: 'portfolio.defaultItems.label')}"
                                   style="width:132px" entity="${portfolio}" onchange="showHideItems()"
                                   checked="${portfolio?.defaultItems!=null ?portfolio?.defaultItems: true}"/>
                    <form:checkbox name="fullAccounting" text="${message(code: 'portfolio.fullAccounting.label')}"
                                   style="width:120px" entity="${portfolio}"
                                   checked="${portfolio?.fullAccounting ?: false}"/>
                    <form:checkbox name="useWageAndDiscount"
                                   text="${message(code: 'portfolio.useWageAndDiscount.label')}"
                                   style="width:120px" entity="${portfolio}"
                                   checked="${portfolio?.useWageAndDiscount ?: false}"/>
                    <form:checkbox name="useBroker" text="${message(code: 'portfolio.useBroker.label')}"
                                   style="width:120px"
                                   entity="${portfolio}"
                                   onchange="showHideBrokers()"
                                   checked="${portfolio?.useBroker ?: false}"/>
                </form:field>
                <div id="portfolioItems">
                    <form:field fieldName="portfolio.items">
                        <div style="width: 500px">
                            <g:each in="${items}" var="itm">
                                <form:checkbox text="${itm.title}" checked="${portfolio?.defaultItems?itm.default:portfolioAvailItems.find{it.item==itm.clazz}}" name="${itm.clazz}"
                                               style="width:180px"/>
                            </g:each>
                        </div>
                    </form:field>
                </div>

                <div id="brokers">
                    <form:field fieldName="portfolio.bokers">
                        <form:multiSelect  name="broker" dataSourceUrl="${createLink(controller: 'portfolio',action: 'jsonSearchBroker')}" style="width: 500px" entity="${portfolioAvailBrokers}"/>
                    </form:field>
                </div>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'portfolio.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
    <script>
        function showHideItems() {
            if ($('#defaultItems').is(':checked')) {
                $('#portfolioItems').fadeOut();
            }
            else {
                $('#portfolioItems').fadeIn();
            }
        }
        function showHideBrokers() {
            if ($('#useBroker').is(':checked')) {
                $('#brokers').fadeIn();
            }
            else {
                $('#brokers').fadeOut();
            }
        }
        showHideBrokers();
        showHideItems();
    </script>
</div>
</body>
</html>