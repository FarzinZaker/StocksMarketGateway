<g:set var="currentCommentListID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
<div class="replyButtonContainer">
    <span class="replyButton"
          onclick="showTwitterCommentEditor($(this), $('#replyEditorContainer_${currentCommentListID}'), $('#commentEditorLoading_${currentCommentListID}'), '${createLink(controller: 'twitter', action: 'commentEditor', id:id)}')">
        <i class="fa fa-comment"></i>
        <g:message code="comment.comment.submit"/>
    </span> <g:if test="${data.size() > 0}">(<span class="replyCounter">${data.size()}</span>)</g:if>
</div>
<twitter:rating materialId="#${id}" inline="true"/>
<form:loading id="commentEditorLoading_${currentCommentListID}"/>

<div class="replyEditor" id="replyEditorContainer_${currentCommentListID}"></div>
<g:if test="${data.size() > 2}">
    <div class="showAllButtonContainer">
        <span class="showAllButton" onclick="$('#replyList_${currentCommentListID} li.hidden').removeClass('hidden').slideDown();$(this).parent().slideUp();">
            <g:message code="comment.list.showAll" args="${[data.size()]}"/>
        </span>
    </div>
</g:if>
<ul class="replyList" id="replyList_${currentCommentListID}">
    <g:each in="${data}" var="item" status="indexer">
        <li class="${indexer > 1 ? 'hidden' : ''}">
            <g:render template="material/comment" model="${[comment: item, showProperties: true]}"/>
        </li>
    </g:each>
</ul>

