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
                        <form:error message="${flash.message}"/>
                        <form:info message="${flash.info}"/>

                        <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
                            <form:field fieldName="user.email" showHelp="0" border="0">
                                <form:textBox name="email" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="user.password" showHelp="0" border="0">
                                <form:password name="password" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="user.confirmPassword" showHelp="0" border="0">
                                <form:password name="confirmPassword" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:submitButton name="submit" text="${message(code: 'register.button.label')}"/>
                        </form></div>
                </div>

                <div class="col-xs-4">
                    <div>
                        <h3 class="steel">
                            <g:message code="login.title"/>
                        </h3>
                        <form:error message="${flash.message}"/>
                        <form:info message="${flash.info}"/>

                        <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
                            <form:field fieldName="login.username" showHelp="0" border="0">
                                <form:textBox name="j_username" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field fieldName="login.password" showHelp="0" border="0">
                                <form:password name="j_password" validation="required" style="width:220px;"/>
                            </form:field>
                            <form:field showHelp="0" border="0">
                                <form:checkbox name="${rememberMeParameter}" text="${message(code: 'login.rememberMe')}"
                                               style="width:220px;" checked="${hasCookie}"/>
                            </form:field>
                            <form:submitButton name="submit" text="${message(code: 'login.button.label')}"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <i class="fa fa-close"></i>
</div>