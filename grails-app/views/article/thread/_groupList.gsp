<g:each in="${groupList}" var="${group}">
    <div class="group" item-id="${group.idNumber}" >
        <span class='image'><img src="${createLink(controller: 'image', action: 'index', params:[id: group.imageId, size:250])}" alt="${group.title}" /></span>
        <h3>${group.title}</h3>
        <p><format:html value="${group.description}"/></p>
        <span class='price'><g:message code="twitter.group.membershipPeriod.1Month"/> ${group.membership1MonthPrice}</span>
        <div class='toolbar'>
            <a href='${createLink(action: 'home', id: group.id)}'><g:message code="twitter.group.view.button"/></a><span onclick='registerInGroup("${group.idNumber}", "${group.title}")'><g:message code="twitter.group.register.button"/></span>
        </div>
    </div>
</g:each>