<%@ page import="stocks.User" %>

<ul class="user-panel">
    <sec:ifLoggedIn>
        <li>
            <a href="${createLink(uri: '/profile')}">
                <i class="fa fa-user"></i> <span>${stocks.User.findByUsername(sec.username())}</span>
            </a>
        </li>
        <li>
            <a href="${createLink(controller: 'user', action: 'changePassword')}">
                <i class="fa fa-key"></i> <span><g:message code="menu.currentUser.changePassword"/></span>
            </a>
        </li>
        <li>
            <a href="${createLink(uri: '/j_spring_security_logout')}">
                <i class="fa fa-sign-out"></i> <span><g:message code="menu.currentUser.logout"/></span>
            </a>
        </li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li><a href="javascript:openLoginDialog()"><g:message code="menu.currentUser.loginOrRegister"/> <i
                class="fa fa-user"></i></a></li>
    </sec:ifNotLoggedIn>
</ul>

<div id="loginPanel" class="k-rtl" style="display: none">
    <div class="window">
        <g:render template="/user/loginModal"/>
    </div>
</div>


<script language="javascript">
    var urlFormat = "../content/web/tooltip/ajax/ajaxContent{0}.html";

    function openLoginDialog() {
        loginPanel.data("kendoWindow").center().open();
    }

    function closeLoginDialog() {
        loginPanel.data("kendoWindow").close();
    }

    var loginPanel = $('#loginPanel').find('.window');
    $(document).ready(function () {
        if (!loginPanel.data("kendoWindow")) {
            loginPanel.kendoWindow({
                width: "780px",
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