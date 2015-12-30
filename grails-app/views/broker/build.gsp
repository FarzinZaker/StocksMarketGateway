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
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.broker'), url:createLink(controller: 'broker')],
                    [text: '<i class="fa fa-institution"></i> ' + message(code:'broker.build.title'), url:createLink(controller: 'broker', action: 'build')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
                %{--<i class="fa fa-institution"></i>--}%
                %{--<g:message code="broker.build.title"/>--}%
            %{--</h1>--}%
            <form:form action="save" name="brokerForm">
                <form:hidden name="id" entity="${brokerInstance}"/>

                <form:field fieldName="broker.name">
                    <form:textBox name="name" style="width:500px" entity="${brokerInstance}" validation="required"/>
                </form:field>

                <form:field fieldName="broker.englishName">
                    <form:textBox name="englishName" style="width:500px" entity="${brokerInstance}" validation="required"/>
                </form:field>

                <form:field fieldName="broker.logo">
                    <form:imageUpload name="logo" id="logoUpload" style="width:500px;" entity="${brokerInstance}"
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