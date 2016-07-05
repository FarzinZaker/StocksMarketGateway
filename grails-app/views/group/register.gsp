<div class="followForm">
    <div class="itemInfo">
        <div class="image">
            <img src="${createLink(controller: 'image', action: 'index', id: group.image?.id)}?&size=250"
                 alt="${group.title}"/>
        </div>

        <div class="description">
            <h3>${group.title}</h3>

            <p><format:html value="${group.description}"/></p>

            <div class="priceSelector">
                <form:hidden id="selectedPeriod" value="1"/>
                <div>
                    <span class='price' data-period="1" data-price="${group.membership1MonthPrice}">
                        <label><g:message code="twitter.group.membershipPeriod.1Month"/></label>
                        <g:if test="${group.membership1MonthPrice}">
                            <g:formatNumber number="${group.membership1MonthPrice}" type="number"/> <g:message
                                code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>
                    <span class='price' data-period="3" data-price="${group.membership3MonthPrice}">
                        <label><g:message code="twitter.group.membershipPeriod.3Month"/></label>
                        <g:if test="${group.membership3MonthPrice}">
                            <g:formatNumber number="${group.membership3MonthPrice}" type="number"/> <g:message
                                code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>
                </div>

                <div>
                    <span class='price' data-period="6" data-price="${group.membership6MonthPrice}">
                        <label><g:message code="twitter.group.membershipPeriod.6Month"/></label>
                        <g:if test="${group.membership6MonthPrice}">
                            <g:formatNumber number="${group.membership6MonthPrice}" type="number"/>
                            <g:message code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>
                    <span class='price' data-period="12" data-price="${group.membership12MonthPrice}">
                        <label><g:message code="twitter.group.membershipPeriod.12Month"/></label>
                        <g:if test="${group.membership12MonthPrice}">
                            <g:formatNumber number="${group.membership12MonthPrice}" type="number"/>
                            <g:message code="rial"/>
                        </g:if>
                        <g:else>
                            <g:message code="free"/>
                        </g:else>
                    </span>
                </div>
            </div>
        </div>

        <div class="clear-fix"></div>
    </div>

    <div class="optionsForm">

        <div class="accountBalance">
            <g:message code="user.profile.balance.value"/>
            <b><g:formatNumber number="${balance}" type="number"/></b> <g:message code="rial"/>
            <br/>
            <span id="balanceAfterRegisterContainer" style="display: none">
                <g:message code="user.profile.balance.afterFollowValue"/>
                <b id="balanceAfterRegister"><g:formatNumber number="${balance - (group.membership1MonthPrice ?: 0)}"
                                                             type="number"/></b> <g:message
                    code="rial"/>
            </span>
            <span id="notEnoughBalance">
                <g:message code="user.profile.balance.notEnough"/>
            </span>
        </div>

        <div class="toolbar">

            <form:button id="btnRegisterInGroup" text="${message(code: 'twitter.group.register.title')}"
                         style="display: none"
                         onclick="saveRegisterInGroup();"/>
            <form:linkButton id="btnChargeAccount" text="${message(code: 'user.profile.payment')}"
                             href="${createLink(controller: 'profile', action: 'payment')}"/>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    var priceList = $('.priceSelector .price');

    priceList.click(function () {

        priceList.removeClass('selected');
        $(this).addClass('selected');
        $('#selectedPeriod').val($(this).attr('data-period'));

        var accountBalance = ${balance} + ${maxDept};
        var priceAttr = $(this).attr('data-price');
        var price = !priceAttr || priceAttr == '' ? 0 : parseInt($(this).attr('data-price'));

        if (accountBalance > price) {
            $('#btnRegisterInGroup').show();
            $('#balanceAfterRegisterContainer').show();
            $('#btnChargeAccount').hide();
            $('#notEnoughBalance').hide();
        }
        else {
            $('#btnRegisterInGroup').hide();
            $('#balanceAfterRegisterContainer').hide();
            $('#btnChargeAccount').show();
            $('#notEnoughBalance').show();
        }
    });

    $.each(priceList, function () {
        var accountBalance = ${balance} + ${maxDept};
        var priceAttr = $(this).attr('data-price');
        var price = !priceAttr || priceAttr == '' ? 0 : parseInt($(this).attr('data-price'));
        if (accountBalance > priceAttr)
            $(this).click();
    });

    function saveRegisterInGroup(groupId) {
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'group', action: 'saveRegistration', id:group.id)}',
            data: {period: $('#selectedPeriod').val()}
        }).done(function (response) {
            if (response == "1") {
                $('#registerWindow').data('kendoWindow').close();
                location.reload();
            }
            else {
                window.alert('${message(code:'twitter.group.register.error')}');
            }
        });

    }
</script>