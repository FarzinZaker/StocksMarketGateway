<g:render template="registerWindow"/>
<div>
    <img width="100%" src="${createLink(controller: 'image', params: [id: group.image?.id, size: 500])}"/>

    <h1>${group.title}</h1>

    <p><format:html value="${group.description}"/></p>

    <g:if test="${membership}">
        <div class="info-small">
            <g:if test="${membership.endDate}">
                <g:message code="twitter.group.membership.timedStatusMessage"
                           args="${[format.jalaliDate(date: membership.endDate, hm: true)]}"/>
            </g:if>
            <g:else>
                <g:message code="twitter.group.membership.statusMessage"/>
            </g:else>
        </div>
    </g:if>
    <g:else>
        <div class="groupPriceInfo dashLet blue">
            <h3><g:message code="twitter.group.membershipPrice.label"/></h3>

            <ul>
                <li>
                    <label><g:message code="twitter.group.membershipPeriod.1Month"/></label>
                    <span>
                        <g:if test="${group.membership1MonthPrice}"><g:formatNumber
                                number="${group.membership1MonthPrice}"
                                type="number"/> <g:message
                                code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>

                    <div class="clear-fix"></div>
                </li>
                <li>
                    <label><g:message code="twitter.group.membershipPeriod.3Month"/></label>
                    <span>
                        <g:if test="${group.membership3MonthPrice}"><g:formatNumber
                                number="${group.membership3MonthPrice}"
                                type="number"/> <g:message
                                code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>

                    <div class="clear-fix"></div>
                </li>
                <li>
                    <label><g:message code="twitter.group.membershipPeriod.6Month"/></label>
                    <span>
                        <g:if test="${group.membership6MonthPrice}">
                            <g:formatNumber number="${group.membership6MonthPrice}" type="number"/>
                            <g:message code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>

                    <div class="clear-fix"></div>
                </li>
                <li>
                    <label><g:message code="twitter.group.membershipPeriod.12Month"/></label>
                    <span>
                        <g:if test="${group.membership12MonthPrice}">
                            <g:formatNumber number="${group.membership12MonthPrice}" type="number"/>
                            <g:message code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>

                    <div class="clear-fix"></div>
                </li>
            </ul>
        </div>

        <div class='toolbar' style="text-align: left">

            <sec:ifLoggedIn>
                <span class="btn k-button" style="width: 120px"
                      onclick='registerInGroup("${group.idNumber}", "${group.title}")'><g:message
                        code="twitter.group.register.button"/></span>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <div class="info-small" style="text-align: right">
                    <g:message code="twitter.group.register.loginRequired"/>
                </div>
            </sec:ifNotLoggedIn>
        </div>
    </g:else>
</div>