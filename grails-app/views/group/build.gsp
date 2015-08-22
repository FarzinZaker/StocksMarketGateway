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
                    [text: message(code: 'twitter.group'), url: createLink(action: 'index')],
                    [text: '<i class="fa-group fa"></i> ' + message(code: "twitter.group.${group ? 'edit' : 'create'}", args: [group?.title]), url: createLink(action: 'build', id: group?.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            %{--<h1 class="orange">--}%
                %{--<i class="fa-group fa"></i>--}%
                %{--<g:message code="twitter.group.${group ? 'edit' : 'create'}" args="${[group?.title]}"/>--}%
            %{--</h1>--}%
            <form:form action="save" name="groupForm">
                <form:hidden name="id" entity="${group}"/>

                <form:field fieldName="twitter.group.title">
                    <form:textBox name="title" style="width:500px" entity="${group}" validation="required"/>
                </form:field>

                <form:field fieldName="twitter.group.membershipType">
                    <form:select name="membershipType" style="width:500px" entity="${group}" validation="required"
                                 items="${[[text: message(code: 'twitter.group.membershipType.open'), value: 'open'], [text: message(code: 'twitter.group.membershipType.closed'), value: 'closed']]}"/>
                </form:field>

                <form:field fieldName="twitter.group.membershipPrice">
                    <form:numericTextBox name="membershipPrice" style="width:500px"
                                         value="${group?.membershipPrice ?: 0}" validation="required" format="n0"/>
                </form:field>

                <form:field fieldName="twitter.group.membershipPeriod">
                    <form:select name="membershipPeriod" style="width:500px" entity="${group}" validation="required"
                                 items="${[
                                         [text: message(code: 'twitter.group.membershipPeriod.weekly'), value: 'weekly'],
                                         [text: message(code: 'twitter.group.membershipPeriod.monthly'), value: 'monthly'],
                                         [text: message(code: 'twitter.group.membershipPeriod.yearly'), value: 'yearly'],
                                         [text: message(code: 'twitter.group.membershipPeriod.unlimited'), value: 'unlimited']
                                 ]}"/>
                </form:field>


                <div>
                    <form:checkbox name="allowExceptionalUsers" checked="${group?.allowExceptionalUsers}" text="${message(code: 'twitter.group.allowExceptionalUsers')}"/>
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