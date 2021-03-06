<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/1/14
  Time: 3:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" import="grails.converters.JSON; stocks.Province;stocks.City" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="register.title"/></title>
    <asset:javascript src="form-validator/security.js"/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-user"></i> ' + message(code: 'register.title'), url: createLink(controller: 'user', action: 'registerInvited', params: [invitation:params.invitation])]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form:error message="${flash.validationError}"/>
            <form:form action="saveRegistration" name="registerForm">
                <form:hidden name="view" value="completeRegistration"/>
                <form:hidden name="id" entity="${user}"/>
                <form:field fieldName="user.nickname">
                    <form:textBox name="nickname" entity="${user}" style="width:500px;" readonly="true"/>
                </form:field>
                <form:field fieldName="user.email">
                    <form:textBox name="email" entity="${user}" validation="email" style="width:500px;" readonly="true"/>
                </form:field>
                <form:field fieldName="user.firstName">
                    <form:textBox name="firstName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.lastName">
                    <form:textBox name="lastName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.sex">
                    <form:select name="sex" entity="${user}" items="${[[text:message(code:'user.sex.male'),value:'male'],[text:message(code:'user.sex.female'),value:'female']]}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.mobile">
                    <form:maskedTextBox name="mobile" mask="09000000000" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.nationalCode">
                    <form:textBox name="nationalCode" entity="${user}" validation="national-code" style="width:500px;"/>
                </form:field>

                <form:field fieldName="user.city">
                    <div style="width: 500px;" class="k-rtl">
                        <g:set var="defaultProvince" value="${stocks.Province.findByDeleted(false)}"/>
                        <input id="province" name="province" style="width: 248px"
                               value="${user?.city?.province?.id ?: defaultProvince?.id}"/>
                        <input id="city" name="cityId" style="width: 248px"
                               value="${user?.city?.id ?: stocks.City.findByProvinceAndDeleted(defaultProvince, false)?.id}"/>
                    </div>
                </form:field>
                %{--<form:field fieldName="captcha">--}%
                    %{--<form:captcha name="captcha" validation="required" width="500"/>--}%
                %{--</form:field>--}%
                <form:submitButton name="submit" text="${message(code:'register.button.label')}"/>
            </form:form>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#province").kendoComboBox({
            dataTextField: "parentName",
            dataValueField: "parentId",
            dataSource:<format:html value="${Province.findAllByDeleted(false).collect{[parentId:it.id, parentName:it.name]} as JSON}"/>,
            change: function(e){
                $("#city").data('kendoComboBox').value($("#city").data('kendoComboBox').dataSource._view[0].childId);
            }
        });

        $("#city").kendoComboBox({
            cascadeFrom: "province",
            dataTextField: "childName",
            dataValueField: "childId",
            dataSource: <format:html value="${City.findAllByDeleted(false).collect{[childId:it.id, childName:it.name, parentId: it.province?.id]} as JSON}"/>
        });
    });
</script>
</body>
</html>