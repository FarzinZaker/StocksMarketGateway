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
    <asset:javascript src="jquery.cropit.min.js"/>
</head>

<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">

            <h1><g:message code="user.profile.edit"/></h1>
            <form:error message="${flash.validationError}"/>
            <form:form action="saveRegistration" name="registerForm">
                <form:field fieldName="user.firstName">
                    <form:textBox name="firstName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.lastName">
                    <form:textBox name="lastName" entity="${user}" validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.image">
                    <style type="text/css" rel="stylesheet">
                        /* Translucent background image */
                        .cropit-image-background {
                            opacity: .2;
                        }

                        /*
                         * If the slider or anything else is covered by the background image,
                         * use relative or absolute position on it
                         */
                        input.cropit-image-zoom-input {
                            position: relative;
                        }

                        /* Limit the background image by adding overflow: hidden */
                        #image-cropper {
                            overflow: hidden;
                        }
                    </style>
                    <div id="image-cropper">
                        <!-- The preview container is needed for background image to work -->
                        <div class="cropit-image-preview-container">
                            <div class="cropit-image-preview"></div>
                        </div>

                        <input type="range" class="cropit-image-zoom-input" />

                        <input type="file" class="cropit-image-input" />
                        <div class="select-image-btn">Select new image</div>
                    </div>
                    <script language="javascript" type="text/javascript">
                        $('#image-cropper').cropit({ imageBackground: true });
                    </script>
                </form:field>
                <form:field fieldName="user.sex">
                    <form:select name="sex" entity="${user}"
                                 items="${[[text: message(code: 'user.sex.male'), value: 'male'], [text: message(code: 'user.sex.female'), value: 'female']]}"
                                 validation="required" style="width:500px;"/>
                </form:field>
                <form:field fieldName="user.mobile">
                    <form:textBox name="mobile" entity="${user}" validation="required" style="width:500px;"/>
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

                <form:submitButton name="submit" text="${message(code: 'register.button.label')}"/>
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