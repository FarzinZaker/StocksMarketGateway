<div class="document-card">
    <div class="meta">
        <a href="${createLink(controller: stocks.documentController(document: document), action: 'thread', id: document.id)}">
        <img class="avatar" src="${createLink(controller: 'image', id: document.image?.id, params: [size: 150])}"/>
        </a>
    </div>

    <div class="data">
        <h3>
            <a href="${createLink(controller: stocks.documentController(document: document), action: 'thread', id: document.id)}">${document}</a>
        </h3>
        <span class="date"><format:jalaliDate date="${document.dateCreated}" hm="true"/></span>

        <p><format:html value="${document.summary}"/></p>

        <g:render template="/document/tags" model="${[document: document, showHeader:'no']}"/>
    </div>
    <div class="clear-fix"></div>
</div>