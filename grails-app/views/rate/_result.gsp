<layout:panel showHeader="no" class="rating" contentClass="ratingResult"
              loginRequiredAction="${message(code: "article.rate.loginRequired")}">
    <div class="info-small">
        <g:message code="article.rate.current.message" args="${[message(code: "rating.options.${rateValue}")]}"/>
    </div>
</layout:panel>