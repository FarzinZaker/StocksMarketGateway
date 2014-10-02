<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="broker.build.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="broker.build.title"/></h1>
            <form:form action="save" name="brokerForm">
                <form:hidden name="id" entity="${brokerInstance}"/>

                <form:field fieldName="broker.name">
                    <form:textBox name="name" style="width:500px" entity="${brokerInstance}" validation="required"/>
                </form:field>

                <form:field fieldName="broker.englishName">
                    <form:textBox name="englishName" style="width:500px" entity="${brokerInstance}" validation="required"/>
                </form:field>

                <form:field fieldName="broker.logo">
                    <form:imageUpload name="logo" style="width:500px;" entity="${brokerInstance}"
                                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
                </form:field>


                <div class="toolbar">
                    <input type="submit" value="${message(code: 'broker.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>