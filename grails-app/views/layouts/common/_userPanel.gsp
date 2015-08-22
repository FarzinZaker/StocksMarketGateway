<%@ page import="stocks.User" %>
<ul class="user-panel">
    <sec:ifLoggedIn>
    %{--<li><a href="${createLink(controller: 'user', action: 'changePassword')}"><g:message code="menu.currentUser.changePassword"/> <i class="glyphicon glyphicon-cog"></i></a></li>--}%
        <li>${stocks.User.findByUsername(sec.username())} <i class="fa fa-user"></i></li>
        <li><a href="${createLink(controller: 'logout')}"><g:message code="menu.currentUser.logout"/></a> <i
                class="fa fa-sign-out"></i></li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li><a href="javascript:openLoginDialog()"><g:message code="menu.currentUser.login"/> <i
                class="glyphicon glyphicon-log-in"></i></a></li>
        <li><a href="${createLink(controller: 'user', action: 'register')}"><g:message
                code="menu.currentUser.register"/> <i class="glyphicon glyphicon-plus"></i></a></li>
    </sec:ifNotLoggedIn>
</ul>

<div id="loginPanel" class="k-rtl">
    <div class="window">
        <g:render template="/user/loginModal"/>
    </div>
</div>

<script language="javascript">

    function openLoginDialog() {
        loginPanel.data("kendoWindow").center().open();
    }
    var loginPanel = $('#loginPanel').find('.window');
    $(document).ready(function () {
        if (!loginPanel.data("kendoWindow")) {
            loginPanel.kendoWindow({
                width: "750px",
                title: false,
                modal: true,
                visible: false,
//                position: {
//                    top: '20%',
//                    left: '25%'
//                },
                actions: [
                    "Close"
                ]
            });
        }
    });
</script> 