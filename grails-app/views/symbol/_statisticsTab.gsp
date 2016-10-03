<div class="dashLet blue hideBeforeLoad noPlaceReservation white" style="margin-top:15px;">
    <h2 style="float:right"><g:message code="symbol.statistics.title"/></h2>

    <div class="k-rtl clear-fix">
        <div id="statisticsTabStrip">
            <ul>
                <g:each in="${[30, 90, 180, 365]}">
                    <li class="${it == 30 ? 'k-state-active' : ''}">
                        <g:message code="symbol.statistics.${it}"/>
                    </li>
                </g:each>
            </ul>

            <g:each in="${[30, 90, 180, 365]}">
                <div class="${it == 30 ? 'k-state-active' : ''} clear-fix">
                    <stocks:symbolStatistics symbolId="${symbolId}" days="${it}"/>
                </div>
            </g:each>
        </div>
    </div>
</div>

<script language="javascript">


    $(document).ready(function () {
        $("#statisticsTabStrip").kendoTabStrip();
    });
</script>