<form:form action="save" name="submitForm">
    <g:hiddenField name="id" value="${params.id}"/>
    <g:hiddenField name="name" id="form_name"/>
    <g:hiddenField name="rules" id="form_rules"/>
    <div class="toolbar" style="border-top:1px dashed #9e9e9e;margin-top:10px;padding-top:10px;">
        <form:submitButton name="submit" text="${message(code: 'screener.save')}"/>
        <form:submitButton name="submitAndExit" text="${message(code: 'screener.saveAndExit')}"/>
    </div>
</form:form>

<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        $("#submitForm").submit(function (e) {
                    var name = $('#nameInput').val();
                    if (!name || name == '') {
                        window.alert('${message(code:'screener.name.notEmpty')}');
                        e.preventDefault();
                        return false;
                    }
                    $('#form_name').val(name);
                    var rules = [];
                    $.each($('#filter-query-panel').find('form'), function (index, item) {
                        rules[rules.length] = $(item).serializeArray();
                    });
                    $('#form_rules').val(JSON.stringify(rules));
                }
        );
    });
</script>