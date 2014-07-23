<layout:panel title="${message(code: "${type}.rate.title")}" class="rating" contentClass="ratingContent" header="h3"
              loginRequiredAction="${message(code: "${type}.rate.loginRequired")}">
    <div class="info-small">
        <g:message code="${type}.rate.current.message" args="${[message(code: "rating.options.${rate.value}")]}"/>
    </div>
</layout:panel>