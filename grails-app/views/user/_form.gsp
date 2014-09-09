<%@ page import="stocks.RoleHelper; grails.converters.JSON; stocks.Province;stocks.City" %>

<form:hidden name="id" entity="${user}"/>

<form:field fieldName="userInfo.firstName">
    <form:textBox name="firstName" style="width:500px" entity="${user}" validation="required"/>
</form:field>

<form:field fieldName="userInfo.lastName">
    <form:textBox name="lastName" style="width:500px" entity="${user}" validation="required"/>
</form:field>

<form:field fieldName="userInfo.sex">
    <form:select name="sex" entity="${user}"
                 items="${[[text: message(code: 'user.sex.male'), value: 'male'], [text: message(code: 'user.sex.female'), value: 'female']]}"
                 validation="required" style="width:500px;"/>
</form:field>

<form:field fieldName="userInfo.mobile">
    <form:textBox name="mobile" style="width:500px" entity="${user}" validation="required"/>
</form:field>

<form:field fieldName="userInfo.nationalCode">
    <form:textBox name="nationalCode" entity="${user}" validation="national-code" style="width:500px;"/>
</form:field>

<form:field fieldName="userInfo.city">
    <div style="width: 500px;" class="k-rtl">
        <g:set var="defaultProvince" value="${Province.findByDeleted(false)}"/>
        <input id="province" name="province" style="width: 248px"
               value="${user?.city?.province?.id ?: defaultProvince?.id}"/>
        <input id="city" name="cityId" style="width: 248px"
               value="${user?.city?.id ?: City.findByProvinceAndDeleted(defaultProvince, false)?.id}"/>
    </div>
</form:field>

<form:field fieldName="userInfo.email">
    <form:textBox name="email" style="width:500px" entity="${user}" validation="required"/>
</form:field>

<form:field fieldName="userInfo.password">
    <form:password name="password" style="width:500px" entity="${user}" validation="required"/>
</form:field>

<form:field fieldName="userInfo.status">
    <div style="width: 500px;">
        <div>
            <form:checkbox name="enabled" checked="${user?.enabled}" text="${message(code: 'userInfo.enabled.label')}"/>
        </div>

        <div>
            <form:checkbox name="accountExpired" checked="${user?.accountExpired}"
                           text="${message(code: 'userInfo.accountExpired.label')}"/>
        </div>

        <div>
            <form:checkbox name="accountLocked" checked="${user?.accountLocked}"
                           text="${message(code: 'userInfo.accountLocked.label')}"/>
        </div>

        <div>
            <form:checkbox name="passwordExpired" checked="${user?.passwordExpired}"
                           text="${message(code: 'userInfo.passwordExpired.label')}"/>
        </div>
    </div>
</form:field>

<form:field fieldName="userInfo.roles">
    <div style="width: 500px;">
        <g:each in="${availableRoles}" var="role" status="i">
            <div>
                <form:checkbox name="roles_${role}" checked="${roles.contains(role)}"
                               text="${message(code: "userInfo.roles.${role}")}"/>
            </div>
        </g:each>
    </div>
</form:field>

<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $("#province").kendoComboBox({
            dataTextField: "parentName",
            dataValueField: "parentId",
            dataSource:<format:html value="${Province.findAllByDeleted(false).collect{[parentId:it.id, parentName:it.name]} as JSON}"/>,
            change: function (e) {
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