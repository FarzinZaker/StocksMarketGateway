<div class="profileMenu">
    <ul>
        <li class="${params.action == 'index' || params.action == null ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'index')}">
                <i class="fa fa-user"></i>
                <span>
                    <g:message code="menu.currentUser.profile"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'edit' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'edit')}">
                <i class="fa fa-edit"></i>
                <span>
                    <g:message code="user.profile.edit"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'payment' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'payment')}">
                <i class="fa fa-shopping-cart"></i>
                <span>
                    <g:message code="user.profile.payment"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'transactions' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'transactions')}">
                <i class="fa fa-exchange"></i>
                <span>
                    <g:message code="user.profile.transactions"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'newsLetterSubscription' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'newsLetterSubscription')}">
                <i class="fa fa-bullhorn"></i>
                <span>
                    <g:message code="user.profile.newsLetterSubscription"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'invite' || params.controller == 'invitation' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'invite')}">
                <i class="fa fa-envelope"></i>
                <span>
                    <g:message code="user.profile.invite"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
        <li class="${params.action == 'social' ? 'active' : ''}">
            <a href="${createLink(controller: 'profile', action: 'social')}">
                <i class="fa fa-share-square-o"></i>
                <span>
                    <g:message code="user.profile.social"/>
                </span>
                <div class="clear-fix"></div>
            </a>
        </li>
    </ul>
</div>
<script language="javascript" type="text/javascript">
    function resizeProfileMenu() {
        return;
        var profileMenu = $('.profileMenu');
        profileMenu.stop().hide();
        var container = profileMenu.parent();
        while (!container.hasClass('container'))
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