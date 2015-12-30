<%@ page import="org.ocpsoft.prettytime.PrettyTime" %>

<g:set var="currentID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
<div class="image">
    <img src="${createLink(controller: 'image', params: [id: material.imageId, size: imageSize ?: 100])}"/>


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
    <a class="title"
       href="${createLink(controller: 'article', action: 'thread', id: material.identifier)}">${material.title}</a>
    <g:if test="${showProperties}">
        <div class="description">${material.description}</div>

        <ul class="propertyList" id="propertyList_${currentID}">
            <span id="propertyLoading_${currentID}">
                <asset:image src="loading.gif"/>
            </span>
        </ul>

        <div class="moreButtonContainer">
            <a href="${createLink(controller: 'article', action: 'thread', id: material.identifier)}" class="moreButton">
                <i class="fa fa-info"></i>
                <g:message code="article.showDetails"/>
            </a>
        </div>
    </g:if>
</div>

<div class="clear-fix"></div>
<script language="javascript" type="text/javascript">
    <g:if test="${showProperties}">
    $("#propertyLoading_${currentID}").show();
    $.ajax({
        type: "POST",
        url: '${createLink(controller: 'twitter', action: 'propertyList', id:material.idNumber)}'
    }).done(function (response) {
        $("#propertyList_${currentID}").html(response);
    });
    </g:if>

    $("#metaLoading_${currentID}").show();
    $.ajax({
        type: "POST",
        url: '${createLink(controller: 'twitter', action: 'meta', id:material.idNumber)}'
    }).done(function (response) {
        $("#meta_${currentID}").html(response);
    });
</script>