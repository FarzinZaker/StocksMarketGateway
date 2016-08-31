<%@ page import="org.ocpsoft.prettytime.PrettyTime" %>
<li class="iconMenu">
    <i class="fa fa-envelope">
        <g:if test="${count}">
            <span><g:formatNumber number="${count}" type="number"/></span>
        </g:if>
    </i>
    <ul>
        <g:each in="${list}" var="message">
            <li class="custom-menu-item">
                <a href="${createLink(controller: 'message', action: 'view', params: [id: message?.rootMessage ? message?.rootMessage?.id : message?.id])}?msg=${message?.id}">
                    <img src="${createLink(controller: 'image', action: 'profile', params: [id: message?.sender?.id, size: 50])}"/>

                    <div class="details">

                        <div class="date">${new PrettyTime(new Locale('fa')).format(message?.dateCreated)}</div>

                        <div class="comment">${message?.sender?.nickname}</div>

                        <div class="clear-fix"></div>

                        <div>${message.shortBody}</div>
                    </div>

                    <div class="clear-fix"></div>
                </a>
            </li>
        </g:each>
        <g:if test="${list?.size()}">
            <li class="k-separator"></li>
        </g:if>
        <li>
            <a href="${createLink(controller: 'message', action: 'list')}">
                <g:message code="message.list.link"/>
            </a>
        </li>
    </ul>
</li>