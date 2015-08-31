<div class="profileMenu">
    <ul>
        <li class="${params.action == 'index' || params.action == null ? 'active' : ''}">
            <a href="${createLink(action: 'index')}">
                <i class="fa fa-user"></i>
                <span>
                    <g:message code="menu.currentUser.profile"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'edit' ? 'active' : ''}">
            <a href="${createLink(action: 'edit')}">
                <i class="fa fa-edit"></i>
                <span>
                    <g:message code="user.profile.edit"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'payment' ? 'active' : ''}">
            <a href="${createLink(action: 'payment')}">
                <i class="fa fa-shopping-cart"></i>
                <span>
                    <g:message code="user.profile.payment"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'transactions' ? 'active' : ''}">
            <a href="${createLink(action: 'transactions')}">
                <i class="fa fa-exchange"></i>
                <span>
                    <g:message code="user.profile.transactions"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'invite' ? 'active' : ''}">
            <a href="${createLink(action: 'invite')}">
                <i class="fa fa-envelope"></i>
                <span>
                    <g:message code="user.profile.invite"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
    </ul>
</div>
<script language="javascript" type="text/javascript">
    function resizeProfileMenu() {
        var profileMenu = $('.profileMenu');
        profileMenu.stop().hide();
        var container = profileMenu.parent();
        while (!container.hasClass('container-fluid'))
            container = container.parent();
        profileMenu.height(container.height() - 20).show();
    }

    $(document).ready(function () {
        resizeProfileMenu();
    });

    $(window).resize(function () {
        resizeProfileMenu();
    });
</script>