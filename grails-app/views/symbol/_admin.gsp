<%@ page import="stocks.RoleHelper" %>

<sec:ifAllGranted roles="${RoleHelper.ROLE_ADMIN}">

    <h3>Admin Actions</h3>
    <ul>
        <li>
            <a href="${createLink(action: 'loadTimeSeries', id: params.id)}" target="_blank">Load Time Series</a>
        </li>
        <li>
            <a href="${createLink(action: 'clearTimeSeries', id: params.id)}" target="_blank">Clear Time Series</a>
        </li>
    </ul>
</sec:ifAllGranted>