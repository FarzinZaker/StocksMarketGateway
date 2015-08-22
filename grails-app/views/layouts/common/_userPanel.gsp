<%@ page import="stocks.User" %>
<ul class="user-panel">
    <sec:ifLoggedIn>
    %{--<li><a href="${createLink(controller: 'user', action: 'changePassword')}"><g:message code="menu.currentUser.changePassword"/> <i class="glyphicon glyphicon-cog"></i></a></li>--}%
        <li>${stocks.User.findByUsername(sec.username())} <i class="fa fa-user"></i></li>
        <li><a href="${createLink(controller: 'logout')}"><g:message code="menu.currentUser.logout"/></a> <i
                class="fa fa-sign-out"></i></li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li style="margin-top:10px;"><a href="javascript:openLoginDialog()"><g:message code="menu.currentUser.loginOrRegister"/> <i
                class="fa fa-user"></i></a></li>
        %{--<li><a href="${createLink(controller: 'user', action: 'register')}"><g:message--}%
                %{--code="menu.currentUser.register"/> <i class="glyphicon glyphicon-plus"></i></a></li>--}%
    </sec:ifNotLoggedIn>
</ul>

<div id="loginPanel" class="k-rtl" style="display: none">
    <div class="window">
        <g:render template="/user/loginModal"/>
    </div>
</div>

<script language="javascript">

    function openLoginDialog() {
        loginPanel.data("kendoWindow").center().open();
    }

    function closeLoginDialog(){
        loginPanel.data("kendoWindow").close();
    }

    var loginPanel = $('#loginPanel').find('.window');
    $(document).ready(function () {
        if (!loginPanel.data("kendoWindow")) {
            loginPanel.kendoWindow({
                width: "750px",
                title: false,
                modal: true,
                visible: false,
                actions: [
                    "Close"
                ]
            });
        }
    });
</script> 