<%@ page import="grails.converters.JSON" %>
<div class="filter-value-panel">
    <g:each in="${indexes}" var="index">
        <div><form:checkbox name="value_${index.id}" text="${index.name}"/></div>
    </g:each>
</div>