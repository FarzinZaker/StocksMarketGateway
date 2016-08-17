<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 9/24/14
  Time: 12:56 PM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="user.profile.edit"/></title>
    <asset:javascript src="form-validator/security.js"/>
    %{--<asset:javascript src="jquery.cropit.min.js"/>--}%
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.currentUser.profile'), url:createLink(controller: 'profile', action: 'index')],
                    [text: '<i class="fa fa-edit"></i> ' + message(code:'user.profile.edit'), url:createLink(controller: 'profile', action: 'edit')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-3">
            <g:render template="menu"/>
        </div>

        <div class="col-xs-9">
            <form:info message="${flash.message}"/>
            <form:error message="${flash.validationError}"/>
            <form:form action="saveProfile" name="profileForm">
                <form:field fieldName="user.nickname">
                    <form:textBox name="nickname" entity="${user}" validation="required" style="width:400px;"/>
                </form:field>
                <form:field fieldName="user.firstName">
                    <form:textBox name="firstName" entity="${user}" validation="required" style="width:400px;"/>
                </form:field>
                <form:field fieldName="user.lastName">
                    <form:textBox name="lastName" entity="${user}" validation="required" style="width:400px;"/>
                </form:field>
                <form:field fieldName="user.image">
                    <form:imageUpload name="image" id="imageUpload" style="width:400px;" entity="${user}"
                                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
                </form:field>
                <form:field fieldName="user.sex">
                    <form:select name="sex" entity="${user}"
                                 items="${[[text: message(code: 'user.sex.male'), value: 'male'], [text: message(code: 'user.sex.female'), value: 'female']]}"
                                 validation="required" style="width:400px;"/>
                </form:field>
                <form:field fieldName="user.mobile">
                    <form:textBox name="mobile" entity="${user}" validation="required" style="width:400px;"/>
                </form:field>
                <form:field fieldName="user.nationalCode">
                    <form:textBox name="nationalCode" entity="${user}" validation="national-code" style="width:400px;"/>
                </form:field>

                <form:field fieldName="user.city">
                    <div style="width: 400px;" class="k-rtl">
                        <g:set var="defaultProvince" value="${stocks.Province.findByDeleted(false)}"/>
                        <input id="province" name="province" style="width: 198px"
                               value="${user?.city?.province?.id ?: defaultProvince?.id}"/>
                        <input id="city" name="cityId" style="width: 198px"
                               value="${user?.city?.id ?: stocks.City.findByProvinceAndDeleted(defaultProvince, false)?.id}"/>
                    </div>
                </form:field>

                <form:submitButton name="submit" text="${message(code: 'profile.saveButton.label')}"/>
            </form:form>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#province").kendoComboBox({
            dataTextField: "parentName",
            dataValueField: "parentId",
            dataSource:<format:html value="${stocks.Province.findAllByDeleted(false).collect{[parentId:it.id, parentName:it.name]} as JSON}"/>,
            change: function (e) {
                $("#city").data('kendoComboBox').value($("#city").data('kendoComboBox').dataSource._view[0].childId);
            }
        });

        $("#city").kendoComboBox({
            cascadeFrom: "province",
            dataTextField: "childName",
            dataValueField: "childId",
            dataSource: <format:html value="${stocks.City.findAllByDeleted(false).collect{[childId:it.id, childName:it.name, parentId: it.province?.id]} as JSON}"/>
        });
    });
</script>
</body>
</html>