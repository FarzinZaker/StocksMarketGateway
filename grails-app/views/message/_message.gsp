<%@ page import="org.ocpsoft.prettytime.PrettyTime" %>

<li>
    <g:if test="${!rootMessage?.deleted && (user == rootMessage?.sender || user == rootMessage?.receiver)}">
        <div class="message-meta">
            <img src="${createLink(controller: 'image', action: 'profile', params: [id: rootMessage?.sender?.id, size: 50])}"/>

            <div class="author-info">
                <a class="author"
                   href="${createLink(controller: 'user', action: 'wall', id: rootMessage?.senderId)}">${rootMessage?.sender?.nickname}</a>
                <span class="date">${new PrettyTime(new Locale('fa')).format(rootMessage?.dateCreated)}</span>
            </div>

            <g:if test="${user == rootMessage?.receiver}">
                <div class="action-links">
                    <a class='btn-reply'
                       href="javascript:sendMessage(${rootMessage?.senderId}, '${rootMessage?.sender?.nickname}' ,${rootMessage?.id})"><i
                            class='fa fa-share'></i></a>
                    <a class='btn-forward'
                       href="javascript:sendMessage(${rootMessage?.senderId}, '${rootMessage?.sender?.nickname}',${rootMessage?.id}, true)"><i
                            class='fa fa-reply'></i></a>
                    <a class='btn-delete' href="javascript:removeGridItem(${rootMessage?.id})"><i
                            class='fa fa-remove'></i></a>
                </div>
            </g:if>

            <div class="clear-fix"></div>
        </div>

        <p><format:html value="${rootMessage?.body?.replace('\n', '<br/>')}"/></p>
    </g:if>
    <ul>
        <g:each in="${list.findAll { it.inReplyTo == rootMessage }?.sort { it.dateCreated }}" var="currentMsg">
            <g:render template="message" model="[rootMessage: currentMsg, msg: msg, list: list]"/>
        </g:each>
    </ul>
</li>