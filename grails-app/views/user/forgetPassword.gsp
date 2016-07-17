<html>
<head>
    <meta name='layout' content='main'/>
    <title><g:message code="menu.currentUser.forgetPassword"/></title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-key"></i> ' + message(code: 'menu.currentUser.forgetPassword'), url: createLink(controller: 'user', action: 'forgetPassword')]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <form:error message="${flash.message}"/>

            <form action='${createLink(action: 'sendPasswordResetLink')}' method='POST' id='forgetPasswordForm' autocomplete='off'>
                <form:field fieldName="login.username">
                    <form:textBox name="username" validation="required" style="width:500px;"/>
                </form:field>
                <form:submitButton name="submit" text="${message(code: 'changePassword.title')}" style="padding:5px 15px"/>
            </form>
        </div>
    </div>
</div>
<script type='text/javascript'>
    <!--
    (function () {
        document.forms['forgetPasswordForm'].elements['username'].focus();
    })();
    // -->

    $(document).ready(function () {
        $.validate({
            form: '#forgetPasswordForm'
        });
    });
</script>
</body>
</html>
