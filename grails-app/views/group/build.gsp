<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 8/8/2015
  Time: 4:30 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.${group ? 'edit' : 'create'}" args="${[group?.title]}"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: message(code: 'twitter.group.list.title'), url: createLink(action: 'list')],
                    [text: '<i class="fa-group fa"></i> ' + message(code: "twitter.group.${group ? 'edit' : 'create'}", args: [group?.title]), url: createLink(action: 'build', id: group?.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            <form:form action="save" name="groupForm">
                <form:hidden name="id" value="${params.id}"/>

                <form:field fieldName="twitter.group.title">
                    <form:textBox name="title" style="width:500px" entity="${group}" validation="required"/>
                </form:field>

                <form:field fieldName="twitter.group.description">
                    <form:editor name="description" width="500" mode="simple" entity="${group}"/>
                </form:field>

                <form:field fieldName="twitter.group.image">
                    <form:imageUpload name="image" style="width:500px;" entity="${group}"
                                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
                </form:field>

                <form:field fieldName="twitter.group.membershipType">
                    <form:select name="membershipType" style="width:500px" entity="${group}" validation="required"
                                 items="${[[text: message(code: 'twitter.group.membershipType.open'), value: 'open'], [text: message(code: 'twitter.group.membershipType.closed'), value: 'closed']]}"/>
                </form:field>

                <form:field fieldName="twitter.group.membershipPrice">
                    <div style="width: 500px;margin-bottom:8px;">
                        <span><g:message code="twitter.group.membershipPeriod.1Month"/>:</span>
                        <form:numericTextBox name="membership1MonthPrice" style="width:200px;margin-left:17px;"
                                             value="${group?.membership1MonthPrice ?: 0}" validation="required" format="n0"/>
                        <span><g:message code="twitter.group.membershipPeriod.3Month"/>:</span>
                        <form:numericTextBox name="membership3MonthPrice" style="width:200px"
                                             value="${group?.membership3MonthPrice ?: 0}" validation="required" format="n0"/>
                    </div>
                    <div style="width: 500px">
                        <span><g:message code="twitter.group.membershipPeriod.6Month"/>:</span>
                        <form:numericTextBox name="membership6MonthPrice" style="width:200px;margin-left:17px;"
                                             value="${group?.membership6MonthPrice ?: 0}" validation="required" format="n0"/>
                        <span><g:message code="twitter.group.membershipPeriod.12Month"/>:</span>
                        <form:numericTextBox name="membership12MonthPrice" style="width:200px"
                                             value="${group?.membership12MonthPrice ?: 0}" validation="required" format="n0"/>
                    </div>
                </form:field>


                <div>
                    <form:checkbox name="allowExceptionalUsers" checked="${group?.allowExceptionalUsers}"
                                   text="${message(code: 'twitter.group.allowExceptionalUsers')}"/>
                </div>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'twitter.group.create.submit.label')}"
                           class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>