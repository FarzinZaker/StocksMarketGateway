<ul class="tilt-grid">

    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-green tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'query', action: 'select')}">
                <i class="fa fa-paper-plane-o"></i><span><g:message code="menu.newsletter.register.list"/></span>
            </a>

        </div>
    </li>



    <li data-tile-type="s,5m,8_l">
        <div>

            <a class="tilt tilt-tile tilt-tile-lime tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'query', action: 'instanceDelete')}">
                <i class="fa fa-paper-plane-o"></i><span><g:message code="menu.newsletter.register.my"/></span>
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