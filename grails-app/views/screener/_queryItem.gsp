<i class="fa fa-close" onclick="removeQueryItem(this)"></i>
<g:message code="filters.queryItem.${filter}.${operator}" args="${[text, parameter]}"/>
<form>
    <g:hiddenField name="filter" value="${filter}"/>
    <g:hiddenField name="parameter" value="${parameter}"/>
    <g:hiddenField name="operator" value="${operator}"/>
    <g:hiddenField name="value" value="${value}"/>
</form>