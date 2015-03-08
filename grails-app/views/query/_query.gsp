<form:field fieldName="query.maxRecordsCount">
    <form:numericTextBox name="maxRecordsCount" value="${queryInstance?.maxRecordsCount?:0}" style="width:488px;" validation="required" decimals="0"/>
</form:field>
<alerting:queryBuilder domainClassName="${domainClass}" name="query" id="queryBuilder" value="${rules}"/>

