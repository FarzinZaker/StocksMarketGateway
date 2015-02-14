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

            <a class="tilt tilt-tile tilt-tile-lime tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'query', action: 'instanceList')}">
                <i class="fa fa-paper-plane-o"></i><span><g:message code="menu.newsletter.register.my"/></span>
            </a>

        </div>
    </li>


    %{--<li data-tile-type="s,5_s,8m">--}%
        %{--<div>--}%

            %{--<a class="tilt tilt-tile tilt-tile-steel tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'user', action:'profile')}">--}%
                %{--<i class="fa fa-user"></i><span><g:message code="menu.currentUser.profile"/></span>--}%
            %{--</a>--}%

        %{--</div>--}%
    %{--</li>--}%
    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-darkBlue tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'portfolio', action: 'list')}">
                <i class="fa fa-shopping-cart"></i><span><g:message code="menu.portfolios.list"/></span>
            </a>

        </div>
    </li>

    %{--tools start--}%
    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-cyan tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'tools', action: 'calculator')}">
                <i class="fa fa-calculator"></i><span><g:message code="menu.tools.calculator"/></span>
            </a>

        </div>
    </li>
    <li data-tile-type="s,6m">
        <div>

            <a class="tilt tilt-tile tilt-tile-cyan tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'tools', action: 'correlation')}">
                <i class="fa fa-link"></i><span><g:message code="menu.tools.correlation"/></span>
            </a>

        </div>
    </li>
    %{--tools end--}%

    %{--screener start--}%
    <li data-tile-type="s,5_s,8m">
        <div>

            <a class="tilt tilt-tile tilt-tile-crimson tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'screener', action:'list')}">
                <i class="fa fa-filter"></i><span><g:message code="menu.screener.list"/></span>
            </a>

        </div>
    </li>
    <li data-tile-type="s,5_s,8m">
        <div>

            <a class="tilt tilt-tile tilt-tile-magenta tilt-icon-center tilt-caption-bc" href="${createLink(controller: 'screener', action:'build')}">
                <i class="fa fa-filter"></i><span><g:message code="menu.screener.new"/></span>
            </a>

        </div>
    </li>
    %{--screener end--}%


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