<div class="row">
    <g:each in="${groups}" var="group" status="indexer">
        <div class="col-sm-3">
            <a href="${createLink(controller: 'group', action: 'home', id: group.id)}"
               class="groupDashboardButton n${indexer + 1}">
                <img src="${createLink(controller: 'image', action: 'index', params: [id: group.imageId, size: 80])}">

                <div>
                    <h5>${group.title}</h5>

                    <p>${group.description?.replaceAll("<(.|\n)*?>", '')}</p>
                </div>
            </a>
        </div>
    </g:each>
</div>