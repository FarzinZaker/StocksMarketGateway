<layout:panel title="${message(code: 'article.rate.title')}" class="rating" contentClass="ratingContent" header="h3"
              loginRequiredAction="${message(code: 'article.rate.loginRequired')}">
    <form id="ratingForm">
        <form:hidden name="id" entity="${document}"/>
        <div class="rating-options">
            <g:each in="${5..1}" var="rate">
                <input type="radio" class="css-checkbox" id="rating-options-${rate}" name="rating" value="${rate}"/>
                <label class="css-label" for="rating-options-${rate}">
                    <g:message code="rating.options.${rate}"/>
                </label>
            </g:each>
        </div>
    </form>
    <form:button name="btn-submitRate" text="${message(code: 'rate.submit')}"/>
    <span class="validator" id="rateValidator">
        <g:message code="rate.validator.message"/>
    </span>
    <form:loading id="rateLoading"/>

</layout:panel>
<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btn-submitRate').click(function () {
            if ($('input[name=rating]:checked').length) {
                $('#rateValidator').fadeOut();
                $('#btn-submitRate').kendoButton({
                    enable: false
                });
                $('#rateLoading').fadeIn();
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'rate', action: 'save')}',
                    data: $('#ratingForm').serialize()
                }).done(function (response) {
                    $('.rating').replaceWith(response)
                });
            }
            else {
                $('#rateValidator').fadeIn();
                $('#btn-submitRate').kendoButton({
                    enable: true
                });
            }
        });
    });
</script>