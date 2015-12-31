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
    <title><g:message code="about.title"/></title>
    <asset:stylesheet src="tilt.min.css"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: '<i class="fa fa-institution"></i> ' + message(code:"brokers.title"), url:createLink(controller: 'brokersHelp')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/underConstruction"/>
            <ul class="tilt-grid">

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-pink tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'brokersHelp', action: 'cooperationPlans')}">
                            <i class="fa fa-th-list"></i><span><g:message code="brokers.collaboration.plans"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-magenta tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'brokersHelp', action: 'cooperationRequest')}">
                            <i class="fa fa-check-square-o"></i><span><g:message code="brokers.collaboration.request"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,5m,8_l">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'brokersHelp', action: 'bulkService')}">
                            <i class="fa fa-shopping-basket"></i><span><g:message code="brokers.collaboration.buy"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-darkBlue tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'brokersHelp', action: 'cooperationRules')}">
                            <i class="fa fa-book"></i><span><g:message code="brokers.collaboration.rules"/></span>
                        </a>

                    </div>
                </li>

            </ul>
        </div>
    </div>
</div>
<asset:javascript src="tilt.min.js"/>
</body>
</html>