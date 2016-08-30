<%@ page import="org.ocpsoft.prettytime.PrettyTime;stocks.User" %>

<g:set var="currentCommentId" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
<div class="image">
    <img id="img_${currentCommentId}"
         src="${createLink(controller: 'image', action: 'profile', params: [id: author?.id ?: 0, size: 100])}"/>

    <span class="date">
        ${comment.dateCreated ? new PrettyTime(new Locale('fa')).format(comment.dateCreated as Date) : '-'}
    </span>

</div>

<div class="description">

    <g:if test="${showProperties}">
        <div class="meta" id="meta_${currentCommentId}">
            <span id="metaLoading_${currentCommentId}">
                <asset:image src="loading.gif"/>
            </span>
        </div>
    </g:if>
    <div class="description correct-images"><format:twit value="${comment.body ?: '-'}"/></div>

    <div class="descriptionEditor"></div>

    <div class="loading">
        <form:loading/>
    </div>

    <twitter:like commentId="${comment.idNumber}"/>
</div>

<div class="clear-fix"></div>
<script>
    $(function(){
        $('.correct-images img').unbind().click(function(){
            showLargeImage(this);
        })
    })
</script>
<g:if test="${showProperties}">
    <script language="javascript" type="text/javascript">

        $("#metaLoading_${currentCommentId}").show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'twitter', action: 'meta', params:[id:comment.idNumber, type:comment.label])}'
        }).done(function (response) {
            $("#meta_${currentCommentId}").html(response);
            $("#img_${currentCommentId}").attr('src', '${createLink(controller: 'image', action: 'profile')}/' + $("#meta_${currentCommentId}").find('.author').attr('data-id') + '?size=100');
        });
    </script>
</g:if>