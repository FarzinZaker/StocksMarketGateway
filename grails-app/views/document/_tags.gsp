
<layout:panel title="${message(code: 'document.tags.label')}" class="articleTags" header="h3" showHeader="${showHeader}">
    <g:set var="tagListId" value="${UUID.randomUUID()}"/>
    <ul id="taglist_${tagListId}">
        <g:each in="${document.tags}" var="tag">
            <li><a class="k-button" id="${params.controller}_tag_${tag.id}" data-id="${tag.id}"
                   href="${createLink(controller: stocks.documentController(document: document) ,action: 'listByTag', id: tag.id)}">${tag.name}</a></li>
        </g:each>
    </ul>
</layout:panel>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $.each($('#taglist_${tagListId}').find('li a'), function (index, item) {
            var id = $(item).attr('data-id');
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'tag', action: "${stocks.documentController(document: document)}Count")}',
                data: { id: id }
            }).done(function (response) {
                $(item).html($(item).text() + " <span class='k-badge'>" + response + "</span>")
            });
        });
    });
</script>