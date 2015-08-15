<div class="container-fluid">
    <form:form name="propertyForm" controller="portfolio" action="saveProperty">
        <div class="row-fluid">
            <div class="col-xs-12">
                <h2><g:message code="${"${clazz}.${params.id ? 'edit' : 'create'}.title"}"/></h2>
            </div>
        </div>

        <div class="row-fluid">
            <div class="col-xs-12">
                <g:hiddenField name="clazz" value="${clazz}"/>
                <g:hiddenField name="id" value="${id}"/>
                <g:hiddenField name="portfolioId" value="${portfolioId}"/>
                <div id="propertyFormFields">
                    <g:render template="portfolioItems/${clazz}"/>
                </div>

                <div class="toolbar" style="margin:0;padding-top:10px;">
                    <form:button name="submit" text="${message(code: 'portfolioItem.save')}"/>
                    <form:button name="cancel" text="${message(code: 'portfolioItem.cancel')}" style="float:left;"/>
                    <script language="javascript" type="text/javascript">
                        $('span[name=submit]').click(function () {
                            if ($('#propertyForm').isValid()) {
                                var that = this;
                                $.ajax({
                                    type: "POST",
                                    url: '${createLink(action: 'saveProperty')}',
                                    data: $('#propertyForm').serialize()
                                }).done(function (response) {
                                    if (response == 1) {
                                        $(that).closest("[data-role=window]").slideUp().html('').kendoWindow("close");
                                    }
                                    else {
                                        $('#propertyFormFields').html(response);
                                    }
                                });
                            }
                        });
                        $('span[name=cancel]').click(function () {
                            $(this).closest("[data-role=window]").slideUp().html('').kendoWindow("close");
                        });
                    </script>
                </div>
            </div>
        </div>
    </form:form>
</div>