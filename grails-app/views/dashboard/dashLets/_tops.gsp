<g:set var="colSpan" value="${12 / tops?.values()?.size()}"/>
<div class="row">
    <g:set var="item" value="${tops.mostActivePerson}"/>
    <g:if test="${item}">
        <div class="col-sm-${colSpan}">
            <div class="groupDashboardButton n1">
                <img src="${createLink(controller: 'image', action: 'profile', params: [id: item.identifier, size: 80])}">

                <div>
                    <h4><g:message code="mostActivePersons.title"/></h4>
                    <a href="${createLink(controller: 'user', action: 'wall', id: item.identifier)}">${item.title} <span
                            class="counter">(<g:formatNumber number="${item.count}" type="number"/>)</span></a>

                    <p onclick="showTopList('${createLink(controller: 'dashboard', action: 'mostActivePersons')}', '${message(code:'mostActivePersons.title')}')"><g:message
                            code="tops.more"/></p>
                </div>
            </div>
        </div>
    </g:if>
    <g:set var="item" value="${tops.mostRatedPerson}"/>
    <g:if test="${item}">
        <div class="col-sm-${colSpan}">
            <div class="groupDashboardButton n2">
                <img src="${createLink(controller: 'image', action: 'profile', params: [id: item?.identifier, size: 80])}">

                <div>
                    <h4><g:message code="mostRatedPerson.title"/></h4>
                    <a href="${createLink(controller: 'user', action: 'wall', id: item?.identifier)}">${item?.title} <span
                            class="counter">(<g:formatNumber number="${item?.rate}" type="number"/>)</span></a>

                    <p onclick="showTopList('${createLink(controller: 'dashboard', action: 'mostRatedPersons')}', '${message(code:'mostRatedPerson.title')}')"><g:message
                            code="tops.more"/></p>
                </div>
            </div>
        </div>
    </g:if>
    <g:set var="item" value="${tops.mostActiveProperty}"/>
    <g:if test="${item}">
        <div class="col-sm-${colSpan}">
            <div class="groupDashboardButton n3">
                <img src="${createLink(controller: 'image', action: 'property', params: [id: item.identifier, type: item.class, size: 80])}">

                <div>
                    <h4><g:message code="mostActiveProperty.title"/></h4>
                    <a href="${createLink(controller: 'twitter', action: 'property', id: item.identifier)}">${item.title} <span
                            class="counter">(<g:formatNumber number="${item.count}" type="number"/>)</span></a>

                    <p onclick="showTopList('${createLink(controller: 'dashboard', action: 'mostActiveProperties')}', '${message(code:'mostActiveProperty.title')}')"><g:message
                            code="tops.more"/></p>
                </div>
            </div>
        </div>
    </g:if>
    <g:set var="item" value="${tops.largestGroup}"/>
    <g:if test="${item}">
        <div class="col-sm-${colSpan}">
            <div class="groupDashboardButton n4">
                <img src="${createLink(controller: 'image', params: [id: item.imageId, size: 80])}">

                <div>
                    <h4><g:message code="largestGroup.title"/></h4>
                    <a href="${createLink(controller: 'group', action: 'home', id: item.idNumber)}">${item.title} <span
                            class="counter">(<g:formatNumber number="${item.size}" type="number"/>)</span></a>

                    <p onclick="showTopList('${createLink(controller: 'dashboard', action: 'largestGroups')}', '${message(code:'largestGroup.title')}')"><g:message
                            code="tops.more"/></p>
                </div>
            </div>
        </div>
    </g:if>
</div>

<div class="hidden k-rtl">
    <div id="topListWindow"></div>
</div>

<div class="hidden" id="topListWindowLoading">
    <div style='height:327px'>
        <span class="loading" style="display: block">
            <asset:image src="loading.gif"/>
            <span><g:message code="wait"/></span>
        </span>
    </div>
</div>

<script language="javascript" type="text/javascript">
    function showTopList(url, title) {
        var win = $('#topListWindow').html($('#topListWindowLoading').html())
                .kendoWindow({
                    width: '400px',
                    content: url,
                    modal: true,
                    close: function (e) {
                    }
                }).data('kendoWindow').title(title).center().open();
        win.bind("refresh", function () {
            win.center();
            win.open();
        });
    }
</script>