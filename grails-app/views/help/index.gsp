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
    <title><g:message code="site.help.title"/></title>
    <asset:stylesheet src="tilt.min.css"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"site.help.title"), url:createLink(controller: 'help')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/underConstruction"/>
            <ul class="tilt-grid">

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-pink tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'screener')}">
                            <i class="fa fa-filter"></i><span><g:message code="support.screener"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-magenta tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'tradeStrategy')}">
                            <i class="fa fa-magic"></i><span><g:message code="support.backtest"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'alerting')}">
                            <i class="fa fa-paper-plane-o"></i><span><g:message code="support.alerting"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-darkBlue tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'portfolio')}">
                            <i class="fa fa-shopping-cart"></i><span><g:message code="support.portfolio"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-cyan tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'calculator')}">
                            <i class="fa fa-calculator"></i><span><g:message code="support.calculator"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-cyan tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'correlation')}">
                            <i class="fa fa-link"></i><span><g:message code="support.correlation"/></span>
                        </a>

                    </div>
                </li>

            <li data-tile-type="s,6m">
                <div>

                    <a class="tilt tilt-tile tilt-tile-orange tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'help', action: 'faq')}">
                        <i class="fa fa-question"></i><span><g:message code="support.faq"/></span>
                    </a>

                </div>
            </li>

            <li data-tile-type="s,6m">
                <div>

                    <a class="tilt tilt-tile tilt-tile-crimson tilt-icon-center tilt-caption-bc" href="javascript:openOnlineSupport()">
                        <i class="fa fa-life-ring"></i><span><g:message code="support.online"/></span>
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