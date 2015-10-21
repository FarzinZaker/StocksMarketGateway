<div class="image">
    <img src="${createLink(controller: 'image', params: [id: material.imageId, size: imageSize ?: 100])}"/>

    <twitter:rateGage materialId="${material.idNumber}"/>
</div>

<div class="description">
    <a href="${createLink(controller: 'article', action: 'thread', id: material.identifier)}">${material.title}</a>

    <p>${material.description}</p>

    <g:if test="${showProperties}">
        <g:set var="currentID" value="${UUID.randomUUID().toString().replace('-', '_')}"/>
        <ul class="propertyList" id="propertyList_${currentID}">
            <span id="propertyLoading_${currentID}">
                <asset:image src="loading.gif"/>
            </span>
        </ul>
    </g:if>
</div>

<g:if test="${showProperties}">
    <script language="javascript" type="text/javascript">
        $("#propertyLoading_${currentID}").show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'twitter', action: 'propertyList', id:material.idNumber)}'
        }).done(function (response) {
            $("#propertyList_${currentID}").html(response);
        });
    </script>
</g:if>