<div class="article">
    <div class="image">
        <img src="${createLink(controller: 'image', id: article?.image?.id, params: [size: 350])}"/>
        <twitter:rateGage materialId="${vertexId}" showLabel="true"/>
    </div>

    <div class="summary"><format:html value="${article.summary}"/></div>

    <div class="body"><format:html value="${article.body}"/></div>
</div>