<%@ page import="grails.util.Environment" %>
%{--<div id="newsFeedContainer" class="dashLet crimson">--}%
%{--<h2 style="float:right"><i class="fa fa-desktop"></i> <g:message code="menu.reports.heatMap"/></h2>--}%
<a href="${createLink(controller: 'report', action: 'heatMap')}" style="display: block;overflow: hidden">
    <img src="${Environment.isDevelopmentMode() ? 'http://www.4tablo.ir/heat-map/heatMap.svg' : resource(dir: 'heat-map', file: 'heatMap.svg?t=' + new Date().time)}"
         style="margin-top: -18px; margin-right: -3px;width:450px;"/>
</a>
%{--</div>--}%