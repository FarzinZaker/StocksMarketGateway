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
                    [text: '<i class="fa fa-book"></i> ' + message(code:"rules.title"), url:createLink(controller: 'rules')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/underConstruction"/>
            <ul class="tilt-grid">

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-pink tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'rules', action: 'copyright')}">
                            <i class="fa fa-copyright"></i><span><g:message code="rules.copyRight"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-magenta tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'rules', action: 'userRules')}">
                            <i class="fa fa-user"></i><span><g:message code="rules.user"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'rules', action: 'privacy')}">
                            <i class="fa fa-ban"></i><span><g:message code="rules.privacy"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,5m,8_l">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-darkBlue tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'rules', action: 'criminalContentTabs')}">
                            <i class="fa fa-list"></i><span><g:message code="rules.ir"/></span>
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