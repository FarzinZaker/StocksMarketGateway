<sec:ifLoggedIn>
    <g:set var="currentRatingID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
    <select id="rating_${currentRatingID}" name="rating">
        <option value=""></option>
        <g:each in="${1..5}" var="rate">
            <option value="${rate}"><g:message code="rating.options.${rate}"/></option>
        </g:each>
    </select>
    <script language="javascript" type="text/javascript">
        $('#rating_${currentRatingID}').barrating({
            theme: 'bars-reversed',
            onSelect: function (value, text, event) {
                $('#rating_${currentRatingID}').barrating('readonly', true);
                $.ajax({
                    type: "POST",
                    url: '${createLink(controller: 'rate', action: 'save')}',
                    data: {
                        id: '#${id}',
                        rating: value
                    }
                });
            }
        });
    </script>
</sec:ifLoggedIn>