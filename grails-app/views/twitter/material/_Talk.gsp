<%@ page import="org.ocpsoft.prettytime.PrettyTime;stocks.User" %>

<g:set var="currentID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
<div class="image">
    <img id="img_${currentID}" src="${createLink(controller: 'image', action: 'profile', params: [id: 0, size: 100])}"/>


    <twitter:rateGage materialId="${material.idNumber}"/>

    <span class="date">
        ${new PrettyTime(new Locale('fa')).format(material.publishDate)}
    </span>

</div>

<div class="description">

    <div class="meta" id="meta_${currentID}">
        <span id="metaLoading_${currentID}">
            <asset:image src="loading.gif"/>
        </span>
    </div>

    <div class="description" id="material_body_${currentID}"><format:twit value="${material.description}"/></div>

    <div class="descriptionEditor"></div>

    <div class="loading">
        <asset:image src="loading.gif"/>
    </div>

    <g:if test="${showProperties}">
        <div class="comments" id="comments_${currentID}">
            <span id="commentsLoading_${currentID}">
                <asset:image src="loading.gif"/>
            </span>
        </div>
    </g:if>
</div>

<div class="clear-fix"></div>
<script language="javascript" type="text/javascript">
    <g:if test="${showProperties}">
    $("#commentLoading_${currentID}").show();
    $.ajax({
        type: "POST",
        url: '${createLink(controller: 'twitter', action: 'commentList', id:material.idNumber)}'
    }).done(function (response) {
        $("#comments_${currentID}").hide().html(response).fadeIn();
    });
    </g:if>

    $("#metaLoading_${currentID}").show();
    $.ajax({
        type: "POST",
        url: '${createLink(controller: 'twitter', action: 'meta', params:[id:material.idNumber, type:material.label])}'
    }).done(function (response) {
        $("#meta_${currentID}").html(response);
        $("#img_${currentID}").attr('src', '${createLink(controller: 'image', action: 'profile')}/' + $("#meta_${currentID}").find('.author').attr('data-id') + '?size=100');
    });
</script>