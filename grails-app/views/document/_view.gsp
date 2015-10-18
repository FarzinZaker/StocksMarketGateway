<div class="article">
    %{--<h1 class="title">${document.title}</h1>--}%

    <img class="image" src="${createLink(controller: 'image', id: document?.image?.id, params:[size:350])}" align="right"/>
    <div class="summary"><format:html value="${document.summary}"/></div>

    <div class="body"><format:html value="${document.body}"/></div>
</div>