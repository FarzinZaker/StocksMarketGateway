<%@ page import="grails.converters.JSON" %>
<div class="filter-value-panel">
<g:each in="${baskets}" var="basket">
    <div><form:checkbox name="value_${basket.id}" text="${basket.name}"/></div>
</g:each>
</div>