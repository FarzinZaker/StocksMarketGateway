<div class="loginModal">

    <div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-xs-4">
                    <div>
                        <div>
                            <h3 class="num"><g:formatNumber number="${stocks.User.count()}" type="number"/></h3>
                            <span class="text">
                                کاربر تا کنون برای پیگیری تحلیل های بازار بورس و تبادل نظر با سایرین به عضویت 4تابلو درآمده اند.
                            </span>
                        </div>
                    </div>
                </div>

                <div class="col-xs-4">
                    <div>
                        <h3 class="steel">
                            <g:message code="register.title"/>
                        </h3>

                        <form action='${createLink(controller: 'user', action: 'saveInitialRegistration')}'
                              method='POST' id='registerModalForm' autocomplete='off'>
                            <form:field fieldName="user.email" showHelp="0" border="0">
                                <form:textBox name="email" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="user.password" showHelp="0" border="0">
                                <form:password name="password" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="user.confirmPassword" showHelp="0" border="0">
                                <form:password name="confirmPassword" validation="required" style="width:220px;"/>
                            </form:field>
                            <div>
                                <form:button name="initialRegister" id="btnInitialRegister"
                                             text="${message(code: 'register.button.label')}"
                                             onclick="doInitialRegistration()"/>
                                <form:loading id="loadInitialRegister"/>
                            </div>
                        </form></div>
                </div>

                <div class="col-xs-4">
                    <div>
                        <h3 class="steel">
                            <g:message code="login.title"/>
                        </h3>

                        <form action='${createLink(uri: '/j_spring_security_check')}' method='POST' id='loginPopupForm'
                              autocomplete='off'>
                            <form:field fieldName="login.username" showHelp="0" border="0">
                                <form:textBox name="j_username" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="login.password" showHelp="0" border="0">
                                <form:password name="j_password" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field showHelp="0" border="0">
                                <form:checkbox name="_spring_security_remember_me" id="popupRememberMe"
                                               text="${message(code: 'login.rememberMe')}"
                                               style="width:220px;" checked="${true}"/>
                            </form:field>
                            <div>
                                <form:submitButton name="submit" text="${message(code: 'login.button.label')}"/>
                            </div>
                        </form>
                        <div class="oAuthToolbar">
                            <a href="${createLink(controller: 'OAuth2', action: 'google')}" class="oAuthButton googleButton">
                                <i class="fa fa-google"></i>
                                <span>
                                    <g:message code="oAuth.button.title"/>
                                    <span>Google</span>
                                </span>
                            </a>
                            <a href="${createLink(controller: 'OAuth2', action: 'yahoo')}" class="oAuthButton yahooButton">
                                <i class="fa fa-yahoo"></i>
                                <span>
                                    <g:message code="oAuth.button.title"/>
                                    <span>Yahoo</span>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <i class="fa fa-close" onclick="closeLoginDialog();"></i>
</div>

<script language="JavaScript" type="text/javascript">
    function doInitialRegistration() {
        $('#btnInitialRegister').hide();
        $('#loadInitialRegister').show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'User', action: 'saveInitialRegistration')}',
            data: $('#registerModalForm').serialize()
        }).done(function (response) {
            if (response == '0')
                window.location.href = '${createLink(uri: '/')}';
            else if (response == '1') {
                closeLoginDialog();
                window.info('<g:message code="register.checkForActivationMail.body"/>');
            }
            else
                window.alert(response);
            $('#btnInitialRegister').show();
            $('#loadInitialRegister').hide();
        });
    }
</script>