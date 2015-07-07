<div class="dashLet orange">
    <h2 style="float:right"><i class="fa fa-line-chart"></i> <g:message code="marketView.title"/></h2>

    <div class="k-rtl clear-fix">
        <div id="tabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="marketView.bourse.title"/>
                </li>
                <li>
                    <g:message code="marketView.commodity.title"/>
                </li>
                <li>
                    <g:message code="marketView.future.title"/>
                </li>
                <li>
                    <g:message code="marketView.energy.title"/>
                </li>
            </ul>

            <div>
                <g:render template="dashLets/marketView/bourse"/>
            </div>

            <div>
            </div>

            <div>
            </div>

            <div>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });
    });
</script>