<g:each in="${scores}" var="score">
    <g:if test="${score.type}">
        <div class="material-prediction">
            <a class="material-prediction-link"
               href="${createLink(controller: 'twitter', action: 'property', params: [id: score.identifier])}">
                ${score.title}
            </a>
            <g:if test="${score.type == 'benefit'}">
                <i class="fa fa-arrow-up material-prediction-type"></i>
            </g:if>
            <g:else>
                <i class="fa fa-arrow-down material-prediction-type"></i>
            </g:else>
            <span class="material-prediction-period">
                <g:message code="twitter.prediction.period.${score.period}"/>
            </span>
            <span class="material-prediction-risk">${score.risk} <i class="fa fa-remove"></i></span>
            <g:if test="${score.applied}">
                <span class="material-prediction-score ${score?.score > 0 ? 'positive' : ''} ${score?.score < 0 ? 'negative' : ''}">
                <g:formatNumber number="${Math.round(score?.score * 100) / 100}" type="number" format="#.##"/>
            </g:if>
            <g:else>
                <span class="material-prediction-score">
                    <i class="fa fa-question"></i>
                </span>
            </g:else>
        </div>
    </g:if>
</g:each>
<div class="clearfix"></div>