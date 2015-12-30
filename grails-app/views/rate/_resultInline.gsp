<sec:ifLoggedIn>
    <g:set var="currentRatingID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
    <select id="rating_${currentRatingID}" name="rating">
        <g:each in="${1..5}" var="rate">
            <option value="${rate}" ${rateValue == rate ? 'selected' : ''}><g:message
                    code="rating.options.${rate}"/></option>
        </g:each>
    </select>
    <script language="javascript" type="text/javascript">
        $('#rating_${currentRatingID}').barrating({
            theme: 'bars-reversed',
            readonly: true
        });
    </script>
</sec:ifLoggedIn>