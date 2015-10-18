<layout:panel title="${message(code: "article.rate.title")}" class="rating" contentClass="ratingContent" header="h3"
              loginRequiredAction="${message(code: "article.rate.loginRequired")}">
    <div class="info-small">
        <g:message code="article.rate.current.message" args="${[message(code: "rating.options.${rateValue}")]}"/>
    </div>
</layout:panel>