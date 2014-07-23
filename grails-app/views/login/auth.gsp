<html>
<head>
	<meta name='layout' content='site'/>
	<title><g:message code="login.title"/></title>
</head>

<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">

            <h1><g:message code="login.title"/></h1>
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
                    <form:checkbox name="${rememberMeParameter}" text="${message(code:'login.rememberMe')}" style="width:500px;" checked="${hasCookie}"/>
                </form:field>
                <form:submitButton name="submit" text="${message(code:'login.button.label')}"/>
            </form>
        </div>
    </div>
</div>
<script type='text/javascript'>
	<!--
	(function() {
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
