<div id="newsFeedContainer" class="dashLet crimson">
    <h2 style="float:right"><i class="fa fa-desktop"></i> <g:message code="menu.reports.heatMap"/></h2>
    <a href="${createLink(controller: 'report', action: 'heatMap')}" style="display: block;">
        <img src="${resource(dir: 'heat-map', file: 'heatMap.svg?t=' + new Date().time)})}" width="100%;" style="margin-top:-20px;"/>
    </a>
</div>