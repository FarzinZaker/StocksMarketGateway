<div class="article">
    <img class="image" src="${createLink(controller: 'image', id: article?.image?.id, params:[size:350])}" align="right"/>
    <div class="summary"><format:html value="${article.summary}"/></div>

    <div class="body"><format:html value="${article.body}"/></div>
</div>