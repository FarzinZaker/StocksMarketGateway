<%@ page import="stocks.tse.AdjustmentHelper; stocks.RoleHelper" %>

<sec:ifAllGranted roles="${RoleHelper.ROLE_ADMIN}">
    <div style="direction: ltr;">
        <div class="bg-danger" style="padding: 10px;margin-top:20px;">Please don't use these options</div>
    <h3>Admin Actions</h3>
    <ul>
        <li>
            <a href="${createLink(action: 'loadTimeSeries', id: params.id)}" target="_blank">Load Time Series</a>
        </li>
        <li>
            <a href="${createLink(action: 'clearTimeSeries', id: params.id)}" target="_blank">Clear Time Series</a>
        </li>
        <li>
            <a href="${createLink(action: 'adjust', id: params.id, params:[type: AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT])}" target="_blank">Adjust [ ${AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT} ]</a>
        </li>
        <li>
            <a href="${createLink(action: 'undoAdjustment', id: params.id, params:[type: AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT])}" target="_blank">Undo Adjustment [ ${AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT} ]</a>
        </li>
    </ul>
    </div>
</sec:ifAllGranted>