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
                    [text: '<i class="fa fa-info"></i> ' + message(code:"about.title"), url:createLink(controller: 'about')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/underConstruction"/>
            <ul class="tilt-grid">

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-pink tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'about', action: 'site')}">
                            <i class="fa fa-globe"></i><span><g:message code="about.site"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,5m,8_l">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-magenta tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'about', action: 'company')}">
                            <i class="fa fa-line-chart"></i><span><g:message code="about.company"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'about', action: 'it')}">
                            <i class="fa fa-laptop"></i><span><g:message code="about.it"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-darkBlue tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'about', action: 'history')}">
                            <i class="fa fa-history"></i><span><g:message code="about.history"/></span>
                        </a>

                    </div>
                </li>

                <li data-tile-type="s,6m">
                    <div>

                        <a class="tilt tilt-tile tilt-tile-cyan tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'about', action: 'contact')}">
                            <i class="fa fa-phone"></i><span><g:message code="about.contact"/></span>
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