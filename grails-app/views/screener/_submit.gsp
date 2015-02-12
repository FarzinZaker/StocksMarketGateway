<g:form action="save">
    <g:hiddenField name="id" value="${params.id}"/>
    <g:hiddenField name="name" id="form_name"/>
    <g:hiddenField name="rules" id="form_rules"/>
    <div class="toolbar" style="border-top:1px dashed #9e9e9e;margin0top:10px;padding-top:10px;" onclick="gatherData()">
        <form:submitButton name="submit" text="${message(code: 'screener.save')}"/>
    </div>
</g:form>

<script language="javascript" type="text/javascript">
    function gatherData(){
        $('#form_name').val($('#nameInput').val());
        var rules = [];
        $.each($('#filter-query-panel').find('form'), function (index, item){
            rules[rules.length] = $(item).serializeArray();
        });
        $('#form_rules').val(JSON.stringify(rules));
    }
</script>