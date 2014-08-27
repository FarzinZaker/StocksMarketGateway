<ul class="tilt-grid">

    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'queryCategory', action: 'build')}">
                <i class="fa fa-folder-open"></i><span><g:message code="menu.category.add"/></span>
            </a>

        </div>
    </li>



    <li data-tile-type="s,5m,8_l">
        <div>

            <a class="tilt tilt-tile tilt-tile-lime tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'queryCategory', action: 'list')}">
                <i class="fa fa-folder-open"></i><span><g:message code="menu.category.list"/></span>
            </a>

        </div>
    </li>

    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'scheduleTemplate', action: 'build')}">
                <i class="fa fa-clock-o"></i><span><g:message code="menu.scheduleTemplate.add"/></span>
            </a>

        </div>
    </li>



    <li data-tile-type="s,5m,8_l">
        <div>

            <a class="tilt tilt-tile tilt-tile-lime tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'scheduleTemplate', action: 'list')}">
                <i class="fa fa-clock-o"></i><span><g:message code="menu.scheduleTemplate.list"/></span>
            </a>

        </div>
    </li>

    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'query', action: 'select')}">
                <i class="fa fa-paper-plane-o"></i><span><g:message code="menu.newsletters.add"/></span>
            </a>

        </div>
    </li>



    <li data-tile-type="s,5m,8_l">
        <div>

            <a class="tilt tilt-tile tilt-tile-lime tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'query', action: 'list')}">
                <i class="fa fa-paper-plane-o"></i><span><g:message code="menu.newsletters.list"/></span>
            </a>

        </div>
    </li>


    <li data-tile-type="s,5_s,8m">
        <div>

            <a class="tilt tilt-tile tilt-tile-steel tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'user', action:'changePassword')}">
                <i class="fa fa-key"></i><span><g:message code="menu.currentUser.changePassword"/></span>
            </a>

        </div>
    </li>

    <li data-tile-type="s,5_s,8m">
        <div>

            <a class="tilt tilt-tile tilt-tile-steel tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'logout')}">
                <i class="fa fa-sign-out"></i><span><g:message code="menu.currentUser.logout"/></span>
            </a>

        </div>
    </li>

</ul>