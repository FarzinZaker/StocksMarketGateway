<div class="k-rtl">
    <ul id="menu">
        <li>
            <g:message code="menu.basicInfo"/>
            <ul>
                <li><a href="${createLink(controller: 'province', action: 'build')}"><g:message code="menu.basicInfo.province.add"/></a></li>
                <li><a href="${createLink(controller: 'province', action: 'list')}"><g:message code="menu.basicInfo.province.list"/></a></li>
            <li><a href="${createLink(controller: 'city', action: 'build')}"><g:message code="menu.basicInfo.city.add"/></a></li>
            <li><a href="${createLink(controller: 'city', action: 'list')}"><g:message code="menu.basicInfo.city.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="menu.broker"/>
            <ul>
                <li><a href="${createLink(controller: 'broker', action: 'build')}"><g:message code="menu.broker.add"/></a></li>
                <li><a href="${createLink(controller: 'broker', action: 'list')}"><g:message code="menu.broker.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="menu.users"/>
            <ul>
                <li><a href="${createLink(controller: 'user', action: 'build')}"><g:message code="menu.users.add"/></a></li>
                <li><a href="${createLink(controller: 'user', action: 'list')}"><g:message code="menu.users.list"/></a></li>
            </ul>
        </li>
        <li>
            <g:message code="menu.newsletters"/>
            <ul>
                <li><a href="${createLink(controller: 'queryCategory', action: 'build')}"><g:message
                        code="menu.category.add"/></a></li>
                <li><a href="${createLink(controller: 'queryCategory', action: 'list')}"><g:message
                        code="menu.category.list"/></a></li>
                <li><a href="${createLink(controller: 'scheduleTemplate', action: 'build')}"><g:message
                        code="menu.scheduleTemplate.add"/></a></li>
                <li><a href="${createLink(controller: 'scheduleTemplate', action: 'list')}"><g:message
                        code="menu.scheduleTemplate.list"/></a></li>
                <li><a href="${createLink(controller: 'query', action: 'selectData')}"><g:message
                        code="menu.newsletters.add"/></a></li>
                <li><a href="${createLink(controller: 'query', action: 'list')}"><g:message
                        code="menu.newsletters.list"/></a></li>
            </ul>
        </li>
        <g:render template="/layouts/common/menu"/>
    </ul>
</div>

<script>
    $(document).ready(function () {
        $("#menu").kendoMenu();
    });
</script>