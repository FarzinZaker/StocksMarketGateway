<html>
<head>
    <meta name='layout' content='main'/>
    <title><g:message code="login.title"/></title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-key"></i> ' + message(code: 'login.title'), url: createLink(controller: 'login', action: 'auth')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="steel">--}%
            %{--<i class="fa fa-key"></i>--}%
            %{--<g:message code="login.title"/>--}%
            %{--</h1>--}%
            <form:error message="${flash.message}"/>
            <form:info message="${flash.info}"/>

            <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
                <form:field fieldName="login.username">
                    <form:textBox name="j_username" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="login.password">
                    <form:password name="j_password" validation="required" style="width:500px;"/>
                </form:field>
                <form:field>
                    <form:checkbox name="${rememberMeParameter}" text="${message(code: 'login.rememberMe')}"
                                   style="width:500px;" checked="${hasCookie}"/>
                </form:field>
                <form:submitButton name="submit" text="${message(code: 'login.button.label')}"/>
            </form>


            <div class="oAuthToolbar">
                <h2><g:message code="login.oAuth.title"/></h2>

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
<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->

    $(document).ready(function () {
        $.validate({
            form: '#loginForm'
        });
    });
</script>
</body>
</html>
